package com.ten.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class BasicAuthInterceptor implements ClientHttpRequestInterceptor {

    private String login;
    private String password;

    public BasicAuthInterceptor(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, encodeCredentialsForBasicAuth(login, password));
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }//в этом методе добавляем хидеры и авторизацию

    public static String encodeCredentialsForBasicAuth(String login, String password) {
        return "Basic " + new Base64().encodeToString((login + ":" + password).getBytes()); //к авторизации по кредам добавляю бейсик авторизацию
    }//base64 когда кодировка из байтов в стринг и данные пердаются в тектсовом виде, мнеьше рис потери данных или их поломки в ходе кодирования
}
