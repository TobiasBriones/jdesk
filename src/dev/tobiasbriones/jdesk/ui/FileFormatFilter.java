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

package dev.tobiasbriones.jdesk.ui;

import dev.tobiasbriones.jdesk.io.FileFormat;
import dev.tobiasbriones.jdesk.io.IOFile;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Filters all files with the specified format or extension.
 *
 * @author Tobias Briones
 * @see FileFilter
 */
public final class FileFormatFilter extends FileFilter {
    private final String extension;

    /**
     * Constructs a filter with the specified file format.
     *
     * @param format file format to filter
     */
    public FileFormatFilter(FileFormat format) {
        this.extension = format.toString();
    }

    /**
     * Constructs a filter with the specified file extension.
     *
     * @param extension extension to filter
     */
    public FileFormatFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public String getDescription() {
        return "Files " + "(" + IOFile.EXTENSION_SEPARATOR + extension.toUpperCase() + ")";
    }

    @Override
    public boolean accept(File file) {
        return IOFile.getExtension(file.getName()).equals(extension);
    }
}
