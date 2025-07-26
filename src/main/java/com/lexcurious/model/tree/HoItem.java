package com.lexcurious.model.tree;

import com.lexcurious.model.detail.Ho;

public class HoItem implements LegalTextItem {
    private final Ho ho;

    public HoItem(Ho ho) {
        this.ho = ho;
    }

    @Override
    public String getDisplayText() {
        return ho.hoContent();
    }

    @Override
    public int getWeirdnessScore() {
        return 0; // 호에는 현재 특이함 점수가 없습니다.
    }

    public Ho getHo() {
        return ho;
    }
}