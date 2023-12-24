import Vue from 'vue'
import App from './App.vue'
// 路由
import router from './router'
// 字体库
import 'font-awesome/css/font-awesome.min.css'
// ajax封装好的库，专门用来发送异步请求
import axios from 'axios'
// 简单来说，qs 是一个增加了一些安全性的查询字符串解析和序列化字符串的库。
// 在项目中使用命令行工具输入：npm install qs
// 安装完成后在需要用到的组件中：import qs from 'qs’
// 具体使用中我查看了：qs.parse()和qs.stringify()
// 什么是序列化？
// 序列化就是把前台数据解析成key，value&key,value...的形式，发送给后台
// 这两种方法虽然都是序列化，但是还是有区别的。
// qs.parse()是将URL解析成对象的形式
// qs.stringify()是将对象 序列化成URL的形式，以&进行拼接
import qs from 'qs'
// 通用的工具类，'./common.js'，多个方法，导入了对象
import {
    getCurDate,
    getLocalStorage,
    getSessionStorage,
    removeLocalStorage,
    removeSessionStorage,
    setLocalStorage,
    setSessionStorage
} from './common.js'


Vue.config.productionTip = false

//设置axios的基础url部分（后台）
axios.defaults.baseURL = 'http://localhost:8080/elm/';
//将axios挂载到vue实例上，使用时就可以 this.$axios 这样使用了
// 向原来的Vue库中，通过原型prototype添加一个$axios方法（通过 prototype 属性向已有的构造器添加方法）
// $是一个约定
Vue.prototype.$axios = axios;

Vue.prototype.$qs = qs;

Vue.prototype.$getCurDate = getCurDate;
Vue.prototype.$setSessionStorage = setSessionStorage;
Vue.prototype.$getSessionStorage = getSessionStorage;
Vue.prototype.$removeSessionStorage = removeSessionStorage;
Vue.prototype.$setLocalStorage = setLocalStorage;
Vue.prototype.$getLocalStorage = getLocalStorage;
Vue.prototype.$removeLocalStorage = removeLocalStorage;

router.beforeEach(function (to, from, next) {
    let user = sessionStorage.getItem('user');
    //除了登录、注册、首页、商家列表、商家信息之外，都需要判断是否登录
    if (!(to.path == '/' || to.path == '/index' || to.path == '/businessList' || to.path == '/businessInfo' || to.path == '/login' || to.path == '/register')) {
        if (user == null) {
            // 跳转到注册页面
            router.push('/login');
// 			reload()方法用于刷新当前文档。
//         reload() 方法类似于你浏览器上的刷新页面按钮。
            location.reload();
        }
    }
    next();
});

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')

