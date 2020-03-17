package com.staff.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.staff.demo.entity.Staff;
import com.staff.demo.entity.StaffOffice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StaffMapper extends BaseMapper<Staff> {
    //    List<Staff> getAllStaff();
    List<Staff> getTableByPage(IPage page, @Param("params") Map<String, Object> params);

    List<Staff> findStaff(@Param("params") Map<String, Object> params);

    int saveStaff(@Param("params") Map<String, Object> params);

    int addStaff(@Param("params") Map<String, Object> params);

    int deleteStaff(@Param("params") Map<String, Object> params);

    int updateStaff(@Param("params") Map<String, Object> params);

    //    List<Staff> findByName(@Param("params") Map<String, Object> params);
    List<Staff> findByName(IPage page, @Param("params") Map<String, Object> params);

    List<StaffOffice> saveOffice();
}
