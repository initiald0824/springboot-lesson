package com.spark.springbootlesson.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author initiald0824
 * @date 2019/7/3 14:40
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object> {

    /**
     * 临时变量保存在flag值表
     */
    private String values;

    /**
     * 初始化values的值
     * 将注解内配置的值赋给临时变量
     * @param constraintAnnotation
     */
    @Override
    public void initialize(FlagValidator constraintAnnotation) {
        this.values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        // 分割定义的有效值
        String[] value_array = values.split(",");
        boolean isFlag = false;
        // 遍历比对有效值
        for (int i = 0; i < value_array.length; i++) {
            if (value_array[i].equals(o)) {
                isFlag = true;
                break;
            }
        }
        // 返回是否存在boolean
        return isFlag;
    }
}
