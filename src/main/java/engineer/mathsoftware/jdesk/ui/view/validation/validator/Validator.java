// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.view.validation.validator;

/**
 * Implemented by a validator to check user inputs.
 *
 * @author Tobias Briones
 */
public interface Validator<I> {
    /**
     * Returns the error text given to this validator.
     *
     * @return the error text
     */
    String getErrorText();

    /**
     * Returns {@code true} if and only if this input is accepted by the
     * validator.
     *
     * @param input input to check
     *
     * @return {@code true} if and only if this input is accepted
     */
    boolean validate(I input);
}
