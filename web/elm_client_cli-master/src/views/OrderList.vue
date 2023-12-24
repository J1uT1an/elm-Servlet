<template>
  <div class="wrapper">

    <!-- header部分 -->
    <header>
      <p>我的订单</p>
    </header>

    <!-- 订单列表部分 -->
    <h3>未支付订单信息：</h3>
    <ul class="order">
      <li v-for="(item,index) in this.OrdersArray" v-if="item.orderState==0">
        <div class="order-info">
          <p>
            {{ item.business.businessName }}
            <i class="fa fa-caret-down" @click="viewif(item)"></i>
          </p>
          <div class="order-info-right">
            <p>&#165;{{ item.orderTotal + item.business.deliveryPrice }}</p>
            <div class="order-info-right-icon" @click="topayment(index)">去支付</div>
          </div>
        </div>
        <ul v-show="item.view" class="order-detailet">
          <li v-for="item1 in item.list">
            <p>{{ item1.food.foodName }} x {{ item1.quantity }}</p>
            <p>&#165;{{ item1.food.foodPrice }}</p>
          </li>

          <li>
            <p>配送费</p>
            <p>&#165;{{ item.business.deliveryPrice }}</p>
          </li>
        </ul>
      </li>

    </ul>

    <h3>已支付订单信息：</h3>
    <ul class="order">
      <li v-for="item in this.OrdersArray" v-if="item.orderState==1">
        <div class="order-info">
          <p>
            {{ item.business.businessName }}
            <i class="fa fa-caret-down" @click="viewif(item)"></i>
          </p>
          <div class="order-info-right">
            <p>&#165;{{ item.orderTotal + item.business.deliveryPrice }}</p>
          </div>
        </div>
        <ul v-show="item.view" class="order-detailet">
          <li v-for="item1 in item.list">
            <p>{{ item1.food.foodName }} x {{ item1.quantity }}</p>
            <p>&#165;{{ item1.food.foodPrice }}</p>
          </li>
          <li>
            <p>配送费</p>
            <p>&#165;{{ item.business.deliveryPrice }}</p>
          </li>
        </ul>
      </li>
    </ul>

    <!-- 底部菜单部分 -->
    <Footer></Footer>

  </div>
</template>
<script>

import Footer from '../components/Footer.vue'

export default {
  data() {
    return {
      OrdersArray: [],
      user: {},


    }
  },
  created() {
    this.user = this.$getSessionStorage('user');
    console.log(this.user);

    this.$axios.post('OrderController/listOrdersByUserId', this.$qs.stringify({
      userId: this.user.userId
    })).then(response => {
      if (response.data != null) {
        this.OrdersArray = response.data;

        console.log(response.data);
        for (let item of this.OrdersArray) {
          item.view = false;
        }
      }

    }).catch(error => {
      console.log(error)
    })


  },
  methods: {
    viewif(item) {
      item.view = !item.view;
      this.OrdersArray.sort()
    },
    topayment(index) {
      this.$router.push({
        path: '/payment',
        query: {
          orderId: this.OrdersArray[index].orderId
        }
      })
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

/****************** 历史订单列表部分 ******************/
.wrapper h3 {
  margin-top: 12vw;
  box-sizing: border-box;
  padding: 4vw;
  font-size: 4vw;
  font-weight: 300;
  color: #999;
}

.wrapper .order {
  width: 100%;
}

.wrapper .order li {
  width: 100%;
}

.wrapper .order li .order-info {
  box-sizing: border-box;
  padding: 2vw 4vw;
  font-size: 4vw;
  color: #666;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .order li .order-info .order-info-right {
  display: flex;
}

.wrapper .order li .order-info .order-info-right .order-info-right-icon {
  background-color: #f90;
  color: #fff;
  border-radius: 3px;
  margin-left: 2vw;
  user-select: none;
  cursor: pointer;
}

.wrapper .order li .order-detailet {
  width: 100%;
}

.wrapper .order li .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 4vw;
  color: #666;
  font-size: 3vw;

  display: flex;
  justify-content: space-between;
  align-items: center;
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