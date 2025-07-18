package com.lexcurious.ui;

import com.lexcurious.model.Law;
import com.lexcurious.model.tree.ArticleItem;
import com.lexcurious.model.tree.ClauseItem;
import com.lexcurious.ui.components.LawTreeCellRenderer;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LawDetailDialog extends JDialog {
    private JPanel contentPane;
    private JLabel lawNameLabel;
    private JTree lawTre;
    private JButton closeButton;

    public LawDetailDialog(Frame owner, Law law) {
        super(owner, law.lawName() + " 상세 정보", true);
        $$$setupUI$$$();

        setContentPane(contentPane);
        setSize(600, 800);
        setLocationRelativeTo(owner);

        lawNameLabel.setText(law.lawName());
        lawTre.setModel(createDummyTreeModel(law));
        lawTre.setCellRenderer(new LawTreeCellRenderer());

        closeButton.addActionListener(e -> dispose());
    }

    private DefaultTreeModel createDummyTreeModel(Law law) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("도로교통법");

        // Sample data for Article 1
        List<ClauseItem> clauses1 = new ArrayList<>();
        clauses1.add(new ClauseItem("이 법은 도로에서의 위험을 방지하고...", 5));
        clauses1.add(new ClauseItem("이 법은 또한 원활한 교통을 확보함을 목적으로 한다.", 3));
        ArticleItem article1 = new ArticleItem("제1조(목적)", clauses1);
        DefaultMutableTreeNode articleNode1 = new DefaultMutableTreeNode(article1);
        for (ClauseItem clause : clauses1) {
            articleNode1.add(new DefaultMutableTreeNode(clause));
        }
        root.add(articleNode1);

        // Sample data for Article 2
        List<ClauseItem> clauses2 = new ArrayList<>();
        clauses2.add(new ClauseItem("이 법에서 사용하는 용어의 뜻은 다음과 같다.", 7));
        ArticleItem article2 = new ArticleItem("제2조(정의)", clauses2);
        DefaultMutableTreeNode articleNode2 = new DefaultMutableTreeNode(article2);
        for (ClauseItem clause : clauses2) {
            articleNode2.add(new DefaultMutableTreeNode(clause));
        }
        root.add(articleNode2);

        // Sample data for Article 3 (no clauses)
        ArticleItem article3 = new ArticleItem("제3조(삭제)", new ArrayList<>());
        DefaultMutableTreeNode articleNode3 = new DefaultMutableTreeNode(article3);
        root.add(articleNode3);

        return new DefaultTreeModel(root);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        lawNameLabel = new JLabel();
        Font lawNameLabelFont = this.$$$getFont$$$("Malgun Gothic", Font.BOLD, 20, lawNameLabel.getFont());
        if (lawNameLabelFont != null) lawNameLabel.setFont(lawNameLabelFont);
        lawNameLabel.setHorizontalAlignment(0);
        lawNameLabel.setText("Law Name");
        contentPane.add(lawNameLabel, BorderLayout.NORTH);
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        lawTre = new JTree();
        scrollPane1.setViewportView(lawTre);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        contentPane.add(panel1, BorderLayout.SOUTH);
        closeButton = new JButton();
        closeButton.setText("닫기");
        panel1.add(closeButton);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}