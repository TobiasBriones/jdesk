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

package dev.tobiasbriones.jdesk.ui.view;

import dev.tobiasbriones.jdesk.WindowContext;
import dev.tobiasbriones.jdesk.resources.AppStringResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Horizontal form that shows to the user a set of inputs to be filled. It handles the user events with
 * {@link SubmitListener} or {@link FormListener}.
 *
 * @author Tobias Briones
 */
public class Form extends Panel {
    private static final long serialVersionUID = -6321132655015363936L;

    /**
     * Listener invoked when the submit button has been clicked on the form.
     */
    public interface SubmitListener {
        /**
         * Called when form has been submitted.
         */
        void onSubmit();
    }

    /**
     * Listener to handel the result of the form.
     *
     * @see SubmitListener
     */
    public interface FormListener extends SubmitListener {
        /**
         * Called when the form has been cancelled.
         */
        void onCancel();
    }

    private static final class InputEnterListener implements ActionListener {
        private final List<Component> inputs;
        private final Button submitButton;

        private InputEnterListener(List<Component> inputs, Button submitButton) {
            this.inputs = inputs;
            this.submitButton = submitButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = 0;

            for (Component input : inputs) {
                if (e.getSource() == input) {
                    if ((i + 1) < inputs.size()) {
                        final Component nextInput = inputs.get(i + 1);

                        if (nextInput instanceof InputText || nextInput instanceof PasswordInputText) {
                            if (nextInput instanceof InputText) {
                                nextInput.requestFocus();
                            }
                            else {
                                nextInput.requestFocus();
                            }
                        }
                    }
                    else {
                        submitButton.doClick();
                    }
                    break;
                }
                i++;
            }
        }
    }

    private final List<Component> inputs;
    private final GridBagConstraints gbc;
    private final Button submitButton;
    private final Button cancelButton;
    private final TextLabel errorLabel;
    private final transient WindowContext context;
    private final transient InputEnterListener iel;

    /**
     * Creates an empty form. To set it use {@link Form#addInput(int, Component)}.
     *
     * @param context  window context
     * @param titleRes form title text resource
     */
    public Form(WindowContext context, int titleRes) {
        super(context);
        this.context = context;
        this.inputs = new ArrayList<>();
        this.gbc = new GridBagConstraints();
        this.submitButton = new Button(context);
        this.cancelButton = new Button(context);
        this.errorLabel = new TextLabel(context);
        this.iel = new InputEnterListener(inputs, submitButton);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.left = 5;
        gbc.insets.right = 5;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(context.getStringResources().get(titleRes)));
    }

    /**
     * Sets the error text to be displayed on the form error label.
     *
     * @param error error
     */
    public final void setErrorText(String error) {
        errorLabel.setText(error);
    }

    /**
     * Sets the error text to be displayed on the form error label.
     *
     * @param errorTextRes error text resource
     */
    public final void setErrorText(int errorTextRes) {
        errorLabel.setText(context.getStringResources().get(errorTextRes));
    }

    /**
     * Sets the passed arguments as the content of all the {@link InputText} and {@link PasswordInputText} added to
     * the form in the order they were added, form the first one until it finishes.
     *
     * @param texts texts for the input text or password input text on this form
     */
    public final void setInputTexts(String... texts) {
        int i = 0;

        for (Component input : inputs) {
            if (i >= texts.length) {
                break;
            }
            if (input instanceof InputText || input instanceof PasswordInputText) {
                if (input instanceof InputText) {
                    ((InputText) input).setText(texts[i]);
                }
                else {
                    ((PasswordInputText) input).setText(texts[i]);
                }
                i++;
            }
        }
        enableSubmit();
        setErrorText("");
    }

    @Override
    public void requestFocus() {
        if (inputs.size() > 0) {
            inputs.get(0).requestFocus();
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (Component input : inputs) {
            input.setEnabled(enabled);
        }
        cancelButton.setEnabled(enabled);
        submitButton.setEnabled(enabled);
    }

    /**
     * Adds the input to the form with a label to show above it.
     *
     * @param labelRes label text resource to show on this input
     * @param input    component to place as the input, for example {@link InputText} or any other {@link Component}
     */
    public final void addInput(int labelRes, Component input) {
        final TextLabel label = new TextLabel(context, context.getStringResources().get(labelRes));

        inputs.add(input);
        if (input instanceof InputText || input instanceof PasswordInputText) {
            if (input instanceof InputText) {
                ((InputText) input).addActionListener(iel);
            }
            else {
                ((PasswordInputText) input).addActionListener(iel);
            }
        }
        label.setForeground(context.getAppStyle().getSecondaryTextColor());
        gbc.insets.top = 2;
        gbc.insets.bottom = 2;
        add(label, gbc);
        gbc.gridy++;
        gbc.insets.top = 2;
        gbc.insets.bottom = 5;
        add(input, gbc);
        gbc.gridy++;
    }

    /**
     * Adds the submit button to the form. It has to be called after adding all the inputs with
     * {@link Form#addInput(int, Component)} to place the button properly. This button will trigger
     * {@link SubmitListener#onSubmit()}.
     *
     * @param submitTextRes submit button text resource
     * @param l             submit listener
     *
     * @see SubmitListener
     */
    public final void addSubmitButton(int submitTextRes, SubmitListener l) {
        gbc.insets.top = 2;
        gbc.insets.bottom = 5;
        submitButton.setText(context.getStringResources().get(submitTextRes));
        submitButton.addActionListener(e -> {
            submitButton.setEnabled(false);
            setErrorText("");
            l.onSubmit();
        });
        add(submitButton, gbc);
        gbc.gridy++;
    }

    /**
     * Adds cancel and submit buttons to the form specifying if put the two buttons horizontally or vertically.
     * It has to be called after adding all the inputs with {@link Form#addInput(int, Component)} to place the buttons
     * properly. The cancel button will trigger {@link FormListener#onCancel()} and the submit button
     * {@link FormListener#onSubmit()}.
     *
     * @param cancelTextRes cancel button text resource
     * @param submitTextRes submit button text resource
     * @param l             form listener
     * @param horizontal    put the bottoms horizontally
     */
    public final void addCancelSubmitButtons(int cancelTextRes, int submitTextRes, FormListener l, boolean horizontal) {
        if (l == null) {
            throw new NullPointerException("FormListener is null");
        }
        final ActionListener al = e -> {
            if (e.getSource() == submitButton) {
                submitButton.setEnabled(false);
                setErrorText("");
                l.onSubmit();
            }
            else {
                l.onCancel();
            }
        };
        gbc.insets.top = 2;
        gbc.insets.bottom = 5;
        gbc.gridwidth = 1;
        cancelButton.setText(context.getStringResources().get(cancelTextRes));
        cancelButton.addActionListener(al);
        submitButton.setText(context.getStringResources().get(submitTextRes));
        submitButton.addActionListener(al);
        if (horizontal) {
            add(cancelButton, gbc);
            gbc.gridx++;
            add(submitButton, gbc);
        }
        else {
            add(submitButton, gbc);
            gbc.gridy++;
            add(cancelButton, gbc);
        }
        gbc.gridy++;
    }

    /**
     * Adds cancel and submit buttons to the form.
     * It has to be called after adding all the inputs with {@link Form#addInput(int, Component)} to place the buttons
     * properly. The cancel button will trigger {@link FormListener#onCancel()} and the submit button
     * {@link FormListener#onSubmit()}.
     *
     * @param cancelTextRes cancel button text resource
     * @param submitTextRes submit button text resource
     * @param l             form listener
     */
    public final void addCancelSubmitButtons(int cancelTextRes, int submitTextRes, FormListener l) {
        addCancelSubmitButtons(cancelTextRes, submitTextRes, l, true);
    }

    /**
     * Adds cancel and submit buttons to the form specifying if put the two buttons horizontally or vertically.
     * It has to be called after adding all the inputs with {@link Form#addInput(int, Component)} to place the buttons
     * properly. The cancel button will trigger {@link FormListener#onCancel()} and the submit button
     * {@link FormListener#onSubmit()}.
     *
     * @param submitTextRes submit button text resource
     * @param l             form listener
     * @param horizontal    put the bottoms horizontally
     */
    public final void addCancelSubmitButtons(int submitTextRes, FormListener l, boolean horizontal) {
        addCancelSubmitButtons(AppStringResources.CANCEL, submitTextRes, l, horizontal);
    }

    /**
     * Adds cancel and submit buttons to the form.
     * It has to be called after adding all the inputs with {@link Form#addInput(int, Component)} to place the buttons
     * properly. The cancel button will trigger {@link FormListener#onCancel()} and the submit button
     * {@link FormListener#onSubmit()}.
     *
     * @param submitTextRes submit button text resource
     * @param l             form listener
     */
    public final void addCancelSubmitButtons(int submitTextRes, FormListener l) {
        addCancelSubmitButtons(AppStringResources.CANCEL, submitTextRes, l, true);
    }

    /**
     * Adds the error label to the form to display user input errors or fails. It has to be called in the end after
     * setting the form cancel/submit buttons.
     */
    public final void addErrorLabel() {
        errorLabel.setForeground(context.getAppStyle().getErrorTextColor());
        add(errorLabel, gbc);
    }

    /**
     * When submitting the form disables the submit button, this methods is used if is needed to enable again
     * the submit button to continue using the form.
     */
    public final void enableSubmit() {
        submitButton.setEnabled(true);
    }

    /**
     * Returns <code>true</code> if and only if all the {@link InputText} and {@link PasswordInputText} added to the
     * form has a content set, that is, a non-empty value considering trim.
     *
     * @return <code>true</code> if and only if all the {@link InputText} and {@link PasswordInputText} in the form
     * are set.
     */
    public final boolean checkAllInputText() {
        for (Component input : inputs) {
            if (input instanceof InputText || input instanceof PasswordInputText) {
                if (input instanceof InputText) {
                    if (((InputText) input).getText().trim().isEmpty()) {
                        return false;
                    }
                }
                else {
                    final char[] text = ((PasswordInputText) input).getPassword();
                    final boolean isEmpty = new String(text).trim().isEmpty();

                    for (int i = 0; i < text.length; i++) {
                        text[i] = 0;
                    }
                    if (isEmpty) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Clears all the {@link InputText} and {@link PasswordInputText} added to the form. Other inputs added has to be
     * manually cleared.
     */
    public final void clear() {
        for (Component input : inputs) {
            if (input instanceof InputText || input instanceof PasswordInputText) {
                if (input instanceof InputText) {
                    ((InputText) input).setText("");
                }
                else {
                    ((PasswordInputText) input).setText("");
                }
            }
        }
        enableSubmit();
        setErrorText("");
    }

    /**
     * Triggers submit button click and eventually the submit event.
     */
    public final void submit() {
        submitButton.doClick();
    }

    /**
     * Triggers the cancel button click and eventually the cancel event.
     */
    public final void cancel() {
        cancelButton.doClick();
    }
}
