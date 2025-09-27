package com.home.weatherbuddy.controller;

import com.home.weatherbuddy.service.InboundService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = InboundController.class)
class InboundControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InboundService inboundService;

    @Test
    void getTempAndHumidity_returnsServiceResponse_onValidPath() throws Exception {
        Double temp = 23.5;
        Double humidity = 40.0;
        int stationId = 7;
        String expected = "Temperature: " + temp + " Humidity: " + humidity + " Station ID: " + stationId;

        when(inboundService.processInboundData(eq(temp), eq(humidity), eq(stationId))).thenReturn(expected);

        mockMvc.perform(get("/inbound/" + temp + "-" + humidity + "-" + stationId))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));

        verify(inboundService).processInboundData(eq(temp), eq(humidity), eq(stationId));
    }

    @Test
    void getTempAndHumidity_returnsBadRequest_onInvalidNumber() throws Exception {
        mockMvc.perform(get("/inbound/abc-40.0-7"))
                .andExpect(status().isBadRequest());

        Mockito.verifyNoInteractions(inboundService);
    }
}
