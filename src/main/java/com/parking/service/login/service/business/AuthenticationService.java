package com.parking.service.login.service.business;

import com.parking.service.login.controller.dto.RequestDto;
import com.parking.service.login.controller.dto.ResponseDto;

public interface AuthenticationService {
    ResponseDto userAuthentication (RequestDto requestDto);
}
