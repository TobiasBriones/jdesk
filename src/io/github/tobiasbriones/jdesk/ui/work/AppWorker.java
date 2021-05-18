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

package io.github.tobiasbriones.jdesk.ui.work;

import javax.swing.*;
import java.util.List;

/**
 * Worker that executes and manages background tasks.
 *
 * @param <R> type of the task result
 * @param <U> type of object passed to publish updates when running the task
 *
 * @see SwingWorker
 */
@SuppressWarnings("unused")
public final class AppWorker<R, U> {
    private static final class Worker<R, U> extends SwingWorker<Void, U> {
        private final LoadingView loadingView;
        private final WorkRunnable<R> runnable;
        private final WorkRunnableListener<U> l;
        private final WorkCallback<R> callback;
        private R result;
        private Exception exception;
        private boolean isCompleted;

        private Worker(
            LoadingView loadingView,
            WorkRunnable<R> runnable,
            WorkRunnableListener<U> l,
            WorkCallback<R> callback
        ) {
            this.loadingView = loadingView;
            this.runnable = runnable;
            this.l = l;
            this.callback = callback;
            this.exception = null;
            this.isCompleted = false;
        }

        private boolean isCompleted() {
            return isCompleted;
        }

        @Override
        protected Void doInBackground() {
            try {
                result = runnable.run();
            }
            catch (Exception e) {
                exception = e;
            }
            return null;
        }

        @Override
        protected void process(List<U> chunks) {
            l.update(chunks);
        }

        @Override
        protected void done() {
            isCompleted = true;

            loadingView.end();
            if (callback != null) {
                if (isCancelled()) {
                    callback.workCancelled();
                }
                else if (exception != null) {
                    callback.workFailed(exception);
                }
                else {
                    callback.workFinished(result);
                }
            }
        }

        private void exec() {
            loadingView.start();
            execute();
        }

        private void update(U update) {
            publish(update);
        }
    }

    private final LoadingView loadingView;
    private final WorkCallback<R> callback;
    private final WorkRunnableListener<U> l;
    private Worker<R, U> worker;

    /**
     * Constructs a new worker to perform a new task on background. It sets the passed {@link WorkRunnableListener} to
     * implement progress updates to notify the UI from the background performing task of {@link Runnable#run()}.
     *
     * @param loadingView loading view
     * @param l           app runnable listener
     * @param callback    callback
     *
     * @see AppWorker#update(Object)
     */
    @SuppressWarnings("WeakerAccess")
    public AppWorker(LoadingView loadingView, WorkCallback<R> callback, WorkRunnableListener<U> l) {
        this.loadingView = loadingView;
        this.callback = callback;
        this.l = l;
        this.worker = null;
    }

    /**
     * Constructs a new worker to perform a new task on background. This constructor will not allow updates
     * notifications from {@link WorkRunnable#run()}.
     *
     * @param loadingView loading view
     * @param callback    callback
     */
    @SuppressWarnings("WeakerAccess")
    public AppWorker(LoadingView loadingView, WorkCallback<R> callback) {
        this(loadingView, callback, null);
    }

    /**
     * Constructs a new worker to perform a new task on background. This constructor will not allow updates
     * notifications from {@link WorkRunnable#run()} nor {@link WorkCallback} notifications.
     *
     * @param loadingView loading view
     */
    @SuppressWarnings("WeakerAccess")
    public AppWorker(LoadingView loadingView) {
        this(loadingView, null, null);
    }

    /**
     * Returns <code>true</code> if this task was cancelled before it completed normally.
     * If {@link AppWorker#execute(WorkRunnable)} has not been called yet it returns false.
     *
     * @return <code>true</code> if this task was cancelled before it completed
     */
    @SuppressWarnings("WeakerAccess")
    public boolean isCancelled() {
        if (worker == null) {
            return false;
        }
        return worker.isCancelled();
    }

    /**
     * Returns <code>true</code> if this work has been completed, that is immediately after finishing
     * the background task and before calling the callback.
     * If {@link AppWorker#execute(WorkRunnable)} has not been called yet it returns false.
     *
     * @return <code>true</code> if and only if this work has been completed
     */
    @SuppressWarnings("WeakerAccess")
    public boolean isCompleted() {
        if (worker == null) {
            return false;
        }
        return worker.isCompleted();
    }

    /**
     * Schedules the SwingWorker for execution on a worker thread. There are a number of worker threads available.
     * In the event all worker threads are busy handling other SwingWorkers this SwingWorker is placed
     * in a waiting queue. Note: SwingWorker is only designed to be executed once. Executing a SwingWorker
     * more than once will not result in invoking the doInBackground method twice.
     *
     * @param runnable runnable
     *
     * @throws RuntimeException if it's requested to execute this work more than one time, it can only be executed one
     *                          work by each {@link AppWorker} instance
     */
    @SuppressWarnings("WeakerAccess")
    public void execute(WorkRunnable<R> runnable) {
        if (worker != null) {
            throw new RuntimeException("This worker was already executed");
        }
        this.worker = new Worker<>(loadingView, runnable, l, callback);

        worker.exec();
    }

    /**
     * Attempts to cancel execution of this task. This attempt will fail if the task has already completed,
     * has already been cancelled, or could not be cancelled for some other reason. If successful, and this
     * task has not started when cancel is called, this task should never run. If the task has already started,
     * then the thread executing this task should be interrupted in an attempt to stop the task.
     * If {@link AppWorker#execute(WorkRunnable)} has not been called yet it returns false.
     *
     * @return false if the task could not be cancelled, typically because it has already completed normally;
     * true otherwise
     */
    @SuppressWarnings("WeakerAccess")
    public boolean cancel() {
        if (worker == null) {
            return false;
        }
        return worker.cancel(true);
    }

    /**
     * Call this methods from {@link WorkRunnable#run()} to post updates to the UI.
     *
     * @param update update
     *
     * @throws RuntimeException if {@link AppWorker#execute(WorkRunnable)} has not been called yet or if this instance
     *                          does not allow updates
     */
    @SuppressWarnings("WeakerAccess")
    public void update(U update) {
        if (worker == null) {
            throw new RuntimeException("This work has not been executed yet");
        }
        if (l == null) {
            throw new RuntimeException("This work does not allow updates");
        }
        worker.update(update);
    }
}
