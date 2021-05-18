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

package io.github.tobiasbriones.jdesk.ui.view.loading;

import io.github.tobiasbriones.jdesk.ui.view.Panel;
import io.github.tobiasbriones.jdesk.ui.work.LoadingView;
import io.github.tobiasbriones.jdesk.WindowContext;

import javax.swing.*;
import java.awt.*;

/**
 * Loading view made of circles running circularly.
 */
@SuppressWarnings("unused")
public final class CircularCirclesLoadingView extends Panel implements LoadingView {
    private static final long serialVersionUID = -4090727995998929826L;
    private static final Dimension SIZE = new Dimension(36, 36);
    private static final int CIRCLE_SIZE = 12;
    private static final int DELAY = 100;
    private final Timer timer;
    private final Color[] colors;
    private int i;

    /**
     * Default constructor for CircularCirclesLoadingView.
     *
     * @param context window context
     */
    @SuppressWarnings("WeakerAccess")
    public CircularCirclesLoadingView(WindowContext context) {
        super(context);
        this.timer = new Timer(DELAY, e -> repaint());
        this.colors = new Color[] {
            Color.decode("#212121"), Color.decode("#1B5E20"),
            Color.decode("#0D47A1"), Color.decode("#880E4F")
        };
        this.i = -1;

        setPreferredSize(SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;

        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        if (i == -1) {
            return;
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (i >= colors.length) {
            i = 0;
        }
        g2.setColor(colors[i++]);
        g2.fillOval(getWidth() - CIRCLE_SIZE, getHeight() / 2 - CIRCLE_SIZE / 2, CIRCLE_SIZE, CIRCLE_SIZE);
        if (i >= colors.length) {
            i = 0;
        }
        g2.setColor(colors[i++]);
        g2.fillOval(getWidth() / 2 - CIRCLE_SIZE / 2, getHeight() - CIRCLE_SIZE, CIRCLE_SIZE, CIRCLE_SIZE);
        if (i >= colors.length) {
            i = 0;
        }
        g2.setColor(colors[i++]);
        g2.fillOval(0, getHeight() / 2 - CIRCLE_SIZE / 2, CIRCLE_SIZE, CIRCLE_SIZE);
        if (i >= colors.length) {
            i = 0;
        }
        g2.setColor(colors[i++]);
        g2.fillOval(getWidth() / 2 - CIRCLE_SIZE / 2, 0, CIRCLE_SIZE, CIRCLE_SIZE);
        if (i >= colors.length) {
            i = 0;
        }
        i++;
    }

    @Override
    public boolean isRunning() {
        return timer.isRunning();
    }

    @Override
    public void start() {
        if (isRunning()) {
            return;
        }
        i = 1;

        repaint();
        timer.start();
    }

    @Override
    public void end() {
        if (!isRunning()) {
            return;
        }
        timer.stop();
        i = -1;

        repaint();
    }

    /**
     * Sets the circles colors for north, west, south, east respectively.
     *
     * @param color1 circle 1 color
     * @param color2 circle 2 color
     * @param color3 circle 3 color
     * @param color4 circle 4 color
     */
    public void setColors(Color color1, Color color2, Color color3, Color color4) {
        colors[0] = color1;
        colors[1] = color2;
        colors[2] = color3;
        colors[3] = color4;
    }
}
