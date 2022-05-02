// Copyright (c) 2022 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Convert {
    public static void main(String[] args) {
        final int initialId = 11;
        final StringBuilder constants = new StringBuilder();
        final StringBuilder values = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("strs"))) {
            final String str = Files.readString(Paths.get("StringResources"));
            final String[] strValues = str.split("\n");
            String line = br.readLine();
            int id = initialId;
            while (line != null) {
                if (line.length() == 0) {
                    line = br.readLine();
                    continue;
                }
                line = line.trim();
                line = line.substring(24);
                int firstSpace = line.indexOf(' ');
                String key = line.substring(0, firstSpace);
                String constantValue =
                    "public static final StringResourceId " + key + " = "
                    + "StringResourceId.of(" + id + ", " + "\"" + key + "\");";
                String value = key + " = " + strValues[id - initialId];
                // Print Strings.java
                constants.append(constantValue);
                constants.append("\n");
                // Print strings.properties
                values.append(value);
                values.append("\n");
                line = br.readLine();
                id++;
            }
            // Print what you want to get HERE //
            // System.out.println(constants);
            System.out.println(values);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Convert() {}
}
