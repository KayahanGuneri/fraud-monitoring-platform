package com.portfolio.fraud.mcp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/mcp/system")
public class McpSystemController {

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of(
                "service", "mcp-server-service",
                "status", "UP"
        );
    }
}