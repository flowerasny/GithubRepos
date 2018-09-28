package com.azimolabs.mobile.aftermobileinternship.main;

import com.azimolabs.mobile.aftermobileinternship.utils.ErrorType;

public class UserFieldError {

    private final ErrorType type;
    private final String errorMessage;

    public UserFieldError(ErrorType type, String errorMessage) {
        this.type = type;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
