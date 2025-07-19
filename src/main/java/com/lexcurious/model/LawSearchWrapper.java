package com.lexcurious.model;

import com.google.gson.annotations.SerializedName;

// law 목록 최상위 wrapper 레코드
public record LawSearchWrapper (
        @SerializedName("LawSearch") LawSearch lawSearch
) {}
