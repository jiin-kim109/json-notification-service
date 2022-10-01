package com.jiin.jsondirectory.api;

import com.jiin.jsondirectory.api.utils.MockApiRequestBuilder;
import com.jiin.jsondirectory.service.RecordService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecordController.class)
@MockBean({RecordService.class })
public class RecordControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RecordService service;

    @Nested
    class GetRecordTest {

        @Test
        void shouldHandleRecordNotFound() throws Exception {
            when(service.get(any())).thenReturn(Optional.empty());

            mockMvc.perform(MockApiRequestBuilder.buildGetRequet("records/aaaa-44ww-bbas-dddf-aaas-dd11")).andExpect(status().isNotFound());
        }
    }

    @Nested
    class DeleteRecordTest {

        @Test
        void shouldHandleRecordNotFound() throws Exception  {
            when(service.get(any())).thenReturn(Optional.empty());

            mockMvc.perform(MockApiRequestBuilder.buildGetRequet("records/aaaa-44ww-bbas-dddf-aaas-dd11")).andExpect(status().isNotFound());
        }
    }
}
