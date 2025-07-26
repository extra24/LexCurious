package com.lexcurious.model.detail;

import com.google.gson.annotations.SerializedName;

// 개별 법률의 상세 정보 모델 : 법률의 상세 데이터 구조
public record LawDetail (
        @SerializedName("법령키") String lawId,
        @SerializedName("조문") ArticlesWrapper articlesWrapper
) {}
