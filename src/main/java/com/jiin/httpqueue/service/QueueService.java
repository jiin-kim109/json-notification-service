package com.jiin.httpqueue.service;

import com.jiin.httpqueue.model.QueueDto;
import com.jiin.httpqueue.model.DocumentId;
import com.jiin.httpqueue.model.QueueResponse;
import com.jiin.httpqueue.mongo.document.QueueEntity;
import com.jiin.httpqueue.storage.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class QueueService {

    public List<QueueDto> listAll() {
        return null;
    }

    @Autowired
    private QueueRepository repository;

    public QueueEntity createOne(QueueDto queueDto) {
        Assert.notNull(queueDto);
        QueueEntity entity = repository.save(QueueEntity.builder().build());
        return entity;
    }

    public Optional<QueueDto> findOne(DocumentId documentId) {
        return Optional.empty();
    }

    public Optional<QueueResponse> deleteOne(DocumentId documentId) {
        return Optional.empty();
    }
}
