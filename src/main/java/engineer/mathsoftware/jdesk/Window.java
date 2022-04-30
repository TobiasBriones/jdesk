// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk;

import engineer.mathsoftware.jdesk.resources.Resources;

import java.awt.*;
import java.nio.file.Paths;

/**
 * Window to display the UI.
 *
 * @author Tobias Briones
 * @see WindowContext
 */
public abstract class Window extends FrameWindow {
    private static final long serialVersionUID = 673841114819291383L;

    /**
     * Constructor for a window to be build with title and size.
     *
     * @param appInstance app instance
     * @param title       window title
     * @param size        window size
     */
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
    public Window(AppInstance appInstance, int titleRes, Dimension size) {
        super(appInstance, titleRes, size);
    }

    /**
     * Constructor for a window to be build with title.
     *
     * @param appInstance app instance
     * @param title       window title
     */
    public Window(AppInstance appInstance, String title) {
        super(appInstance, title);
    }

    /**
     * Constructor for a window to be build with title.
     *
     * @param appInstance app instance
     * @param titleRes    window title
     */
    public Window(AppInstance appInstance, int titleRes) {
        super(appInstance, titleRes);
    }

    /**
     * Constructor for a window to be build with size.
     *
     * @param appInstance app instance
     * @param size        window size
     */
    public Window(AppInstance appInstance, Dimension size) {
        super(appInstance, "", size);
    }

    /**
     * Default constructor for window.
     *
     * @param appInstance app instance
     */
    public Window(AppInstance appInstance) {
        super(appInstance, "");
    }

    public final void setTitle(int titleRes) {
        setTitle(getStringResources().get(titleRes));
    }

    public final void setIcon(String iconName) {
        final String path = Paths.get(
            Resources.ICON_DIRECTORY,
            iconName + ".png"
        ).toString();
        setIconImage(
            Toolkit.getDefaultToolkit()
                   .getImage(
                       getClass().getClassLoader().getResource(path)
                   )
        );
    }

    @Override
    protected void windowVisible(boolean visible) {}

    @Override
    protected void windowOpened() {}

    @Override
    protected void windowDetached() {}
}
