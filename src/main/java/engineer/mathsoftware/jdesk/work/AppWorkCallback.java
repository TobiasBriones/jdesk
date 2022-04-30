// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.work;

import engineer.mathsoftware.jdesk.Window;
import engineer.mathsoftware.jdesk.ui.dialog.AppDialog;

/**
 * Callback that handles {@link #workFailed(Exception)} when the work fails by
 * showing a fail message, also overrides {@link #workCancelled()} and does
 * nothing on it.
 *
 * @param <R> type of the work result
 *
 * @author Tobias Briones
 * @see WorkCallback
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
        AppDialog.showMessage(
            window,
            exception.getMessage(),
            AppDialog.Type.FAIL
        );
    }

    @Override
    public void workCancelled() {}
}
