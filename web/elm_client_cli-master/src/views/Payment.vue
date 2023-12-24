<template>
  <div class="wrapper">

    <!-- header部分 -->
    <header>
      <p>在线支付</p>
    </header>

    <!-- 订单信息部分 -->
    <h3>订单信息：</h3>
    <div class="order-info">
      <p>
        {{ this.orderArray.business.businessName }}
        <i id="showBtn" class="fa fa-caret-down" @click="viewif()"></i>
      </p>
      <p>&#165;{{ this.orderArray.orderTotal + this.orderArray.business.deliveryPrice }}</p>
    </div>

    <!-- 订单明细部分 -->
    <ul v-show="this.view" id="detailetBox" class="order-detailet">
      <li v-for="item in this.orderArray.list">
        <p>{{ item.food.foodName }} x {{ item.quantity }}</p>
        <p>&#165;{{ item.food.foodPrice }}</p>
      </li>

      <li>
        <p>配送费</p>
        <p>&#165;{{ this.orderArray.business.deliveryPrice }}</p>
      </li>
    </ul>

    <!-- 支付方式部分 -->
    <ul class="payment-type">
      <li>
        <img src="../assets/wechat.png">
        <i class="fa fa-check-circle"></i>
      </li>
      <li>
        <img src="../assets/alipay.png">
      </li>
    </ul>
    <div class="payment-button">
      <button>确认支付</button>
    </div>

    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from '../components/Footer.vue'

export default {
  data() {
    return {
      // 获取query参数
      orderId: this.$route.query.orderId,
      orderArray: {
        business: {},
        list: []
      },
      user: {},
      view: false

    }
  },
  created() {
    this.user = this.$getSessionStorage('user');
    this.$axios.post('OrderController/getOrdersById', this.$qs.stringify({
      orderId: this.orderId
    })).then(response => {
      // console.log(response.data);
      if (response.data != null) {
        this.orderArray = response.data;
      } else {
        console.log("查询商家信息食物信息失败！！！！！！！！")
      }
    }).catch(error => {
      console.error(error);
    });
  },
  methods: {
    viewif() {

      this.view = !this.view
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

/****************** 订单信息部分 ******************/
.wrapper h3 {
  margin-top: 12vw;
  box-sizing: border-box;
  padding: 4vw 4vw 0;

  font-size: 4vw;
  font-weight: 300;
  color: #999;
}

.wrapper .order-info {
  box-sizing: border-box;
  padding: 4vw;
  font-size: 4vw;
  color: #666;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .order-info p:last-child {
  color: orangered;
}

/****************** 订单明细部分 ******************/
.wrapper .order-detailet {
  width: 100%;
}

.wrapper .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 4vw;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .order-detailet li p {
  font-size: 3vw;
  color: #666;
}

/****************** 支付方式部分 ******************/
.wrapper .payment-type {
  width: 100%;
}

.wrapper .payment-type li {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .payment-type li img {
  width: 33vw;
  height: 8.9vw;
}

.wrapper .payment-type li .fa-check-circle {
  font-size: 5vw;
  color: #38CA73;
}

.wrapper .payment-button {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw;
}

.wrapper .payment-button button {
  width: 100%;
  height: 10vw;
  border: none;
  /*去掉外轮廓线*/
  outline: none;
  border-radius: 4px;
  background-color: #38CA73;
  color: #fff;
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