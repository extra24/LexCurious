package com.lexcurious.model.detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// Law Detail > 조문 객체
public record ArticlesWrapper (
        @SerializedName("조문단위") List<ArticleUnit> articleUnits
) {}
