// Copyright (c) 2022 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.resources;

public interface StringResourceId {
    int getId();

    String getKey();

    static StringResourceId of(int id, String key) {
        return new StringResourceIdImpl(id, key);
    }
}
