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

package io.github.tobiasbriones.jdesk.ui.view;

import io.github.tobiasbriones.jdesk.WindowContext;
import io.github.tobiasbriones.jdesk.ui.Tools;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Pane that receives short input texts form the user and displays the entered items on the pane with availability to
 * remove them. To retrieve the entered items, this class implements {@link Iterable}.
 */
@SuppressWarnings("unused")
public class ItemInputPane extends Panel implements Iterable<String> {
    private static final long serialVersionUID = -352531864001279392L;

    static final class ItemView extends Panel implements MouseListener {
        private static final long serialVersionUID = 1391246170951661932L;
        private final Color backgroundColor;
        private final Color hoverColor;

        private ItemView(WindowContext context, ItemInputPane panel, String text) {
            super(context);
            this.backgroundColor = context.getAppStyle().getButtonBackgroundColor();
            this.hoverColor = context.getAppStyle().getItemHoverColor();
            final TextLabel closeLabel = new TextLabel(context, "x");
            final MouseListener l = new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    panel.remove(ItemView.this);
                }
            };
            closeLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
            closeLabel.setForeground(context.getAppStyle().getErrorTextColor());
            closeLabel.addMouseListener(l);
            setName(text);
            setToolTipText(text);
            setLayout(new BorderLayout());
            setPadding(5, 10, 5, 10);
            setBackground(backgroundColor);
            add(new TextLabel(context, Tools.getEllipsisText(text, 25)), BorderLayout.WEST);
            add(closeLabel, BorderLayout.EAST);
            addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            setBackground(hoverColor);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            setBackground(hoverColor);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setBackground(backgroundColor);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(hoverColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(backgroundColor);
        }
    }

    private static final class InputListener implements ActionListener {
        private final ItemInputPane panel;

        private InputListener(ItemInputPane panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.addItemRequest();
        }
    }

    private final int columns;
    private final List<ItemView> items;
    private final GridBagConstraints gbc;
    private final ScrollPane scroll;
    private final Panel panelTop;
    private final Panel panelBottom;
    private final InputText inputText;
    private final transient WindowContext context;
    private boolean isLocked;

    /**
     * Constructor for ItemInputPane, it sets the number of columns and rows for the pane, that will determine its size.
     * There is always vertical scroll if needed. It is recommended to set at least two rows.
     *
     * @param context window context
     * @param rows    number of rows
     * @param columns number of columns
     */
    @SuppressWarnings("WeakerAccess")
    public ItemInputPane(WindowContext context, int rows, int columns) {
        super(context);
        if (rows < 0) {
            throw new IllegalArgumentException("Invalid number of rows " + rows);
        }
        if (columns < 0) {
            throw new IllegalArgumentException("Invalid number of columns " + columns);
        }
        this.context = context;
        this.columns = columns;
        this.items = new ArrayList<>();
        this.gbc = new GridBagConstraints();
        this.scroll = new ScrollPane(context);
        this.panelTop = new Panel(context);
        this.panelBottom = new Panel(context);
        this.inputText = new InputText(context, (columns > 1) ? 20 * columns : 30);
        this.isLocked = false;

        scroll.setPreferredSize(new Dimension(0, 35 * rows));
        config();
    }

    /**
     * Default constructor for ItemInputPane, it sets 2 rows and 4 columns for the pane. There is always vertical
     * scroll if needed.
     *
     * @param context window context
     */
    @SuppressWarnings("WeakerAccess")
    public ItemInputPane(WindowContext context) {
        this(context, 2, 4);
    }

    /**
     * Returns the input text of this pane.
     *
     * @return the input text
     */
    @SuppressWarnings("WeakerAccess")
    public InputText getInputText() {
        return inputText;
    }

    /**
     * Returns the number of items added to this pane.
     *
     * @return the number of items added to this pane
     */
    public final int getNumberOfItems() {
        return items.size();
    }

    /**
     * Sets a flag to lock the user from entering new items onto the pane.
     *
     * @param locked locked
     */
    @SuppressWarnings("WeakerAccess")
    public final void setUserInputLocked(boolean locked) {
        this.isLocked = locked;
    }

    /**
     * Sets a hint text to show on the input text when this is empty and with requestFocus lost.
     *
     * @param hintText hint text
     */
    public final void setHint(String hintText) {
        inputText.setHintText(hintText);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            private int next = 0;

            @Override
            public boolean hasNext() {
                return next < items.size();
            }

            @Override
            public String next() {
                return items.get(next++).getName();
            }
        };
    }

    /**
     * Requests the requestFocus for the pane input text.
     */
    @SuppressWarnings("WeakerAccess")
    public final void requestFocus() {
        inputText.requestFocus();
    }

    /**
     * Adds to this pane the requested items.
     *
     * @param load items to add to the pane
     */
    public final void loadPane(List<String> load) {
        ItemView currentItem;

        for (String itemStr : load) {
            currentItem = new ItemView(context, this, itemStr);

            items.add(currentItem);
            addItem(currentItem);
        }
    }

    /**
     * Adds the given item to the panel.
     *
     * @param input input text for the new item
     */
    @SuppressWarnings("WeakerAccess")
    public final void addInput(String input) {
        final ItemView item = new ItemView(context, this, input);

        inputText.setText("");
        items.add(item);
        addItem(item);
    }

    private void config() {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.insets.top = 5;
        gbc.insets.left = 5;
        gbc.insets.bottom = 5;
        gbc.insets.right = 5;

        panelTop.setLayout(new GridBagLayout());
        inputText.addActionListener(new InputListener(this));
        panelBottom.add(inputText);
        scroll.setViewportView(panelTop);
        scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPadding(10, 10, 10, 10);
        add(scroll);
        add(panelBottom);
    }

    private void addItem(ItemView item) {
        if (gbc.gridx == columns) {
            gbc.gridx = 0;
            gbc.gridy++;
        }
        panelTop.add(item, gbc);
        revalidate();
        gbc.gridx++;
    }

    private void addItemRequest() {
        if (isLocked) {
            return;
        }
        final String text = inputText.getText();

        if (!text.trim().isEmpty()) {
            final ItemView item = new ItemView(context, this, text);

            inputText.setText("");
            items.add(item);
            addItem(item);
        }
    }

    private void remove(ItemView itemView) {
        items.remove(itemView);
        panelTop.removeAll();

        gbc.gridx = 0;
        gbc.gridy = 0;
        for (ItemView item : items) {
            addItem(item);
        }
        repaint();
    }
}
