// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.dialog;

import engineer.mathsoftware.jdesk.WindowContext;
import engineer.mathsoftware.jdesk.ui.view.Button;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Button used by app dialogs to ask for the user action.
 *
 * @author Tobias Briones
 */
public final class OptionButton extends Button {
    private static final long serialVersionUID = 1302281349633313120L;
    private static final Border BORDER = new EmptyBorder(5, 10, 5, 10);
    private final Color borderColor;

    /**
     * Default Constructor for an OptionButton.
     *
     * @param context window context
     * @param text    button option text
     */
    public OptionButton(WindowContext context, String text) {
        super(context, text);
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
