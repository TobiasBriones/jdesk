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

package io.github.tobiasbriones.jdesk.models;

/**
 * Thrown when a model is requested to check the fields and those are
 * not accepted.
 */
@SuppressWarnings("unused")
public final class FieldValidationException extends Exception {
    private static final long serialVersionUID = -7950353925734752843L;

    public FieldValidationException(String msg) {
        super(msg);
    }
}
