<template>
  <div class="wrapper">

    <!-- header部分 -->
    <header>
      <p>商家列表</p>
    </header>

    <!-- 商家列表部分 -->
    <ul class="business">
      <li v-for="item in businessArray" @click="toBusinessInfo(item.businessId)">
        <div class="business-img">
          <img :src="item.businessImg">
          <div v-show="item.quantity>0" class="business-img-quantity">{{ item.quantity }}</div>
        </div>
        <div class="business-info">
          <h3>{{ item.businessName }}</h3>
          <p>&#165;{{ item.starPrice }}起送 | &#165;{{ item.deliveryPrice }}配送</p>
          <p>{{ item.businessExplain }}</p>
        </div>
      </li>
    </ul>
    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from '../components/Footer.vue'
// 模块导出
export default {
  data() {
    return {
      // 获取到由index.vue传来的orderTypeId，，，this.$route.query用这种方式获取
      orderTypeId: this.$route.query.orderTypeId,
      businessArray: [],
      user: {},
      cartArray: [],
      sum: []
    }
  },
  // vue中初始化的方法，自动调用
  created() {
    this.user = this.$getSessionStorage('user');
    // 利用axios根据orderTypeId查询商家的信息

    // this.$qs.stringify({
    // orderTypeId:this.orderTypeId  序列化参数
    this.$axios.post('BusinessController/listBusinessByOrderTypeId', this.$qs.stringify({
      orderTypeId: this.orderTypeId
    })).then(response => {
      this.businessArray = response.data;
      console.log(response.data);
      for (let item of this.businessArray) {
        item.quantity = 0;
      }
      if (this.user != null) {
        this.toquantity();
      }

    }).catch(error => {
      console.log(error)
    })


    // if(this.cartArray!=null){
    // 	for(let it of this.cartArray){
    // 		for(let it1 of this.businessArray){
    // 			if(it1.businessId==it.businessId){
    // 				it1.quantity=0
    // 			}
    // 		}
    // 	}
    // }
  },
  methods: {
    toquantity() {
      // 商家列表的小红点
      // 根据用户编号查询此用户所有购物车信息​ 根据用户编号和商家编号，查询此用户购物车中某个商家的所有购物车信息
      this.$axios.post('CartController/listCart', this.$qs.stringify({
        userId: this.user.userId
      })).then(response => {
        if (response.data != null) {
          this.cartArray = response.data;
          // console.log(this.businessArray)
          console.log(this.cartArray)

          for (let item of this.businessArray) {
            for (let i = 0; i < this.cartArray.length; i++) {
              if (item.businessId == this.cartArray[i].businessId) {
                item.quantity += this.cartArray[i].quantity
                console.log(item.quantity)
              }
              // if(this.cartArray[i].businessId==this.cartArray[i+1].businessId){
              // 	this.sum[i].quantity = this.cartArray[i].quantity
              // 	this.sum[i].businessid = this.cartArray[i].businessId
              // 	console.log(sum[i])
              // }
            }
            this.businessArray.sort();
          }
        } else {
          console.log("查询购物车信息失败！！！！！！！！")
        }
      }).catch(error => {
        console.log(error)
      })
    },
    toBusinessInfo(businessId) {
      // 跳转页面
      this.$router.push({
        path: '/businessInfo',
        // 参数,后面的businessId是传进来的toBusinessInfo(businessId),这是形参
        // <li v-for="item in businessArray" @click="toBusinessInfo(item.businessId)"> 这是实参
        query: {
          businessId: businessId
        }
      });
    }
  },
  components: {
    Footer
  }
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

/****************** 商家列表部分 ******************/
.wrapper .business {
  width: 100%;
  margin-top: 12vw;
  margin-bottom: 14vw;
}

.wrapper .business li {
  width: 100%;
  box-sizing: border-box;
  padding: 2.5vw;
  border-bottom: solid 1px #DDD;
  user-select: none;
  cursor: pointer;

  display: flex;
  align-items: center;
}

.wrapper .business li .business-img {
  /*这里设置为相当定位，成为business-img-quantity元素的父元素*/
  position: relative;
}

.wrapper .business li .business-img img {
  width: 20vw;
  height: 20vw;
}

.wrapper .business li .business-img .business-img-quantity {
  width: 5vw;
  height: 5vw;
  background-color: red;
  color: #fff;
  font-size: 3.6vw;
  border-radius: 2.5vw;

  display: flex;
  justify-content: center;
  align-items: center;

  /*设置成绝对定位，不占文档流空间*/
  position: absolute;
  right: -1.5vw;
  top: -1.5vw;
}

.wrapper .business li .business-info {
  margin-left: 3vw;
}

.wrapper .business li .business-info h3 {
  font-size: 3.8vw;
  color: #555;
}

.wrapper .business li .business-info p {
  font-size: 3vw;
  color: #888;
  margin-top: 2vw;
}
</style>