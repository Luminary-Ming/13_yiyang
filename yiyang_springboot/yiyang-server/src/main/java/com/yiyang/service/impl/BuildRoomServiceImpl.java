package com.yiyang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.mapper.BuildRoomMapper;
import com.yiyang.mapper.BuildingMapper;
import com.yiyang.mapper.RoomMapper;
import com.yiyang.pojo.BuildRoom;
import com.yiyang.pojo.Building;
import com.yiyang.pojo.Room;
import com.yiyang.pojo.vo.*;
import com.yiyang.service.BuildRoomService;
import com.yiyang.utils.BizCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BuildRoomServiceImpl extends ServiceImpl<BuildRoomMapper, BuildRoom> implements BuildRoomService {
    @Autowired
    private BuildRoomMapper buildRoomMapper;
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private RoomMapper roomMapper;


    /**
     * 根据 id 查询房间与楼宇关系的信息
     */
    @Override
    public Result<BuildRoomVO> findById(Integer id) {
        BuildRoom buildRoom = buildRoomMapper.selectById(id);
        if (buildRoom != null) {
            // 创建 BuildRoomVO 对象
            BuildRoomVO buildRoomVO = new BuildRoomVO();
            // 设置楼宇名称 = 根据楼宇 id 查到的楼宇名称
            buildRoomVO.setBuildingName(buildingMapper.selectById(buildRoom.getBid()).getName());
            // 设置房间号 = 根据房间 id 查询到的房间号
            buildRoomVO.setRooNum(roomMapper.selectById(buildRoom.getRid()).getNum());
            // 使用 Spring 框架中的工具类，把 buildRoom 对象中的属性值复制给 buildRoomVO 对象
            BeanUtils.copyProperties(buildRoom, buildRoomVO);
            return Result.success(BizCode.SUCCESS, buildRoomVO);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 分页查询、条件查询房间与楼宇关系的信息
     */
    @Override
    public Result<PageResult<List<BuildRoomVO>>> findAll(Integer currentPage, Integer pageSize, String type, String direction) {
        Page<BuildRoom> page = new Page<>(currentPage, pageSize);
        QueryWrapper<BuildRoom> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(type), "type", type)
                .like(StringUtils.isNotBlank(direction), "direction", direction);
        Page<BuildRoom> buildRoomPage = buildRoomMapper.selectPage(page, wrapper);
        if (buildRoomPage.getRecords() != null) {
            // 创建 List<BuildRoomVO> 集合
            List<BuildRoomVO> buildRoomVOList = new ArrayList<>();
            // 获取查询到的 BuildRoom 的信息集合
            List<BuildRoom> buildRoomList = buildRoomPage.getRecords();
            // 遍历每个 buildRoom
            for (BuildRoom buildRoom : buildRoomList) {
                // 创建 BuildRoomVO 对象
                BuildRoomVO buildRoomVO = new BuildRoomVO();
                // 设置楼宇名称 = 根据楼宇 id 查到的楼宇名称
                buildRoomVO.setBuildingName(buildingMapper.selectById(buildRoom.getBid()).getName());
                // 设置房间号 = 根据房间 id 查询到的房间号
                buildRoomVO.setRooNum(roomMapper.selectById(buildRoom.getRid()).getNum());
                // 使用 Spring 框架中的工具类，把 buildRoom 对象中的属性值复制给 buildRoomVO 对象
                BeanUtils.copyProperties(buildRoom, buildRoomVO);
                // 把 buildRoomVO 这个对象存放到集合中，作为最后分页返回的数据
                buildRoomVOList.add(buildRoomVO);
            }
            // 创建 PageResult<List<BuildRoomVO>> 分页对象
            PageResult<List<BuildRoomVO>> pageResult = new PageResult<>();
            pageResult.setTotal(page.getTotal());
            // 把 buildRoomVOList 集合作为最后返回的数据
            pageResult.setData(buildRoomVOList);
            return Result.success(BizCode.SUCCESS, pageResult);
        }
        return Result.fail(BizCode.NO_DATA);
    }


    /**
     * 添加房间与楼宇关系的信息
     */
    @Override
    public Result<Void> addBuildRoom(BuildRoom buildRoom) {
        if (buildRoom.getIsDelete() == null) {
            buildRoom.setIsDelete(0);
        }
        if (save(buildRoom)) {
            return Result.success(BizCode.SUCCESS);
        }
        return Result.fail(BizCode.ADD_BUILDING_ROOM_ERROR);
    }

    /**
     * 修改房间与楼宇关系的信息
     */
    @Override
    public Result<Void> updateBuildRoom(BuildRoom buildRoom) {
        if (updateById(buildRoom)) {
            return Result.success(BizCode.SUCCESS);
        }
        return Result.fail(BizCode.UPDATE_BUILDING_ROOM_ERROR);
    }

    /**
     * 根据 id 删除房间与楼宇关系的信息
     */
    @Override
    public Result<Void> deleteBuildRoom(Integer id) {
        if (removeById(id)) {
            return Result.success(BizCode.SUCCESS);
        }
        return Result.fail(BizCode.DELETE_BUILDING_ROOM_ERROR);
    }

    /**
     * 根据楼宇的 id 查询楼宇的所有房间信息
     */
    @Override
    public Result<List<BuildFloorVO>> findBuildAndRoom(Integer bid) {
        QueryWrapper<BuildRoom> wrapper = new QueryWrapper<>();
        wrapper.eq("bid", bid);
        List<BuildRoom> buildingList = buildRoomMapper.selectList(wrapper);
        if (!buildingList.isEmpty()) {
            // 当前这个List集合中存储的楼宇与房间绑定的数据对象
            List<BuildRoomVO> list = new ArrayList<>();
            for (BuildRoom buildingRoom : buildingList) {
                // 房间id
                Integer rid = buildingRoom.getRid();
                // 查询房间所在的楼层和房间编号
                Room room = this.roomMapper.selectById(rid);
                BuildRoomVO buildRoomVO = new BuildRoomVO();
                // 设置房间所在楼层
                buildRoomVO.setFloor(room.getFloor());
                // 设置房间编号
                buildRoomVO.setRooNum(room.getNum());
                // 将查询到的房间楼宇数据拷贝到VO对象上
                BeanUtils.copyProperties(buildingRoom, buildRoomVO);
                list.add(buildRoomVO);
            }

            // 对上面封装的  List<BuildingRoomVO> 中的房间信息需要安装楼层进行区分
            List<BuildFloorVO> buildFloorVOList = new ArrayList<>();
            Map<String, List<BuildRoomVO>> map = new HashMap<>();
            // 遍历 List<BuildingRoomVO>集合
            for (BuildRoomVO buildingRoomVO : list) {
                if (map.containsKey(buildingRoomVO.getFloor())) {
                    // 判断成立，说明当前楼层对应的房间已经存储部分，当前遍历出来的是同楼层的其他房间信息
                    List<BuildRoomVO> buildingRoomVOS = map.get(buildingRoomVO.getFloor());
                    buildingRoomVOS.add(buildingRoomVO);
                } else {
                    // 当前遍历出来的房间是当前楼层的第一个房间数据
                    List<BuildRoomVO> list2 = new ArrayList<>();
                    list2.add(buildingRoomVO);
                    //map.put(buildingRoomVO.getFloor(), Arrays.asList(buildingRoomVO));
                    map.put(buildingRoomVO.getFloor(), list2);
                }
                String floor = buildingRoomVO.getFloor();
                BuildFloorVO vo = new BuildFloorVO();
            }
            // 遍历Map集合，封装数据
            Set<Map.Entry<String, List<BuildRoomVO>>> entries = map.entrySet();
            for (Map.Entry<String, List<BuildRoomVO>> entry : entries) {
                // 需要单独封装一个对象 BuildFloorVO
                BuildFloorVO buildFloorVO = new BuildFloorVO();
                // 设置楼层
                buildFloorVO.setFloor(entry.getKey());
                // 设置当前楼层中的所有绑定的房间信息
                buildFloorVO.setBuildRoomVOList(entry.getValue());
                buildFloorVOList.add(buildFloorVO);
            }
            return Result.success(BizCode.SUCCESS, buildFloorVOList);
        }
        return Result.fail(BizCode.NO_DATA);
    }
}
