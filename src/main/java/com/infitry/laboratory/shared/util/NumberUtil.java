package com.infitry.laboratory.shared.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NumberUtil {
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
}
