package com.aa.whattoplay.mails.application.internal.listeners;

import com.aa.ddd.common.annotations.EventListener;
import com.aa.ddd.common.events.UserIsRegistered;
import com.aa.whattoplay.mails.application.commands.SendEmailCommand;
import com.aa.whattoplay.mails.domain.EmailService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;

@EventListener
@RequiredArgsConstructor
public class UserIsRegisteredListener implements ApplicationListener<UserIsRegistered> {
    private final Logger logger = LogManager.getLogger(UserIsRegisteredListener.class);
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(UserIsRegistered userIsRegistered) {
        logger.info("Account with id: " + userIsRegistered.getUserId() + " is registered, sending mail.");
        emailService.sendEmail(
                new SendEmailCommand(
                        userIsRegistered.getEmail(),
                        "Register email.",
                        "Hello to the WhatToPlay.2 app!",
                        userIsRegistered.getUsername()));
    }
}
