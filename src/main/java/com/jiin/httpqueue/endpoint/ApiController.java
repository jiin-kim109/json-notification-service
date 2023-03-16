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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;
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
    public ResponseEntity processActionRequest(@RequestParam Map<String, String> body, ServletWebRequest request) {
        log.info("Received request: {}, timestamp: {}", body.toString(), LocalDateTime.now());
        Optional<Actions> action = defineAction(body);
        if (action.isEmpty())
            return notValidActionResponse(request);

        RequestParameter requestParameter = RequestParameter.builder()
                .actionName(action.get())
                .attributeNames(collectAttributeNames(body))
                .attributeValues(collectAttributeValues(body))
                .parameters(filterNonAttributeParameters(body))
                .build();
        ResponseEntity<?> res = ResponseEntity.ok(processAction(requestParameter,request));
        log.info("Successfully processed request");
        return res;
    }

    private Optional<Actions> defineAction(Map<String, String> body) {
        for (Actions action : Actions.values()) {
            if (action.toString().equals(body.get("Action")))
                return Optional.of(action);
        }
        return Optional.empty();
    }

    private Map<Integer, String> collectAttributeNames(Map<String, String> body) {
        Map<Integer, String> attributeNames = body.entrySet().stream()
                .filter(e -> e.getKey().matches("Attribute\\.[1-9]\\.Name"))
                .collect(Collectors.toMap(
                        e -> Integer.parseInt(e.getKey().replaceAll("[^1-9]", "")),
                        e -> e.getValue()
                ));
        return attributeNames;
    }

    private Map<Integer, String> collectAttributeValues(Map<String, String> body) {
        Map<Integer, String> attributeValues = body.entrySet().stream()
                .filter(e -> e.getKey().matches("Attribute\\.[1-9]\\.Value"))
                .collect(Collectors.toMap(
                        e -> Integer.parseInt(e.getKey().replaceAll("[^1-9]", "")),
                        e -> e.getValue()
                ));
        return attributeValues;
    }

    private Map<String, String> filterNonAttributeParameters(Map<String, String> body) {
        Map<String, String> pureParameters = body.entrySet().stream()
                .filter(e -> !e.getKey().matches("Attribute\\.[1-9]\\.Name")
                        || !e.getKey().matches("Attribute\\.[1-9]\\.Value"))
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue()
                ));
        return pureParameters;
    }

    private Object processAction(@Valid RequestParameter body, ServletWebRequest request) {
        Object actionResult = switch (body.getActionName()) {
            case CreateQueue:
                yield queueService.createOne(body);
            case ReceiveMessage:
                yield null;
        };
        return actionResult;
    }

    private ResponseEntity notValidActionResponse(ServletWebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseBuilder.buildNotValidActionResponse(request));
    }
}
