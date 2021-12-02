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

package dev.tobiasbriones.jdesk.ui.work;

import dev.tobiasbriones.jdesk.Window;
import dev.tobiasbriones.jdesk.ui.dialog.AppDialog;

/**
 * Callback that handles {@link #workFailed(Exception)} when the work fails by
 * showing a fail message, also overrides {@link #workCancelled()} and does
 * nothing on it.
 *
 * @param <R> type of the work result
 *
 * @see WorkCallback
 *
 * @author Tobias Briones
 */
public abstract class AppWorkCallback<R> implements WorkCallback<R> {
    private final Window window;

    /**
     * Constructor for AppWorkCallback.
     *
     * @param window window
     */
    public AppWorkCallback(Window window) {
        this.window = window;
    }

    @Override
    public void workFailed(Exception exception) {
        AppDialog.showMessage(window, exception.getMessage(), AppDialog.Type.FAIL);
    }

    @Override
    public void workCancelled() {}
}
