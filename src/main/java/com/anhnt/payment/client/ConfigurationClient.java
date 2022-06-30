package com.anhnt.payment.client;

import com.anhnt.common.domain.configuration.response.MessageResponse;
import com.anhnt.common.domain.response.BodyEntity;
import com.anhnt.payment.client.config.ConfigurationClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="configuration",url="${feign.client.config.configuration.url}",configuration = ConfigurationClientConfiguration.class)
public interface ConfigurationClient {
    @GetMapping(value = "/messages",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BodyEntity<MessageResponse>> getMessages();
}