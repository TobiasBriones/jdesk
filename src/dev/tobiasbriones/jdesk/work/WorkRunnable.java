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

package dev.tobiasbriones.jdesk.work;

/**
 * Requested to perform a background task on a new thread by invoking
 * {@link WorkRunnable#run()}.
 *
 * @param <R> type of result to return after completing the background task
 *
 * @author Tobias Briones
 */
public interface WorkRunnable<R> {
    /**
     * Called when the corresponding background task is going to be performed on a new thread.
     *
     * @return the result of the task
     *
     * @throws Exception if something wrong happened when performing the task
     */
    R run() throws Exception;
}
