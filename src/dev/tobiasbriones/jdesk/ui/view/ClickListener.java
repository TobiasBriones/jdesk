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

package dev.tobiasbriones.jdesk.ui.view;

import dev.tobiasbriones.jdesk.Window;
import dev.tobiasbriones.jdesk.resources.StringResources;
import dev.tobiasbriones.jdesk.ui.dialog.Dialog;

/**
 * Listener added to a view to receive click events. The id associated to this listener is the text resource
 * passed to the view. Generally this interface should be implemented by the {@link Window}
 * or {@link Dialog} to handle view click events. If so, then the view implementation
 * should attach in its constructor the {@link ClickListener} with the passed text resource. The view should
 * implement {@link TextIdClickView} interface to provide its text resource ID. <br>
 * <strong>Warning:</strong> If the same text resource is given to several views and the window or dialog implements
 * ClickListener then the click event will occur for all the views but passing their given text id so the id won't be
 * unique, thus every view which listens to a ClickListener should have a unique text id passed in its constructor in
 * order to properly handle the event.
 *
 * @author Tobias Briones
 */
public interface ClickListener {
    /**
     * Called when a view with {@link ClickListener} triggers a click event.
     *
     * @param view       view which triggered this click event
     * @param viewTextId id of the text resource set on the view
     *
     * @see StringResources
     */
    void onClick(Object view, int viewTextId);
}
