package com.infoshare.mfinance.cli.utils;


import com.infoshare.mfinance.core.utils.TemporaryFoldersProviderUtil;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public final class ValidatorUtil {

    private static ValidatorUtil instance;
    private static Validator validator;

    public static Validator getValidator() {
        return validator;
    }
    static {
        try {
            instance = new ValidatorUtil();
        } catch (Exception e) {
            throw new RuntimeException("Exception was thrown when creating Validator singleton instance.");
        }
    }
    private ValidatorUtil() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        factory.close();
    }

    private static ValidatorUtil getInstance() {
        return instance;
    }


}
