package com.it_cgm.controller;

import com.it_cgm.service.CGMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CGMTestController {

    @Autowired
    private CGMService cgmService;

    @GetMapping(value = "/getStringStats", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getStringStats(@RequestParam String text) {
        return cgmService.analyseSequence(text);
    }

    @GetMapping(value = "/getRequestStats", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getRequestStats() {
        return cgmService.gatherStatistics();
    }
}