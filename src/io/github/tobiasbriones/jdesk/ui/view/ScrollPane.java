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
import io.github.tobiasbriones.jdesk.ui.style.AppStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Scroll pane to a large displaying view.
 */
@SuppressWarnings("unused")
public class ScrollPane extends JScrollPane {
    private static final long serialVersionUID = -8222286430953671484L;

    /**
     * Default constructor for ScrollPane.
     *
     * @param context window context
     */
    @SuppressWarnings("WeakerAccess")
    public ScrollPane(WindowContext context) {
        super();
        config(context.getAppStyle());
    }

    /**
     * Constructor for ScrollPane.
     *
     * @param context window context
     * @param view    view to scroll
     */
    @SuppressWarnings("WeakerAccess")
    public ScrollPane(WindowContext context, Component view) {
        super(view);
        config(context.getAppStyle());
    }

    private void config(AppStyle appStyle) {
        setBackground(appStyle.getWindowBackgroundColor());
        setBorder(BorderFactory.createLineBorder(Color.decode("#BDBDBD")));
        getVerticalScrollBar().setUnitIncrement(12);
        getHorizontalScrollBar().setUnitIncrement(12);
    }
}
