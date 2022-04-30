// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.view;

import engineer.mathsoftware.jdesk.WindowContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Container used to add the GUIs views.
 *
 * @author Tobias Briones
 */
public class Panel extends JPanel {
    private static final long serialVersionUID = -5732557247313892983L;

    // I need to define events or a lifecycle (init) to safely call
    // setBackground, etc.

    /**
     * Default constructor for Panel.
     *
     * @param context window context
     */
    public Panel(WindowContext context) {
        super();
        setBackground(context.getAppStyle().getWindowBackgroundColor());
    }

    /**
     * Constructor for Panel with {@link LayoutManager}.
     *
     * @param context       window context
     * @param layoutManager panel layout manager
     */
    public Panel(WindowContext context, LayoutManager layoutManager) {
        this(context);
        setLayout(layoutManager);
    }

    /**
     * Constructor for Panel with {@link LayoutManager} and panel padding.
     *
     * @param context       window context
     * @param layoutManager panel layout manager
     * @param padding       panel padding
     */
    public Panel(
        WindowContext context,
        LayoutManager layoutManager,
        Insets padding
    ) {
        this(context, layoutManager);
        setPadding(padding);
    }

    /**
     * Sets the padding of the panel.
     *
     * @param padding padding
     */
    public final void setPadding(Insets padding) {
        setBorder(new EmptyBorder(padding));
    }

    /**
     * Sets the padding of the panel.
     *
     * @param top    padding top
     * @param left   padding left
     * @param bottom padding bottom
     * @param right  padding right
     */
    public final void setPadding(int top, int left, int bottom, int right) {
        setBorder(new EmptyBorder(top, left, bottom, right));
    }
}
