// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.resources;

import engineer.mathsoftware.jdesk.*;
import engineer.mathsoftware.jdesk.Window;
import engineer.mathsoftware.jdesk.resources.Resources;
import engineer.mathsoftware.jdesk.resources.StringResources;
import engineer.mathsoftware.jdesk.ui.style.AppStyle;
import engineer.mathsoftware.jdesk.ui.style.DefaultStyle;
import engineer.mathsoftware.jdesk.ui.style.Style;
import engineer.mathsoftware.jdesk.ui.view.TextLabel;

import javax.swing.*;
import java.awt.*;

/**
 * This case tests how Strings are loaded into the application.
 *
 * Strings are resources located at: /values/strings.properties or
 * /values/strings-lang.properties where "lang" is the language code stored
 * in the config.properties file of the application.
 *
 * @author Tobias Briones
 */
public final class StringsTestApp extends App implements AppInstance {
    private static final Font FONT;
    private static final StringResources strings;
    private static final DefaultStyle.ColorPair[] appColors;

    static {
        FONT = Resources.loadFont("Roboto-Light");
        strings = StringResources.load();
        appColors = new DefaultStyle.ColorPair[] {
            new DefaultStyle.ColorPair(
                Style.ACCENT,
                Color.decode("#00B8D4")
            )
        };
    }

    private final AppStyle appStyle;

    public StringsTestApp() {
        super(FONT);
        this.appStyle = new DefaultStyle(FONT, appColors);

        final Window window = new TestWindow(this, new JComponent[] {
            new TextLabel(this, Strings.OK),
            new TextLabel(this, Strings.APP_NAME),
            new TextLabel(this, Strings.CANCEL),
            new TextLabel(this, Strings.CONFIRM),
            new TextLabel(this, Strings.ERROR),
            new TextLabel(this, Strings.FAIL),
            new TextLabel(this, Strings.INFORMATION),
            new TextLabel(this, Strings.INPUT),
            new TextLabel(this, Strings.PASSWORD),
            new TextLabel(this, Strings.SUCCESS),
            new TextLabel(this, Strings.WARNING)
        });
        addMainWindow(window);
    }

    @Override
    public StringResources getStringResources() {
        return strings;
    }

    @Override
    public AppStyle getAppStyle() {
        return appStyle;
    }

    @Override
    public String getAppConfigFile() {
        return "config.properties";
    }

    @Override
    protected AppInstance getAppInstance() {
        return this;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StringsTestApp::new);
    }
}
