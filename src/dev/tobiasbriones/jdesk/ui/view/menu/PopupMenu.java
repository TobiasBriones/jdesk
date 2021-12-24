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

import javax.swing.*;
import java.awt.*;

/**
 * Popup menu to display over a GUI. Use the methods {@link #addItem(MenuItem)}
 * and {@link #addItem(CheckMenuItem)} to add a menu item to this popup menu.
 *
 * @author Tobias Briones
 */
public class PopupMenu extends JPopupMenu {
    private static final long serialVersionUID = 7475696504255395298L;
    private final Color backgroundColor;
    private final GridBagConstraints gbc;

    /**
     * Creates a popup menu.
     *
     * @param context window context
     */
    public PopupMenu(WindowContext context) {
        super();
        this.backgroundColor = context.getAppStyle().getWindowBackgroundColor();
        this.gbc = new GridBagConstraints();

        config();
    }

    /**
     * Creates a popup menu with title.
     *
     * @param context window context
     * @param title   popup menu title
     */
    public PopupMenu(WindowContext context, String title) {
        super(title);
        this.backgroundColor = context.getAppStyle().getWindowBackgroundColor();
        this.gbc = new GridBagConstraints();

        config();
    }

    /**
     * Creates a popup menu with title.
     *
     * @param context  window context
     * @param titleRes popup menu title resource
     */
    public PopupMenu(WindowContext context, int titleRes) {
        this(context, context.getStringResources().get(titleRes));

        config();
    }

    /**
     * By default this PopupMenu has a {@link GridBagLayout} set, this method
     * returns the {@link GridBagConstraints} held on this instance and which
     * should be passed when calling {@link #add(String)} method to add an item
     * or otherwise set another layout with its corresponding parameters.
     *
     * @return the GridBagConstraints held in this popup menu instance
     */
    protected GridBagConstraints getGBC() {
        return gbc;
    }

    @Override
    public void addSeparator() {
        add(new JSeparator(), gbc);
        gbc.gridy++;
    }

    /**
     * Adds a {@link MenuItem} to the popup menu.
     *
     * @param item menu item
     */
    public void addItem(MenuItem item) {
        add(item, gbc);
        gbc.gridy++;
    }

    /**
     * Adds a {@link CheckMenuItem} to the popup menu.
     *
     * @param item check menu item
     */
    public void addItem(CheckMenuItem item) {
        add(item, gbc);
        gbc.gridy++;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void config() {
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
    }
}
