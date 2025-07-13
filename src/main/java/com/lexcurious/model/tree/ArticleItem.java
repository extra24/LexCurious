package com.lexcurious.model.tree;

import java.util.List;

/**
 * 법률의 '조'에 해당하는 데이터를 표현하는 클래스입니다.
 */
public class ArticleItem implements LegalTextItem {
    private final String text;
    private final int totalWeirdnessScore;

    public ArticleItem(String text, List<ClauseItem> clauses) {
        this.text = text;
        this.totalWeirdnessScore = clauses.stream().mapToInt(ClauseItem::getWeirdnessScore).sum();
    }

    @Override
    public String getDisplayText() {
        return text;
    }

    @Override
    public int getWeirdnessScore() {
        return totalWeirdnessScore;
    }
}
