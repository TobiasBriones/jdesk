// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * It manages configuration for a given file, the class can be used statically
 * by its static methods or by an object instance recommended putting or get
 * several values and save later, for example in a settings window.
 *
 * @author Tobias Briones
 */
public final class Config {
    /**
     * Stores an entry into the destination configuration file. If the file does
     * not exist, it's automatically created.
     *
     * @param path  file to store the configuration
     * @param key   key to put into the configuration
     * @param value value to associate to the key
     *
     * @throws IOException if an I/O error occurs
     * @see #save(Path, String, Object, String)
     */
    public static void save(
        Path path,
        String key,
        Object value
    ) throws IOException {
        save(path, key, value, null);
    }

    /**
     * Stores an entry into the destination configuration file. If the file does
     * not exist, it's automatically created.
     *
     * @param path     path to store the configuration file
     * @param key      key to put into the configuration
     * @param value    value to associate to the key
     * @param comments a description of the property list
     *
     * @throws IOException if an I/O error occurs
     */
    public static void save(
        Path path,
        String key,
        Object value,
        String comments
    ) throws IOException {
        final Properties properties = new Properties();
        createFileIfNotExists(path);
        properties.load(Files.newInputStream(path));
        put(properties, key, value);
        properties.store(Files.newOutputStream(path), comments);
    }

    /**
     * Stores a list of entries into the destination configuration file. If the
     * file does not exist, it's automatically created.
     *
     * @param path    path file to store the configuration file
     * @param entries entries to put into the configuration
     *
     * @throws IOException if an I/O error occurs
     * @see #save(Path, Map, String)
     */
    public static void save(
        Path path,
        Map<String, String> entries
    ) throws IOException {
        save(path, entries, null);
    }

    /**
     * Stores a set of entries into the destination configuration file. If the
     * file does not exist, it's automatically created.
     *
     * @param path     path file to store the configuration file
     * @param entries  entries to put into the configuration
     * @param comments a description of the property list
     *
     * @throws IOException if an I/O error occurs
     */
    public static void save(
        Path path,
        Map<String, String> entries,
        String comments
    ) throws IOException {
        final Properties properties = new Properties();
        final Iterator<Map.Entry<String, String>> i = entries.entrySet()
                                                             .iterator();
        Map.Entry<String, String> currentEntry;
        createFileIfNotExists(path);
        properties.load(Files.newInputStream(path));
        while (i.hasNext()) {
            currentEntry = i.next();
            put(properties, currentEntry.getKey(), currentEntry.getValue());
        }
        properties.store(Files.newOutputStream(path), comments);
    }

    /**
     * Gets the value from the associated key in the configuration file. If the
     * file does not exist, it's automatically created.
     *
     * @param path         path file to get the configuration
     * @param key          key to get the value
     * @param defaultValue default value to return if is not in the
     *                     configuration file yet
     *
     * @return the value from the associated key or defaultValue.toString() if
     * was not found
     *
     * @throws IOException if an I/O error occurs
     */
    public static String get(
        Path path,
        String key,
        Object defaultValue
    ) throws IOException {
        final Properties properties = new Properties();
        createFileIfNotExists(path);
        properties.load(Files.newInputStream(path));
        return properties.getProperty(key, defaultValue.toString());
    }

    private final Path path;
    private final Properties properties;

    /**
     * It loads the given configuration file. If the file does not exist, it's
     * automatically created.
     *
     * @param path path file containing the configuration
     *
     * @throws IOException if an I/O error occurs
     * @see Properties
     */
    public Config(Path path) throws IOException {
        this.path = path;
        this.properties = new Properties();
        createFileIfNotExists(path);
        properties.load(Files.newInputStream(path));
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
     * @param defaultValue default value to return if is not in the
     *                     configuration file yet
     *
     * @return the value from the associated key or defaultValue.toString() if
     * was not found
     */
    public String get(String key, Object defaultValue) {
        return properties.getProperty(key, defaultValue.toString());
    }

    /**
     * Stores the configuration into the destination file. If the file does not
     * exist, it's automatically created.
     *
     * @throws IOException if an I/O error occurs
     * @see #store(String)
     */
    public void store() throws IOException {
        store(null);
    }

    /**
     * Stores the configuration into the destination file. If the file does not
     * exist, it's automatically created.
     *
     * @param comments a description of the property list
     *
     * @throws IOException if an I/O error occurs
     */
    public void store(String comments) throws IOException {
        createFileIfNotExists(path);
        properties.store(Files.newOutputStream(path), comments);
    }

    private static void createFileIfNotExists(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    private static void put(Properties properties, String key, Object value) {
        properties.put(key, value.toString());
    }
}
