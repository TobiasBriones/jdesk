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
 * Interface to control a view showing the process taken by a work being
 * executed on background.
 *
 * @author Tobias Briones
 */
public interface LoadingView {
    /**
     * Returns {@code true} if and only if the view is in running mode.
     *
     * @return {@code true} if and only if the view is in running mode.
     */
    boolean isRunning();

    /**
     * Sets the view to running mode.
     */
    void start();

    /**
     * Finishes running mode.
     */
    void end();
}
