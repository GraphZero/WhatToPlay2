package com.aa.whattoplay.mails.domain;

public interface MailsInfoRepository {
    EmailSendingInfo save(EmailSendingInfo emailSendingInfo);
}
