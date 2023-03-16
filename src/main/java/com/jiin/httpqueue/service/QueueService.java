package com.jiin.httpqueue.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.jiin.httpqueue.action.CreateQueue;
import com.jiin.httpqueue.action.RequestParameter;
import com.jiin.httpqueue.mapper.AttributeMapper;
import com.jiin.httpqueue.model.Queue;
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

    public List<Queue> listAll() {
        return null;
    }

    @Autowired
    private QueueRepository repository;

    @Autowired
    private ObjectMapper mapper;

    public QueueService(QueueRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;

        this.mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
    }

    public CreateQueue createOne(RequestParameter req) {
        Assert.notNull(req);

        QueueEntity entity = mapper.convertValue(req.getParameters(), QueueEntity.class);
        AttributeMapper.mapAttributeToObjectFields(entity, req.getAttributes());
        QueueEntity savedEntity = repository.save(entity);

        var queueResult = CreateQueue.CreateQueueResult.builder()
                .queueUrl("")
                .build();
        return CreateQueue.builder().createQueueResult(queueResult).build();
    }

    public Optional<Queue> findOne(DocumentId documentId) {
        return Optional.empty();
    }

    public Optional<QueueResponse> deleteOne(DocumentId documentId) {
        return Optional.empty();
    }
}
