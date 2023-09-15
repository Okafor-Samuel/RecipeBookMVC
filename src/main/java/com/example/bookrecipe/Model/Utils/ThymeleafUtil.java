package com.example.bookrecipe.Model.Utils;

import java.util.Base64;

public class ThymeleafUtil {

    public String encodeToBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
