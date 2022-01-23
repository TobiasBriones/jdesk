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

package engineer.mathsoftware.jdesk.ui.view.validation.validator;

public final class TextLengthValidator implements Validator<String> {
    public static final int LENGTH_NOT_RESTRICTED = -1;
    private int minLength;
    private int maxLength;
    private String errorText;

    /**
     * Constructor for TextLengthValidator with minimum, maximum length, and
     * error text to display when needed.
     *
     * @param minLength minimum length
     * @param maxLength maximum length
     * @param errorText error text
     */
    public TextLengthValidator(int minLength, int maxLength, String errorText) {
        setLengths(minLength, maxLength);
        setErrorText(errorText);
    }

    /**
     * Constructor for TextLengthValidator with maximum length, minimum length
     * is set without any restrictions.
     *
     * @param maxLength maximum length
     */
    public TextLengthValidator(int maxLength, String errorText) {
        this(LENGTH_NOT_RESTRICTED, maxLength, errorText);
    }

    /**
     * Default constructor for TextLengthValidator. Sets no restrictions for the
     * lengths.
     */
    public TextLengthValidator() {
        this(LENGTH_NOT_RESTRICTED, LENGTH_NOT_RESTRICTED, "");
    }

    @Override
    public String getErrorText() {
        return errorText;
    }

    /**
     * Returns <code>true</code> if and only if the length of input is greater
     * than or equal to minimum length and less than or equal to maximum length.
     * If input is null then <code>false</code> is returned.
     *
     * @param input input to check
     *
     * @return <code>true</code> if and only if the length of input is correct
     */
    @Override
    public boolean validate(String input) {
        if (input == null) {
            return false;
        }
        if (minLength == LENGTH_NOT_RESTRICTED && maxLength == LENGTH_NOT_RESTRICTED) {
            return true;
        }
        if (maxLength == LENGTH_NOT_RESTRICTED) {
            return input.length() >= minLength;
        }
        return input.length() >= minLength && input.length() <= maxLength;
    }

    /**
     * Sets the error text to display when needed.
     *
     * @param errorText error text
     */
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    /**
     * Sets the minimum and maximum length to accept on this validator.
     *
     * @param minLength minimum length
     * @param maxLength maximum length
     *
     * @throws RuntimeException if one of the lengths is neither non negative
     *                          nor LENGTH_NOT_RESTRICTED, or minLength is is
     *                          greater than maxLength
     */
    public void setLengths(int minLength, int maxLength) {
        if (minLength < -1 || maxLength < -1) {
            throw new RuntimeException("Invalid lengths " + minLength + ", " + maxLength);
        }
        if (maxLength >= 0 && minLength > maxLength) {
            throw new RuntimeException("Invalid lengths " + minLength + ", " + maxLength);
        }
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
}
