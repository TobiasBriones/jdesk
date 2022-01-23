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

package engineer.mathsoftware.jdesk.ui.view;

import engineer.mathsoftware.jdesk.WindowContext;
import engineer.mathsoftware.jdesk.ui.style.AppStyle;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Input text view.
 *
 * @author Tobias Briones
 */
public class InputText extends JTextField {
    private static final long serialVersionUID = -6456630283142902458L;
    private static final int HEIGHT = 24;
    private final Color textColor;
    private final Color hintTextColor;
    private String hintText;
    private boolean isHintTextShowing;

    /**
     * Default constructor for InputText.
     *
     * @param context window context
     */
    public InputText(WindowContext context) {
        super();
        this.textColor = context.getAppStyle().getTextColor();
        this.hintTextColor = context.getAppStyle().getSecondaryTextColor();
        this.hintText = null;
        this.isHintTextShowing = false;

        config(context.getAppStyle());
    }

    /**
     * Constructor for InputText.
     *
     * @param context window context
     * @param doc     document
     * @param text    text
     * @param columns columns
     */
    public InputText(
        WindowContext context,
        Document doc,
        String text,
        int columns
    ) {
        super(doc, text, columns);
        this.textColor = context.getAppStyle().getTextColor();
        this.hintTextColor = context.getAppStyle().getSecondaryTextColor();
        this.hintText = null;
        this.isHintTextShowing = false;

        config(context.getAppStyle());
    }

    /**
     * Constructor for InputText.
     *
     * @param context window context
     * @param columns columns
     */
    public InputText(WindowContext context, int columns) {
        super(columns);
        this.textColor = context.getAppStyle().getTextColor();
        this.hintTextColor = context.getAppStyle().getSecondaryTextColor();
        this.hintText = null;
        this.isHintTextShowing = false;

        config(context.getAppStyle());
    }

    /**
     * Constructor for InputText.
     *
     * @param context window context
     * @param text    text
     * @param columns columns
     */
    public InputText(WindowContext context, String text, int columns) {
        super(text, columns);
        this.textColor = context.getAppStyle().getTextColor();
        this.hintTextColor = context.getAppStyle().getSecondaryTextColor();
        this.hintText = null;
        this.isHintTextShowing = false;

        config(context.getAppStyle());
    }

    /**
     * Constructor for InputText.
     *
     * @param context window context
     * @param text    text
     */
    public InputText(WindowContext context, String text) {
        super(text);
        this.textColor = context.getAppStyle().getTextColor();
        this.hintTextColor = context.getAppStyle().getSecondaryTextColor();
        this.hintText = null;
        this.isHintTextShowing = false;

        config(context.getAppStyle());
    }

    /**
     * Checks if {@link #getText()} applying <code>trim()</code> is not empty .
     *
     * @return <code>true</code> if and only if the trimmed input text is not
     * empty
     */
    public final boolean isSet() {
        return !getText().trim().isEmpty();
    }

    /**
     * Sets a hint text to show on the input text when this is empty and with
     * requestFocus lost.
     *
     * @param value hint text
     */
    public final void setHintText(String value) {
        this.hintText = value;

        checkForApplyingHintText();
    }

    private void config(AppStyle appStyle) {
        setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 0));
        setPreferredSize(new Dimension(0, HEIGHT));
        setBorder(BorderFactory.createMatteBorder(
            0,
            0,
            1,
            0,
            appStyle.getAccentColor()
        ));
        setFont(appStyle.getFont());
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(BorderFactory.createMatteBorder(
                    0,
                    0,
                    2,
                    0,
                    appStyle.getAccentColor()
                ));
                checkForRemovingHintText();
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorder(BorderFactory.createMatteBorder(
                    0,
                    0,
                    1,
                    0,
                    appStyle.getAccentColor()
                ));
                checkForApplyingHintText();
            }
        });
    }

    private void checkForApplyingHintText() {
        if (hintText != null && getText().length() == 0) {
            isHintTextShowing = true;

            setForeground(hintTextColor);
            setText(hintText);
        }
    }

    private void checkForRemovingHintText() {
        if (isHintTextShowing) {
            isHintTextShowing = false;

            setForeground(textColor);
            setText("");
        }
    }
}
