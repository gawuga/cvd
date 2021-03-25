package com.example.demo.mapper;

import com.example.demo.entiy.data.Code;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Code检测数据mapper
 */
@Mapper
public interface CodeMapper extends DataMapper<Code> {
    /**
     * 插入一条数据
     * @param tableName
     * @param deviceNo
     * @param sn
     * @param testTime
     * @return
     */
    //deviceNo ,testTime ,sn
    @Insert({ "insert into ${tableName}(device_no,sn,test_time,device_line,device_name) values('${deviceNo}','${sn}','${testTime}','${deviceLine}','${deviceName}')" })
    int insert(@Param("tableName") String tableName, @Param("deviceNo") String deviceNo
            , @Param("sn") String sn, @Param("testTime") String testTime, @Param("deviceLine") String deviceLine, @Param("deviceName") String deviceName);
}
