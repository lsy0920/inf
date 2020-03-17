<template>
  <div>
    <div class="findName">
      <!-- 模糊查询 -->
      <el-form :inline="true" class="demo-form">
        <el-form-item>
          <el-input v-model="searchName" class="search_name" size="mini" placeholder="输入员工姓名查询"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="text" @click="onSearchName" class="el-icon-search">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="find">
      <!-- 查询 -->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-input v-model="search" class="search_id" size="mini" placeholder="输入员工编号查询"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="text" @click="onSearch" class="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="text" @click="refreshData" class="el-icon-refresh">刷新</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="add">
      <!-- 添加 -->
      <el-button
        @click="dialogCreateVisible = true"
        icon="el-icon-circle-plus"
        type="text"
        size="small"
      >添加</el-button>
    </div>
    <br>
    <!-- 用户列表 -->
    <el-table :data="staffList" border style="width: 100%">
      <el-table-column prop="department" label="部门"></el-table-column>
      <el-table-column prop="office" label="科室"></el-table-column>
      <el-table-column prop="id" label="员工编号"></el-table-column>
      <el-table-column prop="staffName" label="姓名"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="entryDate" label="入职日期"></el-table-column>
      <el-table-column prop="address" label="家庭住址"></el-table-column>
      <el-table-column prop="phoneNumber" label="手机号"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <!-- <el-button
            @click="dialogUpdateVisible = true"
            icon="el-icon-circle-plus"
            type="text"
            size="small"
          >编辑</el-button>-->
          <el-button
            @click="updateInf(scope.$index,scope.row)"
            icon="el-icon-circle-plus"
            type="text"
            size="small"
          >编辑</el-button>
           <!-- <el-button
            @click="dialogUpdateVisible = true"
            icon="el-icon-circle-plus"
            type="text"
            size="small"
          >编辑</el-button> -->
          <el-button
            @click="handleDelete(scope.row)"
            icon="el-icon-circle-close"
            type="text"
            size="small"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加 -->
    <el-dialog title="添加员工信息" :visible.sync="dialogCreateVisible">
      <Add ref="addInformation" v-if="dialogCreateVisible"></Add>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogCreateVisible = false">取 消</el-button>
        <!-- <el-button type="primary" :loading="createLoading" @click="$refs.addInformation.createStaff()">确 定</el-button> -->
        <el-button type="primary" :loading="createLoading" @click="addClick">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 编辑 -->
    <el-dialog title="编辑员工信息" :visible.sync="dialogUpdateVisible">
      <!-- <Edit ref="editInformation"></Edit> -->
      <el-form id="#update" :model="update" ref="update" label-width="100px">
        <el-form-item label="部门" prop="department">
          <el-input v-model="update.department"></el-input>
        </el-form-item>
        <el-form-item label="科室" prop="office">
          <!-- <el-select v-model="update.office" placeholder="请选择" class="officeWid">
            <el-option
              v-for="item in optionsOffice"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select> -->
          <el-select v-model="update.officeData" size="mini" placeholder="请选择" class="officeWid">
            <el-option v-for="item in objData" :key="item.selectObj" :label="item.selectObj" :value="item.selectObj">
            </el-option> 
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
            placeholder="选择日期"
            value-format="yyyy-MM-dd"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="家庭住址">
          <!-- <el-cascader
            class="addressWidth"
            v-model="update.address"
            :options="options"
            @change="updateAddress"
          ></el-cascader> -->
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

      <!-- <el-form-item label="选择科室" prop="officeSelect">
          <el-select v-model="update.officeSelect" placeholder="请选择" class="officeSelectWid">
            <el-option
              v-for="item in optionsOfficeSelcet"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
      </el-form-item> -->

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogUpdateVisible = false">取 消</el-button>
        <el-button type="primary" :loading="updateLoading" @click="updateStaff">确 定</el-button>
        <!-- <el-button type="primary" :loading="updateLoading" @click="editClick">确 定</el-button> -->
      </div>
    </el-dialog>
    <br>
    <br>
    <div>
      <el-row>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,10,15,20]"
          :page-size="pageSize"
          layout="total,sizes,prev,pager,next,jumper"
          :total="total"
        ></el-pagination>
        <!-- total总条目，page-size每页显示条目个数,current-page：当前页数，支持 .sync 修饰符 
            size-change:pageSize 改变时会触发,current-change:currentPage 改变时会触发
             :current-page="1"
                    :page-sizes="[5,10,15,20]"
                    :page-size="10"
        -->
      </el-row>
    </div>
  </div>
</template>

<script>
// import axios from "axios";

// import provinceCity from "@/assets/json/provinceCity.json";
import Add from "././Add";
// import Edit from "././Edit";
// import options from "@/static/staticData/map"
export default {
  name: "StaffUser",
  components: { Add },
  // components: { Add,Edit },
  data() {
    return {
      staffList: [],
      objData: [],
      update: {
        department: "企业管理与信息化部运维模块",
        office: "",
        id: "",
        staffName: "",
        sex: "",
        entryDate: "",
        // address: "",
        address: [],
        phoneNumber: "",
        officeData:''
      },

      dialogCreateVisible: false,
      createLoading: false,
      dialogUpdateVisible: false,
      updateLoading: false,
      search: "",
      searchName: "",
      pageSize: 5,
      currentPage: 1,
      total: 0,
      valueDate: "",
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
      //     value: "softwareDevelopmentCenter",
      //     label: "软件开发中心"
      //   },
      //   {
      //     value: "dataCenter",
      //     label: "数据中心"
      //   },
      //   {
      //     value: "informationSecurity",
      //     label: "信息安全"
      //   },
      //   {
      //     value: "operationMaintenanceCenter",
      //     label: "运维中心"
      //   }
      // ],
      // optionsOfficeSelect: []
    };
  },
  mounted() {
    this.getTablePage();
    this.getObjData();
  },
  methods: {
    // updateInf(){
    //   this.dialogUpdateVisible = true;
    // },
    updateInf: function(index, row) {
      this.dialogUpdateVisible = true;
      this.update = Object.assign({}, row);
      console.log("====");
     console.log(row);
     console.log("====");
      // let addressTes = row.update.address[0] + "/" + this.update.address[1];
      let addressSplit = row.address.split("/");
      console.log(addressSplit instanceof Array);
      this.update.address= addressSplit;
      //  console.log(addressTes);
    },
    addClick() {
      this.$refs.addInformation.createStaff(); //父组件调用子组件的createStaff()方法
      this.dialogCreateVisible = false; //隐藏弹出框（点击确定时关闭弹出框）
    },
    // editClick() {
    //   this.$refs.editInformation.updateStaff();
    //   this.dialogUpdateVisible = false;
    //   // this.$forceUpdate();
    // },
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
    // createAddress(){

    //   console.log(this.create.address);

    // },
    updateAddress() {
      console.log(this.update.address);
    },
    onSearch() {
      console.log(this.search);
      this.$http({
        method: "post",
        url: "/api/findStaff",
        params: {
          id: this.search
        }
      })
        .then(response => {
          console.log(response.data);
          this.staffList = response.data;
          // this.total = response.data.length;
          this.total = response.data.total;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    // onSearchName() {
    //   console.log(this.searchName);
    //   this.$http({
    //     method: "post",
    //     url: "/api/findByName",
    //     params: {
    //       staffName: this.searchName,
    //     }
    //   })
    //     .then(response => {
    //       console.log(response.data);
    //       this.staffList = response.data.records;
    //       this.total = response.data.length;
    //     })
    //     .catch(function(error) {
    //       console.log(error);
          
    //     });
    // },
    onSearchName() {
      console.log(this.searchName);
      
      this.$http({
        method: "post",
        url: "/api/findByName",
        // url: "/api/staffTable",
        params: {
          staffName: this.searchName,
          currentPage: this.currentPage,
          pageSize: this.pageSize
        }
      })
        .then(response => {
          console.log(response.data);
          this.staffList = response.data.records;
          this.total = response.data.total;
        })
        .catch(function(error) {
          console.log(error);
          
        });
    },
    //分页查询
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.currentPage = 1;
      this.pageSize = val;
      this.getTablePage();
      this.onSearchName();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);

      this.currentPage = val;

      console.log(this.currentPage + " " + this.pageSize);
      this.getTablePage();
      this.onSearchName();
    },

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

    getTablePage() {
      console.log(this.currentPage + " " + this.pageSize + " " + this.total);
      this.$http({
        method: "post",
        url: "/api/staffTable",
        params: {
          currentPage: this.currentPage,
          pageSize: this.pageSize
        }
      })
        .then(response => {
          console.log(response.data);
          this.staffList = response.data.records;
          this.total = response.data.total;
          console.log(this.total);
        })
        .catch(function(error) {
          console.log(error);
        });
    },

    updateStaff() {
      let isPhoneNumber = this.phoneJudge();
      let addressTest = this.update.address[0] + "/" + this.update.address[1];
      // console.log(addressTest);
      console.log(this.update.entryDate);
      let office = this.update.officeData;
      console.log(1);
      console.log(office);
      console.log(2);
      if (isPhoneNumber) {
        this.$http({
          method: "post",
          url: "/api/updateStaff",
          params: {
            department: this.update.department,
            // office: this.update.office,
            office: office,
            id: this.update.id,
            staffName: this.update.staffName,
            sex: this.update.sex,
            entryDate: this.update.entryDate,
            // address: this.update.address,
            address: addressTest,
            phoneNumber: this.update.phoneNumber
          },
          // dateType:"json",
          // contentType : 'application/json',
        })
          .then(response => {
            console.log(response.data);
            this.dialogUpdateVisible = false;
            this.updateLoading = false;
            // update.address = JSON.parse(response.data.update.address);
            this.getTablePage();
          })
          .catch(function(error) {
            console.log(error);
          });
      } else {
        console.log("error phone");
      }
    },
    handleDelete(row) {
      this.$confirm(
        "此操作将永久删除员工 " + row.staffName + ", 是否继续?",
        "提示",
        { type: "warning" }
      )
        .then(() => {
          this.$http({
            method: "post",
            url: "/api/deleteStaff",
            params: {
              department: row.department,
              office: row.office,
              id: row.id,
              staffName: row.staffName,
              sex: row.sex,
              entryDate: row.entryDate,
              address: row.address,
              phoneNumber: row.phoneNumber
            }
          })
            .then(response => {
              console.log(response.data);
              this.$message.success("成功删除了员工" + row.staffName + "!");
              this.getTablePage();
            })
            .catch(response => {
              console.log(error);
              this.$message.error("删除失败!");
            });
        })
        .catch(() => {
          this.$message.info("已取消操作!");
        });
    },
    refreshData() {
      location.reload();
    }
  }
};
</script>
<style scoped>
.add {
  margin-left: 900px;
}
.find {
  margin-right: 670px;
  margin-top: -60px;
}
.findName {
  margin-right: 1400px;
  margin-top: 10px;
}
.dateWidth {
  width: 100%;
}
.cityWidth {
  float: left;
  margin-left: 50px;
  margin-top: -15px;
  /* margin-left: 20px; */
}
.provinceWidth {
  float: left;
  margin-left: 100px;
  margin-top: -15px;
  /* margin-left: 20px; */
}
/* .dateWid {
  width: 100%;
} */
/* .selectAddress {
  width: 100%;
} */
.staffadress {
  width: 10%;
  margin-right: 87%;
}
.addressWidth {
  width: 100%;
}
.officeWid {
  width: 100%;
}
/* .officeSelectWid {
  width: 100%;
} */
</style>
