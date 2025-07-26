package com.lexcurious.ui.components;

import com.lexcurious.model.tree.ArticleItem;
import com.lexcurious.model.tree.ClauseItem;
import com.lexcurious.model.tree.HoItem;
import com.lexcurious.model.tree.LegalTextItem;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class LawTreeCellRenderer extends DefaultTreeCellRenderer {

    // 일관성을 위한 폰트 정의
    private static final Font ROOT_FONT = new Font("SansSerif", Font.BOLD, 14);
    private static final Font ARTICLE_FONT = new Font("SansSerif", Font.BOLD, 12);
    private static final Font CLAUSE_FONT = new Font("SansSerif", Font.PLAIN, 12);

    public LawTreeCellRenderer() {
        // 레이블에 약간의 패딩 추가
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        // 기본 아이콘을 사용하지 않도록 아이콘 재설정
        setIcon(null);

        if (value instanceof DefaultMutableTreeNode) {
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();

            if (userObject instanceof LegalTextItem item) {
                String displayText = item.getDisplayText();
                int score = item.getWeirdnessScore();

                if (item instanceof ArticleItem) {
                    // 조 스타일
                    setText(String.format("[조] %s (점수: %d)", displayText, score));
                    setFont(ARTICLE_FONT);
                } else if (item instanceof ClauseItem) {
                    // 항 스타일
                    setText(String.format("%s (점수: %d)", displayText, score)); // 명확성을 위해 항 들여쓰기
                    setFont(CLAUSE_FONT);
                } else if (item instanceof HoItem) {
                    // 호 스타일
                    setText(String.format("%s", displayText)); // 명확성을 위해 호 들여쓰기
                    setFont(CLAUSE_FONT); // 항과 동일한 폰트 사용
                }
            } else if (userObject != null) {
                // 루트 노드 (법 이름)
                setText("[법] " + userObject);
                setFont(ROOT_FONT);
            }
        }

        return this;
    }
}
