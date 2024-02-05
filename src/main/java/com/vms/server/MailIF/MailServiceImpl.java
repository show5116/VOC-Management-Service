package com.vms.server.MailIF;

import com.vms.server.admin.repository.jpa.AdminIsendMailRepository;
import com.vms.server.admin.repository.jpa.AdminIsendmailAttachFileRepository;
import com.vms.server.admin.repository.jpa.AdminSysSystemCodeDataRepository;
import com.vms.server.domain.entity.etc.Isendmail;
import com.vms.server.domain.entity.etc.IsendmailAttachFile;
import com.vms.server.domain.entity.sys.SysSystemCodeData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.net.InetAddress;
import java.util.List;
import java.util.Properties;

@Service
@Transactional
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
    private final AdminSysSystemCodeDataRepository sysSystemCodeDataRepository;
    private final AdminIsendMailRepository adminIsendMailRepository;
    private final AdminIsendmailAttachFileRepository adminIsendmailAttachFileRepository;
    private String SMTP_SERVER ="";
    private String SMTP_PORT ="";

    @Value("${mail.username}")
    private String USER; // 발신자 이메일 주소
    @Value("${mail.password}")
    private String PW; // 발신자 이메일 계정의 비밀번호

    @Value("${default.plant}")
    private String PLANT;

    @Override
    public void sendMail(Isendmail isendmail) throws AddressException, MessagingException {
        String plant =PLANT;

        List<SysSystemCodeData> smtpServerCodeList = sysSystemCodeDataRepository.findByPlantAndTableNameAndCodeName(plant,"SMTP_CODE", "SMTP_SERVER");
        if(smtpServerCodeList != null && !smtpServerCodeList.isEmpty()){
            SMTP_SERVER = smtpServerCodeList.get(0).getDescription();
        }
        List<SysSystemCodeData> smtpPortCodeList =sysSystemCodeDataRepository.findByPlantAndTableNameAndCodeName(plant,"SMTP_CODE", "SMTP_PORT");
        if(smtpPortCodeList != null && !smtpPortCodeList.isEmpty()){
            SMTP_SERVER = smtpPortCodeList.get(0).getDescription();
        }
        String sender = isendmail.getFromEmail();
        String subject = isendmail.getSubject();// 메일 제목
        String message =isendmail.getMessage(); // 메일 내용

        String receiver = isendmail.getToEmail(); // 받는사람 이메일
        String reference = isendmail.getToCc(); // 참조

        Properties props = System.getProperties();
        Session session = null;

        // localhost에서 실행될 시, 설정 변경.
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String hostName = localHost.getHostName();
            String hostAddress = localHost.getHostAddress();

            if (hostName.startsWith("DESKTOP-") ||  "localhost".equalsIgnoreCase(hostName) || "127.0.0.1".equals(hostAddress)) {
                SMTP_SERVER = "smtp.naver.com";
                SMTP_PORT = "587";
                props.put("mail.smtp.host", SMTP_SERVER);
                props.put("mail.smtp.port", SMTP_PORT);
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                sender= USER;
                session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER, PW);
                    }
                });
            }else{
                session = Session.getDefaultInstance(props);
                props.put("mail.smtp.host", SMTP_SERVER);
                props.put("mail.smtp.port", SMTP_PORT);
                props.put("mail.smtp.auth", "false");

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        session.setDebug(true);

        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(sender));

        String[] multipleRecipients = receiver.split(",");
        InternetAddress[] addresses = new InternetAddress[multipleRecipients.length];


        //받는 사람 추가
        for (int i = 0; i < multipleRecipients.length; i++) {
            addresses[i] = new InternetAddress(multipleRecipients[i].trim());
        }
        mimeMessage.setRecipients(Message.RecipientType.TO, addresses);

        //참조인 추가
        if (reference != null && !reference.isEmpty() && !reference.trim().equals("")) {     // 참조인 추가
            String[] cc = reference.split(",");
            InternetAddress[] ccAddr = new InternetAddress[cc.length];
            for (int i = 0; i < ccAddr.length; i++) {
                ccAddr[i] = new InternetAddress(cc[i].trim());
            }
            mimeMessage.setRecipients(Message.RecipientType.CC, ccAddr);
        }


        mimeMessage.setSubject(subject);
        // 이메일 본문 설정
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html; charset=UTF-8");

        //첨부 파일 추가
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        try {
            List<IsendmailAttachFile> attachFiles = adminIsendmailAttachFileRepository.findBySeqAndSystemName(isendmail.getSeq(), isendmail.getSystemName());
            if (attachFiles != null && !attachFiles.isEmpty()) {
                for (IsendmailAttachFile isendmailAttachFile : attachFiles) {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.attachFile(isendmailAttachFile.getRealFileId());
                    multipart.addBodyPart(attachmentPart);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        mimeMessage.setContent(multipart);

        // 메일 전송
        isendmail.changeSendFlag(1);
        Transport.send(mimeMessage);
        isendmail.changeSendFlag(2);

    }

    @Override
    public void sendMailSendFlag0() throws Exception {
    List<Isendmail> isendmailList = adminIsendMailRepository.findBySendFlag(0);
    if(isendmailList != null && !isendmailList.isEmpty()){
        for(Isendmail isendmail: isendmailList){
            sendMail(isendmail);
        }
    }
    }
}
