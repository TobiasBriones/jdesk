// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.view;

import engineer.mathsoftware.jdesk.Window;
import engineer.mathsoftware.jdesk.resources.StringResources;
import engineer.mathsoftware.jdesk.ui.dialog.Dialog;

/**
 * Listener added to a view to receive click events. The id associated to this
 * listener is the text resource passed to the view. Generally this interface
 * should be implemented by the {@link Window} or {@link Dialog} to handle view
 * click events. If so, then the view implementation should attach in its
 * constructor the {@link ClickListener} with the passed text resource. The view
 * should implement {@link TextIdClickView} interface to provide its text
 * resource ID. <br>
 * <strong>Warning:</strong> If the same text resource is given to several views
 * and the window or dialog implements
 * ClickListener then the click event will occur for all the views but passing
 * their given text id so the id won't be unique, thus every view which listens
 * to a ClickListener should have a unique text id passed in its constructor in
 * order to properly handle the event.
 *
 * @author Tobias Briones
 */
public interface ClickListener {
    /**
     * Called when a view with ClickListener triggers a click event.
     *
     * @param view       view which triggered this click event
     * @param viewTextId id of the text resource set on the view
     *
     * @see StringResources
     */
    void onClick(Object view, int viewTextId);
}
