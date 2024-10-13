package com.yiyang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.mapper.BuildRoomMapper;
import com.yiyang.mapper.RoomMapper;
import com.yiyang.pojo.BuildRoom;
import com.yiyang.pojo.Room;
import com.yiyang.pojo.vo.PageResult;
import com.yiyang.pojo.vo.Result;
import com.yiyang.service.BuildRoomService;
import com.yiyang.service.RoomService;
import com.yiyang.utils.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    public RoomMapper roomMapper;

    @Autowired
    public BuildRoomMapper buildRoomMapper;

    /**
     * 根据 id 查询房间
     */
    @Override
    public Result<Room> findById(Integer id) {
        Room room = roomMapper.selectById(id);
        if (room != null) {
            return Result.success(BizCode.SUCCESS, room);
        }
        return Result.fail(BizCode.FAIL);
    }

    /**
     * 条件查询、分页查询房间
     */
    @Override
    public Result<PageResult<List<Room>>> findAll(Integer currentPage, Integer pageSize, String num, String floor) {
        Page<Room> page = new Page<>(currentPage, pageSize);
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(num), "num", num)
                .like(StringUtils.isNotBlank(floor), "floor", floor);
        Page<Room> roomPage = roomMapper.selectPage(page, wrapper);
        // 如果查询的记录不为空
        if (roomPage.getRecords() != null) {
            PageResult<List<Room>> pageResult = new PageResult<>();
            pageResult.setTotal(roomPage.getTotal());
            pageResult.setData(roomPage.getRecords());
            return Result.success(BizCode.SUCCESS, pageResult);
        }
        return Result.fail(BizCode.FAIL);
    }

    /**
     * 添加房间
     */
    @Override
    public Result<Void> addRoom(Room room) {
        // 如果添加的不是弃用房间，则默认为使用房间
        if (room.getIsDelete() == null) {
            room.setIsDelete(0);
        }
        // 使用 IService<T> 中的 save() 方法
        if (save(room)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 修改房间
     */
    @Override
    public Result<Void> updateRoom(Room room) {
        if (updateById(room)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 根据 id 逻辑删除房间
     */
    @Override
    public Result<Void> deleteRoom(Integer id) {
        if (removeById(id)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 查询全部房间信息
     */
    public Result<List<Room>> findRooms(String bid) {
        // 需要根据楼宇的id，去查询当前楼宇中已经绑定的房间的id信息
        List<BuildRoom> buildRoomList = buildRoomMapper.selectList(new QueryWrapper<BuildRoom>().eq("bid", bid));
        // 通过查询到的楼宇中包含的所有房间与楼宇的关系对象，需要获取到房间的id，添加到一个新的集合中
        List<Integer> ids = new ArrayList<>();
        // 遍历根据楼宇id查询到的楼宇与房间关系对象集合
        for (BuildRoom buildingRoom : buildRoomList) {
            // 获取到房间的id编号，存储在ids集合中
            ids.add(buildingRoom.getRid());
        }
        // 构建查询房间的条件对象
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        // 将房间的 id 列表作为参数传递
        wrapper.notIn("id", ids);
        List<Room> roomList = this.list(wrapper);
        if (!roomList.isEmpty()) {
            return Result.success(BizCode.SUCCESS, roomList);
        }
        return Result.fail(BizCode.NO_DATA);
    }
}
