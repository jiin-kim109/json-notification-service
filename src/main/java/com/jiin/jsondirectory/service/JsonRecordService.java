package com.jiin.jsondirectory.service;

import com.jiin.jsondirectory.model.Record;
import com.jiin.jsondirectory.model.RecordId;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class JsonRecordService implements RecordService{

    @Override
    public Optional<Record> get(RecordId id) {
        return Optional.empty();
    }

    @Override
    public Optional<Record> delete(RecordId id) {
        return Optional.empty();
    }
}
