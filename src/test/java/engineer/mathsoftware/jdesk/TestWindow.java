/*
 * Copyright (c) 2022 Tobias Briones. All rights reserved.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 *
 * This file is part of JDesk.
 *
 * This source code is licensed under the BSD-3-Clause License found in the
 * LICENSE file in the root directory of this source tree or at
 * https://opensource.org/licenses/BSD-3-Clause.
 */

package engineer.mathsoftware.jdesk;

import engineer.mathsoftware.jdesk.ui.view.Panel;

import java.awt.*;

public class TestWindow extends Window {
    public TestWindow(AppInstance appInstance) {
        super(appInstance);
    }

    @Override
    protected void createWindow(Panel panel) {
        final String okStr = getStringResources().get(Strings.OK);
        panel.add(new Label(okStr));
    }

    @Override
    protected void windowCreated() {
        setVisible(true);
    }
}
