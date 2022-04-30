// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk;

import javax.swing.*;

public final class BackgroundWorkTestApp extends TestApp {
    public BackgroundWorkTestApp() {
        super();
    }

    @Override
    protected void createWindow() {
        addMainWindow(new BackgroundWorkTestWindow(this));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BackgroundWorkTestApp::new);
    }
}
