package com.groupeisi.etudiant.utils.enums;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SexeEnumValidator implements ConstraintValidator<SexeEnumConstraint, SexeEnum> {

    @Override
    public boolean isValid(SexeEnum sexeEnum, ConstraintValidatorContext context) {
        return sexeEnum != null && (sexeEnum == SexeEnum.FEMME || sexeEnum == SexeEnum.HOMME);
    }
}
