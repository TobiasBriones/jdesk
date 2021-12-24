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
import dev.tobiasbriones.jdesk.ui.style.TextStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Text label view.
 *
 * @author Tobias Briones
 */
public class TextLabel extends JLabel {
    private static final long serialVersionUID = 1102069398706966479L;

    /**
     * Returns the string within the given bounds. If <code>maxLength</code> is
     * less than or equal to 3 the same text is returned, if the text length is
     * less than or equal to
     * <code>maxLength</code> then is returned as well. If the text length is
     * greater than
     * <code>maxLength</code> then the first <code>maxLength</code> characters
     * of the text plus the
     * ellipsis dots are returned.
     *
     * @param text      text
     * @param maxLength maximum length
     *
     * @return the string within the given bounds and with ellipsis if needed
     */
    public static String getEllipsisText(String text, int maxLength) {
        if (maxLength <= 3) {
            return text;
        }
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength) + "...";
    }

    /**
     * Default constructor for TextLabel.
     *
     * @param context window context
     */
    public TextLabel(WindowContext context) {
        super();
        config(context.getAppStyle());
    }

    /**
     * Constructor for TextLabel.
     *
     * @param context window context
     * @param text    label text
     */
    public TextLabel(WindowContext context, String text) {
        super(text);
        config(context.getAppStyle());
    }

    /**
     * Constructor for TextLabel.
     *
     * @param context window context
     * @param textRes label text resource
     */
    public TextLabel(WindowContext context, int textRes) {
        super(context.getStringResources().get(textRes));
        config(context.getAppStyle());
    }

    /**
     * Constructor for TextLabel.
     *
     * @param context window context
     * @param icon    label icon
     */
    public TextLabel(WindowContext context, Icon icon) {
        super(icon);
        config(context.getAppStyle());
    }

    /**
     * Constructor for TextLabel.
     *
     * @param context             window context
     * @param icon                label icon
     * @param horizontalAlignment horizontal text alignment
     */
    public TextLabel(
        WindowContext context,
        Icon icon,
        int horizontalAlignment
    ) {
        super(icon, horizontalAlignment);
        config(context.getAppStyle());
    }

    /**
     * Constructor for TextLabel.
     *
     * @param context             window context
     * @param text                label text
     * @param icon                label icon
     * @param horizontalAlignment horizontal text alignment
     */
    public TextLabel(
        WindowContext context,
        String text,
        Icon icon,
        int horizontalAlignment
    ) {
        super(text, icon, horizontalAlignment);
        config(context.getAppStyle());
    }

    /**
     * Constructor for TextLabel.
     *
     * @param context             window context
     * @param textRes             label text resource
     * @param icon                label icon
     * @param horizontalAlignment horizontal text alignment
     */
    public TextLabel(
        WindowContext context,
        int textRes,
        Icon icon,
        int horizontalAlignment
    ) {
        super(
            context.getStringResources().get(textRes),
            icon,
            horizontalAlignment
        );
        config(context.getAppStyle());
    }

    /**
     * Constructor for TextLabel.
     *
     * @param context             window context
     * @param text                label text
     * @param horizontalAlignment horizontal text alignment
     */
    public TextLabel(
        WindowContext context,
        String text,
        int horizontalAlignment
    ) {
        super(text, horizontalAlignment);
        config(context.getAppStyle());
    }

    /**
     * Constructor for TextLabel.
     *
     * @param context             window context
     * @param textRes             label text resource
     * @param horizontalAlignment horizontal text alignment
     */
    public TextLabel(
        WindowContext context,
        int textRes,
        int horizontalAlignment
    ) {
        super(context.getStringResources().get(textRes), horizontalAlignment);
        config(context.getAppStyle());
    }

    /**
     * Sets the style for the label's text.
     *
     * @param textStyle style
     */
    public void setTextStyle(TextStyle textStyle) {
        setFont(getFont(textStyle, getFont().getSize()));
    }

    /**
     * Sets the style and size for the label's text.
     *
     * @param textStyle style
     * @param textSize  font size
     */
    public void setTextStyle(TextStyle textStyle, int textSize) {
        setFont(getFont(textStyle, textSize));
    }

    private Font getFont(TextStyle textStyle, int size) {
        switch (textStyle) {
            case NORMAL:
                return getFont().deriveFont(Font.PLAIN, (float) size);

            case BOLD:
                return getFont().deriveFont(Font.BOLD, (float) size);

            case ITALIC:
                return getFont().deriveFont(Font.ITALIC, (float) size);
        }
        return getFont().deriveFont(Font.PLAIN, (float) size);
    }

    private void config(AppStyle appStyle) {
        setFont(appStyle.getFont());
    }
}
