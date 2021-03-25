package com.example.demo.common;


public enum DynamicTableTreadLocal {
    INSTANCE;
    private ThreadLocal<String> tableName = new ThreadLocal<>();

    public String getTableName() {
        return tableName.get();
    }

    public void setTableName(String tableName) {
        this.tableName.set(tableName);
    }

    public void remove() {
        tableName.remove();
    }

}

