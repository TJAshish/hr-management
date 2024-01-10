package com.hrmanagement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "app.image")
@Data
public class SchoolConfig {
	String imageLocation;

}

