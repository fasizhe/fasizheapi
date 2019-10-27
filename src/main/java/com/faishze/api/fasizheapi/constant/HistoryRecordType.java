package com.faishze.api.fasizheapi.constant;

/**
 * @author 杜科
 * @description 历史记录类型枚举
 * @contact 15521177704
 * @since 2019/10/27
 */
public enum HistoryRecordType {
    ARTICLE(0,"文章");

    private int id;
    private String name;

    HistoryRecordType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static HistoryRecordType getHistoryRecordTypeById(int id) {
        for (HistoryRecordType historyRecordType : HistoryRecordType.values()) {
            if (historyRecordType.getId() == id) {
                return historyRecordType;
            }
        }
        return null;
    }

    public static HistoryRecordType getHistoryRecordTypeByName(String name) {
        for (HistoryRecordType historyRecordType : HistoryRecordType.values()) {
            if (historyRecordType.getName() == name) {
                return historyRecordType;
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
