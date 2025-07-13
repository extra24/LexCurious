package com.lexcurious.ui;

import com.lexcurious.model.Law;
import com.lexcurious.model.tree.ArticleItem;
import com.lexcurious.model.tree.ClauseItem;
import com.lexcurious.ui.components.LawTreeCellRenderer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LawDetailDialog extends JDialog {
    private final JLabel lawNameLabel;
    private final JTree lawTree;

    public LawDetailDialog(Frame owner, Law law) {
        super(owner, law.lawName() + " 상세 정보", true);
        setSize(600, 800);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        // 법령명 표시 레이블
        lawNameLabel = new JLabel(law.lawName(), SwingConstants.CENTER);
        lawNameLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
        add(lawNameLabel, BorderLayout.NORTH);

        // JTree 생성 및 더미 데이터 설정
        lawTree = new JTree(createDummyTreeModel(law));
        lawTree.setCellRenderer(new LawTreeCellRenderer());
        add(new JScrollPane(lawTree), BorderLayout.CENTER);

        // 닫기 버튼
        JButton closeButton = new JButton("닫기");
        closeButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
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
        ArticleItem article3 = new ArticleItem("제3조(삭제)", new java.util.ArrayList<>());
        DefaultMutableTreeNode articleNode3 = new DefaultMutableTreeNode(article3);
        root.add(articleNode3);

        return new DefaultTreeModel(root);
    }
}
