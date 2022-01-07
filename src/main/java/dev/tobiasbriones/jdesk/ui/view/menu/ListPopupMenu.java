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

package dev.tobiasbriones.jdesk.ui.view.menu;

import dev.tobiasbriones.jdesk.WindowContext;
import dev.tobiasbriones.jdesk.ui.view.ListPane;
import dev.tobiasbriones.jdesk.ui.view.ScrollPane;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Popup showing a list of items. Use the methods {@link #addItem(String)} to
 * add an item to this popup menu. Call
 * {@link #setItemSelectedListener(ItemSelectedListener)}
 * to receive item events.
 *
 * @author Tobias Briones
 */
public class ListPopupMenu extends PopupMenu {
    private static final long serialVersionUID = 6542389860376614740L;

    /**
     * Listener to handle popup list items events.
     */
    @FunctionalInterface
    public interface ItemSelectedListener {
        /**
         * Called when an item in the popup list has been selected.
         *
         * @param item  selected item value
         * @param index selected item index
         */
        void onItemSelected(String item, int index);
    }

    private final DefaultListModel<String> listModel;
    private transient ItemSelectedListener l;

    /**
     * Default constructor for ListPopupMenu.
     *
     * @param context window context
     */
    public ListPopupMenu(WindowContext context) {
        super(context);
        this.listModel = new DefaultListModel<>();
        final ListPane<String> list = new ListPane<>(context, listModel);
        final ScrollPane scroll = new ScrollPane(context, list);
        final ListSelectionListener lsl = e -> {
            if (!e.getValueIsAdjusting() && list.getSelectedIndex() != -1 && l != null) {
                l.onItemSelected(
                    list.getSelectedValue(),
                    list.getSelectedIndex()
                );
            }
        };

        list.addListSelectionListener(lsl);
        scroll.setBorder(null);
        setLayout(new BorderLayout());
        add(scroll);
    }

    /**
     * Sets the item selected listener for the items added to the popup list.
     *
     * @param value item selected listener
     */
    public final void setItemSelectedListener(ItemSelectedListener value) {
        this.l = value;
    }

    /**
     * Clears the popup menu.
     */
    public final void clear() {
        listModel.clear();
    }

    /**
     * Adds an item to the popup.
     *
     * @param item item text
     */
    public void addItem(String item) {
        listModel.addElement(item);
    }
}
