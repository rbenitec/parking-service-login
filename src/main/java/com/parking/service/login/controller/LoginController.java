package com.parking.service.login.controller;

import com.google.zxing.WriterException;
import com.parking.service.login.controller.dto.RequestAccountDto;
import com.parking.service.login.controller.dto.RequestDto;
import com.parking.service.login.controller.dto.ResponseDto;
import com.parking.service.login.service.business.AccountService;
import com.parking.service.login.service.business.AuthenticationService;
import com.parking.service.login.service.qr.QRCodeGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;
    private final AuthenticationService authenticationService;
    private final QRCodeGenerator getQRCodeImage;

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> authenticationCustomer(@RequestBody RequestDto request) {
        log.info("Call Autentication Customer: {}", request.toString());
        return ResponseEntity.ok(authenticationService.userAuthentication(request));
    }

    @PostMapping("/account-created")
    public ResponseEntity<ResponseDto> accountCreated(@RequestBody RequestAccountDto requestAccount) {
        log.info("Call Created Customer: {}", requestAccount.toString());
        ResponseDto response = accountService.createdAccount(requestAccount);
        if(response!=null) {
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public String getQRCode(Model model){
        String medium="https://rahul26021999.medium.com/";
        String github="https://github.com/rahul26021999";

        byte[] image = new byte[0];
        // Generate and Return Qr Code in Byte Array
        image = getQRCodeImage.getQRCodeImage(medium,250,250);
        // Generate and Save Qr Code Image in static/image folder
        getQRCodeImage.generateQRCodeImage(github,250,250,QR_CODE_IMAGE_PATH);
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("medium",medium);
        model.addAttribute("github",github);
        model.addAttribute("qrcode",qrcode);
        String htmlContent = generateHTMLContent(medium, github, qrcode);
        System.out.println(htmlContent);
        return "qrcode";
    }

    public static String generateHTMLContent(String medium, String github, String qrcodeBase64) {
        return "<html>\n" +
                "<head>\n" +
                "    <title>QR Code Example</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>QR Code Example</h1>\n" +
                "    <p>Medium: <a href=\"" + medium + "\">" + medium + "</a></p>\n" +
                "    <p>GitHub: <a href=\"" + github + "\">" + github + "</a></p>\n" +
                "    <img src=\"data:image/png;base64," + qrcodeBase64 + "\" alt=\"QR Code\" />\n" +
                "</body>\n" +
                "</html>";
    }
}
