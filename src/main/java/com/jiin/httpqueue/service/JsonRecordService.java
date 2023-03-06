package com.jiin.httpqueue.service;

import com.jiin.httpqueue.model.Record;
import com.jiin.httpqueue.model.RecordId;
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
