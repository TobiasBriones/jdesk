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

package engineer.mathsoftware.jdesk.ui.view;

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
    int getTextId();
}
