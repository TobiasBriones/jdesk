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

public final class Progress {
    private int size;
    private int i;

    /**
     * Constructor for Progress with the size of the elements to process.
     *
     * @param size size
     */
    public Progress(int size) {
        this.size = size;
        this.i = 0;
    }

    /**
     * Default constructor for Progress.
     */
    public Progress() {
        this(-1);
    }

    /**
     * Returns the progress of the current process. If the size is not set it
     * will return 0 or if the the processed items is greater than the size of
     * the process it will return 1.
     *
     * @return the progress, float form 0 to 1 inclusive
     */
    public float getProgress() {
        if (size == -1) {
            return 0;
        }
        final float progress = (float) i / size;
        return (progress <= 1) ? progress : 1;
    }

    /**
     * Returns the progress of the current process. If the size is not set it
     * will return 0 or if the the processed items is greater than the size of
     * the process it will return 100.
     *
     * @return the progress in percentage, float form 0 to 100 inclusive
     */
    public float getProgressPercentage() {
        return getProgress() * 100;
    }

    /**
     * Sets the number of elements to be processed.
     *
     * @param size size, integer greater than 0, otherwise will be set to -1
     */
    public void setSize(int size) {
        this.size = size;
        this.i = 0;

        if (size < 1) {
            this.size = -1;
        }
    }

    /**
     * Counts up the number of processed items.
     *
     * @param items new processed items
     */
    public void add(int items) {
        i += items;
    }

    /**
     * Counts up one processed item.
     */
    public void add() {
        add(1);
    }
}
