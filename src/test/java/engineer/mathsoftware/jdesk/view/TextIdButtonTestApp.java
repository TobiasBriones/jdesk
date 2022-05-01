// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.view;

import engineer.mathsoftware.jdesk.Strings;
import engineer.mathsoftware.jdesk.TestApp;
import engineer.mathsoftware.jdesk.TestWindow;
import engineer.mathsoftware.jdesk.Window;
import engineer.mathsoftware.jdesk.resources.StringResourceId;
import engineer.mathsoftware.jdesk.ui.view.Button;
import engineer.mathsoftware.jdesk.ui.view.ClickListener;

import javax.swing.*;

/**
 * This test case shows how to set the button's text via constructor and using
 * the text id. This will take the text id as the button id, and if the
 * underlying window implements {@link ClickListener} then the button will
 * automatically be bound to that listener.
 *
 * The text id was an int for initials versions 0.1.0-. Now it is an {@link
 * StringResourceId} object.
 *
 * @author Tobias Briones
 */
public final class TextIdButtonTestApp extends TestApp implements ClickListener {
    private Button button;

    @Override
    protected void createWindow() {
        addWindow(newWindow());
    }

    @Override
    public void onClick(Object view, StringResourceId viewTextId) {
        final String str = getStringResources().get(viewTextId);
        System.out.println("Click: " + str);
    }

    private Window newWindow() {
        // By passing the string id, the button will have id Strings.OK, and
        // this ActionListener will be triggered when button click is performed.
        button = new Button(this, Strings.OK);
        return new TestWindow(this, new JComponent[] { button });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextIdButtonTestApp::new);
    }
}
