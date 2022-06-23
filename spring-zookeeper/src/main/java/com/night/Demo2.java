package com.night;

import java.util.List;

public class Demo2 {
    // 1 1 2 3 5 8 13
    public static void main(String[] args) {
        System.out.println(f2(7L));
    }

    public static Long f(Long a) {
        if (a == 1) {
            return 1L;
        } else if (a == 2) {
            return 1L;
        }
        return f(a - 1) + f(a - 2);
    }


    public static Long f2(Long a) {
        Long[] li = {1L, 1L};
        if (a <= 2) {
            return 1L;
        }
        for (int i = 3; i <= a; i++) {
            Long temp = li[1];
            li[1] = li[0] + li[1];
            li[0] = temp;
        }
        return li[1];
    }
}
