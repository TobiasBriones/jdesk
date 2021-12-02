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

/**
 * {@link LoadingView} that implements progress, a floating number between 0 and
 * 1 inclusive.
 *
 * @author Tobias Briones
 */
public interface ProgressLoadingView extends LoadingView {
    /**
     * Sets the progress to display on the view.
     *
     * @param progress floating number from 0 to 1 to set the view's progress
     */
    void setProgress(float progress);
}
