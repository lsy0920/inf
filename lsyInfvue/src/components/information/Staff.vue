<template class="staff">
  <div>
    <!-- <el-card>
      部门:<ei-input type="text" v-model="department" placeholder="企业管理与信息化部运维模块" :disabled="true"></ei-input>
    </el-card>-->
    <div class="slesetDept">
      <span class="staffdept">部门：</span>
      <el-input
        placeholder="企业管理与信息化部运维模块"
        v-model="department"
        :disabled="true"
        style="width: 14%"
      ></el-input>
    </div>
    
    <!-- <br>
    <div class="selectOffice">
      <span class="staffoffice">科室：</span>
      <select v-model="office" name="fruit" style="width: 14%">
        <option value="软件开发中心">软件开发中心</option>
        <option value="数据中心">数据中心</option>
        <option value="信息安全">信息安全</option>
        <option value="运维中心">运维中心</option>
      </select>
    </div>
    <br> -->

  <br>
  <div class="selectOffice">
  <span class="staffoffice">科室：</span>
    <el-select v-model="officeData" size="mini" placeholder="请选择" style="width: 14%">
      <el-option v-for="item in objData" :key="item.selectObj" :label="item.selectObj" :value="item.selectObj">
      </el-option> 
    </el-select>
    </div>
    <br>


    <div class="selectId">
      <span class="staffid">编号：</span>
      <el-input placeholder="请输入员工编号" v-model="id" :disabled="false" style="width: 14%"></el-input>
    </div>
    <br>
    <div class="selectName">
      <span class="staffname">姓名：</span>
      <el-input placeholder="请输入员工姓名" v-model="staffName" :disabled="false" style="width: 14%"></el-input>
    </div>
    <br>
    <div class="selectSex">
      <span class="staffsex">性别：</span>
      <input type="radio" id="sexMan" value="男" v-model="sex" style="width: 2%">
      <label for="sexMan">男</label>
      <input type="radio" id="sexWoman" value="女" v-model="sex" style="width: 2%">
      <label for="sexWoman">女</label>
    </div>
    <br>
    <div class="selectDate">
      <span class="demonstration">入职日期：</span>
      <el-date-picker
        v-model="entryDate"
        type="date"
        placeholder="选择日期"
        value-format="yyyy-MM-dd"
        style="width: 14%"
      ></el-date-picker>
    </div>
    <br>
    <!-- <div class="selectAddress">
        <span class="staffadress">家庭住址：</span>
        <el-input placeholder="请输入家庭住址" v-model="address" :disabled="false" style="width: 10%"></el-input>
    </div>-->
    <div class="selectAddress">
      <span class="staffadress">家庭住址：</span>
      <el-select
        v-model="province"
        filterable
        size="small"
        @change="provinceChanged"
        placeholder="请选择省份"
        prop="status"
        style="width: 6.9%"
      >
        <el-option :key="''" :value="''" :label="'全部'"></el-option>
        <el-option :value="item.code" :label="item.name" v-for="item in provinces" :key="item.code"></el-option>
      </el-select>

      <el-select
        v-model="city"
        :loading="loadingCity"
        filterable
        size="small"
        @change="cityChanged"
        placeholder="请选择城市"
        prop="status"
        style="width: 6.9%"
      >
        <el-option :key="''" :value="''" :label="'全部'"></el-option>
        <el-option :value="item.code" :label="item.name" v-for="item in cities" :key="item.code"></el-option>
      </el-select>
    </div>
    <!-- <br>
    <el-input placeholder="请输入家庭住址" v-model="address" :disabled="false" style="width: 10%"></el-input>-->
    <!-- <div class="addressBlock">
      <span class="demonstration">请选择省份</span>
      <el-cascader v-model="value" :options="options" @change="handleProvinceChange"></el-cascader>
    </div>
    <div class="block">
      <span class="demonstration">请选择城市</span>
      <el-cascader
        v-model="value"
        :options="options"
        :props="{ expandTrigger: 'hover' }"
        @change="handleCityChange"
      ></el-cascader>
    </div>-->

    <br>
    <div class="selectPhone">
      <span class="staffphone">手机号：</span>
      <el-input placeholder="请输入员工手机号" v-model="phoneNumber" :disabled="false" style="width: 14%"></el-input>
      <!-- <el-input placeholder="请输入员工手机号" type="number" @blur="phone()" v-model="phoneNumber" :disabled="false" style="width: 10%"></el-input> -->
    </div>
    <br>


    <!-- <br>
    <el-select v-model="officeData" size="mini" placeholder="请选择">
      <el-option v-for="item in objData" :key="item.selectObj" :label="item.selectObj" :value="item.selectObj">
      </el-option> 
    </el-select>
    <br> -->
    
    <br>
    <el-button type="primary" @click="save">保存</el-button>
  </div>
</template>
<script>
// import city from '@/assets/json/city.json'
import provinceCity from "@/assets/json/provinceCity.json";
export default {
  name: "Staff",
  props: {
    provinceCode: {
      type: String,
      default: ""
    },
    cityCode: {
      type: String,
      default: ""
    }
  },

  data() {
    return {
      department: "企业管理与信息化部运维模块",
      office: "",
      id: "",
      staffName: "",
      sex: "",
      entryDate: "",
      // address: "",
      phoneNumber: "",
      // createLoading: false,
      // selected: "",
      // picked: "",
      loadingCity: false,
      provinces: [],
      cities: [],
      city: "",
      province: "",
      // objData: [
      //   { name: "软件开发", id: '1' },
      //   { name: "数据", id: '2' },
      //   { name: "运维", id: '3' },
      //   { name: "信息安全", id: '4' }
      // ], //列表数据
      objData: [],
      officeData:''
      
    };
  },
  created() {
    this.provinces = provinceCity.provinces;
    this.cities = provinceCity.cities;
  },
  mounted() {
    this.province = this.provinceCode;
    this.provinceChanged(this.provinceCode);
    this.city = this.cityCode;
    this.getObjData();
  },

  methods: {
    // optionChange() {
    //   console.log(this.selectObj)
    //   console.log('可以请求数据了')
    // },

    // getObjData:function(){
    //   let data = {};
    //   this.$axios.post(this.url+'saveOffice',data)
    //   .then(res=>{
    //     if(res.data.code===200){
    //       this.objData=res.data.data;
    //       console.log(this.objData);
    //     }else{
    //       console.log(1);
    //     }
    //   }).catch(function(error){
    //     this.$message.error('错误');
    //   })
    // },

     getObjData(){
      this.$http({
        method: "post",
        url: "/api/saveOffice",
        // params: {
        //   selectObj: this.selectObj,
        //   objData: this.objData
        // }
      })
      .then(response =>{
        console.log(response.data);
        // console.log("123");

        // this.selectObj = response.data;
        this.objData = response.data;
      })
      .catch(function(error) {
            console.log(error);
          });
    },

    provinceChanged(value) {
      if (value !== "") {
        this.loadingCity = true;
        for (var item of this.provinces) {
          if (item.code === value) {
            this.cities = item.cities;
            this.city = "";
            this.loadingCity = false;
            this.province = item.name;

            break;
          } else {
            continue;
          }
        }
      } else {
        this.cities = [];
        this.city = "";
      }
      // this.$emit("selectChange", this.province, this.city);
    },
    cityChanged(value) {
      if (value !== "") {
        for (var item of this.cities) {
          if (item.code === value) {
            this.city = item.name;
          }
        }
      }
      // this.$emit("selectChange", this.province, this.city);
    },

    phone() {
      var re = /^1[3456789]\d{9}$/;
      let str = this.phoneNumber;
      if (re.test(str)) {
        alert("手机号校验成功");
        return true;
      } else {
        alert("抱歉手机号不合法");
        return false;
      }
    },

    save() {
      console.log(this.entryDate);
      let isPhone = this.phone();
      // let isProvince = this.provinceChanged();
      // let isCity = this.cityChanged();
      let addressTest = new Array();
      addressTest.push(this.province);
      addressTest.push(this.city);
      let address = addressTest.join("");
      console.log(address);
      let office = this.officeData;
      console.log(1);
      console.log(office);
      console.log(2);
      if (isPhone) {
        this.$http({
          method: "post",
          url: "/api/saveStaff",
          params: {
            department: this.department,
            office: office,
            id: this.id,
            staffName: this.staffName,
            sex: this.sex,
            // entryDate: this.entryDate.toString(),
            entryDate: this.entryDate,
            address: address,
            phoneNumber: this.phoneNumber
            
          }
        })
          .then(response => {
            console.log(response.data);
            //     this.saveLoading = false;
            // this.getTablePage();
          })
          .catch(function(error) {
            console.log(error);
          });
      } else {
        console.log("error phone");
      }
    }
    // selectProvinceFun(event) {
    //   //this.axios
    //   //.get('/api/city')
    //   //.then(res=>{console.log(res.data);})
    //   console.log(event);
    //   if (event) {
    //     this.cityList = event.children;
    //   } else {
    //     this.cityList = [];
    //   }
    //   this.$emit("getLawyerListInfo", [event.label, "province"]);
    //   //this.$emit('getcityListInfo',[event.label,'selectProvince'])
    // }
  }
};
</script>
<style scoped>
.slesetDept {
  margin-left: 1px;
}

.selectId {
  margin-left: 1px;
}
.selectName {
  margin-right: 3px;
}
.selectSex {
  margin-right: 130px;
}
.selectDate {
  margin-right: 35px;
}
.selectAddress {
  margin-right: 36px;
}
.selectPhone {
  margin-right: 20px;
}
.staff {
  text-align: center;
}
.staffdept {
  width: 5px;
  margin-left: 1px;
}
.staffid {
  width: 5px;
  margin-left: 1px;
}
.demonstration {
  width: 5%;
  margin-left: 1px;
}
</style>
