package com.aa.whattoplay.mails.application.commands;

import com.aa.ddd.common.annotations.Command;
import lombok.Value;

@Command
@Value
public class SaveEmailInfoCommand {
    private final String address;
    private final String subject;
}
