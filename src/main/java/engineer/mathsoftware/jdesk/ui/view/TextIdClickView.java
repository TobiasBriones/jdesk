// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.view;

import engineer.mathsoftware.jdesk.resources.StringResourceId;

/**
 * Defines an interface to be implemented by a view which accepts {@link
 * ClickListener}.
 *
 * @author Tobias Briones
 */
public interface TextIdClickView {
    /**
     * Returns the view text resource as its {@link ClickListener} id.
     *
     * @return view text resource as its id
     */
    StringResourceId getTextId();
}
