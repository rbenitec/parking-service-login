package com.parking.service.login.service.qr;

public interface QRCodeGenerator {
    void generateQRCodeImage(String text, int width, int height, String filePath);
    byte[] getQRCodeImage(String text, int width, int height);
}
