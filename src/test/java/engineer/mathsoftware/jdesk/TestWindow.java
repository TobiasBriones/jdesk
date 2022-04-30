// Copyright (c) 2022 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk;

import engineer.mathsoftware.jdesk.ui.dialog.AppDialog;
import engineer.mathsoftware.jdesk.ui.view.Panel;
import engineer.mathsoftware.jdesk.ui.view.loading.BarLoadingView;
import engineer.mathsoftware.jdesk.work.AppWorkCallback;
import engineer.mathsoftware.jdesk.work.AppWorker;
import engineer.mathsoftware.jdesk.work.WorkCallback;
import engineer.mathsoftware.jdesk.work.WorkRunnable;

import java.awt.*;

public class TestWindow extends Window {
    private final BarLoadingView loadingView;

    public TestWindow(AppInstance appInstance) {
        super(appInstance);
        this.loadingView = new BarLoadingView(this);
    }

    @Override
    protected void createWindow(Panel panel) {
        final String okStr = getStringResources().get(Strings.OK);
        panel.add(new Label(okStr));
        panel.add(loadingView);
    }

    @Override
    protected void windowCreated() {
        setVisible(true);
        simulateBackgroundWork();
    }

    private void simulateBackgroundWork() {
        final WorkRunnable<String> runnable = () -> {
            Thread.sleep(1500L);
            return "Work completed";

            // Try failing
            // throw new RuntimeException("failed");
        };
        final AppWorkCallback<String> callback = new AppWorkCallback<String>(this) {
            @Override
            public void workFinished(String result) {
                AppDialog.showMessage(
                    TestWindow.this,
                    "Background Work",
                    result,
                    AppDialog.Type.SUCCESS
                );
            }

            @Override
            public void workFailed(Exception exception) {
                // AppWorkCallback automatically manages fails on the GUI app
                super.workFailed(exception);

                // Add further error handling
                System.out.println("Work failed!");
                System.out.println(exception.getMessage());
            }
        };
        final AppWorker<String, Void> task = new AppWorker<>(
            loadingView,
            callback
        );
        task.execute(runnable);
    }
}
