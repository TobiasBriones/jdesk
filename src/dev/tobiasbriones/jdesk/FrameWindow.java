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

import dev.tobiasbriones.jdesk.resources.StringResources;
import dev.tobiasbriones.jdesk.ui.style.AppStyle;
import dev.tobiasbriones.jdesk.ui.style.DefaultStyle;
import dev.tobiasbriones.jdesk.ui.style.Style;
import dev.tobiasbriones.jdesk.ui.view.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * Abstract frame to build a window.
 *
 * @author Tobias Briones
 * @see Window
 * @see WindowContext
 */
abstract class FrameWindow extends JFrame implements WindowContext {
    private static final long serialVersionUID = 3319905613616655519L;
    private final boolean hasSizeSet;
    private final transient AppInstance appInstance;
    private boolean isMainWindow;
    /**
     * Constructor for a window to be build with title and size.
     *
     * @param appInstance app instance
     * @param title       window title
     * @param size        window size
     */
    FrameWindow(AppInstance appInstance, String title, Dimension size) {
        super(title);
        this.appInstance = appInstance;
        this.hasSizeSet = (size != null);
        this.isMainWindow = false;

        // Check app instance window context
        if (getStringResources() == null || getAppStyle() == null) {
            throw new NullPointerException(
                "Window context is not set, you must implement AppInstance");
        }
        if (size != null) {
            setSize(size);
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowListener(this));
    }

    /**
     * Constructor for a window to be build with title and size.
     *
     * @param appInstance app instance
     * @param titleRes    window title
     * @param size        window size
     */
    FrameWindow(AppInstance appInstance, int titleRes, Dimension size) {
        super();
        this.appInstance = appInstance;
        this.hasSizeSet = (size != null);
        this.isMainWindow = false;

        // Check app instance window context
        if (getStringResources() == null || getAppStyle() == null) {
            throw new NullPointerException(
                "Window context is not set, you must implement AppInstance");
        }
        if (size != null) {
            setSize(size);
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(getStringResources().get(titleRes));
        addWindowListener(new WindowListener(this));
    }

    /**
     * Constructor for a window to be build with title.
     *
     * @param appInstance app instance
     * @param title       window title
     */
    FrameWindow(AppInstance appInstance, String title) {
        this(appInstance, title, null);
    }

    /**
     * Constructor for a window to be build with title.
     *
     * @param appInstance app instance
     * @param titleRes    window title
     */
    FrameWindow(AppInstance appInstance, int titleRes) {
        this(appInstance, titleRes, null);
    }

    /**
     * Returns the app instance of this window.
     *
     * @return the app instance of this window
     *
     * @see App
     * @see WindowContext
     * @see AppInstance
     */
    public final AppInstance getAppInstance() {
        return appInstance;
    }

    /**
     * Returns the window context of this window.
     *
     * @return the context of this window
     *
     * @see App
     * @see WindowContext
     * @see AppInstance
     */
    public final WindowContext getWindowContext() {
        return appInstance;
    }

    /**
     * Returns if this window is a main window.
     *
     * @return true if and only if this window is main
     */
    public final boolean isMainWindow() {
        return isMainWindow;
    }

    /**
     * Sets this window visible and calls {@link #windowVisible(boolean)}.
     *
     * @param visible visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        windowVisible(visible);
    }

    /**
     * Returns the app's string resources associated to this window.
     *
     * @return string resources
     *
     * @see StringResources
     */
    @Override
    public final StringResources getStringResources() {
        return appInstance.getStringResources();
    }

    /**
     * Returns the app's style associated to this window.
     *
     * @return application style
     *
     * @see AppStyle
     * @see Style
     * @see DefaultStyle
     */
    @Override
    public final AppStyle getAppStyle() {
        return appInstance.getAppStyle();
    }

    final void setAsMainWindow() {
        this.isMainWindow = true;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    final void createWindow() {
        final Panel panel = new Panel(getWindowContext());

        createWindow(panel);
        getContentPane().setBackground(getAppStyle().getWindowBackgroundColor());
        getContentPane().add(panel);
        if (!hasSizeSet) {
            pack();
        }
        setLocationRelativeTo(null);
        setBackground(getAppStyle().getWindowBackgroundColor());
        windowCreated();
    }

    /**
     * Called when this window is going to be created.
     *
     * @param panel root panel of the window
     */
    protected abstract void createWindow(Panel panel);

    /**
     * Called immediately after this window is created. It should be decided
     * then to set the window as visible.
     */
    protected abstract void windowCreated();

    /**
     * Called when the window visibility changes.
     *
     * @param visible visible
     */
    protected abstract void windowVisible(boolean visible);

    /**
     * Called when this window is opened.
     */
    protected abstract void windowOpened();

    /**
     * Called when this window is detached. If it is a main window it occurs
     * when is being closed, otherwise when is closed.
     */
    protected abstract void windowDetached();

    private static final class WindowListener implements java.awt.event.WindowListener {
        private final FrameWindow window;

        private WindowListener(FrameWindow window) {
            this.window = window;
        }

        @Override
        public void windowOpened(WindowEvent e) {
            window.windowOpened();
        }

        @Override
        public void windowClosing(WindowEvent e) {
            if (window.isMainWindow()) {
                window.windowDetached();
            }
        }

        @Override
        public void windowClosed(WindowEvent e) {
            window.windowDetached();
        }

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}
    }
}
