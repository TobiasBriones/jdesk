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

import dev.tobiasbriones.jdesk.io.IOFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * It manages configuration for a given file, the class can be used statically
 * by its static methods or by an object instance recommended to put or get
 * several values and save later, for example in a settings window.
 *
 * @author Tobias Briones
 */
public final class Config {
    /**
     * Stores an entry into the destination configuration file.
     * If the file does not exist, it's automatically created.
     *
     * @param configFile file to store the configuration
     * @param key        key to put into the configuration
     * @param value      value to associate to the key
     * @param comments   a description of the property list
     *
     * @throws IOException if an I/O error occurs
     */
    public static void save(IOFile configFile, String key, Object value, String comments) throws IOException {
        final Properties properties = new Properties();

        configFile.createFileIfNotExists();
        properties.load(new FileInputStream(configFile));
        put(properties, key, value);
        properties.store(new FileOutputStream(configFile), comments);
    }

    /**
     * Stores an entry into the destination configuration file.
     * If the file does not exist, it's automatically created.
     *
     * @param configFile file to store the configuration
     * @param key        key to put into the configuration
     * @param value      value to associate to the key
     *
     * @throws IOException if an I/O error occurs
     * @see #save(IOFile, String, Object, String)
     */
    public static void save(IOFile configFile, String key, Object value) throws IOException {
        save(configFile, key, value, null);
    }

    /**
     * Stores a set of entries into the destination configuration file.
     * If the file does not exist, it's automatically created.
     *
     * @param configFile file to store the configuration
     * @param entries    entries to put into the configuration
     * @param comments   a description of the property list
     *
     * @throws IOException if an I/O error occurs
     */
    public static void save(IOFile configFile, HashMap<String, String> entries, String comments) throws IOException {
        final Properties properties = new Properties();
        final Iterator<Entry<String, String>> i = entries.entrySet().iterator();
        Entry<String, String> currentEntry;

        configFile.createFileIfNotExists();
        properties.load(new FileInputStream(configFile));
        while (i.hasNext()) {
            currentEntry = i.next();

            put(properties, currentEntry.getKey(), currentEntry.getValue());
        }
        properties.store(new FileOutputStream(configFile), comments);
    }

    /**
     * Stores a list of entries into the destination configuration file.
     * If the file does not exist, it's automatically created.
     *
     * @param configFile file to store the configuration
     * @param entries    entries to put into the configuration
     *
     * @throws IOException if an I/O error occurs
     * @see #save(IOFile, HashMap, String)
     */
    public static void save(IOFile configFile, HashMap<String, String> entries) throws IOException {
        save(configFile, entries, null);
    }

    /**
     * Gets the value from the associated key in the configuration file.
     * If the file does not exist, it's automatically created.
     *
     * @param configFile   file to get the configuration
     * @param key          key to get the value
     * @param defaultValue default value to return if is not in the configuration file yet
     *
     * @return the value from the associated key or defaultValue.toString() if was not found
     *
     * @throws IOException if an I/O error occurs
     */
    public static String get(IOFile configFile, String key, Object defaultValue) throws IOException {
        final Properties properties = new Properties();

        configFile.createFileIfNotExists();
        properties.load(new FileInputStream(configFile));
        return properties.getProperty(key, defaultValue.toString());
    }

    private static void put(Properties properties, String key, Object value) {
        properties.put(key, value.toString());
    }

    private final IOFile file;
    private final Properties properties;

    /**
     * It loads the given configuration file.
     * If the file does not exist, it's automatically created.
     *
     * @param file file containing the configuration
     *
     * @throws IOException if an I/O error occurs
     * @see Properties
     */
    public Config(IOFile file) throws IOException {
        this.file = file;
        this.properties = new Properties();

        file.createFileIfNotExists();
        properties.load(new FileInputStream(file));
    }

    public Properties getProperties() {
        return properties;
    }

    /**
     * Puts an entry into the destination configuration file.
     *
     * @param key   key to put into the configuration
     * @param value value to associate to the key
     */
    public void put(String key, Object value) {
        properties.put(key, value.toString());
    }

    /**
     * Gets the value from the associated key in the configuration file.
     *
     * @param key          key to get the value
     * @param defaultValue default value to return if is not in the configuration file yet
     *
     * @return the value from the associated key or defaultValue.toString() if was not found
     */
    public String get(String key, Object defaultValue) {
        return properties.getProperty(key, defaultValue.toString());
    }

    /**
     * Stores the configuration into the destination file.
     * If the file does not exist, it's automatically created.
     *
     * @param comments a description of the property list
     *
     * @throws IOException if an I/O error occurs
     */
    public void store(String comments) throws IOException {
        file.createFileIfNotExists();
        properties.store(new FileOutputStream(file), comments);
    }

    /**
     * Stores the configuration into the destination file.
     * If the file does not exist, it's automatically created.
     *
     * @throws IOException if an I/O error occurs
     * @see #store(String)
     */
    public void store() throws IOException {
        store(null);
    }
}
