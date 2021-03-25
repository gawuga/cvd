package com.example.demo.mapper;

import com.example.demo.entiy.data.Aoi;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * aoi检测数据mapper
 */
@Mapper
public interface AoiMapper extends DataMapper<Aoi> {
    /**
     * 插入一条数据
     * @param tableName
     * @param productModel
     * @param ngCode
     * @param testResult
     * @param testTime
     * @param sn
     * @return
     */
    @Insert({ "insert into ${tableName}(test_result,sn,product_model,ng_code,test_time,device_line,device_name) values('${testResult}','${sn}'" +
            ",'${product_model}','${ngCode}','${testTime}','${deviceLine}','${deviceName}')" })
    int insert(@Param("tableName") String tableName, @Param("product_model") String productModel
            , @Param("ngCode") String ngCode, @Param("testResult") String testResult,
               @Param("testTime") String testTime, @Param("sn") String sn
            , @Param("deviceLine") String deviceLine, @Param("deviceName") String deviceName);
}
