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

package dev.tobiasbriones.jdesk.ui.style;

import java.awt.*;

/**
 * App style used to stylize the app UI components such as windows, dialogs and
 * views.
 *
 * @author Tobias Briones
 */
public interface AppStyle {
    /**
     * Returns the app font.
     *
     * @return the app font
     */
    Font getFont();

    /**
     * Returns the top color of the UI.
     *
     * @return the top color
     */
    Color getTopBackgroundColor();

    /**
     * Returns the window background color.
     *
     * @return the window background color
     */
    Color getWindowBackgroundColor();

    /**
     * Returns the dialog background color.
     *
     * @return the dialog background color
     */
    Color getDialogBackgroundColor();

    /**
     * Returns the generic item hover color.
     *
     * @return the item hover color
     */
    Color getItemHoverColor();

    /**
     * Returns the generic item pressed color.
     *
     * @return the item pressed color
     */
    Color getItemPressedColor();

    /**
     * Returns the generic item focused color.
     *
     * @return the item focused color
     */
    Color getItemFocusedColor();

    /**
     * Returns the general button color.
     *
     * @return the button color
     */
    Color getButtonBackgroundColor();

    /**
     * Returns the accent color used for remarkable parts and elements of the UI.
     *
     * @return the accent color
     */
    Color getAccentColor();

    /**
     * Returns the disabled color.
     *
     * @return the disabled color
     */
    Color getDisabledColor();

    /**
     * Returns the primary text color.
     *
     * @return the text color
     */
    Color getTextColor();

    /**
     * Returns the secondary text color.
     *
     * @return the secondary text color
     */
    Color getSecondaryTextColor();

    /**
     * Returns the error text color.
     *
     * @return the error text color
     */
    Color getErrorTextColor();
}
