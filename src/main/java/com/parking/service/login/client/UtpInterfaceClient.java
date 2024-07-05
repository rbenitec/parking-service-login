package com.parking.service.login.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.service.login.client.dto.RequestUtpClient;
import com.parking.service.login.client.dto.ResponseUtpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
@Component
@Slf4j
@RequiredArgsConstructor
public class UtpInterfaceClient {

    private final RestTemplate restTemplate;

    @Value("${config.api.utp.url}")
    private String urlUtp;

    public Optional<ResponseUtpClient> getUserUtp (RequestUtpClient requestUtpClient) {
        try {
            ResponseUtpClient responseEntity = restTemplate.exchange(
                    urlUtp,
                    HttpMethod.POST,
                    new HttpEntity<>(requestUtpClient),
                    ResponseUtpClient.class).getBody();
            assert responseEntity != null;
            return Optional.of(responseEntity);
        } catch (Exception e) {
            log.error("Error while getting la consulta al api utp-interface: {}", e.getMessage());
        }
        return Optional.empty();
    }
}
