// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.view;

import engineer.mathsoftware.jdesk.TestApp;
import engineer.mathsoftware.jdesk.TestWindow;
import engineer.mathsoftware.jdesk.Window;
import engineer.mathsoftware.jdesk.ui.view.Button;

import javax.swing.*;

public final class ButtonTestApp extends TestApp {
    private Button button;

    @Override
    protected void createWindow() {
        addWindow(newWindow());
        button.setText("Click the Button");
        button.addActionListener(e -> System.out.println("Click!"));
    }

    private Window newWindow() {
        button = new Button(this);
        return new TestWindow(this, new JComponent[] { button });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ButtonTestApp::new);
    }
}
