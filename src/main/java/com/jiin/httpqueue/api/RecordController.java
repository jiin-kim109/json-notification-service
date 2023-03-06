package com.jiin.httpqueue.api;

import com.jiin.httpqueue.model.RecordId;
import com.jiin.httpqueue.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@Log4j2
@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping(value = "/api/v1/records/{id}")
    public ResponseEntity getRecord(RecordId recordId, ServletWebRequest request) {
        log.info("Received with record id: {}", recordId.getId());
        ResponseEntity<?> res = recordService.get(recordId).map(ResponseEntity::ok).orElse(errorRecordNotFound(request));
        log.info("Successfully processed request");
        return res;
    }

    @DeleteMapping(value = "/api/v1/records/{id}")
    public ResponseEntity deleteRecord(RecordId recordId, ServletWebRequest request) {
        log.info("Received request with record id: {}", recordId.getId());
        ResponseEntity<?> res = recordService.delete(recordId).map(ResponseEntity::ok).orElse(errorRecordNotFound(request));
        log.info("Successfully processed request");
        return res;
    }

    private ResponseEntity errorRecordNotFound(ServletWebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to find the record");
    }
}
