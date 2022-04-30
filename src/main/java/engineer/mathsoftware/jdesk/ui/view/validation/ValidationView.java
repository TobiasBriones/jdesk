// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.view.validation;

import engineer.mathsoftware.jdesk.ui.style.TextStyle;

/**
 * Implemented by a view which validates user input data.
 *
 * @author Tobias Briones
 */
public interface ValidationView {
    /**
     * Returns {@code true} if and only if the input has been accepted.
     *
     * @return {@code true} if and only if the input has been accepted
     */
    boolean isInputAccepted();

    /**
     * Sets the validation text style.
     *
     * @param textStyle text style
     * @param textSize  text size
     */
    void setValidationTextStyle(TextStyle textStyle, int textSize);

    /**
     * Shows the validation text with the passed error text.
     *
     * @param error text for the validation label
     */
    void showValidationText(String error);

    /**
     * Hides the validation text.
     */
    void hideValidationText();
}
