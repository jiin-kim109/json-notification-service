package com.jiin.jsondirectory.service;

import com.jiin.jsondirectory.model.Record;
import com.jiin.jsondirectory.model.RecordId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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