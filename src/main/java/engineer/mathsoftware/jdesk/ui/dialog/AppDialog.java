// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.dialog;

import engineer.mathsoftware.jdesk.Window;
import engineer.mathsoftware.jdesk.WindowContext;
import engineer.mathsoftware.jdesk.resources.AppStringResources;
import engineer.mathsoftware.jdesk.resources.StringResourceId;
import engineer.mathsoftware.jdesk.resources.StringResources;
import engineer.mathsoftware.jdesk.ui.view.InputText;
import engineer.mathsoftware.jdesk.ui.view.PasswordInputText;
import engineer.mathsoftware.jdesk.ui.view.TextLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Factory for application dialogs.
 *
 * @author Tobias Briones
 */
public final class AppDialog {
    private static final Insets DIALOG_PADDING = new Insets(0, 10, 5, 10);
    private static final String ICON_INFO = "icons/ic_info_message.png";
    private static final String ICON_WARNING = "icons/ic_warning_message.png";
    private static final String ICON_ERROR = "icons/ic_error_message.png";
    private static final String ICON_PASSWORD = "icons/ic_password_message.png";
    private static final int ICON_SIZE = 48;
    private static final int ICON_RIGHT_MARGIN = 10;

    public enum Type { INFO, SUCCESS, WARNING, ERROR, FAIL }

    public enum ConfirmResult { RESULT_OK, RESULT_CANCEL }

    /**
     * Shows a message dialog with title, message and icon depending on {@code
     * type} argument.
     *
     * @param window window to attach the dialog
     * @param title  dialog title
     * @param msg    dialog message
     * @param type   dialog type
     */
    public static void showMessage(
        Window window,
        String title,
        String msg,
        Type type
    ) {
        final StringResources sr = window.getStringResources();
        final DialogPanel panel = new DialogPanel(window);
        final TextLabel titleLabel = getTitleLabel(
            window,
            title,
            (type != null)
        );
        final TextLabel msgLabel = getMessageLabel(window, msg);
        final String okStr = sr.get(AppStringResources.OK);
        final OptionButton okButton = new OptionButton(window, okStr);
        final ActionPanel actionPanel = new ActionPanel(window, okButton);
        final Dialog dialog = new Dialog(window);
        final Icon icon = (type != null) ? loadIcon(type) : null;
        final ActionListener l = e -> dialog.dispose();
        okButton.addActionListener(l);
        panel.setLayout(new BorderLayout());
        panel.add(titleLabel, BorderLayout.PAGE_START);
        panel.add(msgLabel, BorderLayout.CENTER);
        panel.add(actionPanel, BorderLayout.PAGE_END);
        if (icon != null) {
            final TextLabel iconLabel = new TextLabel(window, icon);
            iconLabel.setBorder(new EmptyBorder(0, 0, 0, ICON_RIGHT_MARGIN));
            panel.add(iconLabel, BorderLayout.LINE_START);
        }
        dialog.setView(panel, DIALOG_PADDING);
        dialog.getRootPane().setDefaultButton(okButton);
        dialog.setVisible(true);
    }

    /**
     * Shows a message dialog with title, message and icon depending on {@code
     * type} argument.
     *
     * @param window   window to attach the dialog
     * @param titleRes dialog title resource
     * @param msgRes   dialog message resource
     * @param type     dialog type
     */
    public static void showMessage(
        Window window,
        StringResourceId titleRes,
        StringResourceId msgRes,
        Type type
    ) {
        final String title = window.getStringResources().get(titleRes);
        final String msg = window.getStringResources().get(msgRes);
        showMessage(window, title, msg, type);
    }

    /**
     * Shows a message dialog with message and default title and icon depending
     * on {@code type} argument.
     *
     * @param window window to attach the dialog
     * @param msg    dialog message
     * @param type   dialog type
     */
    public static void showMessage(
        Window window,
        String msg,
        Type type
    ) {
        final int stringPosition =
            AppStringResources.INFORMATION.getId() + type.ordinal();
        // final String title = window.getStringResources().get(stringPosition);
        // TODO FIX title resource implementation
        showMessage(window, "", msg, type);
    }

    /**
     * Shows a message dialog with message and default title depending on {@code
     * type} argument.
     *
     * @param window window to attach the dialog
     * @param msgRes dialog message resource
     * @param type   dialog type
     */
    public static void showMessage(
        Window window,
        StringResourceId msgRes,
        Type type
    ) {
        final String msg = window.getStringResources().get(msgRes);
        showMessage(window, msg, type);
    }

    /**
     * Shows a message dialog with title and message.
     *
     * @param window window to attach the dialog
     * @param title  dialog title
     * @param msg    dialog message
     */
    public static void showMessage(
        Window window,
        String title,
        String msg
    ) {
        showMessage(window, title, msg, null);
    }
    // -------------------- MESSAGE DIALOG -------------------- //

    /**
     * Shows a message dialog with title and message.
     *
     * @param window   window to attach the dialog
     * @param titleRes dialog title resource
     * @param msgRes   dialog message resource
     */
    public static void showMessage(
        Window window,
        StringResourceId titleRes,
        StringResourceId msgRes
    ) {
        final String title = window.getStringResources().get(titleRes);
        final String msg = window.getStringResources().get(msgRes);
        showMessage(window, title, msg);
    }

    /**
     * Shows a message dialog with empty title and with message.
     *
     * @param window window to attach the dialog
     * @param msg    dialog message
     */
    public static void showMessage(
        Window window,
        String msg
    ) {
        showMessage(window, "", msg, null);
    }

    /**
     * Shows a message dialog with empty title and with message.
     *
     * @param window window to attach the dialog
     * @param msgRes dialog message resource
     */
    public static void showMessage(
        Window window,
        StringResourceId msgRes
    ) {
        final String msg = window.getStringResources().get(msgRes);
        showMessage(window, msg);
    }

    /**
     * Shows a confirm dialog with title, message and the string for the
     * accepted button.
     *
     * @param window   window to attach the dialog
     * @param title    dialog title
     * @param msg      dialog message
     * @param okAction text for positive action
     *
     * @return RESULT_OK if and only if the user accepts this confirm dialog
     * request
     */
    public static ConfirmResult showConfirm(
        Window window,
        String title,
        String msg,
        String okAction
    ) {
        final StringResources sr = window.getStringResources();
        final DialogPanel panel = new DialogPanel(window);
        final TextLabel titleLabel = getTitleLabel(window, title, false);
        final TextLabel msgLabel = getMessageLabel(window, msg);
        final String cancelStr = sr.get(AppStringResources.CANCEL);
        final OptionButton cancelButton = new OptionButton(window, cancelStr);
        final OptionButton okButton = new OptionButton(window, okAction);
        final ActionPanel actionPanel = new ActionPanel(
            window,
            cancelButton,
            okButton
        );
        final Dialog dialog = new Dialog(window);
        final ActionListener l = e -> {
            dialog.dispose();
            if (e.getSource() == okButton) {
                dialog.getRootPane()
                      .putClientProperty("result", ConfirmResult.RESULT_OK);
            }
            else if (e.getSource() == cancelButton) {
                dialog.getRootPane()
                      .putClientProperty("result", ConfirmResult.RESULT_CANCEL);
            }
        };
        final ConfirmResult result;
        cancelButton.addActionListener(l);
        okButton.addActionListener(l);
        panel.setLayout(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(msgLabel, BorderLayout.CENTER);
        panel.add(actionPanel, BorderLayout.SOUTH);
        dialog.setView(panel, DIALOG_PADDING);
        dialog.getRootPane().setDefaultButton(cancelButton);
        dialog.setVisible(true);
        result = (ConfirmResult) dialog.getRootPane()
                                       .getClientProperty("result");
        return (result != null) ? result : ConfirmResult.RESULT_CANCEL;
    }

    /**
     * Shows a confirm dialog with title, message and the string for the
     * accepted button.
     *
     * @param window      window to attach the dialog
     * @param titleRes    dialog title resource
     * @param msgRes      dialog message resource
     * @param okActionRes text resource for positive action
     *
     * @return RESULT_OK if and only if the user accepts this confirm dialog
     * request
     */
    public static ConfirmResult showConfirm(
        Window window,
        StringResourceId titleRes,
        StringResourceId msgRes,
        StringResourceId okActionRes
    ) {
        final String title = window.getStringResources().get(titleRes);
        final String msg = window.getStringResources().get(msgRes);
        final String okAction = window.getStringResources().get(okActionRes);
        return showConfirm(window, title, msg, okAction);
    }

    /**
     * Shows a confirm dialog with title and message.
     *
     * @param window window to attach the dialog
     * @param title  dialog title
     * @param msg    dialog message
     *
     * @return RESULT_OK if and only if the user accepts this confirm dialog
     * request
     */
    public static ConfirmResult showConfirm(
        Window window,
        String title,
        String msg
    ) {
        final String okStr = window.getStringResources()
                                   .get(AppStringResources.OK);
        return showConfirm(window, title, msg, okStr);
    }

    /**
     * Shows a confirm dialog with title and message.
     *
     * @param window   window to attach the dialog
     * @param titleRes dialog title resource
     * @param msgRes   dialog message resource
     *
     * @return RESULT_OK if and only if the user accepts this confirm dialog
     * request
     */
    public static ConfirmResult showConfirm(
        Window window,
        StringResourceId titleRes,
        StringResourceId msgRes
    ) {
        final String title = window.getStringResources().get(titleRes);
        final String msg = window.getStringResources().get(msgRes);
        return showConfirm(window, title, msg);
    }

    /**
     * Shows a confirm dialog with message.
     *
     * @param window window to attach the dialog
     * @param msg    dialog message
     *
     * @return RESULT_OK if and only if the user accepts this confirm dialog
     * request
     */
    public static ConfirmResult showConfirm(
        Window window,
        String msg
    ) {
        final String title = window.getStringResources()
                                   .get(AppStringResources.CONFIRM);
        return showConfirm(window, title, msg);
    }
    // -------------------- CONFIRM DIALOG -------------------- //

    /**
     * Shows a confirm dialog with message.
     *
     * @param window window to attach the dialog
     * @param msgRes dialog message resource
     *
     * @return RESULT_OK if and only if the user accepts this confirm-dialog
     * request
     */
    public static ConfirmResult showConfirm(
        Window window,
        StringResourceId msgRes
    ) {
        final String msg = window.getStringResources().get(msgRes);
        return showConfirm(window, msg);
    }

    /**
     * Shows an input dialog to request the user a string answer, with title,
     * message and default input text.
     *
     * @param window  window to attach the dialog
     * @param title   dialog title
     * @param msg     dialog message
     * @param content default text to show on the input
     *
     * @return the text entered by the user
     */
    public static String showInput(
        Window window,
        String title,
        String msg,
        Object content
    ) {
        return showInput(
            window,
            title,
            msg,
            content.toString(),
            new InputText(window, 20),
            false
        );
    }

    /**
     * Shows an input dialog to request the user a string answer, with title,
     * message and default input text.
     *
     * @param window   window to attach the dialog
     * @param titleRes dialog title resource
     * @param msgRes   dialog message resource
     * @param content  default text to show on the input
     *
     * @return the text entered by the user
     */
    public static String showInput(
        Window window,
        StringResourceId titleRes,
        StringResourceId msgRes,
        Object content
    ) {
        final String title = window.getStringResources().get(titleRes);
        final String msg = window.getStringResources().get(msgRes);
        return showInput(window, title, msg, content);
    }

    /**
     * Shows an input dialog to request the user a string answer, with title and
     * message.
     *
     * @param window window to attach the dialog
     * @param title  dialog title
     * @param msg    dialog message
     *
     * @return the text entered by the user
     */
    public static String showInput(
        Window window,
        String title,
        String msg
    ) {
        return showInput(window, title, msg, "");
    }

    /**
     * Shows an input dialog to request the user a string answer, with title and
     * message.
     *
     * @param window   window to attach the dialog
     * @param titleRes dialog title resource
     * @param msgRes   dialog message resource
     *
     * @return the text entered by the user
     */
    public static String showInput(
        Window window,
        StringResourceId titleRes,
        StringResourceId msgRes
    ) {
        final String title = window.getStringResources().get(titleRes);
        final String msg = window.getStringResources().get(msgRes);
        return showInput(window, title, msg);
    }

    /**
     * Shows an input dialog to request the user a string answer, with default
     * title, with message and default input text.
     *
     * @param window  window to attach the dialog
     * @param msg     dialog message
     * @param content default text to show on the input
     *
     * @return the text entered by the user
     */
    public static String showInput(
        Window window,
        String msg,
        Object content
    ) {
        final String title = window.getStringResources()
                                   .get(AppStringResources.INPUT);
        return showInput(window, title, msg, content);
    }
    // -------------------- INPUT DIALOG -------------------- //

    /**
     * Shows an input dialog to request the user a string answer, with default
     * title, with message and default input text.
     *
     * @param window  window to attach the dialog
     * @param msgRes  dialog message resource
     * @param content default text to show on the input
     *
     * @return the text entered by the user
     */
    public static String showInput(
        Window window,
        StringResourceId msgRes,
        Object content
    ) {
        final String msg = window.getStringResources().get(msgRes);
        return showInput(window, msg, content);
    }

    /**
     * Shows an input dialog to request the user a string answer, with default
     * title and with message.
     *
     * @param window window to attach the dialog
     * @param msg    dialog message
     *
     * @return the text entered by the user
     */
    public static String showInput(
        Window window,
        String msg
    ) {
        final String title = window.getStringResources()
                                   .get(AppStringResources.INPUT);
        return showInput(window, title, msg, "");
    }

    /**
     * Shows an input dialog to request the user a string answer, with default
     * title and with message.
     *
     * @param window window to attach the dialog
     * @param msgRes dialog message resource
     *
     * @return the text entered by the user
     */
    public static String showInput(
        Window window,
        StringResourceId msgRes
    ) {
        final String msg = window.getStringResources().get(msgRes);
        return showInput(window, msg);
    }

    /**
     * Shows a password input dialog to request the user a secret password, with
     * title and message.
     *
     * @param window window to attach the dialog
     * @param title  dialog title
     * @param msg    dialog message
     *
     * @return the string containing the user entered password
     */
    public static String showPasswordInput(
        Window window,
        String title,
        String msg
    ) {
        return showInput(
            window,
            title,
            msg,
            "",
            new PasswordInputText(window, 20),
            true
        );
    }

    /**
     * Shows a password input dialog to request the user a secret password, with
     * title and message.
     *
     * @param window   window to attach the dialog
     * @param titleRes dialog title resource
     * @param msgRes   dialog message resource
     *
     * @return the string containing the user entered password
     */
    public static String showPasswordInput(
        Window window,
        StringResourceId titleRes,
        StringResourceId msgRes
    ) {
        final String title = window.getStringResources().get(titleRes);
        final String msg = window.getStringResources().get(msgRes);
        return showPasswordInput(window, title, msg);
    }

    /**
     * Shows a password input dialog to request the user a secret password, with
     * default title and with message.
     *
     * @param window window to attach the dialog
     * @param msg    dialog message
     *
     * @return the string containing the user entered password
     */
    public static String showPasswordInput(
        Window window,
        String msg
    ) {
        final String title = window.getStringResources()
                                   .get(AppStringResources.PASSWORD);
        return showPasswordInput(window, title, msg);
    }

    /**
     * Shows a password input dialog to request the user a secret password, with
     * default title and with message.
     *
     * @param window window to attach the dialog
     * @param msgRes dialog message resource
     *
     * @return the string containing the user entered password
     */
    public static String showPasswordInput(
        Window window,
        StringResourceId msgRes
    ) {
        final String msg = window.getStringResources().get(msgRes);
        return showPasswordInput(window, msg);
    }

    private AppDialog() {}

    private static Icon loadIcon(Type type) {
        final String iconName;
        switch (type) {
            case INFO:
                iconName = ICON_INFO;
                break;
            case SUCCESS:
                iconName = ICON_INFO;
                break;
            case WARNING:
                iconName = ICON_WARNING;
                break;
            case ERROR:
                iconName = ICON_ERROR;
                break;
            case FAIL:
                iconName = ICON_ERROR;
                break;
            default:
                iconName = ICON_INFO;
                break;
        }
        return new ImageIcon(AppDialog.class.getClassLoader()
                                            .getResource(iconName));
    }

    private static TextLabel getTitleLabel(
        WindowContext context,
        String title,
        boolean hasIcon
    ) {
        final TextLabel label = new TextLabel(context, title);
        label.setFont(context.getAppStyle().getFont().deriveFont(Font.BOLD));
        if (hasIcon) {
            label.setBorder(new EmptyBorder(
                0,
                ICON_SIZE + ICON_RIGHT_MARGIN,
                5,
                0
            ));
        }
        else {
            label.setBorder(new EmptyBorder(0, 0, 5, 0));
        }
        return label;
    }

    private static TextLabel getMessageLabel(
        WindowContext context,
        String msg
    ) {
        final TextLabel label = new TextLabel(context, msg);
        label.setForeground(context.getAppStyle().getSecondaryTextColor());
        return label;
    }

    private static String showInput(
        Window window,
        String title,
        String msg,
        String content,
        JComponent component,
        boolean isPassword
    ) {
        final StringResources sr = window.getStringResources();
        final DialogPanel panel = new DialogPanel(window);
        final DialogPanel center = new DialogPanel(window);
        final TextLabel titleLabel = getTitleLabel(window, title, isPassword);
        final TextLabel msgLabel = getMessageLabel(window, msg);
        final String cancelStr = sr.get(AppStringResources.CANCEL);
        final String okStr = sr.get(AppStringResources.OK);
        final OptionButton cancelButton = new OptionButton(window, cancelStr);
        final OptionButton okButton = new OptionButton(window, okStr);
        final ActionPanel actionPanel = new ActionPanel(
            window,
            cancelButton,
            okButton
        );
        final Dialog dialog = new Dialog(window);
        final ActionListener l = e -> {
            if (e.getSource() == okButton) {
                final String text;
                if (isPassword) {
                    text =
                        new String(((PasswordInputText) component).getPassword());
                }
                else {
                    text = ((InputText) component).getText();
                }
                if (text.trim().isEmpty()) {
                    return;
                }
                dialog.dispose();
                dialog.getRootPane().putClientProperty("input", text);
            }
            else if (e.getSource() == cancelButton) {
                dialog.dispose();
                dialog.getRootPane().putClientProperty("input", null);
            }
        };
        if (isPassword) {
            ((PasswordInputText) component).setText(content);
        }
        else {
            ((InputText) component).setText(content);
        }
        msgLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        cancelButton.addActionListener(l);
        okButton.addActionListener(l);
        center.setLayout(new BorderLayout());
        center.add(msgLabel, BorderLayout.PAGE_START);
        center.add(component, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());
        panel.add(titleLabel, BorderLayout.PAGE_START);
        panel.add(center, BorderLayout.CENTER);
        panel.add(actionPanel, BorderLayout.PAGE_END);
        if (isPassword) {
            final Icon icon =
                new ImageIcon(AppDialog.class.getClassLoader()
                                             .getResource(ICON_PASSWORD));
            final TextLabel iconLabel = new TextLabel(window, icon);
            iconLabel.setBorder(new EmptyBorder(0, 0, 0, ICON_RIGHT_MARGIN));
            panel.add(iconLabel, BorderLayout.LINE_START);
        }
        dialog.setView(panel, DIALOG_PADDING);
        dialog.getRootPane().setDefaultButton(okButton);
        dialog.setVisible(true);
        return (String) dialog.getRootPane().getClientProperty("input");
    }
}
