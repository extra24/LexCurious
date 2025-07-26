package com.lexcurious.ui.components;

import com.lexcurious.model.search.Law;

import javax.swing.*;
import java.awt.*;

public class LawListCellRenderer extends JPanel implements ListCellRenderer<Law> {
    private final JLabel lawNameLabel;
    private final JLabel promulgationInfoLabel;

    public LawListCellRenderer() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        lawNameLabel = new JLabel();
        lawNameLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 14));

        promulgationInfoLabel = new JLabel();
        promulgationInfoLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
        promulgationInfoLabel.setForeground(Color.GRAY);

        add(lawNameLabel, BorderLayout.CENTER);
        add(promulgationInfoLabel, BorderLayout.SOUTH);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Law> list, Law value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        lawNameLabel.setText(value.lawName());
        promulgationInfoLabel.setText("공포번호: " + value.promulgationNumber() + " | 공포일자: " + value.getPromulgationDate());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
