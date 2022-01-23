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

package engineer.mathsoftware.jdesk.work;

import java.util.List;

/**
 * It executes a work on background with {@link AppWorker} and updates the
 * {@link ProgressLoadingView} when the update is received.
 *
 * @author Tobias Briones
 */
public final class ProgressWorkManager<R> {
    private final AppWorker<R, Progress> work;
    private final Progress progress;
    /**
     * Constructor for ProgressWorkManager with the size of the progress to be
     * used to update the {@link ProgressLoadingView}.
     *
     * @param loadingView  loading view
     * @param callback     work callback
     * @param progressSize size of items to be processed
     */
    public ProgressWorkManager(
        ProgressLoadingView loadingView,
        WorkCallback<R> callback,
        int progressSize
    ) {
        this.work = new AppWorker<>(
            loadingView,
            callback,
            new Listener(loadingView)
        );
        this.progress = new Progress(progressSize);
    }

    /**
     * Constructor for ProgressWorkManager with no size specified. Call {@link
     * #getProgress()} to set the progress.
     *
     * @param loadingView loading view
     * @param callback    work callback
     */
    public ProgressWorkManager(
        ProgressLoadingView loadingView,
        WorkCallback<R> callback
    ) {
        this(loadingView, callback, -1);
    }

    /**
     * Returns the {@link AppWorker} used to perform the background task.
     *
     * @return app worker
     */
    public AppWorker<R, Progress> getWork() {
        return work;
    }

    /**
     * Returns the progress to set updates before calling {@link #update()}.
     *
     * @return the progress
     */
    public Progress getProgress() {
        return progress;
    }

    /**
     * It executes the work.
     *
     * @param runnable work runnable
     */
    public void execute(WorkRunnable<R> runnable) {
        work.execute(runnable);
    }

    /**
     * It is called from {@link WorkRunnable#run()} to update the progress of
     * the {@link ProgressLoadingView}.
     */
    public void update() {
        work.update(progress);
    }

    private static final class Listener implements WorkRunnableListener<Progress> {
        private final ProgressLoadingView progressLoadingView;

        private Listener(ProgressLoadingView progressLoadingView) {
            this.progressLoadingView = progressLoadingView;
        }

        @Override
        public void update(List<Progress> updates) {
            final Progress progress = updates.get(updates.size() - 1);

            progressLoadingView.setProgress(progress.getProgress());
        }
    }
}
