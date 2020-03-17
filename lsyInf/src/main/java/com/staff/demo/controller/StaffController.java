package com.staff.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.staff.demo.entity.Staff;
import com.staff.demo.entity.StaffOffice;
import com.staff.demo.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StaffController {
    @Autowired
    StaffService staffService;

    @RequestMapping("/staffTable")
    public Page getTableByPage(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        return staffService.getTableByPage(params);
    }

    @RequestMapping("/findStaff")
    public List<Staff> findStaff(@RequestParam Map<String, Object> params) {
        List<Staff> findStaffById = staffService.findStaff(params);
        System.out.println("findStaffById>>>" + findStaffById);

        for (int i = 0, size = findStaffById.size(); i < size; i++) {
            Staff staff = findStaffById.get(i);
            System.out.println("department:" + staff.getDepartment());
            System.out.println("office:" + staff.getOffice());
            System.out.println("id:" + staff.getId());
            System.out.println("staffName:" + staff.getStaffName());
            System.out.println("sex:" + staff.getSex());
            System.out.println("entryDate:" + staff.getEntryDate());
            System.out.println("address:" + staff.getAddress());
            System.out.println("phoneNumber:" + staff.getPhoneNumber());
        }
        return findStaffById;
    }

    @RequestMapping("/saveStaff")
    public int saveStaff(@RequestParam Map<String, Object> params) {
        List<Staff> judgeId = staffService.findStaff(params);
        if (judgeId.size() < 1) {
            staffService.saveStaff(params);
            System.out.println("saveStaff>>>" + params);
        } else {
            staffService.updateStaff(params);
            System.out.println("updateStaff>>>" + params);
        }
//        staffService.saveStaff(params);
//        System.out.println("saveStaff>>>" + params);
        return 1;
    }

    @RequestMapping("/addStaff")
    public int addStaff(@RequestParam Map<String, Object> params) {
        List<Staff> judgeById = staffService.findStaff(params);
        if (judgeById.size() < 1) {
            staffService.addStaff(params);
            System.out.println("addStaff>>>" + params);
        } else {
            staffService.updateStaff(params);
            System.out.println("updateStaff>>>" + params);
        }
//        staffService.addStaff(params);
//        System.out.println("addStaff>>>" + params);
        return 1;
    }

    @RequestMapping("/deleteStaff")
    public int deleteStaff(@RequestParam Map<String, Object> params) {
        staffService.deleteStaff(params);
        System.out.println("deleteStaff>>>" + params);
        return 1;
    }

    @RequestMapping("/updateStaff")
    public int updateStaff(@RequestParam Map<String, Object> params) {
        System.out.println("updateStaff>>>" + params);
        return staffService.updateStaff(params);
    }

    @RequestMapping("/findByName")
//    public List<Staff> findByName(@RequestParam Map<String, Object> params) {
//        List<Staff> findName = staffService.findByName(params);
//        System.out.println("findByName>>>" + params);
//        return findName;
//    }
    public Page findByName(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        return staffService.findByName(params);
    }

    @RequestMapping("/saveOffice")
    public List<StaffOffice> saveOffice() {

        return staffService.saveOffice();
    }


}
