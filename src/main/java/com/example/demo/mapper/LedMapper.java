package com.example.demo.mapper;

import com.example.demo.entiy.data.Led;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Led检测数据mapper
 */
@Mapper
public interface LedMapper extends DataMapper<Led> {
    /**
     * 插入一条数据
     * @param tableName
     * @param productModel
     * @param useTime
     * @param result
     * @param modelFail
     * @param testTime
     * @return
     */
    //productModel ,result ,useTime ,modelFail ,testTime
    @Insert({ "insert into ${tableName}(product_model,result,use_time,model_fail,test_time,device_line,device_name) values('${productModel}','${result}','${useTime}','${modelFail}','${testTime}','${deviceLine}','${deviceName}')" })
    int insert(@Param("tableName") String tableName, @Param("productModel") String productModel
            , @Param("useTime") String useTime, @Param("result") String result, @Param("modelFail") String modelFail
            , @Param("testTime") String testTime, @Param("deviceLine") String deviceLine, @Param("deviceName") String deviceName);
}
