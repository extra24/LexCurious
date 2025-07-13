package com.lexcurious.model;

import com.google.gson.annotations.SerializedName;

// Law Detail > 조문 > 조문단위 > 항
public record Clause (
        @SerializedName("항번호") String clauseNumber,
        @SerializedName("항내용") String clauseContent
) {}
