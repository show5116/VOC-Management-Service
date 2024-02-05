package com.vms.server.admin.service.impl;

import com.vms.server.admin.service.FileViewerService;
import com.vms.server.domain.vo.ViewerVo;
import com.vms.server.util.FileValidation;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileViewerServiceImpl implements FileViewerService {

    public byte[] convertDocxToPdf(String sourceDocx) {
        try (FileInputStream fis = new FileInputStream(sourceDocx);
             XWPFDocument document = new XWPFDocument(fis);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            PdfOptions options = PdfOptions.create();
            PdfConverter.getInstance().convert(document, out, options);

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ViewerVo readFile(String filePath) throws Exception{
        File file = new File(filePath);
        //파일 검사
        FileValidation.fileValidate(file);

        //파일 확장자 확인
        Path fileLocation = Paths.get(filePath);
        String fileName = file.getName();
        String extension = FilenameUtils.getExtension(fileName).toUpperCase();
        String baseName = FilenameUtils.getBaseName(fileName);
        byte[] fileData = null;
        //확장자 별 파일 처리
        if(extension.equals("XLS") || extension.equals("XLSX")){
            fileData = Files.readAllBytes(fileLocation);
        }else if(extension.equals("DOC") || extension.equals("DOCX")){
            fileData = convertDocxToPdf(filePath);
        }else if(extension.equals("PPT") || extension.equals("PPTX")){
            fileData = convertPptToPdf(filePath);
        }else if(extension.equals("PDF")){
            fileData = Files.readAllBytes(fileLocation);
        }else{

        }

        return new ViewerVo(fileData, extension);
    }

    public byte[] convertPptToPdf(String pptPath) throws Exception {
        try (XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(pptPath));
             PDDocument doc = new PDDocument()) {

            Dimension pgsize = ppt.getPageSize();
            double scale = 2; // 스케일을 2배로 설정

            for (XSLFSlide slide : ppt.getSlides()) {
                BufferedImage img = new BufferedImage((int) (pgsize.width * scale), (int) (pgsize.height * scale), BufferedImage.TYPE_INT_ARGB);
                Graphics2D graphics = img.createGraphics();

                // 렌더링 품질 설정
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

                // 스케일 적용
                graphics.scale(scale, scale);

                // 슬라이드 렌더링
                slide.draw(graphics);

                PDPage page = new PDPage(new org.apache.pdfbox.pdmodel.common.PDRectangle(pgsize.width, pgsize.height));
                doc.addPage(page);

                PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, toByteArray(img, "png"), slide.getTitle());
                try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                    contents.drawImage(pdImage, 0, 0, pgsize.width, pgsize.height);
                }
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            doc.save(out);
            return out.toByteArray();
        }
    }

    private byte[] toByteArray(BufferedImage img, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, format, baos);
        return baos.toByteArray();
    }


}
