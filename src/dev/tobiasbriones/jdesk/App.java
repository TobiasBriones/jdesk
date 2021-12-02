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

import dev.tobiasbriones.jdesk.resources.AppStringResources;
import dev.tobiasbriones.jdesk.resources.Resources;
import dev.tobiasbriones.jdesk.resources.StringResources;

import javax.swing.*;
import java.awt.*;

/**
 * Entry point to build an application.
 *
 * @author Tobias Briones
 */
public abstract class App {
    /**
     * File to store the app main configuration.
     */
    public static final String APP_CONFIG_FILE = "app_config.properties";

    private static void presetUI(Font font) {
        // Set LookAndFeel
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }
            catch (Exception ignored) {
            }
        }
        // Register font
        if (font != null) {
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            try {
                ge.registerFont(font);
            }
            catch (Exception ignored) {
            }
        }
    }

    private Window mw;

    /**
     * Constructor for an Application which registers the app's main font.
     *
     * @param font app's main font to be registered
     */
    @SuppressWarnings("WeakerAccess")
    public App(Font font) {
        this.mw = null;

        presetUI(font);
    }

    /**
     * Default constructor for an Application.
     */
    @SuppressWarnings("WeakerAccess")
    public App() {
        this(null);
    }

    /**
     * Returns the app instance for this app.
     *
     * @return the app instance
     *
     * @see AppInstance
     */
    @SuppressWarnings("WeakerAccess")
    protected abstract AppInstance getAppInstance();

    /**
     * Adds a main window for the application, if an old main window is already attached it will be
     * detached from the application and replaced by the new main window.
     *
     * @param window main window to attach to the application
     *
     * @see #addWindow(Window)
     */
    public final void addMainWindow(Window window) {
        if (mw != null) {
            mw.dispose();
        }
        this.mw = window;
        final StringResources sr = getAppInstance().getStringResources();

        window.setAsMainWindow();
        window.setTitle(sr.get(AppStringResources.APP_NAME));
        window.setIcon(Resources.APP_ICON);
        addWindow(window);
    }

    /**
     * Adds a window for the application.
     *
     * @param window window to attach to the application
     */
    @SuppressWarnings("WeakerAccess")
    public void addWindow(Window window) {
        SwingUtilities.invokeLater(window::createWindow);
    }
}
