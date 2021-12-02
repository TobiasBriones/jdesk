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

package dev.tobiasbriones.jdesk;

import dev.tobiasbriones.jdesk.resources.StringResources;
import dev.tobiasbriones.jdesk.ui.style.AppStyle;

/**
 * Context provided from the application.
 *
 * @author Tobias Briones
 */
public interface WindowContext {
    /**
     * Returns the string resources for this window context.
     *
     * @return string resources for this context
     *
     * @see StringResources
     */
    StringResources getStringResources();

    /**
     * Returns the app style for this window context.
     *
     * @return app style for this context
     *
     * @see AppStyle
     */
    AppStyle getAppStyle();
}
