package com.aa.whattoplay.games.ui;

import com.aa.whattoplay.games.application.ExternalDataCacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CacherController {
    private final ExternalDataCacherService externalDataCacherService;

    @RequestMapping(path = "/cacheCollections", method = RequestMethod.POST)
    public ResponseEntity<String> finishActivity(){
        externalDataCacherService.cacheCollections();
        return new ResponseEntity<>("Finished caching collections", HttpStatus.OK);
    }


}
