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

/**
 * Class of tools for helping to construct the user interface.
 *
 * @author Tobias Briones
 */
public final class Tools {
    /**
     * Returns the string within the given bounds. If <code>maxLength</code> is less than or equal to 3 the same text
     * is returned, if the text length is less than or equal to <code>maxLength</code> then is returned as well.
     * If the text length is greater than <code>maxLength</code> then the first <code>maxLength</code> characters
     * of the text plus the ellipsis dots are returned.
     *
     * @param text      text
     * @param maxLength maximum length
     *
     * @return the string within the given bounds and with ellipsis if needed
     */
    public static String getEllipsisText(String text, int maxLength) {
        if (maxLength <= 3) {
            return text;
        }
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength) + "...";
    }

    private Tools() {}
}
