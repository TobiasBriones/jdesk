// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.view;

import engineer.mathsoftware.jdesk.resources.StringResourceId;

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
     * Constructor for TextIdViewActionListener. This {@link ActionListener}
     * object will trigger {@link ClickListener#onClick(Object,
     * StringResourceId)} when this view has a click event. The view must be a
     * {@link TextIdClickView} to add this listener.
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
