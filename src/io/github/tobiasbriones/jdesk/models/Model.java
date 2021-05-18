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
 * Model base to implement any application model which will have to check if
 * is editable when is requested to be set.
 */
@SuppressWarnings("unused")
public class Model {
    private final boolean isEditable;

    /**
     * Constructs a new model.
     *
     * @param isEditable flag to set weather this model can be edited. The subclass will have to check
     *                   {@link #isEditable()} before setting a field of the model
     */
    public Model(boolean isEditable) {
        this.isEditable = isEditable;
    }

    /**
     * Returns <code>true</code> if and only if this model is editable.
     *
     * @return <code>true</code> if and only if this model is editable
     */
    @SuppressWarnings("WeakerAccess")
    public final boolean isEditable() {
        return isEditable;
    }
}
