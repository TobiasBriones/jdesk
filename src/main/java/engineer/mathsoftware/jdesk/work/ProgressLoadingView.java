// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.work;

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
