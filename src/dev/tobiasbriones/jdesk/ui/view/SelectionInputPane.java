/*
 * Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 *
 * This file is part of JDesk.
 *
 * This source code is licensed under the BSD-3-Clause License found in the
 * LICENSE file in the root directory of this source tree or at
 * https://opensource.org/licenses/BSD-3-Clause.
 */

package dev.tobiasbriones.jdesk.ui.view;

import dev.tobiasbriones.jdesk.work.LoadingView;
import dev.tobiasbriones.jdesk.WindowContext;
import dev.tobiasbriones.jdesk.ui.view.menu.ListPopupMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Pane which gives to the user a list of items to pick according to the user
 * inputText text and adds to the pane those that the user chose with
 * availability to remove them.
 *
 * @author Tobias Briones
 */
public class SelectionInputPane<E> extends Panel implements LoadingView {
    private static final long serialVersionUID = -460382072727635296L;

    /**
     * Listener for requesting the list of items to display as user inputText according to what the user currently
     * writes on
     * the inputText text.
     */
    public interface ItemsRequestListener {

        /**
         * Requests the list of items to display as the user inputText from the search the user is entering.
         *
         * @param search user search
         */
        void onItemsRequested(String search);
    }

    private static final class InputListener implements DocumentListener {
        private final SelectionInputPane pane;

        private InputListener(SelectionInputPane pane) {
            this.pane = pane;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }

        private void update() {
            pane.updateItemsRequest();
        }
    }

    private final ItemInputPane selectedInputs;
    private final ListPopupMenu popup;
    private boolean isSearching;
    private transient ItemsRequestListener l;

    /**
     * Creates a pane with and dynamical inputText to let the user select several items.
     *
     * @param context window context
     * @param rows    number of rows for the {@link ItemInputPane}
     * @param columns number of columns for the {@link ItemInputPane}
     */
    public SelectionInputPane(WindowContext context, int rows, int columns) {
        super(context);
        this.selectedInputs = new ItemInputPane(context, rows, columns);
        this.popup = new ListPopupMenu(context);
        this.isSearching = false;
        this.l = null;
        final ListPopupMenu.ItemSelectedListener l = (item, index) -> {
            selectedInputs.addInput(item);
            popup.setVisible(false);
        };
        final MouseListener ml = new MouseAdapter() {

            private boolean flag = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (flag && !selectedInputs.getInputText().getText().trim().isEmpty()) {
                    popup.setVisible(true);
                    selectedInputs.getInputText().requestFocus();
                }
                flag = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                flag = true;
            }
        };

        selectedInputs.setUserInputLocked(true);
        selectedInputs.getInputText().getDocument().addDocumentListener(new InputListener(this));
        selectedInputs.getInputText().addMouseListener(ml);
        popup.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, context.getAppStyle().getSecondaryTextColor()));
        popup.setItemSelectedListener(l);
        add(selectedInputs);
    }

    /**
     * Default constructor for SelectionInputPane with 1 row and 1 column for the {@link ItemInputPane}.
     *
     * @param context window context
     */
    public SelectionInputPane(WindowContext context) {
        this(context, 2, 2);
    }

    /**
     * Sets the ItemsRequestListener which listens for the user inputs and request to the application the list of
     * possible items to display so the user can choose the appropriate item.
     *
     * @param l items request listener
     */
    public void setItemsRequestListener(ItemsRequestListener l) {
        this.l = l;
    }

    /**
     * Sets the list of searched items which the user will choose as inputText.
     *
     * @param items items to display
     */
    public void setInputText(List<E> items) {
        popup.clear();
        for (E item : items) {
            popup.addItem(item.toString());
        }
        popup.setPreferredSize(getPreferredSize());
        popup.show(selectedInputs, 0, getHeight() - 10);
        popup.setVisible(true);
        selectedInputs.requestFocus();
    }

    @Override
    public boolean isRunning() {
        return isSearching;
    }

    @Override
    public void start() {
        isSearching = true;
    }

    @Override
    public void end() {
        isSearching = false;
    }

    private void updateItemsRequest() {
        if (l != null && !selectedInputs.getInputText().getText().trim().isEmpty()) {
            l.onItemsRequested(selectedInputs.getInputText().getText());
        }
        else if (selectedInputs.getInputText().getText().trim().isEmpty()) {
            popup.setVisible(false);
        }
    }
}
