package com.aa.whattoplay.accounts.ui;


import com.aa.whattoplay.accounts.application.IAccountControlService;
import com.aa.whattoplay.accounts.application.commands.RegisterAccountCommand;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
public class AccountManagmentController {
    private final IAccountControlService accountControlService;

    @RequestMapping(path = "/registerUser", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody final RegisterAccountCommand user) throws ConstraintViolationException {
        accountControlService.registerAccount(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>("Successfully added user to database", responseHeaders, HttpStatus.CREATED);
    }

}
