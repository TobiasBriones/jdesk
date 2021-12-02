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

/**
 * Loading view made of a circular transitions.
 */
@SuppressWarnings("unused")
public final class CircularTransitionLoadingView extends Panel implements LoadingView {
    private static final long serialVersionUID = -5698502420906253195L;
    private static final Dimension NORMAL_SIZE = new Dimension(36, 36);
    private static final Dimension BIG_SIZE = new Dimension(48, 48);
    private static final int DELAY = 33;
    private final Timer timer;
    private Color color;
    private int z;
    private boolean w;

    /**
     * Default constructor for CircularTransitionLoadingView.
     *
     * @param context window context
     */
    @SuppressWarnings("WeakerAccess")
    public CircularTransitionLoadingView(WindowContext context) {
        super(context);
        this.timer = new Timer(DELAY, e -> {
            repaint();
            w = (z < (int) -(0.38 * getWidth())) || (!(z > (int) (0.62 * getWidth())) && w);
            z = (w) ? (z + 3) : (z - 3);

            if (z > getWidth() / 2) {
                z--;
            }
            else if (z < getWidth() / 2) {
                z++;
            }
        });
        this.color = context.getAppStyle().getAccentColor();
        this.z = 0;
        this.w = true;

        setPreferredSize(NORMAL_SIZE);
    }

    /**
     * Sets the view color.
     *
     * @param color view color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isRunning()) {
            return;
        }
        final Graphics2D g2 = (Graphics2D) g;

        if (z == -1) {
            g2.setColor(getBackground());
            g2.fillRect(z, z, getWidth() + z, getHeight() + z);
            z++;
            return;
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillOval(0, 0, getWidth(), getHeight());
        g2.setColor(getBackground());
        g2.fillOval(z, z, getWidth() + z, getHeight() + z);
        g2.setColor(getBackground());
        g2.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
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
        z = 0;
        w = true;

        repaint();
        timer.start();
    }

    @Override
    public void end() {
        if (!isRunning()) {
            return;
        }
        timer.stop();
        z = -1;
        w = true;

        repaint();
    }

    /**
     * Sets a big size for the view.
     */
    public void setBig() {
        setPreferredSize(BIG_SIZE);
    }
}
