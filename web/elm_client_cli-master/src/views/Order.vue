<template>
  <div class="wrapper">

    <!-- header部分 -->
    <header>
      <p>确认订单</p>
    </header>

    <!-- 订单信息部分 -->
    <div class="order-info">

      <h5>订单配送至：</h5>
      <div class="order-info-address">
        <p @click="toUserAddress()">{{ explain }}</p>
        <i class="fa fa-angle-right"></i>
      </div>
      <p>{{ contactName }}{{ contactSex }} {{ contactTel }}</p>
    </div>


    <!-- {{item.business.businessName}} -->
    <h3>{{ this.businessname }}</h3>
    <!-- 订单明细部分 -->
    <ul class="order-detailed">
      <li v-for="item in foodArray">
        <div class="order-detailed-left">
          <img :src="item.food.foodImg">
          <p>{{ item.food.foodName }} x {{ item.quantity }}</p>
        </div>
        <p>&#165;{{ item.food.foodPrice }}</p>
      </li>

    </ul>

    <div class="order-deliveryfee">
      <p>配送费</p>
      <p>&#165;{{ this.deliveryprice }}</p>
    </div>


    <!-- 合计部分 -->
    <div class="total">
      <div class="total-left">
        &#165;{{ this.ordertotal + this.deliveryprice }}
      </div>
      <div class="total-right" @click="toOrder()">
        去支付
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      explain: '请选择要配送的地址：',
      // 获取query参数
      businessId: this.$route.query.businessId,

      daId: this.$route.query.daId,
      contactSex: this.$route.query.contactSex,
      contactName: this.$route.query.contactName,
      contactTel: this.$route.query.contactTel,
      address: this.$route.query.address,
      user: {},
      foodArray: [],
      businessname: '',
      deliveryprice: '',
      ordertotal: 0,
      orderId: 0,

    }
  },

  created() {


    this.user = this.$getSessionStorage('user');
    if (this.address != null) {
      this.explain = this.address
    }
    ;
    console.log('11' + this.businessId)
    this.$axios.post('CartController/listCart', this.$qs.stringify({
      userId: this.user.userId, businessId: this.businessId
    })).then(response => {
      // 如果查询成功的话，把查到的数据保存在foodArray数组里,
      this.foodArray = response.data;
      console.log("12222")
      console.log(this.businessId)
      console.log(response.data);
      for (let i = 0; i < this.foodArray.length; i++) {
        this.businessname = this.foodArray[i].business.businessName;
        this.deliveryprice = this.foodArray[i].business.deliveryPrice;
        this.ordertotal += this.foodArray[i].food.foodPrice * this.foodArray[i].quantity;
        // if(this.foodArray[i].businessId!=this.businessid){
        // 	console.log("111111111111111111111")
        // 	// this.foodArray.splice(i,1);
        // 	console.log(this.foodArray[i].businessId)
        // 	console.log(this.businessid)

        // }else{
        // 	continue;
        // }
        // console.log(this.foodArray)
      }

    }).catch(error => {
      console.log(error)
    });

  },
  methods: {
    toUserAddress() {
      console.log(this.businessId),
          this.$router.push({

            path: '/useraddress',
            query: {
              businessId: this.businessId
            }

          })
    },
    toOrder() {
      if (this.address == null) {
        alert("请先选择收货地址！！")
      }
      if (this.address != null) {
        // 调用后台的createOrders合成方法
        this.$axios.post('OrderController/createOrders', this.$qs.stringify({
          userId: this.user.userId, businessId: this.businessId, daId: this.daId, orderTotal: this.ordertotal
        })).then(response => {
          if (response.data != 0) {
            this.orderId = response.data;
            // 如果请求成功,返回orderId订单编号
            // 则表明 * 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录，​ 并获取自动生成的订单编号，​
            //  * 然后根据用户编号、商家编号从购物车表中查询所有数据，
            //  * 批量添加到订单明细表中，​
            //  * 然后根据用户编号、商家编号删除购物车表中的数据。
            console.log(response.data)
            // 成功之后跳转到支付页面
            this.$router.push({
              path: '/payment',
              query: {
                orderId: this.orderId,
              }
            })

          } else {
            console.log("调用合成方法失败！！！！！！")
          }

        }).catch(error => {
          console.error(error);
        });
      }
    }
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
.wrapper .order-info {
  /*注意这里，不设置高，靠内容撑开。因为地址有可能折行*/
  width: 100%;
  margin-top: 12vw;
  background-color: #0097EF;
  box-sizing: border-box;
  padding: 2vw;
  color: #fff;
}

.wrapper .order-info h5 {
  font-size: 3vw;
  font-weight: 300;
}

.wrapper .order-info .order-info-address {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;

  font-weight: 700;
  user-select: none;
  cursor: pointer;
  margin: 1vw 0;
}

.wrapper .order-info .order-info-address p {
  width: 90%;
  font-size: 5vw;
}

.wrapper .order-info .order-info-address i {
  font-size: 6vw;
}

.wrapper .order-info p {
  font-size: 3vw;
}

.wrapper h3 {
  box-sizing: border-box;
  padding: 3vw;
  font-size: 4vw;
  color: #666;
  border-bottom: solid 1px #DDD;
}

/****************** 订单明细部分 ******************/
.wrapper .order-detailed {
  width: 100%;
}

.wrapper .order-detailed li {
  width: 100%;
  height: 16vw;
  box-sizing: border-box;
  padding: 3vw;
  color: #666;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .order-detailed li .order-detailed-left {
  display: flex;
  align-items: center;
}

.wrapper .order-detailed li .order-detailed-left img {
  width: 10vw;
  height: 10vw;
}

.wrapper .order-detailed li .order-detailed-left p {
  font-size: 3.5vw;
  margin-left: 3vw;
}

.wrapper .order-detailed li p {
  font-size: 3.5vw;
}

.wrapper .order-deliveryfee {
  width: 100%;
  height: 16vw;
  box-sizing: border-box;
  padding: 3vw;
  color: #666;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 3.5vw;
}

/****************** 订单合计部分 ******************/
.wrapper .total {
  width: 100%;
  height: 14vw;

  position: fixed;
  left: 0;
  bottom: 0;

  display: flex;
}

.wrapper .total .total-left {
  flex: 2;
  background-color: #505051;
  color: #fff;
  font-size: 4.5vw;
  font-weight: 700;
  user-select: none;

  display: flex;
  justify-content: center;
  align-items: center;
}

.wrapper .total .total-right {
  flex: 1;
  background-color: #38CA73;
  color: #fff;
  font-size: 4.5vw;
  font-weight: 700;
  user-select: none;
  cursor: pointer;

  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
