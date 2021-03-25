package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entiy.Device;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备模块操作数据库类
 */
@Mapper
public interface DeviceMapper extends BaseMapper<Device> {
}
