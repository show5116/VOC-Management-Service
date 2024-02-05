package com.vms.server.MailIF;

import com.vms.server.domain.entity.etc.Isendmail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MailService {

    void sendMail(Isendmail isendmail) throws AddressException, MessagingException;

    void sendMailSendFlag0() throws Exception;
}
