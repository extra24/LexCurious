package com.lexcurious.model.tree;

/**
 * JTree에 표시될 모든 법률 텍스트 단위(조, 항 등)가 구현할 공통 인터페이스입니다.
 */
public interface LegalTextItem {
    /**
     * 화면에 표시될 텍스트를 반환합니다.
     * @return 표시될 텍스트 (예: "제1조(목적) 이 법은...")
     */
    String getDisplayText();

    /**
     * 항목의 특이함 점수를 반환합니다.
     * '항'의 경우 고유 점수, '조'의 경우 하위 항들의 점수 합계입니다.
     * @return 특이함 점수
     */
    int getWeirdnessScore();
}
