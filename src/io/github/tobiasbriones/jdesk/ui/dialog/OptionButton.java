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

package io.github.tobiasbriones.jdesk.ui.dialog;

import io.github.tobiasbriones.jdesk.WindowContext;
import io.github.tobiasbriones.jdesk.ui.view.Button;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Button used by app dialogs to ask for the user action.
 */
public final class OptionButton extends Button {
    private static final long serialVersionUID = 1302281349633313120L;
    private static final Border BORDER = new EmptyBorder(5, 10, 5, 10);
    private final Color borderColor;

    /**
     * Default Constructor for an OptionButton.
     *
     * @param context window context
     * @param option  button option text
     */
    @SuppressWarnings("WeakerAccess")
    public OptionButton(WindowContext context, String option) {
        super(context, option);
        this.borderColor = context.getAppStyle().getAccentColor();

        setBorder(BORDER);
        setBold();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(borderColor);
        g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
        if (isFocused()) {
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
