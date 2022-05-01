// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.view.menu;

import engineer.mathsoftware.jdesk.WindowContext;
import engineer.mathsoftware.jdesk.resources.StringResourceId;
import engineer.mathsoftware.jdesk.ui.view.CheckButton;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Check menu item view to put on a {@link Menu} or {@link PopupMenu}. This item
 * is a {@link CheckButton}.
 *
 * @author Tobias Briones
 */
public class CheckMenuItem extends CheckButton {
    private static final long serialVersionUID = 3773434900621498824L;
    private static final Insets PADDING = new Insets(5, 20, 5, 10);
    private final StringResourceId textId;
    private final Color backgroundColor;
    private final Color hoverColor;
    private final Color pressedColor;

    /**
     * Constructor for CheckMenuItem with text and checked flag.
     *
     * @param context window context
     * @param textRes button text resource
     * @param checked mark the button as checked
     */
    public CheckMenuItem(WindowContext context, StringResourceId textRes, boolean checked) {
        super(context, textRes, checked);
        this.textId = textRes;
        this.backgroundColor = context.getAppStyle().getWindowBackgroundColor();
        this.hoverColor = context.getAppStyle().getItemHoverColor();
        this.pressedColor = context.getAppStyle().getItemPressedColor();

        config();
    }

    /**
     * Constructor for CheckMenuItem with text, it sets the button as unchecked
     * by default.
     *
     * @param context window context
     * @param textRes button text resource
     */
    public CheckMenuItem(WindowContext context, StringResourceId textRes) {
        this(context, textRes, false);
    }

    /**
     * Constructor for CheckMenuItem with text and checked flag.
     *
     * @param context window context
     * @param text    button text
     * @param checked mark the button as checked
     */
    public CheckMenuItem(WindowContext context, String text, boolean checked) {
        super(context, text, checked);
        this.textId = null;
        this.backgroundColor = context.getAppStyle().getWindowBackgroundColor();
        this.hoverColor = context.getAppStyle().getItemHoverColor();
        this.pressedColor = context.getAppStyle().getItemPressedColor();

        config();
    }

    /**
     * Constructor for CheckMenuItem with text, it sets the button as unchecked
     * by default.
     *
     * @param context window context
     * @param text    button text
     */
    public CheckMenuItem(WindowContext context, String text) {
        this(context, text, false);
    }

    // Click listener is handled by Menu
    @Override
    public StringResourceId getTextId() {
        return textId;
    }

    private void config() {
        setPadding(PADDING);
        addMouseListener(new ItemMouseAdapter());
    }

    private final class ItemMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            setBackground(pressedColor);
            super.mouseClicked(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setBackground(backgroundColor);
            super.mouseReleased(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(hoverColor);
            super.mouseEntered(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(backgroundColor);
            super.mouseExited(e);
        }
    }
}
