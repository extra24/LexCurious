package com.lexcurious.model.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// law 목록의 totalCnt, page, List<Law>를 포함하는 모델
public record LawSearch(
        @SerializedName("totalCnt") int totalCnt,
        @SerializedName("page") int pageNumber,
        @SerializedName("law") List<Law> laws
) {}
