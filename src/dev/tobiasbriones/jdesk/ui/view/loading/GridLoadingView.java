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

package dev.tobiasbriones.jdesk.ui.view.loading;

import dev.tobiasbriones.jdesk.ui.view.Panel;
import dev.tobiasbriones.jdesk.ui.work.LoadingView;
import dev.tobiasbriones.jdesk.WindowContext;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Loading view with a grid of random running little circles.
 *
 * @author Tobias Briones
 */
public final class GridLoadingView extends Panel implements LoadingView {
    private static final long serialVersionUID = -1113850587102601895L;
    private static final Dimension SIZE = new Dimension(36, 36);
    private static final Random RANDOM = new Random();
    private static final int SQUARE_SIZE = 4;
    private static final int MARGIN = 4;
    private static final int DELAY_MS = 150;
    private final Color backgroundColor;
    private final Timer timer;
    private Color color;
    private Color activatedColor;
    private boolean clean;

    /**
     * Default constructor for GridLoadingView.
     *
     * @param context window context
     */
    public GridLoadingView(WindowContext context) {
        super(context);
        this.backgroundColor = context.getAppStyle().getWindowBackgroundColor();
        this.timer = new Timer(DELAY_MS, e -> repaint());
        this.color = context.getAppStyle().getAccentColor().darker();
        this.activatedColor = context.getAppStyle().getAccentColor();
        this.clean = true;

        setPreferredSize(SIZE);
    }

    /**
     * Sets the view squares color which are not activated in the current animation tick.
     *
     * @param color view color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the view squares color which are activated in the current animation tick.
     *
     * @param activatedColor view activatedColor
     */
    public void setActivatedColor(Color activatedColor) {
        this.activatedColor = activatedColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int currentLeft;
        int currentTop;

        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(color);
        if (clean) {
            clean = false;
            return;
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                currentLeft = x * SQUARE_SIZE + 2 * MARGIN * x + MARGIN;
                currentTop = y * SQUARE_SIZE + 2 * MARGIN * y + MARGIN;

                paintSquare(g, currentLeft, currentTop, RANDOM.nextBoolean());
            }
        }
    }

    @Override
    public boolean isRunning() {
        return timer.isRunning();
    }

    @Override
    public void start() {
        if (timer.isRunning()) {
            return;
        }

        repaint();
        timer.start();
    }

    @Override
    public void end() {
        if (!timer.isRunning()) {
            return;
        }

        timer.stop();
        clean = true;

        repaint();
    }

    private void paintSquare(Graphics g, int x, int y, boolean activated) {
        final Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (activated) {
            final int increasedPixels = 2;

            g2.setColor(activatedColor);
            g2.fillOval(
                x - increasedPixels / 2,
                y - increasedPixels / 2,
                SQUARE_SIZE + increasedPixels,
                SQUARE_SIZE + increasedPixels
            );
        }
        else {
            g2.setColor(color);
            g2.fillOval(x, y, SQUARE_SIZE, SQUARE_SIZE);
        }
    }
}
