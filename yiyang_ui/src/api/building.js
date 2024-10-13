/**
 * 用于封装与楼宇相关的接口的请求函数
 */

// 导入封装的axios实例对象
import instance from "@/utils/http.js";
import * as url from "@/api/url.js";

/**
 * 查询楼宇列表函数接口
 */
export const findAllBuildingAPI = (params) => {
    return instance({
        url: url.BUILDING_URL,
        method: "get",
        params,
    });
};

/**
 * 根据id删除楼宇信息
 */
export const deleteBuildingAPI = (data) => {
    return instance({
        url: url.BUILDING_URL + "/" + data,
        method: "delete",
    });
};

/**
 * 添加楼宇信息
 */
export const addBuildingAPI = (data) => {
    return instance({
        url: url.BUILDING_URL,
        method: "post",
        data,
    });
};

/**
 * 修改楼宇信息
 */
export const updateBuildingAPI = (data) => {
    return instance({
        url: url.BUILDING_URL,
        method: "put",
        data,
    });
};

/**
 * 楼宇绑定房间数据
 */
export const addBindingRoomAPI = (data) => {
    return instance({
        url: url.BUILDINGROOM_URL,
        method: "post",
        data,
    });
};

/**
 * 根据楼宇id查询楼宇对应的房间数据信息
 */
export const buildRoomAPI = (params) => {
    return instance({
        url: url.BUILDINGROOM__BID_URL,
        method: "get",
        params,
    });
};
