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

import io.github.tobiasbriones.jdesk.Window;
import io.github.tobiasbriones.jdesk.WindowContext;
import io.github.tobiasbriones.jdesk.resources.Resources;
import io.github.tobiasbriones.jdesk.resources.StringResources;
import io.github.tobiasbriones.jdesk.ui.style.AppStyle;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

/**
 * Application dialog.
 */
@SuppressWarnings("unused")
public class Dialog extends JDialog implements WindowContext {
    private static final long serialVersionUID = -597263550794114904L;
    private final transient WindowContext windowContext;

    /**
     * Constructor for Dialog.
     *
     * @param window windowContext to be attached
     * @param title  dialog title
     */
    @SuppressWarnings("WeakerAccess")
    public Dialog(Window window, String title) {
        super(window, title);
        this.windowContext = window.getWindowContext();

        config(window.getAppStyle().getDialogBackgroundColor());
    }

    /**
     * Default constructor for Dialog.
     *
     * @param window windowContext to be attached
     */
    @SuppressWarnings("WeakerAccess")
    public Dialog(Window window) {
        this(window, "");
    }

    /**
     * Sets dialog title.
     *
     * @param titleRes dialog title resource
     */
    public final void setTitle(int titleRes) {
        setTitle(windowContext.getStringResources().get(titleRes));
    }

    /**
     * Sets dialog icon.
     *
     * @param iconName dialog icon name
     */
    public final void setIcon(String iconName) {
        final String path = Paths.get(Resources.ICON_DIRECTORY, iconName + ".png").toString();

        setIconImage(Toolkit.getDefaultToolkit().getImage(path));
    }

    /**
     * Sets the view to show on this dialog with default padding.
     *
     * @param panel root panel for the dialog
     */
    public final void setView(DialogPanel panel) {
        setView(panel, new Insets(10, 10, 5, 10));
    }

    @Override
    public StringResources getStringResources() {
        return windowContext.getStringResources();
    }

    @Override
    public AppStyle getAppStyle() {
        return windowContext.getAppStyle();
    }

    /**
     * Sets the view to show on this dialog.
     *
     * @param panel   root panel for the dialog
     * @param padding padding to put to the panel
     */
    @SuppressWarnings("WeakerAccess")
    public final void setView(DialogPanel panel, Insets padding) {
        panel.setPadding(padding);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void config(Color backgroundColor) {
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        getContentPane().setBackground(backgroundColor);
        setBackground(backgroundColor);
    }
}
