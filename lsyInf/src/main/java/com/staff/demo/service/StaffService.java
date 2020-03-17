package com.staff.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.staff.demo.entity.Staff;
import com.staff.demo.entity.StaffOffice;

import java.util.List;
import java.util.Map;

public interface StaffService extends IService<Staff> {
    //    List<Staff> getAllStaff();
    Page getTableByPage(Map<String, Object> params);

    List<Staff> findStaff(Map<String, Object> params);

    int saveStaff(Map<String, Object> params);

    int addStaff(Map<String, Object> params);

    int deleteStaff(Map<String, Object> params);

    int updateStaff(Map<String, Object> params);

    //    List<Staff> findByName(Map<String, Object> params);
    Page findByName(Map<String, Object> params);

    List<StaffOffice> saveOffice();
}
