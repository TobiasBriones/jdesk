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

package dev.tobiasbriones.jdesk.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * Implements more methods for the {@link File} API. This class is deprecated
 * since it extends the old {@link File} class and it is not necessary or
 * general purpose enough to keep using.
 *
 * @author Tobias Briones
 * @deprecated
 */
@Deprecated
public final class IOFile extends java.io.File {
    /**
     * Character used to identify the separation between the file name and the
     * file extension.
     */
    @SuppressWarnings("WeakerAccess")
    public static final char EXTENSION_SEPARATOR = '.';
    private static final long serialVersionUID = 7501742095068093888L;

    /**
     * Returns the extension of the file name.
     *
     * @param fileName file name to search its extension
     *
     * @return the extension of this file name or empty if it does not have one
     */
    @SuppressWarnings("WeakerAccess")
    public static String getExtension(String fileName) {
        final int index = fileName.lastIndexOf(EXTENSION_SEPARATOR);
        return (index != -1) ? fileName.substring(index + 1) : "";
    }

    /**
     * Creates a new <code>File</code> instance by converting the given pathname
     * string into an abstract pathname.  If the given string is the empty
     * string, then the result is the empty abstract pathname.
     *
     * @param pathname A pathname string
     *
     * @throws NullPointerException If the <code>pathname</code> argument is
     *                              <code>null</code>
     */
    public IOFile(String pathname) {
        super(pathname);
    }

    /**
     * Creates a new <code>File</code> instance from a parent pathname string
     * and a child pathname string.
     *
     * <p> If <code>parent</code> is <code>null</code> then the new
     * <code>File</code> instance is created as if by invoking the
     * single-argument <code>File</code> constructor on the given
     * <code>child</code> pathname string.
     *
     * <p> Otherwise the <code>parent</code> pathname string is taken to denote
     * a directory, and the <code>child</code> pathname string is taken to
     * denote either a directory or a file.  If the <code>child</code> pathname
     * string is absolute then it is converted into a relative pathname in a
     * system-dependent way.  If <code>parent</code> is the empty string then
     * the new <code>File</code> instance is created by converting
     * <code>child</code> into an abstract pathname and resolving the result
     * against a system-dependent default directory.  Otherwise each pathname
     * string is converted into an abstract pathname and the child abstract
     * pathname is resolved against the parent.
     *
     * @param parent The parent pathname string
     * @param child  The child pathname string
     *
     * @throws NullPointerException If <code>child</code> is <code>null</code>
     */
    public IOFile(String parent, String child) {
        super(parent, child);
    }

    /**
     * Creates a new <code>File</code> instance from a parent abstract pathname
     * and a child pathname string.
     *
     * <p> If <code>parent</code> is <code>null</code> then the new
     * <code>File</code> instance is created as if by invoking the
     * single-argument <code>File</code> constructor on the given
     * <code>child</code> pathname string.
     *
     * <p> Otherwise the <code>parent</code> abstract pathname is taken to
     * denote a directory, and the <code>child</code> pathname string is taken
     * to denote either a directory or a file.  If the <code>child</code>
     * pathname string is absolute then it is converted into a relative pathname
     * in a system-dependent way.  If <code>parent</code> is the empty abstract
     * pathname then the new <code>File</code> instance is created by converting
     * <code>child</code> into an abstract pathname and resolving the result
     * against a system-dependent default directory.  Otherwise each pathname
     * string is converted into an abstract pathname and the child abstract
     * pathname is resolved against the parent.
     *
     * @param parent The parent abstract pathname
     * @param child  The child pathname string
     *
     * @throws NullPointerException If <code>child</code> is <code>null</code>
     */
    public IOFile(java.io.File parent, String child) {
        super(parent, child);
    }

    /**
     * Creates a new {@code File} instance by converting the given {@code file:}
     * URI into an abstract pathname.
     *
     * <p> The exact form of a {@code file:} URI is system-dependent, hence
     * the transformation performed by this constructor is also
     * system-dependent.
     *
     * <p> For a given abstract pathname <i>f</i> it is guaranteed that
     *
     * <blockquote><code>
     * new File(</code><i>&nbsp;f</i><code>.{@link #toURI()
     * toURI}()).equals(</code><i>&nbsp;f</i><code>.{@link #getAbsoluteFile()
     * getAbsoluteFile}())
     * </code></blockquote>
     * <p>
     * so long as the original abstract pathname, the URI, and the new abstract
     * pathname are all created in (possibly different invocations of) the same
     * Java virtual machine.  This relationship typically does not hold,
     * however, when a {@code file:} URI that is created in a virtual machine on
     * one operating system is converted into an abstract pathname in a virtual
     * machine on a different operating system.
     *
     * @param uri An absolute, hierarchical URI with a scheme equal to {@code
     *            "file"}, a non-empty path component, and undefined authority,
     *            query, and fragment components
     *
     * @throws NullPointerException     If {@code uri} is {@code null}
     * @throws IllegalArgumentException If the preconditions on the parameter do
     *                                  not hold
     * @see #toURI()
     * @see URI
     * @since 1.4
     */
    public IOFile(URI uri) {
        super(uri);
    }

    /**
     * Returns the extension of the file denoted by this abstract pathname.
     *
     * @return the extension of this file name or empty if it does not have one
     *
     * @see #getExtension(String)
     */
    @SuppressWarnings("WeakerAccess")
    public String getExtension() {
        return getExtension(getName());
    }

    /**
     * Checks the format of this file.
     *
     * @param extension extension to compare with this file
     *
     * @return true if an only if this file has the passed extension
     *
     * @see #getExtension()
     */
    @SuppressWarnings("WeakerAccess")
    public boolean isFormat(String extension) {
        return getExtension().equals(extension);
    }

    /**
     * Checks the format of this file.
     *
     * @param format file format to compare with this file
     *
     * @return true if an only if this file has the passed format
     *
     * @see #isFormat(String)
     * @see #getExtension()
     */
    public boolean isFormat(FileFormat format) {
        return isFormat(format.toString());
    }

    /**
     * Creates a file denoted by this abstract pathname iff this file does not
     * exist.
     *
     * @throws IOException if the file exists but is not a file or if the file
     *                     does not exist and an I/O error occurs when creating
     *                     the file
     * @see #createNewFile()
     */
    public void createFileIfNotExists() throws IOException {
        if (exists()) {
            if (!isFile()) {
                throw new IOException(
                    "Can't create file. File already exists but is not a file"
                    + " " + this);
            }
            return;
        }
        if (!createNewFile()) {
            throw new IOException("Fail to create file " + this);
        }
    }

    /**
     * Creates a directory named by this abstract pathname iff this file does
     * not exist.
     *
     * @throws IOException if the directory exists but is not a directory or if
     *                     the directory does not exist and an I/O error occurs
     *                     when creating the directory
     * @see #mkdir()
     */
    public void makeDirectoryIfNotExist() throws IOException {
        if (exists()) {
            if (!isDirectory()) {
                throw new IOException(
                    "Can't create directory. File already exists but is not a"
                    + " directory " + this);
            }
            return;
        }
        if (!mkdir()) {
            throw new IOException("Fail to create directory " + this);
        }
    }

    /**
     * Creates the directory named by this abstract pathname, including any
     * necessary but nonexistent parent directories, iff this file does not
     * exist. Note that if this operation fails it may have succeeded in
     * creating some of the necessary parent directories.
     *
     * @throws IOException if the directory does not exist and an I/O error
     *                     occurs when creating the directory
     * @see #mkdirs()
     */
    public void makeDirectoriesIfNotExist() throws IOException {
        if (exists()) {
            if (!isDirectory()) {
                throw new IOException(
                    "Can't create directory. File already exists but is not a"
                    + " directory " + this);
            }
            return;
        }
        if (!mkdirs()) {
            throw new IOException("Fail to create directory(ies) " + this);
        }
    }
}
