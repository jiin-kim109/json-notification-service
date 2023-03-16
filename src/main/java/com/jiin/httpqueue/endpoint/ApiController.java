package com.jiin.httpqueue.endpoint;

import com.jiin.httpqueue.action.Actions;
import com.jiin.httpqueue.action.RequestParameter;
import com.jiin.httpqueue.service.QueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ApiController {

    private QueueService queueService;

    @Autowired
    public ApiController(QueueService queueService) {
        this.queueService = queueService;
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET}, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity processActionRequest(@RequestParam MultiValueMap<String, String> body, ServletWebRequest request) {
        log.info("Received request: {}, timestamp: {}", body.toString(), LocalDateTime.now());
        var parameters = body.toSingleValueMap();
        var action = collectActionName(parameters);
        ResponseEntity<?> res;
        if (action.isEmpty())
            res = notValidActionResponse(request);
        else {
            RequestParameter requestParameter = RequestParameter.builder()
                    .actionName(action.get())
                    .attributeNames(collectAttributeNames(parameters))
                    .attributeValues(collectAttributeValues(parameters))
                    .parameters(filterNonAttributeParameters(parameters))
                    .build();
            res = ResponseEntity.ok(processAction(requestParameter,request));
        }
        log.info("Successfully processed request");
        return res;
    }

    private Optional<Actions> collectActionName(Map<String, String> parameters) {
        for (Actions action : Actions.values()) {
            if (action.toString().equals(parameters.get("Action")))
                return Optional.of(action);
        }
        return Optional.empty();
    }

    private Map<Integer, String> collectAttributeNames(Map<String, String> parameters) {
        Map<Integer, String> attributeNames = parameters.entrySet().stream()
                .filter(e -> e.getKey().matches("Attribute\\.[1-9]\\.Name"))
                .collect(Collectors.toMap(
                        e -> Integer.parseInt(e.getValue().replaceAll("[^1-9]", "")),
                        e -> e.getValue()
                ));
        return attributeNames;
    }

    private Map<Integer, String> collectAttributeValues(Map<String, String> parameters) {
        Map<Integer, String> attributeValues = parameters.entrySet().stream()
                .filter(e -> e.getKey().matches("Attribute\\.[1-9]\\.Value"))
                .collect(Collectors.toMap(
                        e -> Integer.parseInt(e.getValue().replaceAll("[^1-9]", "")),
                        e -> e.getValue()
                ));
        return attributeValues;
    }

    private Map<String, String> filterNonAttributeParameters(Map<String, String> parameters) {
        Map<String, String> pureParameters = parameters.entrySet().stream()
                .filter(e -> !e.getKey().matches("Attribute\\.[1-9]\\.Name")
                        || !e.getKey().matches("Attribute\\.[1-9]\\.Value"))
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue()
                ));
        return pureParameters;
    }

    private Object processAction(RequestParameter requestParameter, ServletWebRequest request) {
        Object actionResult = switch (requestParameter.getActionName()) {
            case CreateQueue:
                yield queueService.createOne(requestParameter);
            case ReceiveMessage:
                yield null;
        };
        return actionResult;
    }

    private ResponseEntity notValidActionResponse(ServletWebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseBuilder.buildNotValidActionResponse(request));
    }
}
