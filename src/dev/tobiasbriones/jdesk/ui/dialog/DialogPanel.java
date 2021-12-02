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
import dev.tobiasbriones.jdesk.ui.view.Panel;
import dev.tobiasbriones.jdesk.ui.style.AppStyle;

import java.awt.*;

/**
 * Panel used by the application dialogs.
 */
@SuppressWarnings("unused")
public class DialogPanel extends Panel {
    private static final long serialVersionUID = 2745851950237186422L;

    /**
     * Default constructor for DialogPanel.
     *
     * @param context window context
     */
    @SuppressWarnings("WeakerAccess")
    public DialogPanel(WindowContext context) {
        super(context);
        config(context.getAppStyle());
    }

    /**
     * Constructor for DialogPanel.
     *
     * @param context       window context
     * @param layoutManager layoutManager
     * @param padding       padding
     */
    @SuppressWarnings("WeakerAccess")
    public DialogPanel(WindowContext context, LayoutManager layoutManager, Insets padding) {
        super(context, layoutManager, padding);
        config(context.getAppStyle());
    }

    /**
     * Constructor for DialogPanel.
     *
     * @param context       window context
     * @param layoutManager layoutManager
     */
    @SuppressWarnings("WeakerAccess")
    public DialogPanel(WindowContext context, LayoutManager layoutManager) {
        super(context, layoutManager);
        config(context.getAppStyle());
    }

    private void config(AppStyle appStyle) {
        setBackground(appStyle.getDialogBackgroundColor());
    }
}
