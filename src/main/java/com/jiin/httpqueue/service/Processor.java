package com.jiin.httpqueue.service;

import com.jiin.httpqueue.action.Action;
import com.jiin.httpqueue.action.ActionResponse;
import com.jiin.httpqueue.action.CreateQueue;
import com.jiin.httpqueue.validator.ValidAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Processor {
    private QueueService queueService;

    @Autowired
    public Processor(QueueService queueService) {
        this.queueService = queueService;
    }

    public ActionResponse processAction(Action action) {
        ActionResponse responseObject = switch (action.getActionName()) {
            case "CreateQueue" -> processCreateAction(action);
            default -> null;
        };
        return responseObject;
    }
    private CreateQueue processCreateAction(Action action) {
        return null;
    }
}
