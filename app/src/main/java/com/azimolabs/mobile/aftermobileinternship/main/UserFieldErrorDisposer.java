package com.azimolabs.mobile.aftermobileinternship.main;

import android.content.Context;

import com.azimolabs.mobile.aftermobileinternship.R;
import com.azimolabs.mobile.aftermobileinternship.base.BaseActivity;
import com.azimolabs.mobile.aftermobileinternship.utils.ErrorType;

import javax.inject.Inject;

public class UserFieldErrorDisposer {

    private final Context context;

    @Inject
    public UserFieldErrorDisposer(BaseActivity context) {
        this.context = context;
    }

    public UserFieldError getError(ErrorType type) {
        switch (type) {
            case EMPTY_FIELD:
                return new UserFieldError(type, context.getString(R.string.please_fill_in));
            case UNKNOWN_USER:
                return new UserFieldError(type, context.getString(R.string.user_doesn_t_exist));
            case NO_REPOSITORIES:
                return new UserFieldError(type, context.getString(R.string.user_has_no_repositories));
            default:
                return new UserFieldError(type, context.getString(R.string.connection_error));
        }
    }
}
