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

package io.github.tobiasbriones.jdesk.resources;

import io.github.tobiasbriones.jdesk.App;
import io.github.tobiasbriones.jdesk.Config;
import io.github.tobiasbriones.jdesk.io.IOFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public final class StringResources {
    /**
     * Denotes the default file name containing the string resources.
     * Other string resources files from other languages are denoted
     * by <code>StringResources-LANG</code>
     */
    @SuppressWarnings("WeakerAccess")
    public static final String STRING_RESOURCES_FILE = "StringResources";
    private static final String LANGUAGE_KEY = "language";

    /**
     * Stores the given language to the app config file.
     *
     * @param language app language
     *
     * @throws IOException if and I/O error occurs
     * @see App#APP_CONFIG_FILE
     */
    public static void saveLanguage(String language) throws IOException {
        Config.save(new IOFile(App.APP_CONFIG_FILE), LANGUAGE_KEY, language);
    }

    private final List<String> strings;

    /**
     * Constructs an app string resources object with the passed language.
     *
     * @param src class contained into the package in which the string resource files are going to be loaded
     */
    public StringResources(Class<?> src) throws IOException {
        this.strings = new ArrayList<>();
        final String language = Config.get(new IOFile(App.APP_CONFIG_FILE), LANGUAGE_KEY, "");
        String file = STRING_RESOURCES_FILE;

        if (!language.isEmpty()) {
            file += "-" + language;
        }
        try (
            BufferedReader br = new BufferedReader(new InputStreamReader(
                src.getResourceAsStream(file),
                StandardCharsets.UTF_8
            ))
        ) {
            String line;

            while ((line = br.readLine()) != null) {
                strings.add(line);
            }
        }
    }

    /**
     * Returns the requested string from the string resources.
     *
     * @param stringRes string resource
     *
     * @return the requested string
     *
     * @throws RuntimeException if stringRes does not belong to the list of the loaded strings
     */
    public String get(int stringRes) {
        if (stringRes < 0) {
            throw new RuntimeException("String resources IDs are not negative integers.");
        }
        if (stringRes >= strings.size()) {
            throw new RuntimeException("String resource not found " + stringRes);
        }
        return strings.get(stringRes);
    }
}
