package com.mayard.booksearch.book.service;

import com.mayard.booksearch.book.model.vo.ApiResponseVo;
import com.mayard.booksearch.book.model.vo.BookSearchRequestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class BookApiService {

    private Logger logger = LoggerFactory.getLogger(BookApiService.class);

    @Value("${kakao.api.host}")
    private String kakaoApiHost;

    @Value("${kakao.api.search.book.uri}")
    private String kakaoApiSearchBookUri;

    @Value("${kakao.api.app.key}")
    private String kakaoApiAppKey;


    public ApiResponseVo searchBook(BookSearchRequestVo requestVo) {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity entity = new HttpEntity(getRequestHeader());

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(kakaoApiHost + kakaoApiSearchBookUri)
                .queryParam("query", requestVo.getQuery())
                .queryParam("sort", requestVo.getSort())
                .queryParam("page", requestVo.getPage())
                .queryParam("size", requestVo.getSize())
                .queryParam("target", requestVo.getTarget())
                ;

        if (requestVo.getCategory() != null) {
            uriBuilder.queryParam("category", requestVo.getCategory().getCategoryNo());
        }

        ResponseEntity<ApiResponseVo> responseEntity = restTemplate.exchange(uriBuilder.build().encode().toUri(), HttpMethod.GET, entity, ApiResponseVo.class);


        logger.info("[API RESULT] [URL: {}] [REQ: {}] [RES: {}]", uriBuilder.toUriString(), requestVo.toString(), responseEntity.toString());

        return responseEntity.getBody();
    }

    private HttpHeaders getRequestHeader() {        // API 호출을 하기 위해 필요한 헤더를 만든다.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", kakaoApiAppKey);

        return httpHeaders;
    }
}
