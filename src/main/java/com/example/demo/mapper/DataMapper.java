package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entiy.ExtraData;
import com.example.demo.entiy.PieResult;
import com.example.demo.entiy.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作检测数据的基本mapper
 * @param <T>
 */
public interface DataMapper<T>  extends BaseMapper<T>{
    /**
     * 近3天的数据
     * @param tableName
     * @param page
     * @param pageSize
     * @return
     */
    @Select("SELECT * FROM ${tableName} WHERE id > (${page}-1)*${pageSize} AND DATE_SUB(CURDATE(), INTERVAL ${time} DAY) <=DATE(test_time) AND device_line=${deviceLine} AND device_name='${deviceName}' LIMIT ${pageSize};") //3
    List<T> get(@Param("tableName") String tableName, @Param("time") String time, @Param("page") int page, @Param("pageSize") int pageSize, @Param("deviceLine") String deviceLine, @Param("deviceName") String deviceName);


    /**
     * 得到每个月的数据
     * @param tableName
     * @return
     */
    @Select("SELECT * FROM ${tableName} WHERE id > (${page}-1)*${pageSize} AND YEAR(test_time)='${year}' AND MONTH(test_time)='${month}' AND device_line=${deviceLine} AND device_name='${deviceName}' LIMIT ${pageSize};") //3
    List<T> getByMonth(@Param("tableName") String tableName, @Param("page") int page
            , @Param("pageSize") int pageSize
            , @Param("deviceLine") String deviceLine
            , @Param("deviceName") String deviceName, @Param("year") String year, @Param("month") String month);



    /**
     * 按照月份统计每天检测数量
     * @param tableName
     * @return
     */
    @Select({ "SELECT" +
            "    COUNT(id) AS 'co'," +
            "    DAY (test_time) AS 't_date'" +
            "FROM" +
            "    ${tableName} WHERE" +
            "    YEAR(test_time)='${year}' AND MONTH(test_time)='${month}' " +
            "GROUP BY" +
            "    DAY (test_time)" })
    List<Result> countDayChange(@Param("tableName") String tableName, @Param("year") String year, @Param("month") String month);


    /**
     * SELECT
     *     COUNT(id) AS '数量',
     *     ng_code  AS 'ng_code'
     * FROM
     *     logo_1000 WHERE
     *     YEAR(test_time)='2020' AND MONTH(test_time)='12'
     * GROUP BY
     *     ng_code
     */
    @Select({ "SELECT" +
            "    COUNT(id) AS 'value'," +
            "    ng_code  AS 'ng_code'" +
            "FROM" +
            "    ${tableName} WHERE" +
            "    YEAR(test_time)='${year}' AND MONTH(test_time)='${month}' " +
            "GROUP BY" +
            "    ng_code" })
    List<PieResult>  countNgCode(@Param("tableName") String tableName, @Param("year") String year, @Param("month") String month);



    /**
     * 插入额外数据
     * @param tableName
     * @return
     */
    @Insert({ "insert into ${tableName}(data_id,field_name,value) values('${dataId}','${fieldName}','${value}')" })
    int insertExtra(@Param("tableName") String tableName, @Param("dataId") int dataId
            , @Param("fieldName") String fieldName, @Param("value") String value);

    @Select("SELECT * FROM ${tableName} WHERE data_id= ${dataId};")
    List<ExtraData> getExtraData(@Param("tableName") String tableName, @Param("dataId") int dataId);



    /**
     * 按照月份统计每天检测数量-----按设备统计
     * @param tableName
     * @return
     */
    @Select({ "SELECT" +
            "    COUNT(id) AS 'co'," +
            "    DAY (test_time) AS 't_date'" +
            "FROM" +
            "    ${tableName} WHERE" +
            "    YEAR(test_time)='${year}' AND MONTH(test_time)='${month}' AND device_line=${deviceLine} AND device_name='${deviceName}' " +
            "GROUP BY" +
            "    DAY (test_time)" })
    List<Result> countDayChangeByMac(@Param("tableName") String tableName, @Param("year") String year, @Param("month") String month, @Param("deviceLine") String deviceLine, @Param("deviceName") String deviceName);


    @Select({ "SELECT" +
            "    COUNT(id) AS 'value'," +
            "    ng_code  AS 'ng_code'" +
            "FROM" +
            "    ${tableName} WHERE" +
            "    YEAR(test_time)='${year}' AND MONTH(test_time)='${month}' AND device_line=${deviceLine} AND device_name='${deviceName}' " +
            "GROUP BY" +
            "    ng_code" })
    List<PieResult>  countNgCodeByMac(@Param("tableName") String tableName, @Param("year") String year, @Param("month") String month, String deviceLine, String deviceName);
}
