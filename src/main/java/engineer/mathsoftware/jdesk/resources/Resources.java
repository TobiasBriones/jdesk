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

package engineer.mathsoftware.jdesk.resources;

import engineer.mathsoftware.jdesk.io.FileFormat;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

public final class Resources {
    /**
     * App icon format.
     */
    public static final FileFormat ICON_FORMAT = FileFormat.IMAGE_PNG;

    /**
     * Font ttf format.
     */
    public static final FileFormat FONT_FORMAT = FileFormat.FONT_TTF;

    /**
     * App icons directory.
     */
    public static final String ICON_DIRECTORY = File.separator + "icons";

    /**
     * App fonts directory.
     */
    public static final String FONT_DIRECTORY = File.separator + "fonts";

    public static final int SMALL_FONT_SIZE = 10;
    public static final int NORMAL_FONT_SIZE = 12;
    public static final int TITLE_FONT_SIZE = 14;
    public static final String APP_ICON = "ic_launcher";

    /**
     * Loads the requested icon.
     *
     * @param iconName name of the icon without file extension
     *
     * @return the icon loaded into {@link ImageIcon}
     */
    public static Icon loadIcon(String iconName) {
        final String path = getFilePath(ICON_DIRECTORY, iconName, ICON_FORMAT);
        return new ImageIcon(
            Objects.requireNonNull(Resources.class.getClassLoader()
                                                  .getResource(path))
        );
    }

    /**
     * Loads the requested font.
     *
     * @param fontName font name without file extension
     * @param size     font size
     * @param style    font style
     *
     * @return the loaded font, <code>null</code> if fails to load the font
     */
    public static Font loadFont(String fontName, int size, int style) {
        final String path = getFilePath(FONT_DIRECTORY, fontName, FONT_FORMAT);
        try {
            return Font.createFont(
                Font.TRUETYPE_FONT,
                Objects.requireNonNull(Resources.class.getClassLoader()
                                                      .getResourceAsStream(path))
            ).deriveFont(style, (float) size);
        }
        catch (Exception e) {
            System.err.println("Fail to load font " + fontName);
        }
        return null;
    }

    /**
     * Loads the requested font.
     *
     * @param fontName font name without file extension
     * @param size     font size
     *
     * @return the loaded font, <code>null</code> if fails to load the font
     */
    public static Font loadFont(String fontName, int size) {
        final String path = getFilePath(FONT_DIRECTORY, fontName, FONT_FORMAT);
        try {
            return Font.createFont(
                Font.TRUETYPE_FONT,
                Objects.requireNonNull(Resources.class.getClassLoader()
                                                      .getResourceAsStream(path))
            ).deriveFont((float) size);
        }
        catch (Exception e) {
            System.err.println("Fail to load font " + fontName);
        }
        return null;
    }

    /**
     * Loads the requested font.
     *
     * @param fontName font name without file extension
     *
     * @return the loaded font, <code>null</code> if fails to load the font
     */
    public static Font loadFont(String fontName) {
        final String path = getFilePath(FONT_DIRECTORY, fontName, FONT_FORMAT);
        try {
            return Font.createFont(
                Font.TRUETYPE_FONT,
                Objects.requireNonNull(Resources.class.getClassLoader()
                                                      .getResourceAsStream(path))
            ).deriveFont((float) NORMAL_FONT_SIZE);
        }
        catch (Exception e) {
            System.err.println("Fail to load font " + fontName);
        }
        return null;
    }

    private static String getFilePath(
        String directory,
        String fileName,
        Object fileExtension
    ) {
        return Paths.get(directory, fileName + "." + fileExtension).toString();
    }

    private Resources() {}
}
