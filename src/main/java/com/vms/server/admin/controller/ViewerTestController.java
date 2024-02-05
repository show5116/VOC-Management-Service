package com.vms.server.admin.controller;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ViewerTestController {

    @GetMapping("/pdf/{filename}")
    public ResponseEntity<InputStreamResource> viewPdf(@PathVariable String filename) throws FileNotFoundException {
        String filePath = "C:/Users/Admin/Documents/" + filename;
        try (PDDocument  document = PDDocument.load(new File(filePath))) {
            // PDF 보안 설정 적용
            AccessPermission accessPermission = new AccessPermission();
            accessPermission.setCanPrint(false); // 인쇄 제한
            accessPermission.setCanModify(false); // 수정 제한

            // 메모리에 PDF 데이터를 저장
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(in));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/img/{filename}")
    public String viewImg(@PathVariable String filename, Model model) {
        System.out.println(filename);
        model.addAttribute("imgname", filename);
        return "ImgViewer";
    }
    @GetMapping("/pic/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        // 파일 시스템의 절대 경로에서 파일을 찾습니다.
        File file = new File("C:/Users/Admin/Downloads/" + filename);
        if (!file.exists()) {
            // 적절한 예외 처리 또는 오류 응답을 반환합니다.
            return ResponseEntity.notFound().build();
        }

        // 파일을 Resource로 변환하여 반환합니다.
        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // 파일 형식에 맞게 변경해야 할 수 있습니다.
                .body(resource);
    }


    @GetMapping("/doc/{filename}")
    public ResponseEntity<Resource> getDOC(@PathVariable String filename) {
        byte[] pdfData = convertDocxToPdf("C:/Users/Admin/Downloads/" + filename + ".docx");

        if (pdfData == null) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayResource resource = new ByteArrayResource(pdfData);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
//    @GetMapping("/hwp/{filename}")
//    public ResponseEntity<Resource> getHwp(@PathVariable String filename) {
//        byte[] pdfData = convertHwpToPdfBytes("C:/Users/Admin/Downloads/" + filename);
//
//        if (pdfData == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        ByteArrayResource resource = new ByteArrayResource(pdfData);
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(resource);
//    }

    @GetMapping("/ppt/{filename}")
    public ResponseEntity<Resource> getPpt(@PathVariable String filename) throws Exception {
        byte[] pdfData = convertPptToPdf("C:/Users/Admin/Downloads/" + filename);

        if (pdfData == null) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayResource resource = new ByteArrayResource(pdfData);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

//    @GetMapping("/pptx/{filename}")
//    public ResponseEntity<Resource> getPPTX(@PathVariable String filename) throws Exception{
//        try {
//            Path fileLocation = Paths.get("C:/Users/Admin/Downloads/" + filename + ".pptx");
//            byte[] data = Files.readAllBytes(fileLocation);
//            ByteArrayResource resource = new ByteArrayResource(data);
//
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.presentationml.presentation"))
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + ".pptx\"")
//                    .body(resource);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.notFound().build();
//        }
//    }


    @GetMapping("/excel/{filename}")
    public ResponseEntity<Resource> getExcel(@PathVariable String filename) {
        try {
            Path fileLocation = Paths.get("C:/Users/Admin/Downloads/" + filename + ".xlsx");
            byte[] data = Files.readAllBytes(fileLocation);
            ByteArrayResource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .header("Content-Disposition", "attachment; filename=" + filename + ".xlsx")
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
//    private byte[] convertHwpToPdfBytes(String path) {
//        HWPFile hwpFile;
//        String hwpText;
//        try {
//            hwpFile = HWPReader.fromFile(path);
//            hwpText = TextExtractor.extract(hwpFile, TextExtractMethod.InsertControlTextBetweenParagraphText);
//
//            try (PDDocument doc = new PDDocument()) {
//                PDPage page = new PDPage();
//                doc.addPage(page);
//
//                try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
//                    contents.beginText();
//                    contents.setFont(PDType1Font.TIMES_ROMAN, 12);
//                    contents.newLineAtOffset(100, 700);
//                    String[] lines = hwpText.split("\n");
//                    for (String line : lines) {
//                        contents.showText(line);
//                        contents.newLineAtOffset(0, -15); // 다음 줄로 이동
//                    }
//                    contents.endText();
//                }
//
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                doc.save(out);
//                return out.toByteArray();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

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
