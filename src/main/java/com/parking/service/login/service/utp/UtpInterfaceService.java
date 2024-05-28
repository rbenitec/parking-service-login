package com.parking.service.login.service.utp;

import com.parking.service.login.client.dto.ResponseUtpClient;

public interface UtpInterfaceService {
    ResponseUtpClient getUserUtp (String username);
}
