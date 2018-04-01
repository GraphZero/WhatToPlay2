package com.aa.whattoplay.games.application.commands;

import com.aa.ddd.common.annotations.Command;
import com.aa.whattoplay.games.domain.suggestions.UserRating;
import lombok.Value;

@Command
@Value
public class AddUserRating {
    private long userId;
    private long gameId;
    private UserRating userRating;
}
