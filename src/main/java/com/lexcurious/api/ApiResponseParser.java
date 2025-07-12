package com.lexcurious.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// 외부 API의 JSON 응답 데이터를 모델로 파싱하는 유틸 클래스
// *유틸 클래스 : 여러 클래스에서 공통적으로 사용되는 메서드를 모아서 클래스로 만든것
public class ApiResponseParser {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private ApiResponseParser(){}

    // 법률 목록 API의 JSON응답을 LawListResponse 객체로 파싱


    // 법률 본문 API의 JSON응답을 LawDetail 객체로 파싱


}
