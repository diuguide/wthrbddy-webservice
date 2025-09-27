package com.home.weatherbuddy.controller;

import com.home.weatherbuddy.model.StationInstance;
import com.home.weatherbuddy.service.OutboundService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractView;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OutboundController.class, properties = {
        "spring.thymeleaf.enabled=false"
})
class OutboundControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OutboundService outboundService;

    @Test
    void returnCurrentTime_addsModelAndReturnsWeather_whenStationFound() throws Exception {
        int stationId = 42;
        StationInstance instance = new StationInstance(12.3, 45.6, stationId);
        // Force a stable timestamp for assertion
        LocalDateTime ts = LocalDateTime.of(2023, 5, 6, 7, 8, 9);
        instance.setTimestamp(ts);

        when(outboundService.getStationInstanceById(eq(stationId))).thenReturn(instance);

        mockMvc.perform(get("/station/" + stationId))
                .andExpect(status().isOk())
                .andExpect(view().name("weather"))
                .andExpect(model().attributeExists("currentWeather"))
                .andExpect(model().attribute("formattedTimestamp", equalTo("2023-05-06 07:08:09")));
    }

    @Test
    void returnCurrentTime_returnsWeatherWithoutModel_whenStationMissing() throws Exception {
        int missingId = 99;
        when(outboundService.getStationInstanceById(eq(missingId))).thenReturn(null);

        mockMvc.perform(get("/station/" + missingId))
                .andExpect(status().isOk())
                .andExpect(view().name("weather"))
                .andExpect(model().attributeDoesNotExist("currentWeather", "formattedTimestamp"));
    }

    // Provide a minimal stub view to bypass real template rendering in tests
    @TestConfiguration
    static class StubViewConfig {
        @org.springframework.context.annotation.Bean
        ViewResolver viewResolver() {
            return (viewName, locale) -> new AbstractView() {
                {
                    setContentType("text/html");
                }

                @Override
                protected void renderMergedOutputModel(Map<String, Object> model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
                    // no-op: avoid invoking Thymeleaf engine
                }
            };
        }
    }
}
