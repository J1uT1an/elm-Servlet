<template>
  <div class="wrapper">

    <!-- header部分 -->
    <header>
      <p>用户登陆</p>
    </header>

    <!-- 表单部分 -->
    <ul class="form-box">
      <li>
        <div class="title">
          手机号码：
        </div>
        <div class="content">
          <input v-model="user.userId" placeholder="手机号码" type="text">
        </div>
      </li>
      <li>
        <div class="title">
          密码：
        </div>
        <div class="content">
          <input v-model="user.password" placeholder="密码" type="password">
        </div>
      </li>
    </ul>

    <div class="button-login">
      <button @click="backorder()">登陆</button>
    </div>
    <div class="button-register">
      <button @click="toRegister()">去注册</button>
    </div>

    <!-- 底部菜单部分 -->
    <ul class="footer">
      <li onclick="location.href='index.html'">
        <i class="fa fa-home"></i>
        <p>首页</p>
      </li>
      <li>
        <i class="fa fa-compass"></i>
        <p>发现</p>
      </li>
      <li onclick="location.href='orderList.html'">
        <i class="fa fa-file-text-o"></i>
        <p>订单</p>
      </li>
      <li>
        <i class="fa fa-user-o"></i>
        <p>我的</p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        userId: '',
        password: ''
      },


    }
  },
  methods: {

    backorder() {

      // 首先向后台发送用户名，调用根据用户编号查询用户表返回的行数方法
      // 判断数据库有没有该用户
      this.$axios.post('UserController/getUserById', this.$qs.stringify({
        userId: this.user.userId
      })).then(respone => {
        // 如果存在该用户的话，就调用根据用户编号与密码查询用户信息方法
        // 判断该用户输入的密码是否正确
        if (respone.data == 1) {
          this.$axios.post('UserController/getUserByIdByPass', this.$qs.stringify({
            userId: this.user.userId, password: this.user.password
          })).then(respone => {
            // 如果正确的话就登陆成功，就返回之前点餐的页面
            if (respone.data != null) {
              history.go(-1)
              this.user = respone.data;
              // // 为了防止数据溢出，不将图片的信息存入
              // this.user.userImg=''
              this.$setSessionStorage('user', respone.data)

              // 反之，则用户名或密码错误
            } else {
              alert("用户名或密码错误！！")

            }

            console.log(respone.data)
          }).catch(error => {
            console.log(error)
          })

        } else {
          // 用户不存在，就需要注册
          alert("用户未注册，请注册")
        }

      }).catch(error => {
        console.log(error)
      })

    },
    toRegister() {
      this.$router.push({
        path: '/register'
      })
    }
  },

  created() {


  },


}

</script>
<style scoped>
/****************** 总容器 ******************/
.wrapper {
  width: 100%;
  height: 100%;
}

/****************** header部分 ******************/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: #0097FF;
  color: #fff;
  font-size: 4.8vw;

  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;

  display: flex;
  justify-content: center;
  align-items: center;
}

/****************** 表单部分 ******************/
.wrapper .form-box {
  width: 100%;
  margin-top: 12vw;
}

.wrapper .form-box li {
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
  display: flex;
  align-items: center;
}

.wrapper .form-box li .title {
  flex: 0 0 18vw;
  font-size: 3vw;
  font-weight: 700;
  color: #666;
}

.wrapper .form-box li .content {
  flex: 1;
}

.wrapper .form-box li .content input {
  border: none;
  outline: none;
  width: 100%;
  height: 4vw;
  font-size: 3vw;
}

.wrapper .button-login {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
}

.wrapper .button-login button {
  width: 100%;
  height: 10vw;
  font-size: 3.8vw;
  font-weight: 700;
  color: #fff;
  background-color: #38CA73;
  border-radius: 4px;

  border: none;
  outline: none;
}

.wrapper .button-register {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
}

.wrapper .button-register button {
  width: 100%;
  height: 10vw;
  font-size: 3.8vw;
  font-weight: 700;
  /*与上面登陆按钮不同的只有颜色、背景色、边框不同*/
  color: #666;
  background-color: #EEE;
  border: solid 1px #DDD;
  border-radius: 4px;

  border: none;
  outline: none;
}

/****************** 底部菜单部分 ******************/
.wrapper .footer {
  width: 100%;
  height: 14vw;
  border-top: solid 1px #DDD;
  background-color: #fff;

  position: fixed;
  left: 0;
  bottom: 0;

  display: flex;
  justify-content: space-around;
  align-items: center;
}

.wrapper .footer li {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  color: #999;
  user-select: none;
  cursor: pointer;
}

.wrapper .footer li p {
  font-size: 2.8vw;
}

.wrapper .footer li i {
  font-size: 5vw;
}
</style>
