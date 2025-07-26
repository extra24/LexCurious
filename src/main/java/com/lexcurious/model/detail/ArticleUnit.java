package com.lexcurious.model.detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// Law Detail > 조문 > 조문단위 객체
public record ArticleUnit (
        @SerializedName("조문키") String articleKey,
        @SerializedName("조문내용") String articleContent,
        @SerializedName("항") List<Clause> clause    // ArticleUnitDeserializer에서 처리
) {}
