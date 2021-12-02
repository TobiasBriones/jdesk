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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Used by a {@link TextIdClickView} to perform its {@link ClickListener}.
 *
 * @author Tobias Briones
 */
public final class TextIdViewActionListener implements ActionListener {
    private final ClickListener l;

    /**
     * Constructor for TextIdViewActionListener. This {@link ActionListener} object will trigger
     * {@link ClickListener#onClick(Object, int)} when this view has a click event.
     * The view must be a {@link TextIdClickView} to add this listener.
     *
     * @param l view click listener
     */
    public TextIdViewActionListener(ClickListener l) {
        this.l = l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final TextIdClickView view = (TextIdClickView) e.getSource();

        l.onClick(view, view.getTextId());
    }
}
