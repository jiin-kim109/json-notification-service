package com.jiin.jsondirectory.api;

import com.jiin.jsondirectory.model.RecordId;
import com.jiin.jsondirectory.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping(value = "/api/v1/records/{id}")
    public ResponseEntity getRecord(RecordId id, ServletWebRequest request) {
        ResponseEntity<?> res = recordService.get(id).map(ResponseEntity::ok).orElse(errorRecordNotFound(request));
        return res;
    }

    @DeleteMapping(value = "/api/v1/records/{id}")
    public ResponseEntity deleteRecord(RecordId id, ServletWebRequest request) {
        ResponseEntity<?> res = recordService.delete(id).map(ResponseEntity::ok).orElse(errorRecordNotFound(request));
        return res;
    }

    private ResponseEntity errorRecordNotFound(ServletWebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to find the record");
    }
}
