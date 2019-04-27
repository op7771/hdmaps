package com.bluedigm.hdmap.map.apis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/maps")
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/hello")
    public ResponseEntity<Mono<String>> hello(@Value("${say.hello}") String  hello) {
        LOGGER.debug("Call Maps");
        return ResponseEntity.ok(Mono.just(hello));
    }
}
