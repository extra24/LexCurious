package com.lexcurious.api;

import com.lexcurious.model.search.LawSearchWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration")
class LawApiClientIntegrationTest {

    private LawApiClient lawApiClient;

    @BeforeEach
    void setUp() {
        // 실제 API URL과 키를 사용하는 기본 생성자를 호출합니다.
        lawApiClient = new LawApiClient();
    }

    @Test
    @DisplayName("실제 법률 검색 API 호출 테스트")
    void getLawSearch_withRealApi() throws IOException {
        // Given
        String keyword = "민법";

        // When
        LawSearchWrapper result = lawApiClient.getLawSearch(keyword);

        // Then
        // 실제 API의 응답은 계속 변할 수 있으므로, null이 아니고 결과 목록이 비어있지 않은지만 확인합니다.
        assertNotNull(result);
        assertNotNull(result.lawSearch());
        assertTrue(result.lawSearch().totalCnt() > 0, "검색 결과가 하나 이상 있어야 합니다.");
        assertFalse(result.lawSearch().laws().isEmpty(), "법률 목록이 비어있지 않아야 합니다.");

        System.out.println("성공: '" + keyword + "' 키워드로 총 " + result.lawSearch().totalCnt() + "개의 법률을 찾았습니다.");
    }
}
