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
    public ResponseEntity<String> cacheCollections(){
        externalDataCacherService.cacheCollections();
        return new ResponseEntity<>("Finished caching collections", HttpStatus.OK);
    }

    @RequestMapping(path = "/cacheFranchises", method = RequestMethod.POST)
    public ResponseEntity<String> cacheFranchises(){
        externalDataCacherService.cacheFranchises();
        return new ResponseEntity<>("Finished caching franchises", HttpStatus.OK);
    }

    @RequestMapping(path = "/cacheDevelopers", method = RequestMethod.POST)
    public ResponseEntity<String> cacheDevelopers(){
        externalDataCacherService.cacheDevelopers();
        return new ResponseEntity<>("Finished caching developers", HttpStatus.OK);
    }

}
