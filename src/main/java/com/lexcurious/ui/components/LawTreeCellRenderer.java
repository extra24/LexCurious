package com.lexcurious.ui.components;

import com.lexcurious.model.tree.ArticleItem;
import com.lexcurious.model.tree.ClauseItem;
import com.lexcurious.model.tree.LegalTextItem;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class LawTreeCellRenderer extends DefaultTreeCellRenderer {

    // Define fonts for consistency
    private static final Font ROOT_FONT = new Font("SansSerif", Font.BOLD, 14);
    private static final Font ARTICLE_FONT = new Font("SansSerif", Font.BOLD, 12);
    private static final Font CLAUSE_FONT = new Font("SansSerif", Font.PLAIN, 12);

    public LawTreeCellRenderer() {
        // Add some padding to the label
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        // Reset icon to avoid default icons
        setIcon(null);

        if (value instanceof DefaultMutableTreeNode) {
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();

            if (userObject instanceof LegalTextItem item) {
                String displayText = item.getDisplayText();
                int score = item.getWeirdnessScore();

                if (item instanceof ArticleItem) {
                    // Article Style
                    setText(String.format("[조] %s (점수: %d)", displayText, score));
                    setFont(ARTICLE_FONT);
                } else if (item instanceof ClauseItem) {
                    // Clause Style
                    setText(String.format("    %s (점수: %d)", displayText, score)); // Indent clauses for clarity
                    setFont(CLAUSE_FONT);
                }
            } else if (userObject != null) {
                // Root Node (Law Name)
                setText("[법] " + userObject);
                setFont(ROOT_FONT);
            }
        }

        return this;
    }
}
