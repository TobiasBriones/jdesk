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

package engineer.mathsoftware.jdesk.ui.view;

import engineer.mathsoftware.jdesk.WindowContext;

import java.awt.*;

/**
 * Empty view which can be added to a container to space components, it can come
 * in handy as a trick.
 *
 * @author Tobias Briones
 */
public final class SpacingView extends Panel {
    private static final long serialVersionUID = 2038750229780540125L;

    public enum Orientation { HORIZONTAL, VERTICAL }

    /**
     * Constructor for SpacingView with width and height.
     *
     * @param context window context
     * @param width   view width
     * @param height  view height
     */
    public SpacingView(WindowContext context, int width, int height) {
        super(context);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.cyan);
    }

    /**
     * Constructor for SpacingView with size and orientation.
     *
     * @param context     window context
     * @param size        view dimension, depending on the orientation this is
     *                    the view width or height, the other dimension will be
     *                    1px
     * @param orientation view orientation
     */
    public SpacingView(
        WindowContext context,
        int size,
        Orientation orientation
    ) {
        this(
            context,
            (orientation == Orientation.HORIZONTAL) ? size : 1,
            (orientation == Orientation.VERTICAL) ? size : 1
        );
    }

    /**
     * Default constructor for SpacingView, it sets an horizontal SpacingView
     * with the specified width.
     *
     * @param context window context
     * @param width   view width
     */
    public SpacingView(WindowContext context, int width) {
        this(context, width, Orientation.HORIZONTAL);
    }
}
