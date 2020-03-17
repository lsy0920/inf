<template>
  <!-- <div v-if="dialogCreateVisible"> -->
    <div>
    <el-form id="#create" :model="create" ref="create" label-width="100px">
      <el-form-item label="部门" prop="department">
        <el-input v-model="create.department"></el-input>
      </el-form-item>
      <el-form-item label="科室" prop="office">
        <!-- <el-select v-model="create.office" placeholder="请选择" class="officeWid">
          <el-option
            v-for="item in optionsOffice"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select> -->
        <el-select v-model="create.officeData" size="mini" placeholder="请选择" class="officeWid">
            <el-option v-for="item in objData" :key="item.selectObj" :label="item.selectObj" :value="item.selectObj">
            </el-option> 
          </el-select>
      </el-form-item>
      <el-form-item label="员工编号" prop="id">
        <el-input v-model="create.id"></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="staffName">
        <el-input v-model="create.staffName"></el-input>
      </el-form-item>
      <!-- <el-form-item label="性别" prop="sex">
          <el-input v-model="create.sex"></el-input>
      </el-form-item>-->
      <el-form-item label="性别" prop="sex">
        <el-radio v-model="create.sex" label="男">男</el-radio>
        <el-radio v-model="create.sex" label="女">女</el-radio>
      </el-form-item>
      <el-form-item label="入职日期" prop="entryDate">
        <el-date-picker
          class="dateWid"
          v-model="create.entryDate"
          type="date"
          placeholder="选择日期"
          value-format="yyyy-MM-dd"
        ></el-date-picker>
      </el-form-item>
      <!-- <div class="addressSelect">
          <span class="staffAddress">家庭住址</span>
          <el-cascader
          class="addressWidth"
          v-model="create.address"
          :options="options"
          @change="createAddress">
          </el-cascader>
      </div>-->
      <el-form-item label="家庭住址">
        <el-cascader
          class="addressWidth"
          v-model="create.address"
          :options="options"
          @change="createAddress"
        ></el-cascader>
      </el-form-item>

      <el-form-item label="手机号" prop="phoneNumber">
        <el-input v-model="create.phoneNumber"></el-input>
      </el-form-item>
    </el-form>
    <!-- <div slot="footer" class="dialog-footer">
      <el-button @click="dialogCreateVisible = false">取 消</el-button>
      <el-button
        type="primary"
        :loading="createLoading"
        @click="createStaff"
      >确 定</el-button> -->
      <!-- <el-button
        type="primary"
        :loading="createLoading"
        @click="$refs.addInformation.createStaff()"
      >确 定</el-button> -->
    <!-- </div> -->
  </div>
</template>
<script>
// import {options} from "@/assets/json/mapSelect"
// import {optionsOffice} from "@/assets/json/mapSelect"
export default {
  name: "Add",
  data() {
    return {
      objData: [],
      create: {
        department: "企业管理与信息化部运维模块",
        office: "",
        id: "",
        staffName: "",
        sex: "",
        // entryDate: "2016-05-02",
        entryDate: "",
        address: [],
        phoneNumber: "",
        officeData:''
      },
      dialogCreateVisible: false,
      // dialogCreateVisible: true,
      createLoading: false,
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
      // optionsOffice: [
      //   {
      //     value: "软件开发中心",
      //     label: "软件开发中心"
      //   },
      //   {
      //     value: "数据中心",
      //     label: "数据中心"
      //   },
      //   {
      //     value: "信息安全",
      //     label: "信息安全"
      //   },
      //   {
      //     value: "运维中心",
      //     label: "运维中心"
      //   }
      // ]
    };
  },
  mounted() {
    this.getObjData();
  },
  methods: {
    getObjData(){
      this.$http({
        method: "post",
        url: "/api/saveOffice",
      })
      .then(response =>{
        console.log(response.data);
        // console.log("123");
        this.objData = response.data;
      })
      .catch(function(error) {
            console.log(error);
          });
    }, 
    phone() {
      var re = /^1[3456789]\d{9}$/;
      let str = this.create.phoneNumber;
      if (re.test(str)) {
        alert("手机号校验成功");
        return true;
      } else {
        alert("抱歉手机号不合法");
        return false;
      }
    },
    createAddress() {
      console.log(this.create.address);
    },
    createStaff() {
      let isPhone = this.phone();
      console.log(this.create.sex);
      let addressTest = this.create.address[0] + "/" + this.create.address[1];
      console.log(addressTest);
      let office = this.create.officeData;
      console.log(1);
      console.log(office);
      console.log(2);
      if (isPhone) {
        this.$http({
          method: "post",
          url: "/api/addStaff",
          params: {
            department: this.create.department,
            // office: this.create.office,
            office: office,
            id: this.create.id,
            staffName: this.create.staffName,
            sex: this.create.sex,
            entryDate: this.create.entryDate,
            // address: this.create.address,
            address: addressTest,
            phoneNumber: this.create.phoneNumber
          }
        })
          .then(response => {
            console.log(response.data);
            // this.$emit('create: dialogCreateVisible',false);
            // this.dialogCreateVisible = false;
            // this.$emit("dialogdialogCreateVisible = false");
            this.$emit("dialogCreateVisible = false");
            this.createLoading = false;
            this.$emit("getTablePage()");
          })
          .catch(function(error) {
            console.log(error);
          });
      } else {
        console.log("error phone");
      }
    }
  }
};
</script>
<style scoped>
.officeWid {
  width: 100%;
}
.dateWid {
  width: 100%;
}
.addressWidth {
  width: 100%;
}
</style>



