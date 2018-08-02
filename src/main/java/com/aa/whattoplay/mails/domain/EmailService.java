package com.aa.whattoplay.mails.domain;

import com.aa.whattoplay.mails.application.commands.SendEmailCommand;

public interface EmailService {
    void sendEmail(SendEmailCommand sendEmailCommand);
}
