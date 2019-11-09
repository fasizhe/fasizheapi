package com.faishze.api.fasizheapi.constant;

public enum ArticleType{
    UGC(0,"UGC"),
    NEWS(1,"NEWS");

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

    public static ArticleType getArticleTypeByName(String name) {
        for (ArticleType articleType : ArticleType.values()) {
            if (articleType.getName().equals(name)) {
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
