package com.home.weatherbuddy.controller;

import com.home.weatherbuddy.service.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inbound")
public class InboundController {
    private final InboundService inboundService;

    @Autowired
    public InboundController(InboundService inboundService) {
        this.inboundService = inboundService;
    }

    @GetMapping("/{temp}-{humidity}-{station_id}")
    @ResponseBody
    public String getTempAndHumidity(@PathVariable("temp") Double temp, @PathVariable("humidity") Double humidity , @PathVariable("station_id") int system_id) {

        return inboundService.processInboundData(temp, humidity, system_id);
    }

}
