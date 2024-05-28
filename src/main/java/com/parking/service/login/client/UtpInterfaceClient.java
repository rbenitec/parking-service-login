package com.parking.service.login.client;

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

    @Value("${config.api.document.url}")
    private String urlUtp;

    public Optional<ResponseUtpClient> getUserUtp (RequestUtpClient requestUtpClient) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-user-id", "43839339");
        headers.set("x-consumer-id", "landingPasivos");
        headers.set("x-consumer-request-id", "51f2b61f-fe38-41e7");
        headers.set("x-transaction-id", "landingpasivos.aperturacuentadigital-1708374235-d861bdac-6c24-4f59-9c4c-e1bb65277ee1");
        headers.set("x-request-id", "3d9ad627-1ac4-41e0");

        HttpEntity<RequestUtpClient> requestEntity = new HttpEntity<>(requestUtpClient, headers);
        try {
            ResponseEntity<ResponseUtpClient> responseEntity =
                    restTemplate.exchange(urlUtp, HttpMethod.POST, requestEntity, ResponseUtpClient.class);
            return Optional.ofNullable(responseEntity.getBody());
        } catch (Exception e) {
            log.error("Error while getting la consulta del documento: {}", e.getMessage());
        }
        return Optional.empty();
    }
}
