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

package io.github.tobiasbriones.jdesk.ui.view;

import io.github.tobiasbriones.jdesk.WindowContext;
import io.github.tobiasbriones.jdesk.ui.style.AppStyle;

import javax.swing.*;
import java.util.Vector;

/**
 * Combo box view.
 *
 * @param <E> type of elements
 */
@SuppressWarnings("unused")
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
