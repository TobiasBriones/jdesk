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

package engineer.mathsoftware.jdesk.ui.style;

import java.awt.*;

/**
 * Class to implement the app style.
 *
 * @author Tobias Briones
 * @see AppStyle
 */
public class Style implements AppStyle {
    public static final int TOP_BACKGROUND = 0;
    public static final int WINDOW_BACKGROUND = 1;
    public static final int DIALOG_BACKGROUND = 2;
    public static final int ITEM_HOVER = 3;
    public static final int ITEM_PRESSED = 4;
    public static final int ITEM_FOCUSED = 5;
    public static final int BUTTON_BACKGROUND = 6;
    public static final int ACCENT = 7;
    public static final int DISABLED = 8;
    public static final int TEXT = 9;
    public static final int SECONDARY_TEXT = 10;
    public static final int ERROR_TEXT = 11;

    /**
     * Returns the text wrapped into HTML applying text style, it does not take
     * into account the color alpha value, the text will be inserted inside the
     * body tag.
     *
     * @param appStyle application style
     * @param htmlText HTML text
     *
     * @return the HTML string which body contains <code>htmlText</code> and
     * application text style
     */
    public static String wrapTextInHTML(AppStyle appStyle, String htmlText) {
        return wrapTextInHTML(
            appStyle.getFont(),
            appStyle.getTextColor(),
            htmlText
        );
    }

    /**
     * Returns the text wrapped into HTML applying dialog message style, it does
     * not take into account the color alpha value, the text will be inserted
     * inside the body tag.
     *
     * @param appStyle application style
     * @param htmlText HTML text
     *
     * @return the HTML string which body contains {@code htmlText} and
     * application text style
     */
    public static String wrapDialogTextInHTML(
        AppStyle appStyle,
        String htmlText
    ) {
        return wrapTextInHTML(
            appStyle.getFont(),
            appStyle.getSecondaryTextColor(),
            htmlText
        );
    }

    private final Font font;
    private final Color topBackgroundColor;
    private final Color windowBackgroundColor;
    private final Color dialogBackgroundColor;
    private final Color itemHoverColor;
    private final Color itemPressedColor;
    private final Color itemFocusedColor;
    private final Color buttonBackgroundColor;
    private final Color accentColor;
    private final Color disabledColor;
    private final Color textColor;
    private final Color secondaryTextColor;
    private final Color errorTextColor;

    /**
     * Constructor for instantiating an AppStyle object.
     *
     * @param font   font
     * @param colors array of colors representing the list of AppStyle's colors
     */
    public Style(Font font, Color[] colors) {
        if (colors.length != 12) {
            throw new IllegalArgumentException("There must be 12 colors");
        }
        this.font = font;
        this.topBackgroundColor = colors[TOP_BACKGROUND];
        this.windowBackgroundColor = colors[WINDOW_BACKGROUND];
        this.dialogBackgroundColor = colors[DIALOG_BACKGROUND];
        this.itemHoverColor = colors[ITEM_HOVER];
        this.itemPressedColor = colors[ITEM_PRESSED];
        this.itemFocusedColor = colors[ITEM_FOCUSED];
        this.accentColor = colors[ACCENT];
        this.disabledColor = colors[DISABLED];
        this.buttonBackgroundColor = colors[BUTTON_BACKGROUND];
        this.textColor = colors[TEXT];
        this.secondaryTextColor = colors[SECONDARY_TEXT];
        this.errorTextColor = colors[ERROR_TEXT];
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public Color getTopBackgroundColor() {
        return topBackgroundColor;
    }

    @Override
    public Color getWindowBackgroundColor() {
        return windowBackgroundColor;
    }

    @Override
    public Color getDialogBackgroundColor() {
        return dialogBackgroundColor;
    }

    @Override
    public Color getItemHoverColor() {
        return itemHoverColor;
    }

    @Override
    public Color getItemPressedColor() {
        return itemPressedColor;
    }

    @Override
    public Color getItemFocusedColor() {
        return itemFocusedColor;
    }

    @Override
    public Color getButtonBackgroundColor() {
        return buttonBackgroundColor;
    }

    @Override
    public Color getAccentColor() {
        return accentColor;
    }

    @Override
    public Color getDisabledColor() {
        return disabledColor;
    }

    @Override
    public Color getTextColor() {
        return textColor;
    }

    @Override
    public Color getSecondaryTextColor() {
        return secondaryTextColor;
    }

    @Override
    public Color getErrorTextColor() {
        return errorTextColor;
    }

    private static String wrapTextInHTML(
        Font font,
        Color color,
        String htmlText
    ) {
        final String fontFamily = font.getFamily();
        final int fontSize = font.getSize();
        final int r = color.getRed();
        final int g = color.getGreen();
        final int b = color.getBlue();
        final String textColor = "rgb(" + r + "," + g + "," + b + ")";
        final String css =
            "font-family:" + fontFamily + ";font-size:" + fontSize + ";" +
            "color:" + textColor + ";";

        return "<html><body style='" + css + "'>" + htmlText + "</body></html>";
    }
}
