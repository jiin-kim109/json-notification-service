package com.jiin.httpqueue.api;

import com.jiin.httpqueue.model.DocumentId;
import com.jiin.httpqueue.service.QueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;

@Log4j2
@RestController
@RequiredArgsConstructor
public class QueueController {

    private QueueService queueService;

    @GetMapping(value = "/api/queues")
    public ResponseEntity listQueue(ServletWebRequest request) {
        log.info("API Invoked: {}, timestamp: {}", "ListQueue", LocalDateTime.now());
        ResponseEntity<?> res = ResponseEntity.ok(queueService.listAll());
        log.info("Successfully processed request");
        return res;
    }

    @GetMapping(value = "/api/queues/{id}")
    public ResponseEntity getQueue(DocumentId documentId, ServletWebRequest request) {
        log.info("API Invoked: {}, queue id: {}, timestamp: {}", "GetQueue", documentId.getId(), LocalDateTime.now());
        ResponseEntity<?> res = queueService.findOne(documentId).map(ResponseEntity::ok).orElse(notFoundError(request));
        log.info("Successfully processed request");
        return res;
    }

    @DeleteMapping(value = "/api/queues/{id}")
    public ResponseEntity deleteQueue(DocumentId documentId, ServletWebRequest request) {
        log.info("API Invoked: {}, queue id: {}, timestamp: {}", "DeleteQueue", documentId.getId(), LocalDateTime.now());
        ResponseEntity<?> res = queueService.deleteOne(documentId).map(ResponseEntity::ok).orElse(notFoundError(request));
        log.info("Successfully processed request");
        return res;
    }

    private ResponseEntity notFoundError(ServletWebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to find the queue");
    }
}
