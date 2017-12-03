package com.infoshareacademy.web.services.validators.classlevel;

public interface FieldsCheck {
    /**
     * FieldsCheck Interface is used for constraint groups sequence management.
     * All Java Bean 'field constraints', that need to be validated before
     * validation of 'class level constraint', must belong to this group.
     * Example of class field annotation: @Pattern(groups = FieldsCheck.class)
     */
}
