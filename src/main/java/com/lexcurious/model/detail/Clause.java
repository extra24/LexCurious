package com.lexcurious.model.detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// Law Detail > 조문 > 조문단위 > 항
public record Clause (
        @SerializedName("항번호") String clauseNumber,
        @SerializedName("항내용") String clauseContent,
        @SerializedName("호") List<Ho> ho
) {}
