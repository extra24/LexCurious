package com.lexcurious.api;

import com.google.gson.annotations.SerializedName;
import com.lexcurious.model.Law;

import java.util.List;

// totalCnt, page, List<Law>를 포함하는 API 응답의 전체 구조(모델)
public record LawListResponse(
        @SerializedName("totalCnt") int totalCnt,
        @SerializedName("page") int pageNumber,
        @SerializedName("law") List<Law> laws
) {}
