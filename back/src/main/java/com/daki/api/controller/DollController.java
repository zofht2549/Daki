package com.daki.api.controller;

import com.daki.api.service.DollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doll")
public class DollController {

    @Autowired
    DollService dollService;


}
