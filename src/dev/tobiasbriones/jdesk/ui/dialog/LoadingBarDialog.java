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

package dev.tobiasbriones.jdesk.ui.dialog;

import dev.tobiasbriones.jdesk.Window;
import dev.tobiasbriones.jdesk.ui.view.loading.BarLoadingView;
import dev.tobiasbriones.jdesk.ui.work.AppWorker;
import dev.tobiasbriones.jdesk.ui.work.WorkCallback;
import dev.tobiasbriones.jdesk.ui.work.WorkRunnable;

import java.awt.*;

/**
 * Dialog implementing a loading bar to display the execution of tasks.
 *
 * @author Tobias Briones
 */
public class LoadingBarDialog extends Dialog {
    private static final long serialVersionUID = 1165045874001773979L;
    private final DialogPanel panel;
    private final BarLoadingView lbv;

    /**
     * Creates a LoadingBarDialog, to set the dialog content call {@link #setLBDView(Panel)}.
     *
     * @param window window to attach the dialog
     */
    public LoadingBarDialog(Window window) {
        super(window);
        this.panel = new DialogPanel(window);
        this.lbv = new BarLoadingView(window);

        panel.setLayout(new BorderLayout());
        panel.add(lbv, BorderLayout.PAGE_START);
    }

    /**
     * Sets the content of the dialog.
     *
     * @param panelView dialog root panel
     */
    public final void setLBDView(Panel panelView) {
        panel.add(panelView, BorderLayout.CENTER);
        setView(panel, new Insets(0, 0, 0, 0));
    }

    /**
     * Executes the requested task on the dialog.
     *
     * @param callback work callback
     * @param runnable work runnable
     * @param <R>      type of the work result
     *
     * @return the app worker which executes this task
     *
     * @see AppWorker
     */
    public final <R> AppWorker<R, Void> execute(WorkCallback<R> callback, WorkRunnable<R> runnable) {
        final AppWorker<R, Void> work = new AppWorker<>(lbv, callback);

        work.execute(runnable);
        return work;
    }
}
