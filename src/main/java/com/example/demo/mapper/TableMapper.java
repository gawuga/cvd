package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;

public interface TableMapper {


    /**
     * 是否存在表
     * @param tableName
     * @return
     */
    int existTable(@Param("tableName") String tableName);
    /**
     * 删除表
     * @param tableName
     * @return
     */
    int dropTable(@Param("tableName") String tableName);
    /**
     * 创建表
     * @param tableName
     * @return
     */
    int createLedTable(@Param("tableName") String tableName);

    int createLogoTable(@Param("tableName") String tableName);

    int createCodeTable(@Param("tableName") String tableName);

    int createLabelTable(@Param("tableName") String tableName);

    int createAoiOrSocketTable(@Param("tableName") String tableName);

}
