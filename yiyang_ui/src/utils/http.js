/**
 * 这个文件用于封装axios的最基本的一些参数
 */
import axios from "axios";

// 设置axios发送请求时的一些公共的参数
const instance = axios.create({
    // 设置axios请求后端程序的公共的路径
    baseURL: "http://127.0.0.1:80/",
    // 设置axios的请求超时时间
    //timeout: 1000,
    // 设置请求中公共的请求头信息
    //headers: { "X-Custom-Header": "foobar" },
    responseType: "json",
});

// 添加请求拦截器
instance.interceptors.request.use(
    function (config) {
        // 判断当前这个请求有没有其他的权限要求
        // 在发送请求之前做些什么
        return config;
    },
    function (error) {
        // 对请求错误做些什么
        return Promise.reject(error);
    }
);
// 添加响应拦截器
instance.interceptors.response.use(
    function (response) {
        if (response.data.code == 2000) {
            ElNotification.success("操作成功");
        }
        return response;
    },
    function (error) {
        // 对响应错误做点什么
        return Promise.reject(error);
    }
);

export default instance;
