package com.beloved.common.valid.annotation;

import com.beloved.common.service.BaseEnum;
import com.beloved.common.utils.ObjectUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.groups.Default;
import java.lang.annotation.*;
import java.util.Arrays;
import java.util.Objects;


@Documented
@Constraint(
    validatedBy = {EnumValidator.EnumConstraintValidator.class}
)
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {
    
    /**
     * 枚举
     */
    Class<? extends BaseEnum> enumType();

    String message() default "{javax.validation.constraints.EnumValidator.message}";

    Class<? extends Default>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    @Target(value = {ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        EnumValidator[] value();
    }
    
    class EnumConstraintValidator implements ConstraintValidator<EnumValidator, Object> {
        private EnumValidator annotation;
        
        /**
         * 初始化
         * @param constraintAnnotation 注解对象
         */
        @Override
        public void initialize(EnumValidator constraintAnnotation) {
            this.annotation = constraintAnnotation;
        }
        
        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if (ObjectUtils.isEmpty(value)) return false;

            BaseEnum[] baseEnums = annotation.enumType().getEnumConstants();

            BaseEnum baseEnum = Arrays.stream(baseEnums)
                    .filter(e -> Objects.equals(e.getLabel(), value))
                    .findFirst()
                    .orElse(null);

            if (ObjectUtils.isEmpty(baseEnum)) {
                return false;
            }
            return true;
        }
    }
    
}
