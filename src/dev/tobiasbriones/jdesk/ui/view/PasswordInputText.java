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
import dev.tobiasbriones.jdesk.ui.style.AppStyle;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Input text view which manages user input passwords.
 */
@SuppressWarnings("unused")
public class PasswordInputText extends JPasswordField {
    private static final long serialVersionUID = 740143870155528177L;
    private static final int HEIGHT = 24;

    /**
     * Default constructor for PasswordInputText.
     *
     * @param context window context
     */
    public PasswordInputText(WindowContext context) {
        super();
        config(context.getAppStyle());
    }

    /**
     * Constructor for PasswordInputText.
     *
     * @param context window context
     * @param doc     document
     * @param text    text
     * @param columns columns
     */
    public PasswordInputText(WindowContext context, Document doc, String text, int columns) {
        super(doc, text, columns);
        config(context.getAppStyle());
    }

    /**
     * Constructor for PasswordInputText.
     *
     * @param context window context
     * @param columns columns
     */
    public PasswordInputText(WindowContext context, int columns) {
        super(columns);
        config(context.getAppStyle());
    }

    /**
     * Constructor for PasswordInputText.
     *
     * @param context window context
     * @param text    text
     * @param columns columns
     */
    public PasswordInputText(WindowContext context, String text, int columns) {
        super(text, columns);
        config(context.getAppStyle());
    }

    /**
     * Constructor for PasswordInputText.
     *
     * @param context window context
     * @param text    text
     */
    public PasswordInputText(WindowContext context, String text) {
        super(text);
        config(context.getAppStyle());
    }

    private void config(AppStyle appStyle) {
        setPreferredSize(new Dimension(0, HEIGHT));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, appStyle.getAccentColor()));
        setFont(appStyle.getFont());
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, appStyle.getAccentColor()));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, appStyle.getAccentColor()));
            }
        });
    }
}
