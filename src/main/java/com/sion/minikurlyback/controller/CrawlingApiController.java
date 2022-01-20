package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.parser.OnePageParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CrawlingApiController {
    private final OnePageParser onePageParser;

    @GetMapping("/parse")
    public void parse(@RequestParam String url) throws IOException {
        onePageParser.parse(url);
    }
}
