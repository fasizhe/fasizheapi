package com.faishze.api.fasizheapi.constant;

public enum ArticleType implements BaseEnum {
    UGC(0,"用户原创内容"),
    NEWS(1,"新闻资讯");

    private final Integer value;
    private final String description;

    ArticleType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
