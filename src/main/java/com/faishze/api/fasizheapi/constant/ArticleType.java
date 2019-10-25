package com.faishze.api.fasizheapi.constant;

public enum ArticleType{
    UGC(0,"用户原创内容"),
    NEWS(1,"新闻资讯");

    private final int id;
    private final String name;

    ArticleType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ArticleType getArticleTypeById(int id) {
        for (ArticleType articleType : ArticleType.values()) {
            if (articleType.getId() == id) {
                return articleType;
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
