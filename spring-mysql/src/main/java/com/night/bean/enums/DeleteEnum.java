package com.night.bean.enums;


import lombok.AllArgsConstructor;

/**
 * @author night
 */
@AllArgsConstructor
public enum DeleteEnum {
    DELETE(1),
    DEFAULT(0);

    private Integer type;

    public static DeleteEnum parse(int type) {
        for (DeleteEnum value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

    public Integer type(){
        return type;
    }
}
