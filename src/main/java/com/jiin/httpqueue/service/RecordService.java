package com.jiin.httpqueue.service;

import com.jiin.httpqueue.model.Record;
import com.jiin.httpqueue.model.RecordId;

import java.util.Optional;

public interface RecordService {

    Optional<Record> get(RecordId id);

    Optional<Record> delete(RecordId id);
}
