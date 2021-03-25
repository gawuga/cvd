package com.example.demo.mapper;

import com.example.demo.entiy.Result;
import com.example.demo.entiy.data.Logo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Logo检测数据mapper
 */
@Mapper
public interface LogoMapper extends DataMapper<Logo> {
    //{ "insert into sys_role(id, role_name, enabled, create_by, create_time) values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})" }
   //"INSERT INTO ${tableName}(product_id,ng_code,test_result,use_time,test_time) values(#{productId},#{ngCode},#{testResult},#{useTime},#{testTime})

    /**
     * 插入一条数据
     * @param tableName
     * @param productId
     * @param ngCode
     * @param testResult
     * @param testTime
     * @return
     */
    @Insert({ "insert into ${tableName}(product_id,ng_code,test_result,use_time,test_time,device_line,device_name) values('${productId}','${ngCode}','${testResult}','${useTime}','${testTime}','${deviceLine}','${deviceName}')" })
    int insert(@Param("tableName") String tableName, @Param("productId") String productId
            , @Param("ngCode") String ngCode, @Param("testResult") String testResult, @Param("useTime") String useTime
            , @Param("testTime") String testTime, @Param("deviceLine") String deviceLine, @Param("deviceName") String deviceName);


    @Select({ "SELECT" +
            "    COUNT(id) AS 'co'," +
            "    DAY (test_time) AS 't_date'" +
            "FROM" +
            "    ${tableName} WHERE" +
            "    YEAR(test_time)='2020' AND MONTH(test_time)='12' " +
            "GROUP BY" +
            "    DAY (test_time)" })
    List<Result> countDayChange(@Param("tableName") String tableName);

}
