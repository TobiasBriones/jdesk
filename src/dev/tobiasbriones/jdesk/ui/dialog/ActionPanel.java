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

package dev.tobiasbriones.jdesk.ui.dialog;

import dev.tobiasbriones.jdesk.WindowContext;
import dev.tobiasbriones.jdesk.ui.view.Button;

import java.awt.*;

/**
 * Panel to show the actions when displaying a user input dialog.
 *
 * @author Tobias Briones
 */
public final class ActionPanel extends DialogPanel {
    private static final long serialVersionUID = -9017958368948957680L;

    /**
     * Constructor for an ActionPanel with positive action.
     *
     * @param context        window context
     * @param positiveAction positive action button
     */
    public ActionPanel(WindowContext context, Button positiveAction) {
        super(context);
        setLayout(new FlowLayout(FlowLayout.TRAILING));
        add(positiveAction);
    }

    /**
     * Constructor for an ActionPanel with negative and positive actions.
     *
     * @param context        window context
     * @param negativeAction negative action button
     * @param positiveAction positive action button
     */
    public ActionPanel(
        WindowContext context,
        Button negativeAction,
        Button positiveAction
    ) {
        super(context);
        setLayout(new FlowLayout(FlowLayout.TRAILING));
        add(negativeAction);
        add(positiveAction);
    }

    /**
     * Constructor for an ActionPanel with negative, positive and neutral
     * actions.
     *
     * @param context        window context
     * @param negativeAction negative action button
     * @param positiveAction positive action button
     * @param neutralButton  neutral action button
     */
    public ActionPanel(
        WindowContext context,
        Button negativeAction,
        Button positiveAction,
        Button neutralButton
    ) {
        super(context);
        final DialogPanel leftPanel = new DialogPanel(context);
        final DialogPanel rightPanel = new DialogPanel(
            context,
            new FlowLayout(FlowLayout.TRAILING)
        );

        leftPanel.add(neutralButton);
        rightPanel.add(negativeAction);
        rightPanel.add(positiveAction);
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.LINE_START);
        add(rightPanel, BorderLayout.LINE_END);
    }
}
