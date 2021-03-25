package com.example.demo.mapper;

import com.example.demo.entiy.data.Label;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Label检测数据mapper
 */
@Mapper
public interface LabelMapper extends DataMapper<Label> {
    /**
     * 插入一条数据
     * @param tableName
     * @param productModel
     * @param sn
     * @param orderNo
     * @param testTime
     * @return
     */
    //testTime ,sn,orderNo ,productModel
    @Insert({ "insert into ${tableName}(product_model,test_time,order_no,sn,device_line,device_name) values('${productModel}','${testTime}','${orderNo}','${sn}','${deviceLine}','${deviceName}')" })
    int insert(@Param("tableName") String tableName, @Param("productModel") String productModel
            , @Param("sn") String sn, @Param("orderNo") String orderNo, @Param("testTime") String testTime, @Param("deviceLine") String deviceLine
            , @Param("deviceName") String deviceName);
}
