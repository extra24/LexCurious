package com.lexcurious.model;

import com.google.gson.annotations.SerializedName;

// 법령 본문 정보 모델 : 현행법령 본문 모델
public record LawDetail(
        @SerializedName("법령ID") int lawId,
        @SerializedName("법령명_한글") String lawName,
        @SerializedName("조문내용") String lawContent
) {
}
