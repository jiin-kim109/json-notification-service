package com.jiin.httpqueue.api;

import com.jiin.httpqueue.api.utils.MockApiRequestBuilder;
import com.jiin.httpqueue.service.QueueService;
import com.jiin.httpqueue.service.RecordService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = QueueController.class)
@MockBean({QueueService.class })
public class QueueControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private QueueService service;

    private ObjectId queueUUID;

    @BeforeEach
    public void beforeEach() {
        this.queueUUID = new ObjectId("507f1f77bcf86cd799439011");
    }

    @Nested
    class ListQueueTest {

    }

    @Nested
    class GetQueueTest {

        @Test
        void shouldHandleQueueNotFound() throws Exception {
            when(service.findOne(any())).thenReturn(Optional.empty());

            mockMvc.perform(MockApiRequestBuilder.buildGetRequet("queues/aaaa-44ww-bbas-dddf-aaas-dd11")).andExpect(status().isNotFound());
        }
    }

    @Nested
    class DeleteQueueTest {

        @Test
        void shouldHandleRecordNotFound() throws Exception  {
            when(service.deleteOne(any())).thenReturn(Optional.empty());

            mockMvc.perform(MockApiRequestBuilder.buildGetRequet("queues/aaaa-44ww-bbas-dddf-aaas-dd11")).andExpect(status().isNotFound());
        }
    }
}
