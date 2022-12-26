import axios from "axios";
// @ts-ignore
import {useUserStore} from "../store/userStore.ts";
const baseURL_TEMP = import.meta.env.DEV ? import.meta.env.VITE_BASE_URL : import.meta.env.VITE_BASE_URL_PROD

const myAxios = axios.create({
    baseURL: baseURL_TEMP,
    timeout: 5000,
    headers: {'X-Custom-Header': 'foobar'}
});

// 添加请求拦截器
myAxios.interceptors.request.use(function (config) {
    if(useUserStore().getAuthorization()){
        // @ts-ignore
        config.headers.Authorization =  useUserStore().getAuthorization()
        return config;
    }
    // 在发送请求之前做些什么
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
myAxios.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    return response
}, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    if(error.response){
        const {status} = error.response;
        if(status === 403){
            //开启登录
            useUserStore().isShowModal = true
            return Promise.reject(error);
        }
    }
    return Promise.reject(error);
});
export {
    myAxios
}