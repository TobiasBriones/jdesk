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

import dev.tobiasbriones.jdesk.WindowContext;

import javax.swing.*;
import java.awt.*;

/**
 * Rounded button which functionality is to provide the user a main action on
 * the window or dialog. It shouldn't be added to a window or dialog more than
 * once because of UI design patterns.
 *
 * @author Tobias Briones
 */
public class MainActionButton extends Button {
    private static final long serialVersionUID = -578290235961396969L;

    /**
     * Constructor for MainActionButton, it adds the button icon which should be
     * half the size of the button. The default button size is 56px.
     *
     * @param context window context
     * @param icon    button icon
     */
    public MainActionButton(WindowContext context, Icon icon) {
        super(context, icon);
        setBackgroundColor(context.getAppStyle().getAccentColor());
        setFocusable(false);
        setSize(56);
        setHoverColor(getBackgroundColor().darker());
        setPressedColor(getBackgroundColor().darker());
    }

    /**
     * Constructor for MainActionButton, it adds the button icon which should be
     * half the size of the button. The default button size is 56px. The
     * argument hidingTextRes gives to this button the text id to listen to
     * {@link ClickListener} but it does not show the text.
     *
     * @param context       window context
     * @param icon          button icon
     * @param hidingTextRes text resource to provide to this button to add the
     *                      {@link ClickListener}, the text won't be displayed
     */
    public MainActionButton(
        WindowContext context,
        Icon icon,
        int hidingTextRes
    ) {
        super(context, hidingTextRes, icon);
        setText("");
        setBackgroundColor(context.getAppStyle().getAccentColor());
        setFocusable(false);
        setSize(56);
        setHoverColor(getBackgroundColor().darker());
        setPressedColor(getBackgroundColor().darker());
    }

    /**
     * Button size, that is, width and height.
     *
     * @param size button size
     */
    public final void setSize(int size) {
        super.setPreferredSize(new Dimension(size, size));
    }

    @Override
    public final void setPreferredSize(Dimension preferredSize) {
        final int size = Math.min(preferredSize.width, preferredSize.height);
        super.setPreferredSize(new Dimension(size, size));
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        if (getPressedColor() != null && getModel().isPressed()) {
            g.setColor(getPressedColor());
            g.fillOval(0, 0, getWidth(), getHeight());
        }
        else if (getHoverColor() != null && getModel().isRollover()) {
            g.setColor(getHoverColor());
            g.fillOval(0, 0, getWidth(), getHeight());
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if (getDisabledColor() != null && !isEnabled()) {
            g.setColor(getDisabledColor());
            g.fillOval(0, 0, getWidth(), getHeight());
        }
        else {
            g.setColor(getBackgroundColor());
            g.fillOval(0, 0, getWidth(), getHeight());
        }
        getIcon().paintIcon(
            this,
            g,
            ((getWidth() - 1) - (getIcon().getIconWidth() - 1)) / 2,
            ((getHeight() - 1) - (getIcon().getIconHeight() - 1)) / 2
        );
    }
}
