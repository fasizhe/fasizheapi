package com.faishze.api.fasizheapi.constant;

import org.springframework.http.HttpStatus;

/**
 * 描述: 性别
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-9
 */
public enum Gender {
    UNKNOWN(0, "未知"),
    MAN(1, "男"),
    WOMAN(2, "女");

    private final int id;
    private final String name;

    Gender(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Gender getGenderById(int id) {
        for (Gender gender : Gender.values()) {
            if (gender.getId() == id) {
                return gender;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
