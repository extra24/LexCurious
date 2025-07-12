package com.lexcurious.model;

import com.google.gson.annotations.SerializedName;

// 개별 법률의 상세 정보 모델 : 법률의 상세 데이터 구조
// @TODO Detail 내용 수정 필요
public record LawDetail(
        @SerializedName("법령ID") int lawId,
        @SerializedName("법령명_한글") String lawName,
        @SerializedName("조문내용") String lawContent
) {
}
