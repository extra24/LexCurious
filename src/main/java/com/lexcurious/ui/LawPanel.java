package com.lexcurious.ui;

import com.lexcurious.api.LawApiClient;
import com.lexcurious.model.detail.LawDetailWrapper;
import com.lexcurious.model.search.Law;
import com.lexcurious.model.search.LawSearchWrapper;
import com.lexcurious.ui.components.LawListCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LawPanel extends JPanel {
    private final LawApiClient lawApiClient;
    private final DefaultListModel<Law> lawListModel;
    private final JList<Law> lawList;

    public LawPanel() {
        lawApiClient = new LawApiClient();
        lawListModel = new DefaultListModel<>();
        lawList = new JList<>(lawListModel);

        setLayout(new BorderLayout());
        add(new JScrollPane(lawList), BorderLayout.CENTER);

        lawList.setCellRenderer(new LawListCellRenderer());

        lawList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = lawList.locationToIndex(evt.getPoint());
                    Law selectedLaw = lawListModel.getElementAt(index);
                    showLawDetailDialog(selectedLaw);
                }
            }
        });
    }

    private void showLawDetailDialog(Law law) {
        try {
            LawDetailWrapper lawDetail = lawApiClient.getLawDetail(law.lawSerialId());
            LawDetailDialog dialog = new LawDetailDialog((Frame) SwingUtilities.getWindowAncestor(this), law, lawDetail);
            dialog.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "법률 상세 정보를 불러오는 데 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void searchLaws(String query) {
        try {
            LawSearchWrapper response = lawApiClient.getLawSearch(query);
            List<Law> laws = response.lawSearch() != null ? response.lawSearch().laws() : Collections.emptyList();
            lawListModel.clear();
            for (Law law : laws) {
                lawListModel.addElement(law);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "법률 검색에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
