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

import dev.tobiasbriones.jdesk.WindowContext;
import dev.tobiasbriones.jdesk.ui.view.Panel;
import dev.tobiasbriones.jdesk.work.LoadingView;

import javax.swing.*;
import java.awt.*;

/**
 * Bar animated to display the loading of a background task.
 *
 * @author Tobias Briones
 */
public final class BarLoadingView extends Panel implements LoadingView {
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_INCREASING_BAR_SPEED = 1;
    public static final float BAR_WIDTH_CHANGE_PER_SECOND_SLOW = 0.5f;
    public static final float BAR_WIDTH_CHANGE_PER_SECOND_NORMAL = 1.0f;
    public static final float BAR_WIDTH_CHANGE_PER_SECOND_FAST = 2.0f;
    private static final long serialVersionUID = 2387093602960521687L;
    private static final int TIME_INTERVAL = 33;
    private static final int HEIGHT = 1;
    private final Timer timer;
    private final Color backgroundColor;
    private final Color barColor;
    private int style;
    private float speed;
    private int x;
    private long lastTime;

    /**
     * Creates a BarLoadingView with normal style and normal speed.
     *
     * @param context window context
     */
    public BarLoadingView(WindowContext context) {
        super(context);
        this.timer = new Timer(TIME_INTERVAL, e -> repaint());
        this.backgroundColor = getBackground();
        this.barColor = context.getAppStyle().getAccentColor();
        this.style = STYLE_NORMAL;
        this.speed = BAR_WIDTH_CHANGE_PER_SECOND_NORMAL;
        this.x = -1;
        this.lastTime = -1;

        setWidth(100);
    }

    /**
     * Sets the bar style.
     *
     * @param style bar style
     */
    public void setStyle(int style) {
        this.style = style;
    }

    /**
     * Sets the speed of the bar.
     *
     * @param speed bar speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Sets the width for the bar.
     *
     * @param width bar width
     */
    public void setWidth(int width) {
        setPreferredSize(new Dimension(width, HEIGHT));
    }

    /**
     * Sets the preferred size only by taking into account the width dimension.
     *
     * @param preferredSize preferred size
     */
    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(new Dimension(preferredSize.width, HEIGHT));
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
        x = 0;
        lastTime = System.currentTimeMillis();

        timer.start();
    }

    @Override
    public void end() {
        if (!timer.isRunning()) {
            return;
        }
        timer.stop();
        x = -1;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        final float time = System.currentTimeMillis() - lastTime;
        final int width = getWidth();
        int xChange;

        if (x == -1) {
            g.setColor(backgroundColor);
            g.clearRect(0, 0, width, HEIGHT);
            return;
        }
        if (style == STYLE_NORMAL) {
            xChange = (int) ((time / 1000) * (speed * width));
        }
        else {
            xChange = (int) ((time / 1000) * speed * x);
        }
        if (xChange == 0) {
            xChange = 1;
        }
        if (x >= width) {
            x = 0;
        }
        else {
            x += xChange;

            if (x > width) {
                x = width;
            }
        }
        g.setColor(backgroundColor);
        g.clearRect(0, 0, width, HEIGHT);
        g.setColor(barColor);
        g.fillRect(0, 0, x, HEIGHT);
        lastTime = System.currentTimeMillis();
    }
}
