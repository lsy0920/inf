<template>
  <div>
    <el-form id="#update" :model="update" ref="update" label-width="100px">
      <el-form-item label="部门" prop="department">
        <el-input v-model="update.department"></el-input>
      </el-form-item>
      <el-form-item label="科室" prop="office">
        <el-select v-model="update.office" placeholder="请选择" class="officeWid">
          <el-option
            v-for="item in optionsOffice"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="员工编号" prop="id">
        <el-input v-model="update.id"></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="staffName">
        <el-input v-model="update.staffName"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio v-model="update.sex" label="男">男</el-radio>
        <el-radio v-model="update.sex" label="女">女</el-radio>
      </el-form-item>
      <el-form-item label="入职日期" prop="entryDate">
        <el-date-picker
          class="dateWidth"
          v-model="update.entryDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="家庭住址">
        <el-cascader
          class="addressWidth"
          v-model="update.address"
          :options="options"
          @change="updateAddress"
        ></el-cascader>
      </el-form-item>
      <el-form-item label="手机号" prop="phoneNumber">
        <el-input v-model="update.phoneNumber"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  name: "Edit",
  
  data() {
    return {
      update: {
        department: "企业管理与信息化部运维模块",
        office: "",
        id: "",
        staffName: "",
        sex: "",
        entryDate: "",
        // address: "",
        address: [],
        phoneNumber: ""
      },
      dialogUpdateVisible: false,
      updateLoading: false,
      options: [
        {
          value: "北京市",
          label: "北京市",
          children: [
            {
              value: "北京市",
              label: "北京市"
            }
          ]
        },
        {
          value: "河北省",
          label: "河北省",
          children: [
            {
              value: "石家庄市",
              label: "石家庄市"
            },
            {
              value: "唐山市",
              label: "唐山市"
            }
          ]
        }
      ],
      optionsOffice: [
        {
          value: "软件开发中心",
          label: "软件开发中心"
        },
        {
          value: "数据中心",
          label: "数据中心"
        },
        {
          value: "信息安全",
          label: "信息安全"
        },
        {
          value: "运维中心",
          label: "运维中心"
        }
      ]
    };
  },
  methods: {
      phoneJudge() {
      var re = /^1[3456789]\d{9}$/;
      let str = this.update.phoneNumber;
      if (re.test(str)) {
        alert("手机号校验成功");
        return true;
      } else {
        alert("抱歉手机号不合法");
        return false;
      }
    },
    updateAddress() {
      console.log(this.update.address);
    },
    updateStaff() {
      let isPhoneNumber = this.phoneJudge();
      let addressTest = this.update.address[0] + "/" + this.update.address[1];
      console.log(addressTest);
      console.log(this.update.entryDate);
      if (isPhoneNumber) {
        this.$http({
          method: "post",
          url: "/api/updateStaff",
          params: {
            department: this.update.department,
            office: this.update.office,
            id: this.update.id,
            staffName: this.update.staffName,
            sex: this.update.sex,
            entryDate: this.update.entryDate,
            // address: this.update.address,
            address: addressTest,
            phoneNumber: this.update.phoneNumber
          }
        })
          .then(response => {
            console.log(response.data);
            this.$emit("dialogUpdateVisible = false");
            // this.dialogUpdateVisible = false;
            this.updateLoading = false;
            // this.getTablePage();
            this.$emit("getTablePage()");
            // this.$forceUpdate();
          })
          .catch(function(error) {
            console.log(error);
          });
      } else {
        console.log("error phone");
      }
    },
  }
};
</script>
<style scoped>
.officeWid {
    width: 100%;
}
.dateWidth {
    width: 100%;
}
.addressWidth {
    width: 100%;
}
</style>

