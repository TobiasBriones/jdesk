// Copyright (c) 2022 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk;

import engineer.mathsoftware.jdesk.ui.view.Panel;

import javax.swing.*;

public class TestWindow extends Window {
    private final JComponent[] components;

    public TestWindow(
        AppInstance appInstance,
        JComponent[] components
    ) {
        super(appInstance);
        this.components = components;
    }

    @Override
    protected void createWindow(Panel panel) {
        for (final JComponent component : components) {
            panel.add(component);
        }
    }

    @Override
    protected void windowCreated() {
        setVisible(true);
    }
}
