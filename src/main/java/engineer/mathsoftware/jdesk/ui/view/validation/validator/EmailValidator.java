// Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/jdesk

package engineer.mathsoftware.jdesk.ui.view.validation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Email validator. This validator does not cover the 100% of email addresses,
 * so it should not be generally trusted and hence some valid email addresses
 * can be checked as invalid.
 *
 * @author Tobias Briones
 */
public final class EmailValidator implements Validator<String> {
    /**
     * Regex to match when validating the input.
     */
    public static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+"
                                             + "(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private String errorText;

    /**
     * Constructor for EmailValidator with error text. This validator does not
     * cover the 100% of email addresses so it should not be generally trusted
     * and hence some valid email addresses can be checked as invalid.
     */
    public EmailValidator(String errorText) {
        setErrorText(errorText);
    }

    /**
     * Default constructor for EmailValidator. This validator does not cover the
     * 100% of email addresses so it should not be generally trusted and hence
     * some valid email addresses can be checked as invalid.
     */
    public EmailValidator() {
        this("");
    }

    @Override
    public String getErrorText() {
        return errorText;
    }

    /**
     * Returns {@code true} if and only if the input has a valid email address
     * structure. It checks the input against the regex {@link
     * EmailValidator#EMAIL_REGEX}. This validator does not cover the 100% of
     * email addresses so it should not be generally trusted and hence some
     * valid email addresses can be checked as invalid.
     *
     * @param input input to check
     *
     * @return {@code true} if and only if the input has a valid email address
     * structure
     */
    @Override
    public boolean validate(String input) {
        final Pattern pattern = Pattern.compile(
            EMAIL_REGEX,
            Pattern.CASE_INSENSITIVE
        );
        final Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
