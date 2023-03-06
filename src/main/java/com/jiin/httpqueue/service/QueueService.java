package com.jiin.httpqueue.service;

import com.jiin.httpqueue.model.QueueDto;
import com.jiin.httpqueue.model.DocumentId;
import com.jiin.httpqueue.model.QueueResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class QueueService {

    public List<QueueDto> listAll() {
        return null;
    }

    public QueueResponse createOne(QueueDto queueDto) {
        Assert.notNull(queueDto);
        return null;
    }

    public Optional<QueueDto> findOne(DocumentId documentId) {
        return Optional.empty();
    }

    public Optional<QueueResponse> deleteOne(DocumentId documentId) {
        return Optional.empty();
    }
}
