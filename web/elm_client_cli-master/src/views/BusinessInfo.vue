<template>
  <!-- 每个vue中有三个部分template，script，style -->
  <div class="wrapper">

    <!-- header部分 -->
    <header>
      <p>商家信息</p>
    </header>

    <!-- 商家logo部分 -->
    <div class="business-logo">
      <!-- 属性绑定:src不需要加大括号"business.businessImg" -->
      <img :src="business.businessImg">
    </div>

    <!-- 商家信息部分 -->
    <div class="business-info">
      <h1>{{ business.businessName }}</h1>
      <p>&#165;{{ business.starPrice }}起送 &#165;{{ business.deliveryPrice }}配送</p>
      <p>{{ business.businessExplain }}</p>
    </div>

    <!-- 食品列表部分 -->
    <ul class="food">
      <li v-for="(item,index) in foodArray">
        <div class="food-left">
          <img :src="item.foodImg">
          <div class="food-left-info">
            <h3>{{ item.foodName }}</h3>
            <p>{{ item.foodExplain }}</p>
            <p>&#165;{{ item.foodPrice }}</p>
          </div>
        </div>
        <div class="food-right">
          <div>
            <!-- 减号按钮 -->
            <i v-show="item.quantity!=0" class="fa fa-minus-circle" @click="minus(index)"></i>
          </div>
          <p><span v-show="item.quantity>0">{{ item.quantity }}</span></p>
          <div>
            <!-- 加号按钮 -->
            <i class="fa fa-plus-circle" @click="plus(index)"></i>
          </div>
        </div>
      </li>

    </ul>

    <!-- 购物车部分 -->
    <div class="cart">
      <div class="cart-left">
        <div class="cart-left-icon">
          <i class="fa fa-shopping-cart"></i>
          <div v-show="this.countPrice.count>0" class="cart-left-icon-quantity">{{ countPrice.count }}</div>
        </div>
        <div class="cart-left-info">
          <p>&#165;{{ Price }}</p>
          <p>另需配送费{{ business.deliveryPrice }}元</p>
        </div>
      </div>
      <div class="cart-right">
        <!-- 不够起送费 -->
        <!--
        <div class="cart-right-item">
          &#165;15起送
        </div>
        -->
        <!-- 达到起送费 -->
        <div class="cart-right-item" @click="toOrder()">
          去结算
        </div>
      </div>
    </div>

  </div>
</template>


<script>
export default {
  data() {
    return {
      // 获取query参数
      businessId: this.$route.query.businessId,
      business: {},
      foodArray: [],
      user: {},
      // cartArray:[],
      countPrice: {
        count: 0,
        price: 0
      },


    }
  },
  created() {
    // 从setSessionStorage中获取到用户的信息，如果没有用户信息，返回null
    this.user = this.$getSessionStorage('user');
    console.log(this.user);

    // 通过$axios向后台发送异步请求，获取商家信息
    this.$axios.post('BusinessController/getBusinessById', this.$qs.stringify({
      businessId: this.businessId
    })).then(response => {
      if (response.data != null) {
        // 如果请求成功从后台获取到数据
        this.business = response.data;
        console.log(response.data)
      } else {
        console.log("获取商家信息失败！！！！！！")
      }

    }).catch(error => {
      console.error(error);
    });


    // 通过$axios向后台发送异步请求,获取食品信息
    this.$axios.post('FoodController/listFoodByBusinessId', this.$qs.stringify({
      businessId: this.businessId
    })).then(response => {
      if (response.data != null) {
        // 如果请求成功从后台获取到数据
        this.foodArray = response.data;
        for (let i = 0; i < this.foodArray.length; i++) {
          this.foodArray[i].quantity = 0;
        }
        // 注意！！！！！！！！！！
        if (this.user != null) {
          this.listCart();
        }
      } else {
        console.log("食物查询失败！！！！！！！！！")
      }


    }).catch(error => {
      console.error(error);
    });

  },
  // 鼠标操作产生的方法
  methods: {
    toOrder() {
      if (this.user == null) {
        this.$router.push({
          path: '/login'
        })
      } else {
        this.$router.push({
          path: '/order',
          query: {
            businessId: this.businessId
          }
        })
      }
    },
    listCart() {
      // 当刷新页面的时候，原有的数据不会保存，所以需要定义一个查询购物车所有食品的信息
      // 该方法应该在页面初始化的时候被调用，即在created里面调用，
      // 注意！！！！！！！！！！！不能在created里单独写，会出现刷新有时候有数据有时候没有的情况
      // ！！！！！！！！！要写在查询后台食物列表成功之后，不会出现该问题
      this.$axios.post('CartController/listCart', this.$qs.stringify({
        userId: this.user.userId, businessId: this.businessId
      })).then(response => {
        // 如果查询成功的话，把查到的数据保存在cart数组里,
        let cartArray = response.data;
        console.log(response.data)
        // console.log('222222222222'+cartArray);
        // console.log('1111111111111111'+this.foodArray);
        // 遍历foodArray数组，找到前台后台对应的foodId，然后把后台查到的数量数据与前台对应上
        for (let foodItem of this.foodArray) {
          // console.log(foodItem);
          foodItem.quantity = 0;
          for (let cartItem of cartArray) {
            // 找到前台后台对应的foodId
            if (cartItem.foodId == foodItem.foodId) {
              // 后台查到的数量数据与前台对应上
              foodItem.quantity = cartItem.quantity;
              // 同步购物车数量（小红点）的值
              this.countPrice.count = this.countPrice.count + foodItem.quantity

            }
          }
        }
        ;

        // 刷新列表
        this.foodArray.sort();


      }).catch(error => {
        console.log(error)
      })

    },

    minus(index) {
      // 减号按钮
      // 首先验证用户是否登录，判断用户是否存在，如果不存在，跳转到用户登录界面
      if (this.user == null) {
        this.$router.push({
          path: '/login'
        })
      } else {
        // 点击一下减号按钮，购物车数量（小红点）减1
        this.countPrice.count--;
        // this.countsum1 = this.countsum1-1;
        // 登录的话，就留在该页面，判断一下前台的数量是否为1，如果是1的话，再点击减号按钮，购物车就需要删除该条记录
        if (this.foodArray[index].quantity == 1) {
          this.removeCart(index);
        } else {
          this.foodArray[index].quantity--;
          this.updateCart(index);

        }

      }

    },
    plus(index) {
      // 加号按钮
      // 首先验证用户是否登录，如果没登陆，就跳转到用户登录界面
      if (this.user == null) {
        this.$router.push({
          path: '/login'
        })
      } else {
        // 点击一下加号按钮，购物车数量（小红点）加1
        this.countPrice.count++
        // this.countsum = this.countsum+1
        // 登录了就留在该页面
        // 判断foodArray数组中food对象的quantity是否为0，(因为在刚显示该页面的时候，将前台的数量设置为0)
        // 如果是的话就向购物车表中添加一条记录
        // 不是的话就调用更新的方法，更新数据库购物车中的数量
        if (this.foodArray[index].quantity == 0) {
          this.saveCart(index);
        } else {
          // this.foodArray[index].quantity++;前台的数量加一，把数量发送给后台
          this.foodArray[index].quantity++;
          this.updateCart(index, 1);
        }


      }
    },

    // 向购物车表中添加一条记录
    saveCart(index) {
      this.$axios.post('CartController/saveCart', this.$qs.stringify({
        userId: this.user.userId, businessId: this.foodArray[index].businessId, foodId: this.foodArray[index].foodId
      })).then(response => {

        // 如果添加成功的话，就将前台的数量设置为1.与后台cart表中数据对应
        if (response.data == 1) {
          this.foodArray[index].quantity = 1;
          // 注意，数量更新后要在页面刷新，才能显示
          this.foodArray.sort()
        } else {
          console.log("添加记录失败！！！！")
        }
      }).catch(error => {
        console.log(error)
      })
    },

    // 根据用户编号、商家编号、食品编号更新数量
    updateCart(index) {
      this.$axios.post('CartController/updateCart', this.$qs.stringify({
        userId: this.user.userId,
        businessId: this.foodArray[index].businessId,
        foodId: this.foodArray[index].foodId,
        quantity: this.foodArray[index].quantity
      })).then(response => {
        // 如果后台数据库中数量更新成功，就需要把前台的数据更新为后台的数量，
        if (response.data == 1) {
          // 注意，数量更新后要在页面刷新，才能显示
          this.foodArray.sort()
        } else {
          console.log("更新记录失败！！！！")
        }
      }).catch(error => {
        console.log(error)
      })
    },
    // 据用户编号、商家编号、食品编号删除购物车表中的一条食品记录​ 根据用户编号、商家编号删除购物车表中的多条条记录
    removeCart(index) {
      this.$axios.post('CartController/removeCart', this.$qs.stringify({
        userId: this.user.userId, businessId: this.foodArray[index].businessId, foodId: this.foodArray[index].foodId
      })).then(response => {
        // 如果删除该条记录成功的话，就需要将前台的数据清除为0，并且减号按钮和数量要消失不见
        if (response.data == 1) {
          this.foodArray[index].quantity = 0;
          // 刷新列表
          this.foodArray.sort()
        }
      }).catch(error => {
        console.log(error)
      })
    }

  },
  computed: {
    Price() {
      // 因为在页面上产生操作就会回到这里，就会重新遍历foodArray数组，所以将总价格重置为0
      this.countPrice.price = 0;
      for (let i = 0; i < this.foodArray.length; i++) {

        this.countPrice.price = this.countPrice.price + this.foodArray[i].quantity * this.foodArray[i].foodPrice
      }
      console.log(this.countPrice.price)
      return this.countPrice.price
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

/****************** 商家logo部分 ******************/
.wrapper .business-logo {
  width: 100%;
  height: 35vw;
  /*使用上外边距避开header部分*/
  margin-top: 12vw;

  display: flex;
  justify-content: center;
  align-items: center;
}

.wrapper .business-logo img {
  width: 40vw;
  height: 30vw;
  border-radius: 5px;
}

/****************** 商家信息部分 ******************/
.wrapper .business-info {
  width: 100%;
  height: 20vw;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.wrapper .business-info h1 {
  font-size: 5vw;
}

.wrapper .business-info p {
  font-size: 3vw;
  color: #666;
  margin-top: 1vw;
}

/****************** 食品列表部分 ******************/
.wrapper .food {
  width: 100%;
  /*使用下外边距避开footer部分*/
  margin-bottom: 14vw;
}

.wrapper .food li {
  width: 100%;
  box-sizing: border-box;
  padding: 2.5vw;
  user-select: none;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .food li .food-left {
  display: flex;
  align-items: center;
}

.wrapper .food li .food-left img {
  width: 20vw;
  height: 20vw;
}

.wrapper .food li .food-left .food-left-info {
  margin-left: 3vw;
}

.wrapper .food li .food-left .food-left-info h3 {
  font-size: 3.8vw;
  color: #555;
}

.wrapper .food li .food-left .food-left-info p {
  font-size: 3vw;
  color: #888;
  margin-top: 2vw;
}

.wrapper .food li .food-right {
  width: 16vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .food li .food-right .fa-minus-circle {
  font-size: 5.5vw;
  color: #999;
  cursor: pointer;
}

.wrapper .food li .food-right p {
  font-size: 3.6vw;
  color: #333;
}

.wrapper .food li .food-right .fa-plus-circle {
  font-size: 5.5vw;
  color: #0097EF;
  cursor: pointer;
}

/****************** 购物车部分 ******************/
.wrapper .cart {
  width: 100%;
  height: 14vw;

  position: fixed;
  left: 0;
  bottom: 0;

  display: flex;
}

.wrapper .cart .cart-left {
  flex: 2;
  background-color: #505051;
  display: flex;
}

.wrapper .cart .cart-left .cart-left-icon {
  width: 16vw;
  height: 16vw;
  box-sizing: border-box;
  border: solid 1.6vw #444;
  border-radius: 8vw;
  background-color: #3190E8;
  font-size: 7vw;
  color: #fff;

  display: flex;
  justify-content: center;
  align-items: center;

  margin-top: -4vw;
  margin-left: 3vw;

  position: relative;
}

.wrapper .cart .cart-left .cart-left-icon-quantity {
  width: 5vw;
  height: 5vw;
  border-radius: 2.5vw;
  background-color: red;
  color: #fff;
  font-size: 3.6vw;

  display: flex;
  justify-content: center;
  align-items: center;

  position: absolute;
  right: -1.5vw;
  top: -1.5vw;
}

.wrapper .cart .cart-left .cart-left-info p:first-child {
  font-size: 4.5vw;
  color: #fff;
  margin-top: 1vw;
}

.wrapper .cart .cart-left .cart-left-info p:last-child {
  font-size: 2.8vw;
  color: #AAA;
}

.wrapper .cart .cart-right {
  flex: 1;
}

/*达到起送费时的样式*/
.wrapper .cart .cart-right .cart-right-item {
  width: 100%;
  height: 100%;
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

/*不够起送费时的样式（只有背景色和鼠标样式的区别）*/
/*
.wrapper .cart .cart-right .cart-right-item{
	width: 100%;
	height: 100%;
	background-color: #535356;
	color: #fff;
	font-size: 4.5vw;
	font-weight: 700;
	user-select: none;
	
	display: flex;
	justify-content: center;
	align-items: center;
}
*/
</style>