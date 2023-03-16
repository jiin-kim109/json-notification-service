package com.jiin.httpqueue.endpoint;

import com.jiin.httpqueue.action.ErrorResponse;
import com.jiin.httpqueue.endpoint.utils.TestActionUtil;
import com.jiin.httpqueue.service.QueueService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@WebMvcTest(controllers = ApiController.class)
public class ApiControllerTest {

    private static final String API_URL = "/";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QueueService service;

    @Nested
    class ControllerTest {

        @Test
        void shouldFailIfActionIsNotValid() throws Exception {
            MultiValueMap<String, String> emptyBody = new LinkedMultiValueMap<>();

            makeMockRequest(API_URL, TestActionUtil.convertToQueryString(emptyBody))
                    .andExpect(status().isBadRequest())
                    .andExpect(xpath("/ErrorResponse/Error/Type").string(ErrorResponse.Error.ErrorType.Sender.toString()))
                    .andExpect(xpath("/ErrorResponse/Error/Code").string(ErrorResponse.Error.ErrorCode.InvalidActionName.toString()))
                    .andExpect(xpath("/ErrorResponse/Error/Message").string(
                            "Value for parameter Action is not valid \\r" +
                            "Must be a valid action name"));
        }

        @Test
        void shouldCollectAttributeNamesFromQueryParameters() {

        }

        @Test
        void shouldCollectAttributeValuesFromQueryParameters() {

        }

        @Test
        void shouldCollectNonAttributeParameters() {

        }

        @Test
        void shouldFailIfRequiredParametersAreMissing() {

        }

        @Test
        void shouldFailIfServiceUnableToProcessAction() {

        }
    }

    @Nested
    class CreateQueueActionTest {

        @Test
        void shouldHandleValidCreateQueueRequest() throws Exception {
            when(service.createOne(any())).thenReturn(null);

            makeMockRequest(API_URL,
                    TestActionUtil.convertToQueryString(TestActionUtil.CreateQueue.withMandatoryFieldOnly()))
                    .andExpect(status().isOk());

        }
    }

    private ResultActions makeMockRequest(String url, String queryString) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(queryString)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED));
    }
}
