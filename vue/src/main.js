import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import '@fortawesome/fontawesome-free/css/all.css';
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/css/global.css'
import '@/assets/css/theme/index.css'
import '@/assets/fonts/font-awesome.css'
import '@/assets/emoji'
import request from "@/utils/request";
import emojis from "@/assets/emoji";

Vue.config.productionTip = false
Vue.prototype.$emojis = emojis
Vue.prototype.$request = request
Vue.prototype.$baseUrl = process.env.VUE_APP_BASEURL + '/HomestayAPI'   //所有URL都加上了‘/api’前缀


Vue.use(ElementUI, {size: "small"})

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
