package com.ten.service;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;

public class BasicAuthRestTemplate extends RestTemplate {

    private String login;
    private String password;

    public BasicAuthRestTemplate(String login, String password) {
        super();
        this.login = login;
        this.password = password;
        addAuthentication();
    }
//добавляю готоввые хидеры и авторизацию к обекту ресттемплейт которы йбуду использовать в юзерДТОсервисе

    private void addAuthentication() {
        if (StringUtils.isEmpty(login)) {
            throw new RuntimeException("Login is mandatory for Basic Auth");
        }
        List<ClientHttpRequestInterceptor> interceptors = Collections.singletonList(new BasicAuthInterceptor(login, password));
        setRequestFactory(new InterceptingClientHttpRequestFactory(getRequestFactory(), interceptors));
    }
}

