// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk;

import engineer.mathsoftware.jdesk.resources.StringResources;
import engineer.mathsoftware.jdesk.ui.style.AppStyle;

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
