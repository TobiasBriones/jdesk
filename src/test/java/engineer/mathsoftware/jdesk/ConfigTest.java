// Copyright (c) 2022 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigTest {
    private static final Path configPath = Paths.get(
        "src", "test", "config.properties"
    );

    ConfigTest() {}

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(configPath);
    }

    @Test
    void testGeneralFunctionality() throws IOException {
        final Config config1 = new Config(configPath);
        final String expected = "value";
        config1.put("key", expected);
        config1.store();

        final Config config2 = new Config(configPath);
        final String actual = config2.get("key", "");
        assertEquals(
            expected,
            actual,
            "Read value should equals " + expected
        );
    }

    @AfterAll
    static void afterAll() throws IOException {
        Files.deleteIfExists(configPath);
    }
}