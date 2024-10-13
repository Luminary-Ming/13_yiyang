/**
 * 用于封装与房间相关的接口的请求函数
 */

// 导入封装的axios实例对象
import instance from "@/utils/http.js";
//import { ROOM_URL, LOGIN_URL , USER_URL } from "@/api/url.js";
import * as url from "@/api/url.js";
/**
 * 添加房间的接口函数
 */
export const addRoomAPI = (data) => {
    return instance({
        url: url.ROOM_URL,
        method: "post",
        // 针对post、put等请求方式，参数必须使用data属性进行传递
        data,
    });
};

/**
 * 修改房间的接口函数
 */
export const updateRoomAPI = (data) => {
    return instance({
        url: url.ROOM_URL,
        method: "put",
        // 针对post、put等请求方式，参数必须使用data属性进行传递
        data,
    });
};

/**
 * 删除房间的接口函数
 */
export const deleteRoomAPI = (data) => {
    return instance({
        url: url.ROOM_URL + "/" + data,
        method: "delete",
    });
};

/**
 * 分页和条件查询房间的接口函数
 */
export const findAllRoomAPI = (params) => {
    return instance({
        url: url.ROOM_URL,
        method: "get",
        params,
    });
};

/**
 * 根据id查询房间的接口函数
 */
export const findByIdAPI = (params) => {
    return instance({
        url: url.ROOM_URL + "/" + params,
        method: "get",
    });
};

/**
 * 查询所有房间数据
 */
export const findRoomsAPI = (params) => {
    return instance({
        url: url.ROOMS_URL,
        method: "get",
        params,
    });
};
