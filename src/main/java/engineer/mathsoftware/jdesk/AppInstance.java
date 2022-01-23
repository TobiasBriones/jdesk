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

package engineer.mathsoftware.jdesk;

/**
 * Instance given by the application implementation.
 *
 * @author Tobias Briones
 * @see WindowContext
 */
public interface AppInstance extends WindowContext {
    /**
     * Returns the application configuration file. Mainly used to store settings
     * of the application windows.
     *
     * @return the application configuration file
     */
    String getAppConfigFile();
}
