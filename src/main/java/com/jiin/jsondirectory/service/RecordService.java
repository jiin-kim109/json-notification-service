package com.jiin.jsondirectory.service;

import com.jiin.jsondirectory.model.Record;
import com.jiin.jsondirectory.model.RecordId;

import java.util.Optional;

public interface RecordService {

    Optional<Record> get(RecordId id);

    Optional<Record> delete(RecordId id);
}
