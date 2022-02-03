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

package engineer.mathsoftware.jdesk.ui.view.validation;

import engineer.mathsoftware.jdesk.WindowContext;
import engineer.mathsoftware.jdesk.ui.style.TextStyle;
import engineer.mathsoftware.jdesk.ui.view.InputText;
import engineer.mathsoftware.jdesk.ui.view.Panel;
import engineer.mathsoftware.jdesk.ui.view.TextLabel;
import engineer.mathsoftware.jdesk.ui.view.validation.validator.Validator;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Input text with complex implementations such as text length counter and input
 * user validations.
 *
 * @author Tobias Briones
 */
public class ComplexInputText extends Panel implements ValidationView {
    private static final long serialVersionUID = 5444537149754102424L;

    /**
     * View used for adding it to a {@link ComplexInputText} to clear the input
     * text.
     */
    public static final class ClearView extends TextLabel {
        private static final long serialVersionUID = -4416773433702042680L;

        public ClearView(WindowContext context) {
            super(context, "x");
            setTextStyle(TextStyle.NORMAL, 10);
        }

        public ClearView(WindowContext context, Color color) {
            this(context);
            setForeground(color);
        }
    }

    private final Panel topPanel;
    private final InputText inputText;
    private final TextLabel lengthLabel;
    private final TextLabel validationLabel;
    private final transient List<Validator<String>> validators;
    private transient ValidationListener validationListener;
    /**
     * Constructor for ComplexInputText.
     *
     * @param context window context
     * @param columns columns for the input text
     */
    public ComplexInputText(WindowContext context, int columns) {
        super(context);
        this.topPanel = new Panel(context);
        this.inputText = new InputText(context, columns);
        this.lengthLabel = new TextLabel(context);
        this.validationLabel = new TextLabel(context);
        this.validators = new ArrayList<>();
        this.validationListener = null;

        config(context);
    }

    /**
     * Default constructor for ComplexInputText.
     *
     * @param context window context
     */
    public ComplexInputText(WindowContext context) {
        this(context, 0);
    }

    private ValidationListener getValidationListener() {
        return validationListener;
    }

    /**
     * Sets the validation listener on this view to trigger input changes to be
     * validated by the application.
     *
     * @param l validation listener
     */
    public final void setValidationListener(ValidationListener l) {
        this.validationListener = l;
    }

    private List<Validator<String>> getValidators() {
        return validators;
    }

    /**
     * Returns the input text of this view.
     *
     * @return the input text of this view
     */
    public final InputText getInputText() {
        return inputText;
    }

    /**
     * Returns the text of the input.
     *
     * @return the text of the input
     */
    public final String getText() {
        return inputText.getText();
    }

    /**
     * Sets the text to show for the input text.
     *
     * @param text text
     */
    public final void setText(String text) {
        inputText.setText(text);
    }

    /**
     * Returns the length of the input text.
     *
     * @return the length of the input text
     */
    public final int getLength() {
        return getText().length();
    }

    private void setLengthText(int length) {
        lengthLabel.setText(String.valueOf(length));
    }

    private void setValidationText(String error) {
        validationLabel.setText(error);
    }

    @Override
    public boolean isInputAccepted() {
        return validationLabel.getText().trim().isEmpty();
    }

    @Override
    public final void setValidationTextStyle(
        TextStyle textStyle,
        int textSize
    ) {
        validationLabel.setTextStyle(textStyle, textSize);
    }

    @Override
    public final void showValidationText(String error) {
        validationLabel.setText(error);
    }

    @Override
    public final void hideValidationText() {
        validationLabel.setText(" ");
    }

    /**
     * Adds a view to clear the input text.
     *
     * @param clearView view to show to clear the input text
     */
    public final void addClearView(Component clearView) {
        topPanel.add(clearView);
        clearView.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setText("");
            }
        });
    }

    public void addValidator(Validator<String> validator) {
        validators.add(validator);
    }

    private void config(WindowContext context) {
        final Border border = inputText.getBorder();
        final GridBagConstraints gbc = new GridBagConstraints();

        inputText.setBorder(new EmptyBorder(2, 0, 2, 0));
        inputText.getDocument()
                 .addDocumentListener(new InputTextDocumentListener(this));
        lengthLabel.setTextStyle(TextStyle.NORMAL, 10);
        lengthLabel.setText("0");
        validationLabel.setForeground(context.getAppStyle()
                                             .getErrorTextColor());
        validationLabel.setBorder(new EmptyBorder(2, 5, 2, 5));
        validationLabel.setText(" ");
        topPanel.setLayout(new GridBagLayout());
        topPanel.setBorder(border);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.98;
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(inputText, gbc);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.02;
        gbc.gridx++;
        topPanel.add(lengthLabel, gbc);
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.PAGE_START);
        add(validationLabel, BorderLayout.CENTER);
    }

    private static final class InputTextDocumentListener implements DocumentListener {
        private final ComplexInputText inputText;

        private InputTextDocumentListener(ComplexInputText inputText) {
            this.inputText = inputText;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {}

        private void update() {
            final String text = inputText.getText();

            inputText.setLengthText(text.length());
            inputText.hideValidationText();
            if (text.isEmpty()) {
                return;
            }
            // Validate
            if (inputText.getValidationListener() != null) {
                final List<Validator<String>> validators =
                    inputText.getValidators();
                boolean checkValidationListener = true;

                // First update all the validators put on the
                // ComplexInputText, at last check for the validation
                // listener
                for (Validator<String> validator : validators) {
                    if (!validator.validate(text)) {
                        checkValidationListener = false;

                        inputText.setValidationText(validator.getErrorText());
                        break;
                    }
                }
                if (checkValidationListener) {
                    // Is left to the app implementation
                    inputText.getValidationListener().onValidate(inputText);
                }
            }
        }
    }
}
