import axios from 'axios'
import router from "@/router";

// 创建可一个新的axios对象
const request = axios.create({
    //所有URL都加上了‘/api’前缀
    baseURL: process.env.VUE_APP_BASEURL/*+ '/HomestayAPI'*/,  // 后端的接口地址  ip:port/HomestayAPI
    timeout: 30000,                          // 30s请求超时
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';        // 设置请求头格式
    let user = JSON.parse(localStorage.getItem("xm-user") || '{}')  // 获取缓存的用户信息
    config.headers['token'] = user.token  // 设置请求头

/*    // 添加/api前缀到请求路径
    if (!config.url.startsWith('/api') && !config.url.startsWith('http')) {
        config.url = '/api' + config.url;
    }*/

    return config
}, error => {
    console.error('request error: ' + error) // for debug
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;

        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        if (res.code === '401') {
            router.push('/login')
        }
        return res;
    },
    error => {
        console.error('response error: ' + error) // for debug
        return Promise.reject(error)
    }
)


export default request