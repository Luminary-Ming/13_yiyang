package com.yiyang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyang.pojo.Room;
import com.yiyang.pojo.vo.PageResult;
import com.yiyang.pojo.vo.Result;

import java.util.List;

public interface RoomService extends IService<Room> {
    /**
     * 根据 id 查询房间
     */
    Result<Room> findById(Integer id);

    /**
     * 条件查询、分页查询房间
     */
    Result<PageResult<List<Room>>> findAll(Integer currentPage, Integer pageSize, String num, String floor);

    /**
     * 添加房间
     */
    Result<Void> addRoom(Room room);

    /**
     * 修改房间
     */
    Result<Void> updateRoom(Room room);

    /**
     * 根据 id 逻辑删除房间
     */
    Result<Void> deleteRoom(Integer id);

    /**
     * 查询全部房间信息
     */
    public Result<List<Room>> findRooms(String bid);
}
