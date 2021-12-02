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

import dev.tobiasbriones.jdesk.ui.view.ClickListener;
import dev.tobiasbriones.jdesk.ui.view.TextIdClickView;
import dev.tobiasbriones.jdesk.ui.view.TextLabel;
import dev.tobiasbriones.jdesk.WindowContext;
import dev.tobiasbriones.jdesk.ui.style.AppStyle;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Window menu that contains the {@link MenuItem} items to display a menu on the {@link MenuBar}.
 */
@SuppressWarnings("unused")
public class Menu extends TextLabel {
    private static final long serialVersionUID = 3425034364105078041L;
    private static final Border PADDING = new EmptyBorder(2, 8, 2, 8);

    private static final class MenuItemClickListener extends MouseAdapter {
        private final Menu menu;

        private MenuItemClickListener(Menu menu) {
            this.menu = menu;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            final TextIdClickView textIdClickView = (TextIdClickView) e.getSource();
            final WindowContext context = menu.getContext();

            if (menu.getPopup().isShowing()) {
                menu.getPopup().setVisible(false);
            }
            if (textIdClickView.getTextId() != -1 && context instanceof ClickListener) {
                ((ClickListener) context).onClick(textIdClickView, textIdClickView.getTextId());
            }
            super.mouseClicked(e);
        }
    }

    private final Color backgroundColor;
    private final Color hoverColor;
    private final Color pressedColor;
    private final PopupMenu popup;
    private final transient WindowContext context;
    private final transient MouseListener ml;
    private MenuBar menuBar;

    /**
     * Constructor for Menu with the menu name.
     *
     * @param context context context
     * @param name    menu name
     */
    @SuppressWarnings("WeakerAccess")
    public Menu(WindowContext context, String name) {
        super(context, name);
        this.context = context;
        this.backgroundColor = context.getAppStyle().getTopBackgroundColor();
        this.hoverColor = context.getAppStyle().getItemHoverColor();
        this.pressedColor = context.getAppStyle().getItemPressedColor();
        this.popup = new PopupMenu(context);
        this.ml = new MenuItemClickListener(this);
        this.menuBar = null;

        config(context.getAppStyle());
    }

    /**
     * Constructor for Menu with the menu name.
     *
     * @param context context context
     * @param nameRes menu name resource
     */
    @SuppressWarnings("WeakerAccess")
    public Menu(WindowContext context, int nameRes) {
        this(context, context.getStringResources().get(nameRes));
    }

    private WindowContext getContext() {
        return context;
    }

    private PopupMenu getPopup() {
        return popup;
    }

    void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    /**
     * Adds the menu item to this menu.
     *
     * @param item menu item
     */
    public void addItem(MenuItem item) {
        popup.addItem(item);
        item.addMouseListener(ml);
    }

    /**
     * Adds the check menu item to this menu.
     *
     * @param item check menu item
     */
    public void addItem(CheckMenuItem item) {
        popup.addItem(item);
        item.addMouseListener(ml);
    }

    /**
     * Adds a separator at this point of the menu popup.
     */
    public void addSeparator() {
        popup.addSeparator();
    }

    private void config(AppStyle appStyle) {
        setOpaque(true);
        setBorder(PADDING);
        setBackground(appStyle.getTopBackgroundColor());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressedColor);
                if (!popup.isShowing()) {
                    showPopup();
                }
                else {
                    popup.setVisible(false);
                }
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                if (menuBar != null && menuBar.getMenuShowing() != null && menuBar.getMenuShowing() != Menu.this) {
                    menuBar.getMenuShowing().popup.setVisible(false);
                    showPopup();
                }
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!popup.isShowing()) {
                    setBackground(backgroundColor);
                }
                super.mouseExited(e);
            }
        });
        popup.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                if (menuBar != null) {
                    menuBar.setMenuShowing(null);
                }
                setBackground(backgroundColor);
            }

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });
    }

    private void showPopup() {
        if (menuBar != null) {
            menuBar.setMenuShowing(this);
        }
        popup.show(this, 0, getHeight());
    }
}
