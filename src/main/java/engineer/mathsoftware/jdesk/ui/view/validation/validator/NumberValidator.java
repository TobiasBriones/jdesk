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

public final class NumberValidator implements Validator<String> {
    public enum NumberType { INTEGER, DECIMAL, LONG }

    private final NumberType type;
    private final boolean unsigned;
    private final boolean zero;
    private String errorText;

    /**
     * Constructor for NumberValidator with type of number to accept and whether
     * is unsigned and zero.
     *
     * @param numberType number type to accept
     * @param unsigned   accept unsigned values
     * @param zero       accept the number zero
     * @param errorText  error text to display when needed
     */
    public NumberValidator(
        NumberType numberType,
        boolean unsigned,
        boolean zero,
        String errorText
    ) {
        this.type = numberType;
        this.unsigned = unsigned;
        this.zero = zero;
        setErrorText(errorText);
    }

    /**
     * Constructor for NumberValidator. It sets can be zero.
     *
     * @param numberType number type to accept
     * @param unsigned   accept unsigned values
     * @param errorText  error text to display when needed
     */
    public NumberValidator(
        NumberType numberType,
        boolean unsigned,
        String errorText
    ) {
        this(numberType, unsigned, true, errorText);
    }

    /**
     * Default constructor for NumberValidator. It sets signed and can be zero.
     *
     * @param numberType number type to accept
     * @param errorText  error text to display when needed
     */
    public NumberValidator(NumberType numberType, String errorText) {
        this(numberType, false, true, errorText);
    }

    /**
     * Default constructor for NumberValidator. It sets number type to INTEGER,
     * signed and can be zero.
     */
    public NumberValidator() {
        this(NumberType.INTEGER, false, true, "");
    }

    @Override
    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    /**
     * Returns {@code true} if and only if this input is a valid number checking
     * the parameters of this NumberValidator, note that if the input is out of
     * range of the specified number type it will return {@code false}.
     *
     * @param input input to check
     *
     * @return {@code true} if and only if this input is a valid number
     */
    @Override
    public boolean validate(String input) {
        try {
            final double number = getDouble(input);

            switch (type) {
                case INTEGER:
                    if (Integer.parseInt(input) != number) {
                        return false;
                    }
                    break;

                case LONG:
                    if (Long.parseLong(input) != number) {
                        return false;
                    }
                    break;
            }
            if (unsigned && number < 0) {
                return false;
            }
            if (!zero && number == 0) {
                return false;
            }
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static double getDouble(String input) {
        return Double.parseDouble(input);
    }
}
