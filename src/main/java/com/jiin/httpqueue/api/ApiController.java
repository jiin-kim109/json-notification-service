package com.jiin.httpqueue.api;

import com.jiin.httpqueue.action.Action;
import com.jiin.httpqueue.service.Processor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ApiController {

    @Autowired
    private Processor actionProcessor;

    @PostMapping(value = "/")
    public ResponseEntity processRequestPOST(@Valid @RequestBody QueryParam params, ServletWebRequest request) {
        log.info("API Invoked: {}, timestamp: {}", params.getAction(), LocalDateTime.now());
        Action action = Action.builder()
                .actionName(params.getAction())
                .parameters(params.getParameters())
                .build();
        ResponseEntity<?> res = ResponseEntity.ok(actionProcessor.processAction(action));
        log.info("Successfully processed request");
        return res;
    }

    @GetMapping(value = "/")
    public ResponseEntity processRequestGET(@RequestParam(value="Action", required = true) String actionName, @RequestParam HashMap<String, String> parameters, ServletWebRequest request) {
        log.info("API Invoked: {}, timestamp: {}", actionName, LocalDateTime.now());
        Action action = Action.builder()
                .actionName(actionName)
                .parameters(parameters)
                .build();
        ResponseEntity<?> res = ResponseEntity.ok(actionProcessor.processAction(action));
        log.info("Successfully processed request");
        return res;
    }
}
