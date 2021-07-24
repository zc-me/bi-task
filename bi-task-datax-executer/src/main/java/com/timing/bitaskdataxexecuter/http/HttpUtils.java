package com.timing.bitaskdataxexecuter.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

public class HttpUtils {

    private static final String BASE_URL = "http://localhost:8889";

    public static ResponseEntity callInterfaceByHttp(String method, Map requestData) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.TEMPLATE_AND_VALUES);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(factory);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(method, requestData, String.class);
        return responseEntity;
    }

}
