package com.staff.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("office_master")
public class StaffOffice {
    private String selectObj;
    private String office_id;

    public String getSelectObj() {
        return selectObj;
    }

    public void setSelectObj(String selectObj) {
        this.selectObj = selectObj;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }
}
