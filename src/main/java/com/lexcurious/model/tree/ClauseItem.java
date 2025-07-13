package com.lexcurious.model.tree;

/**
 * 법률의 '항'에 해당하는 데이터를 표현하는 클래스입니다.
 */
public class ClauseItem implements LegalTextItem {
    private final String text;
    private final int weirdnessScore;

    public ClauseItem(String text, int weirdnessScore) {
        this.text = text;
        this.weirdnessScore = weirdnessScore;
    }

    @Override
    public String getDisplayText() {
        return text;
    }

    @Override
    public int getWeirdnessScore() {
        return weirdnessScore;
    }
}
