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
 * Implemented by the UI to update the progress of the background task running
 * on {@link AppWorker}.
 *
 * @author Tobias Briones
 */
public interface WorkRunnableListener<U> {
    /**
     * Called when an update was post from {@link WorkRunnable#run()} to update
     * the progress on the UI.
     *
     * @param updates list of recently updates
     */
    void update(List<U> updates);
}
