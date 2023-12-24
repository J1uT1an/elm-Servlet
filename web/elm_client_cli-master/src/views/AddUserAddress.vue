<template>
  <div class="wrapper">

    <!-- header部分 -->
    <header>
      <p>新增送货地址</p>
    </header>

    <!-- 表单部分 -->
    <ul class="form-box">
      <li>
        <div class="title">
          联系人：
        </div>
        <div class="content">
          <input v-model="contactName" placeholder="联系人姓名" type="text">
        </div>
      </li>
      <li>
        <div class="title">
          性别：
        </div>
        <div class="content" style="font-size: 3vw;">
          <input v-model="contactSex" checked style="width:6vw;height:3.2vw;" type="radio" value="1">男
          <input v-model="contactSex" style="width:6vw;height:3.2vw;" type="radio" value="0">女
        </div>
      </li>
      <li>
        <div class="title">
          电话：
        </div>
        <div class="content">
          <input v-model="contactTel" placeholder="电话" type="tel">
        </div>
      </li>
      <li>
        <div class="title">
          收货地址：
        </div>
        <div class="content">
          <input v-model="address" placeholder="收货地址" type="text">
        </div>
      </li>
    </ul>

    <div class="button-add">
      <button @click="saveDeliveryAddress()">保存</button>
    </div>

    <!-- 底部菜单部分 -->
    <Footer></Footer>

  </div>
</template>

<script>
export default {
  data() {
    return {
      contactSex: 1,
      contactName: '',
      contactTel: '',
      address: '',
      user: {}
    }
  },
  created() {
    this.user = this.$getSessionStorage('user');
  },
  methods: {
    saveDeliveryAddress() {
      this.$axios.post('DeliveryaddressController/saveDeliveryAddress', this.$qs.stringify({
        contactName: this.contactName, contactSex: this.contactSex, contactTel: this.contactTel,
        address: this.address, userId: this.user.userId

      })).then(response => {
        if (response.data == 1) {
          // 如果请求成功提醒用户添加成功
          alert("用户添加地址成功！！！！！！！！！")
          history.go(-1)

        } else {
          console.log("添加失败！！！！！！")
        }

      }).catch(error => {
        console.error(error);
      });
    }
  },
}
</script>

<style scoped>
/*************** 总容器 ***************/
.wrapper {
  width: 100%;
  height: 100%;
}

/*************** header ***************/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: #0097FF;
  display: flex;
  justify-content: space-around;
  align-items: center;
  color: #fff;
  font-size: 4.8vw;
  position: fixed;
  left: 0;
  top: 0;
  /*保证在最上层*/
  z-index: 1000;
}

/*************** （表单信息） ***************/
.wrapper .form-box {
  width: 100%;
  margin-top: 12vw;
}

.wrapper .form-box li {
  box-sizing: border-box;
  padding: 4vw 3vw 0vw 3vw;
  display: flex;
}

.wrapper .form-box li .title {
  flex: 0 0 18vw;
  font-size: 3vw;
  font-weight: 700;
  color: #666;
}

.wrapper .form-box li .content {
  flex: 1;

  display: flex;
  align-items: center;
}

.wrapper .form-box li .content input {
  border: none;
  outline: none;
  width: 100%;
  height: 4vw;
  font-size: 3vw;
}

.wrapper .button-add {
  box-sizing: border-box;
  padding: 4vw 3vw 0vw 3vw;
}

.wrapper .button-add button {
  width: 100%;
  height: 10vw;
  font-size: 3.8vw;
  font-weight: 700;

  border: none;
  outline: none;
  border-radius: 4px;
  color: #fff;
  background-color: #38CA73;
}

</style>
