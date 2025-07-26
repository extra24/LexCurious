package com.lexcurious.model.detail;

import com.google.gson.annotations.SerializedName;

// Law Detail > 조문 > 조문 > 항 > 호
public record Ho (
        @SerializedName("호번호") String hoNumber,
        @SerializedName("호내용") String hoContent
) {}
