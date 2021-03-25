package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 数据库表的操作mapper
 */
@Mapper
public interface TableMapper2 {
    @Update({"create table ${tableName}(     `product_model` VARCHAR(64)," +
            "  `test_time` DATETIME," +
            "  `result` VARCHAR(64)," +
            "  `use_time` VARCHAR(64)," +
            "  `device_line` VARCHAR(64)," +
            "  `device_name` VARCHAR(64)," +
            "  `model_fail` VARCHAR(64),  `id` BIGINT(10) NOT NULL AUTO_INCREMENT," +
            "  PRIMARY KEY (`id`))"})
    int createLedTable(@Param("tableName") String tableName);

    @Update({"create table ${tableName}(`product_model` VARCHAR(64)," +
            "  `test_time` DATETIME," +
            "  `test_result` VARCHAR(64)," +
            "  `use_time` VARCHAR(64)," +
            "  `ng_code` VARCHAR(64)," +
            "  `device_line` VARCHAR(64)," +
            "  `device_name` VARCHAR(64)," +
            "  `product_id` VARCHAR(64),`id` BIGINT(10) NOT NULL AUTO_INCREMENT," +
            "  PRIMARY KEY (`id`))"})
    int createLogoTable(@Param("tableName") String tableName);

    @Update({"create table ${tableName}(`device_no` VARCHAR(64)," +
            "  `test_time` DATETIME," +
            "  `device_line` VARCHAR(64)," +
            "  `device_name` VARCHAR(64)," +
            "  `sn` VARCHAR(64),`id` BIGINT(10) NOT NULL AUTO_INCREMENT," +
            "  PRIMARY KEY (`id`))"})
    int createCodeTable(@Param("tableName") String tableName);

    @Update({"create table ${tableName}( `product_model` VARCHAR(64)," +
            "  `test_time` DATETIME," +
            "  `order_no` VARCHAR(64)," +
            "  `device_line` VARCHAR(64)," +
            "  `device_name` VARCHAR(64)," +
            "  `sn` VARCHAR(64),`id` BIGINT(10) NOT NULL AUTO_INCREMENT," +
            "  PRIMARY KEY (`id`))"})
    int createLabelTable(@Param("tableName") String tableName);

    @Update({"create table ${tableName}( `product_model` VARCHAR(64)," +
            "  `test_time` DATETIME," +
            "  `test_result` VARCHAR(64)," +
            "  `ng_code` VARCHAR(64)," +
            "  `device_line` VARCHAR(64)," +
            "  `device_name` VARCHAR(64)," +
            "  `sn` VARCHAR(64),`id` BIGINT(10) NOT NULL AUTO_INCREMENT," +
            "  PRIMARY KEY (`id`))"})
    int createAoiOrSocketTable(@Param("tableName") String tableName);

    @Update({"drop table if exists ${tableName}"})
    public void dropExistTable(@Param("tableName") String tableName);



    @Update({"create table ${tableName}(`data_id` BIGINT(10)," +
            "  `field_name` VARCHAR(64)," +
            "  `value` VARCHAR(64),`id` BIGINT(10) NOT NULL AUTO_INCREMENT,"+
            "   PRIMARY KEY (`id`))"})
    int createDataExtraTable(@Param("tableName") String tableName);
    //select max(id) from user;

    @Select({"select max(id) from ${tableName}"})
    int lastId(@Param("tableName") String tableName);


}
