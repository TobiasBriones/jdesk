// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.view;

import engineer.mathsoftware.jdesk.WindowContext;
import engineer.mathsoftware.jdesk.ui.style.AppStyle;

import javax.swing.*;
import java.util.Vector;

/**
 * Combo box view.
 *
 * @param <E> type of elements
 *
 * @author Tobias Briones
 */
public class ComboBox<E> extends JComboBox<E> {
    private static final long serialVersionUID = -2380217949048973860L;

    public ComboBox(WindowContext context) {
        super();
        config(context.getAppStyle());
    }

    public ComboBox(WindowContext context, ComboBoxModel<E> aModel) {
        super(aModel);
        config(context.getAppStyle());
    }

    public ComboBox(WindowContext context, E[] items) {
        super(items);
        config(context.getAppStyle());
    }

    public ComboBox(WindowContext context, Vector<E> items) {
        super(items);
        config(context.getAppStyle());
    }

    private void config(AppStyle appStyle) {
        setFont(appStyle.getFont());
        setFocusable(false);
    }
}
