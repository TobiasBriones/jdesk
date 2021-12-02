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
import dev.tobiasbriones.jdesk.WindowContext;
import dev.tobiasbriones.jdesk.resources.AppStringResources;
import dev.tobiasbriones.jdesk.ui.view.Panel;
import dev.tobiasbriones.jdesk.ui.view.TextLabel;
import dev.tobiasbriones.jdesk.ui.view.loading.BarLoadingView;
import dev.tobiasbriones.jdesk.ui.work.AppWorkCallback;
import dev.tobiasbriones.jdesk.ui.work.AppWorker;
import dev.tobiasbriones.jdesk.ui.work.WorkCallback;
import dev.tobiasbriones.jdesk.ui.work.WorkRunnable;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * App dialog which executes background tasks.
 *
 * @param <R> type of the result of the work to execute
 *
 * @author Tobias Briones
 */
public final class TaskDialog<R> extends Dialog {
    private static final long serialVersionUID = -4612812543270615492L;

    /**
     * Callback to listen to the executing task results.
     *
     * @param <R> type of the work result
     *
     * @see AppWorkCallback
     */
    public abstract static class TaskDialogCallback<R> extends AppWorkCallback<R> {
        public TaskDialogCallback(Window window) {
            super(window);
        }

        /**
         * Invoked when the user asks for cancelling the work. Returns <code>true</code> if and only if the cancel
         * request is accepted by the application and so the work will be cancelled.
         *
         * @return <code>true</code> if and only if the cancel request is accepted by the application
         */
        public abstract boolean cancelRequest();
    }

    /**
     * Task dialog callback which implements {@link #cancelRequest()} to allow all user cancel requests.
     *
     * @param <R> type of the work result
     *
     * @see TaskDialogCallback
     */
    public abstract static class TaskDialogCallbackCancelEnabled<R> extends TaskDialogCallback<R> {
        public TaskDialogCallbackCancelEnabled(Window window) {
            super(window);
        }

        @Override
        public boolean cancelRequest() {
            return true;
        }
    }

    /**
     * Task dialog callback which implements {@link #cancelRequest()} to disallow all user cancel requests.
     *
     * @param <R> type of the work result
     *
     * @see TaskDialogCallback
     */
    public abstract static class TaskDialogCallbackCancelDisabled<R> extends TaskDialogCallback<R> {
        public TaskDialogCallbackCancelDisabled(Window window) {
            super(window);
        }

        @Override
        public boolean cancelRequest() {
            return false;
        }
    }

    private static final class DialogWorkCallback<R> implements WorkCallback<R> {
        private final TaskDialog<R> td;
        private final TaskDialogCallback<R> tdc;

        private DialogWorkCallback(TaskDialog<R> td, TaskDialogCallback<R> tdc) {
            this.td = td;
            this.tdc = tdc;
        }

        @Override
        public void workFinished(R result) {
            td.dispose();
            if (tdc != null) {
                tdc.workFinished(result);
            }
        }

        @Override
        public void workFailed(Exception exception) {
            td.dispose();
            if (tdc != null) {
                tdc.workFailed(exception);
            }
        }

        @Override
        public void workCancelled() {
            td.dispose();
            if (tdc != null) {
                tdc.workCancelled();
            }
        }
    }

    private final boolean isCancelable;
    private final TextLabel msgLabel;
    private final BarLoadingView barLoadingView;
    private final String msg;
    private int taskSize;
    private transient TaskDialogCallback<R> callback;
    private transient AppWorker<R, Void> work;

    /**
     * Constructor for TaskDialog with title, message and is cancelable flag.
     *
     * @param window       window to attach the dialog
     * @param title        dialog title
     * @param msg          dialog message
     * @param isCancelable make this work cancelable
     */
    public TaskDialog(Window window, String title, String msg, boolean isCancelable) {
        super(window);
        this.msgLabel = new TextLabel(window, msg);
        this.isCancelable = isCancelable;
        this.barLoadingView = new BarLoadingView(window);
        this.msg = msg + " ";
        this.callback = null;
        this.work = null;
        this.taskSize = -1;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancelTask();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        createUI(window, title, msg);
    }

    /**
     * Constructor for TaskDialog with title, message and is cancelable flag.
     *
     * @param window       window to attach the dialog
     * @param titleRes     dialog title resource
     * @param msgRes       dialog message resource
     * @param isCancelable make this work cancelable
     */
    public TaskDialog(Window window, int titleRes, int msgRes, boolean isCancelable) {
        this(window, window.getStringResources().get(titleRes), window.getStringResources().get(msgRes), isCancelable);
    }

    /**
     * Constructor for TaskDialog with title, message and it cancelable.
     *
     * @param window window to attach the dialog
     * @param title  dialog title
     * @param msg    dialog message
     */
    public TaskDialog(Window window, String title, String msg) {
        this(window, title, msg, true);
    }

    /**
     * Constructor for TaskDialog with title, message and it cancelable.
     *
     * @param window   window to attach the dialog
     * @param titleRes dialog title resource
     * @param msgRes   dialog message resource
     */
    public TaskDialog(Window window, int titleRes, int msgRes) {
        this(window, titleRes, msgRes, true);
    }

    /**
     * Returns the {@link BarLoadingView} displayed on the dialog while loading.
     *
     * @return the dialog loading bar view
     */
    public BarLoadingView getBarLoadingView() {
        return barLoadingView;
    }

    /**
     * Sets the callback to notify the results of the work to execute.
     *
     * @param callback task dialog callback
     *
     * @see WorkCallback
     */
    public void setCallback(TaskDialogCallback<R> callback) {
        this.callback = callback;
    }

    public void setTaskSize(int taskSize) {
        this.taskSize = taskSize;
    }

    public void setMessage(String message) {
        msgLabel.setText(message);
    }

    /**
     * Executes the runnable task on this dialog and shows the dialog.
     *
     * @param runnable runnable
     *
     * @throws RuntimeException if this method is called more than one time
     * @see AppWorker
     */
    public void execute(WorkRunnable<R> runnable) {
        if (work != null) {
            throw new RuntimeException("This dialog has already executed a work");
        }
        work = new AppWorker<>(barLoadingView, new DialogWorkCallback<>(this, callback));

        work.execute(runnable);
        setVisible(true);
    }

    public void update(int now) {
        msgLabel.setText(msg + now + "/" + taskSize);
        pack();
    }

    private void createUI(WindowContext context, String title, String msg) {
        final Panel panel = new Panel(context);
        final Panel topPanel = new Panel(context);
        final TextLabel titleLabel = new TextLabel(context, title);

        titleLabel.setFont(context.getAppStyle().getFont().deriveFont(Font.BOLD));
        titleLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        msgLabel.setForeground(context.getAppStyle().getSecondaryTextColor());
        msgLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        topPanel.setLayout(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.PAGE_START);
        topPanel.add(barLoadingView, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());
        panel.setPadding(0, 10, 5, 10);
        panel.add(topPanel, BorderLayout.PAGE_START);
        panel.add(msgLabel, BorderLayout.CENTER);
        if (isCancelable) {
            final String cancelStr = context.getStringResources().get(
                AppStringResources.CANCEL);
            final OptionButton cancelButton = new OptionButton(context, cancelStr);
            final ActionPanel actionPanel = new ActionPanel(context, cancelButton);
            final ActionListener l = e -> cancelTask();

            cancelButton.addActionListener(l);
            panel.add(actionPanel, BorderLayout.PAGE_END);
        }
        getContentPane().add(panel);

        pack();
        setLocationRelativeTo(null);
    }

    private void cancelTask() {
        if (work == null || work.isCancelled() || !isCancelable) {
            return;
        }

        if (callback != null) {
            if (callback.cancelRequest()) {
                work.cancel();
            }
        }
        else {
            work.cancel();
        }
    }
}
