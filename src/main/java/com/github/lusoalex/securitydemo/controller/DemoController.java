package com.github.lusoalex.securitydemo.controller;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @GetMapping("/demo")
    public Publisher<String> demo() {
        return Mono.just("success");
    }

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public Publisher<String> index(@AuthenticationPrincipal Jwt jwt) {
        return Mono.just(
                String.format("Hello, your id is %s and name is %s", jwt.getSubject(), jwt.getClaimAsString("name"))
        );
    }
}
