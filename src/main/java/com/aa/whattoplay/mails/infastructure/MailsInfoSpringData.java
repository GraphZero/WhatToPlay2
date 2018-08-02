package com.aa.whattoplay.mails.infastructure;

import com.aa.whattoplay.mails.domain.EmailSendingInfo;
import com.aa.whattoplay.mails.domain.MailsInfoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailsInfoSpringData extends MailsInfoRepository, JpaRepository<EmailSendingInfo, Long> {
}
