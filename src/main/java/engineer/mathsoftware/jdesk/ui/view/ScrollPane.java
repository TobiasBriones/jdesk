// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.view;

import engineer.mathsoftware.jdesk.WindowContext;
import engineer.mathsoftware.jdesk.ui.style.AppStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Scroll pane to a large displaying view.
 *
 * @author Tobias Briones
 */
public class ScrollPane extends JScrollPane {
    private static final long serialVersionUID = -8222286430953671484L;

    /**
     * Default constructor for ScrollPane.
     *
     * @param context window context
     */
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
