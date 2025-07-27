
package com.lexcurious.api;

import com.lexcurious.model.detail.LawDetailWrapper;
import com.lexcurious.model.search.LawSearchWrapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import org.junit.jupiter.api.Tag;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
class LawApiClientTest {

    private MockWebServer mockWebServer;
    private LawApiClient lawApiClient;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        String baseUrl = mockWebServer.url("/").toString();
        lawApiClient = new LawApiClient(new okhttp3.OkHttpClient(), new com.google.gson.GsonBuilder()
                .registerTypeAdapter(com.lexcurious.model.detail.ArticleUnit.class, new com.lexcurious.model.detail.ArticleUnitDeserializer())
                .create(), baseUrl, baseUrl);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("법률 검색 API 호출 테스트")
    void getLawSearch() throws IOException {
        // 1. 준비 (Given)
        String keyword = "도로교통법";
        // 가짜 서버가 반환할 JSON 응답
        String mockResponse = "{\"LawSearch\":{\"totalCnt\":1,\"page\":1,\"law\":[{\"법령일련번호\":12345,\"법령명한글\":\"도로교통법\"}]}}";
        // mockWebServer에 다음 요청이 오면 해당 응답을 보낼 수 있도록 설정
        mockWebServer.enqueue(new MockResponse().setBody(mockResponse).setResponseCode(200));

        // 2. 실행 (When) : 테스트 대상 메소드 호출 -> 내부적으로 MockWebServer에 요청 보냄
        LawSearchWrapper result = lawApiClient.getLawSearch(keyword);

        // 3. 검증 (Then) : MockWebServer가 보내준 가짜 응답을 lawApiClient가 올바르게 객체로 변환했는지 확인
        assertNotNull(result);
        assertEquals(1, result.lawSearch().totalCnt());
        assertEquals(1, result.lawSearch().laws().size());
        assertEquals("도로교통법", result.lawSearch().laws().get(0).lawName());
    }

    @Test
    @DisplayName("법률 상세 정보 API 호출 테스트")
    void getLawDetail() throws IOException {
        // Given
        int lsiSeq = 12345;
        String mockResponse = "{\"법령\":{\"법령키\":\"12345\",\"조문\":{\"조문단위\":[{\"조문키\":\"1\",\"조문내용\":\"내용\"}]}}}";
        mockWebServer.enqueue(new MockResponse().setBody(mockResponse).setResponseCode(200));

        // When
        LawDetailWrapper result = lawApiClient.getLawDetail(lsiSeq);

        // Then
        assertNotNull(result);
        assertNotNull(result.lawDetails());
        assertEquals("12345", result.lawDetails().lawId());
    }
}
