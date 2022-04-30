// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.dialog;

import engineer.mathsoftware.jdesk.WindowContext;
import engineer.mathsoftware.jdesk.ui.style.AppStyle;
import engineer.mathsoftware.jdesk.ui.view.Panel;

import java.awt.*;

/**
 * Panel used by the application dialogs.
 *
 * @author Tobias Briones
 */
public class DialogPanel extends Panel {
    private static final long serialVersionUID = 2745851950237186422L;

    /**
     * Default constructor for DialogPanel.
     *
     * @param context window context
     */
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
    public DialogPanel(
        WindowContext context,
        LayoutManager layoutManager,
        Insets padding
    ) {
        super(context, layoutManager, padding);
        config(context.getAppStyle());
    }

    /**
     * Constructor for DialogPanel.
     *
     * @param context       window context
     * @param layoutManager layoutManager
     */
    public DialogPanel(WindowContext context, LayoutManager layoutManager) {
        super(context, layoutManager);
        config(context.getAppStyle());
    }

    private void config(AppStyle appStyle) {
        setBackground(appStyle.getDialogBackgroundColor());
    }
}
