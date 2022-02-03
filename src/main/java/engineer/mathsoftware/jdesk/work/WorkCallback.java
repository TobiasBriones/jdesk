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

/**
 * WorkCallback to notify the work results such as finished, failed or
 * cancelled. The methods of this callback are invoked on the EDT to process the
 * result on the GUI thread.
 *
 * @param <R> type of result return by {@link WorkRunnable#run()}.
 *
 * @author Tobias Briones
 */
public interface WorkCallback<R> {
    /**
     * Called when the work is completed and has successfully finished.
     *
     * @param result the result of the task
     */
    void workFinished(R result);

    /**
     * Called when the work is completed and did not finish successfully
     * because an exception was thrown on {@link WorkRunnable#run()}.
     *
     * @param exception exception thrown by the task
     */
    void workFailed(Exception exception);

    /**
     * Called when the work is completed and cancelled. Notice that the work
     * might be cancelled after being completed and will not be notified.
     */
    void workCancelled();
}
