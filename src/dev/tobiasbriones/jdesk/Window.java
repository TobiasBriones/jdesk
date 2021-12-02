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

package dev.tobiasbriones.jdesk;

import dev.tobiasbriones.jdesk.resources.Resources;

import java.awt.*;
import java.nio.file.Paths;

/**
 * Window to display the UI.
 *
 * @see WindowContext
 */
@SuppressWarnings("unused")
public abstract class Window extends FrameWindow {
    private static final long serialVersionUID = 673841114819291383L;

    /**
     * Constructor for a window to be build with title and size.
     *
     * @param appInstance app instance
     * @param title       window title
     * @param size        window size
     */
    @SuppressWarnings("WeakerAccess")
    public Window(AppInstance appInstance, String title, Dimension size) {
        super(appInstance, title, size);
    }

    /**
     * Constructor for a window to be build with title and size.
     *
     * @param appInstance app instance
     * @param titleRes    window title
     * @param size        window size
     */
    @SuppressWarnings("WeakerAccess")
    public Window(AppInstance appInstance, int titleRes, Dimension size) {
        super(appInstance, titleRes, size);
    }

    /**
     * Constructor for a window to be build with title.
     *
     * @param appInstance app instance
     * @param title       window title
     */
    @SuppressWarnings("WeakerAccess")
    public Window(AppInstance appInstance, String title) {
        super(appInstance, title);
    }

    /**
     * Constructor for a window to be build with title.
     *
     * @param appInstance app instance
     * @param titleRes    window title
     */
    @SuppressWarnings("WeakerAccess")
    public Window(AppInstance appInstance, int titleRes) {
        super(appInstance, titleRes);
    }

    /**
     * Constructor for a window to be build with size.
     *
     * @param appInstance app instance
     * @param size        window size
     */
    @SuppressWarnings("WeakerAccess")
    public Window(AppInstance appInstance, Dimension size) {
        super(appInstance, "", size);
    }

    /**
     * Default constructor for window.
     *
     * @param appInstance app instance
     */
    @SuppressWarnings("WeakerAccess")
    public Window(AppInstance appInstance) {
        super(appInstance, "");
    }

    @SuppressWarnings("WeakerAccess")
    public final void setTitle(int titleRes) {
        setTitle(getStringResources().get(titleRes));
    }

    @SuppressWarnings("WeakerAccess")
    public final void setIcon(String iconName) {
        final String path = Paths.get(Resources.ICON_DIRECTORY, iconName + ".png").toString();

        setIconImage(Toolkit.getDefaultToolkit().getImage(path));
    }

    @Override
    protected void windowVisible(boolean visible) {}

    @Override
    protected void windowOpened() {}

    @Override
    protected void windowDetached() {}
}
