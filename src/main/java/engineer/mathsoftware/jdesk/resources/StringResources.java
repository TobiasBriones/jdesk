// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.resources;

import engineer.mathsoftware.jdesk.App;
import engineer.mathsoftware.jdesk.Config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public final class StringResources {
    /**
     * Denotes the default file name containing the string resources. Other
     * string resources files from other languages are denoted by {@code
     * StringResources-LANG}
     */
    public static final String STRING_RESOURCES_FILE = File.separator +
                                                       "values" +
                                                       File.separator +
                                                       "strings";
    private static final String LANGUAGE_KEY = "language";

    public static StringResources load() {
        try {
            return new StringResources(StringResources.class);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Stores the given language to the app config file.
     *
     * @param language app language
     *
     * @throws IOException if and I/O error occurs
     * @see App#APP_CONFIG_FILE
     */
    public static void saveLanguage(String language) throws IOException {
        Config.save(Paths.get(App.APP_CONFIG_FILE), LANGUAGE_KEY, language);
    }

    private final Properties strings;

    /**
     * Constructs an app string resources object with the passed language.
     *
     * @param src class contained into the package in which the string resource
     *            files are going to be loaded
     */
    public StringResources(Class<?> src) throws IOException {
        strings = new Properties();
        final String language = Config.get(
            Paths.get(App.APP_CONFIG_FILE),
            LANGUAGE_KEY,
            ""
        );
        String file = STRING_RESOURCES_FILE;
        if (!language.isEmpty()) {
            file += "-" + language;
        }
        file += ".properties";
        try (InputStream is = src.getClassLoader().getResourceAsStream(file)) {
            strings.load(is);
        }
    }

    /**
     * Returns the requested string from the string resources.
     *
     * @param stringRes string resource
     *
     * @return the requested string
     *
     * @throws RuntimeException if stringRes does not belong to the list of the
     *                          loaded strings
     */
    public String get(StringResourceId stringRes) {
        return strings.getProperty(stringRes.getKey(), "");
    }
}
