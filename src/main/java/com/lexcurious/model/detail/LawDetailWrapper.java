package com.lexcurious.model.detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// law detail 최상위 wrapper 레코드
public record LawDetailWrapper(
        @SerializedName("법령") LawDetail lawDetails
) {}
