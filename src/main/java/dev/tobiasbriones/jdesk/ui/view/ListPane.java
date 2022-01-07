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
import java.util.Vector;

/**
 * List to display items.
 *
 * @param <E> type of items used by this list pane
 *
 * @author Tobias Briones
 */
public class ListPane<E> extends JList<E> {
    private static final long serialVersionUID = -7494601406358051974L;

    /**
     * Default cell render for {@link ListPane}
     */
    public static final class DefaultCellRender extends Panel implements ListCellRenderer<Object> {
        private static final long serialVersionUID = -5394395866485914727L;
        private static final Insets DEFAULT_PADDING = new Insets(0, 5, 0, 5);
        private final Color backgroundColor;
        private final Color pressedColor;
        private final TextLabel label;

        public DefaultCellRender(
            WindowContext context,
            Color backgroundColor,
            Color pressedColor
        ) {
            super(context);
            this.backgroundColor = backgroundColor;
            this.pressedColor = pressedColor;
            this.label = new TextLabel(context);

            setLayout(new FlowLayout(FlowLayout.LEADING));
            setPadding(DEFAULT_PADDING);
            add(label);
        }

        public DefaultCellRender(WindowContext context) {
            this(
                context,
                context.getAppStyle().getWindowBackgroundColor(),
                context.getAppStyle().getItemPressedColor()
            );
        }

        @Override
        public Component getListCellRendererComponent(
            JList<?> list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
        ) {
            label.setText(value.toString());
            if (isSelected) {
                setBackground(pressedColor);
            }
            else {
                setBackground(backgroundColor);
            }
            return this;
        }
    }

    /**
     * Default constructor for ListPane.
     *
     * @param context window context
     */
    public ListPane(WindowContext context) {
        super();
        config(context);
    }

    /**
     * Constructor for ListPane.
     *
     * @param context window context
     * @param items   items
     */
    public ListPane(WindowContext context, E[] items) {
        super(items);
        config(context);
    }

    /**
     * Constructor for ListPane.
     *
     * @param context window context
     * @param items   items
     */
    public ListPane(WindowContext context, ListModel<E> items) {
        super(items);
        config(context);
    }

    /**
     * Constructor for ListPane.
     *
     * @param context window context
     * @param items   items
     */
    public ListPane(WindowContext context, Vector<? extends E> items) {
        super(items);
        config(context);
    }

    private void config(WindowContext context) {
        setBackground(context.getAppStyle().getWindowBackgroundColor());
        setCellRenderer(new DefaultCellRender(context));
    }
}
