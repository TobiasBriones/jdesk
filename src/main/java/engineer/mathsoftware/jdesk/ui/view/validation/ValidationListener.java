// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.view.validation;

/**
 * Listener sending validation request from an input view.
 *
 * @author Tobias Briones
 */
public interface ValidationListener {
    /**
     * Called when the input view has changed and is requested to validate the
     * new user input.
     *
     * @param validationView view which validates user input
     */
    void onValidate(ValidationView validationView);
}
