package com.haopn.demo.util;

import com.haopn.demo.entity.QEmployee;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;

public class EmployeeExpressions {
    public static BooleanExpression hasBirthday() {
        LocalDate today = LocalDate.now();
        return QEmployee.employee.birthday.dayOfYear().eq(today.getDayOfYear()).
                and(QEmployee.employee.birthday.month().eq(today.getMonthValue()));
    }

    public static BooleanExpression isLongTermEmployee() {
        LocalDate today = LocalDate.now();
        return QEmployee.employee.createAt.lt(today.minusYears(2));
    }

}


