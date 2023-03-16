package com.jiin.httpqueue.endpoint;

import com.jiin.httpqueue.action.Actions;
import com.jiin.httpqueue.action.ErrorResponse;
import com.jiin.httpqueue.action.RequestParameter;
import com.jiin.httpqueue.endpoint.utils.TestActionUtil;
import com.jiin.httpqueue.service.QueueService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@WebMvcTest(controllers = ApiController.class)
@MockBean({ QueueService.class })
public class ApiControllerTest {

    private static final String API_URL = "/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QueueService service;

    @Captor
    private ArgumentCaptor<RequestParameter> requestBodyCaptor;

    @Nested
    class ControllerTest {

        @Test
        void shouldFailIfActionIsNotValid() throws Exception {
            Map<String, Object> emptyBody = new HashMap<>();

            makeMockRequest(API_URL, TestActionUtil.convertToQueryString(emptyBody))
                    .andExpect(status().isBadRequest())
                    .andExpect(xpath("/ErrorResponse/Error/Type").string(ErrorResponse.Error.ErrorType.Sender.toString()))
                    .andExpect(xpath("/ErrorResponse/Error/Code").string(ErrorResponse.Error.ErrorCode.InvalidActionName.toString()))
                    .andExpect(xpath("/ErrorResponse/Error/Message").string(
                            "Value for parameter Action is not valid \r" +
                                    "Must be a valid action name"));
        }

        @Test
        void shouldFailIfRequiredParametersAreMissing() {

        }

        @Test
        void shouldCollectAttributeNamesFromQueryParameters() throws Exception {
            Map<String, Object> body = new HashMap<>();
            addAction(body, Actions.CreateQueue);
            addAttributeName(body, 1, "DelaySeconds");
            addAttributeName(body, 2, "MaximumMessageSize");
            addAttributeName(body, 3, "MessageRetentionPeriod");

            makeMockRequest(API_URL, TestActionUtil.convertToQueryString(body));
            verify(service, times(1)).createOne(requestBodyCaptor.capture());

            RequestParameter actual = requestBodyCaptor.getValue();
            assertThat(actual.getAttributeNames().get(1)).isEqualTo("DelaySeconds");
            assertThat(actual.getAttributeNames().get(2)).isEqualTo("MaximumMessageSize");
            assertThat(actual.getAttributeNames().get(3)).isEqualTo("MessageRetentionPeriod");
        }

        @Test
        void shouldIgnoreAttributeNameThatHasNegativeIndex() {

        }

        @Test
        void shouldFailIfAttributeNameHasDuplicateIndex() {

        }

        @Test
        void shouldCollectAttributeValuesFromQueryParameters() {

        }

        @Test
        void shouldIgnoreAttributeValueThatHasNegativeIndex() {

        }

        @Test
        void shouldFailIfAttributeValueHasDuplicateIndex() {

        }

        @Test
        void shouldCollectNonAttributeParameters() {

        }
    }

    @Nested
    class CreateQueueActionTest {

        @Test
        void shouldHandleValidCreateQueueRequest() throws Exception {
            when(service.createOne(any())).thenReturn(null);

        }
    }

    private ResultActions makeMockRequest(String url, String queryString) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(queryString)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED));
    }

    private void addAction(Map body, Actions action) {
        body.put("Action", action);
    }

    private void addAttributeName(Map body, int index, String name) {
        body.put(String.format("Attribute.%d.Name", index), name);
    }

    private void addAttributeValue(Map body, int index, Object value) {
        body.put(String.format("Attribute.%d.Value", index), value);
    }
}
