package com.staff.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.staff.demo.entity.Staff;
import com.staff.demo.entity.StaffOffice;
import com.staff.demo.mapper.StaffMapper;
import com.staff.demo.page.Query;
import com.staff.demo.service.StaffService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("StaffService")
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {
    //    @Override
//    public List<Staff> getAllStaff() {
//        return baseMapper.getAllStaff();
//    }
    @Override
    public Page getTableByPage(Map<String, Object> params) {
        Page page = new Query<Staff>(params).getPage();

        return page.setRecords(baseMapper.getTableByPage(page, params));
    }

    @Override
    public List<Staff> findStaff(Map<String, Object> params) {
        return baseMapper.findStaff(params);
    }

    @Override
    public int saveStaff(Map<String, Object> params) {
        baseMapper.saveStaff(params);
        return 1;
    }

    @Override
    public int addStaff(Map<String, Object> params) {
        baseMapper.addStaff(params);
        return 1;
    }

    @Override
    public int deleteStaff(Map<String, Object> params) {
        baseMapper.deleteStaff(params);
        return 1;
    }

    @Override
    public int updateStaff(Map<String, Object> params) {
        System.out.println(params);
        return baseMapper.updateStaff(params);
    }

    @Override
//    public List<Staff> findByName(Map<String, Object> params) {
//        return baseMapper.findByName(params);
//    }
    public Page findByName(Map<String, Object> params) {
        Page page = new Query<Staff>(params).getPage();

        return page.setRecords(baseMapper.findByName(page, params));
    }

    @Override
    public List<StaffOffice> saveOffice() {
        return baseMapper.saveOffice();
    }
}
