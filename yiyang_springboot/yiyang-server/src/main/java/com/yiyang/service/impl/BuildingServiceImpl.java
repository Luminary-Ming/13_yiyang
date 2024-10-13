package com.yiyang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.mapper.BuildRoomMapper;
import com.yiyang.mapper.BuildingMapper;
import com.yiyang.pojo.BuildRoom;
import com.yiyang.pojo.Building;
import com.yiyang.pojo.vo.BuildingVO;
import com.yiyang.pojo.vo.PageResult;
import com.yiyang.pojo.vo.Result;
import com.yiyang.service.BuildingService;
import com.yiyang.utils.BizCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private BuildRoomMapper buildRoomMapper;

    /**
     * 根据 id 查询楼宇信息
     */
    @Override
    public Result<Building> findById(Integer id) {
        Building building = buildingMapper.selectById(id);
        if (building != null) {
            return Result.success(BizCode.SUCCESS, building);
        }
        return Result.fail(BizCode.FAIL);
    }

    /**
     * 分页查询、条件查询楼宇信息
     */
    @Override
    public Result<PageResult<List<BuildingVO>>> findAll(Integer currentPage, Integer pageSize, String name, String color) {
        Page<Building> page = new Page<>(currentPage, pageSize);
        QueryWrapper<Building> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name)
                .like(StringUtils.isNotBlank(color), "color", color);
        Page<Building> buildingPage = buildingMapper.selectPage(page, wrapper);
        if (buildingPage.getRecords() != null) {
            // 创建 List<BuildingVO> 集合
            List<BuildingVO> buildingVOList = new ArrayList<>();
            // 获取查询到的楼宇信息的集合
            List<Building> buildingList = buildingPage.getRecords();
            // 遍历每个楼宇
            for (Building building : buildingList) {
                // 查询每个楼宇中的房间数
                QueryWrapper<BuildRoom> buildRoomWrapper = new QueryWrapper<>();
                buildRoomWrapper.eq("bid", building.getId());
                // 创建 BuildingVO 对象
                BuildingVO buildingVO = new BuildingVO();
                // 使用 Spring 框架中的工具类，把 Building 对象中的属性值复制给 BuildingVO 对象
                BeanUtils.copyProperties(building, buildingVO);
                // 房间数 = 根据楼宇id查询到的总记录数
                buildingVO.setRoomCount(buildRoomMapper.selectCount(buildRoomWrapper));
                // 把 buildingVO 这个对象存放到集合中，作为最后分页返回的数据
                buildingVOList.add(buildingVO);
            }
            // 创建 PageResult<List<BuildingVO>> 分页对象
            PageResult<List<BuildingVO>> pageResult = new PageResult<>();
            pageResult.setTotal(buildingPage.getTotal());
            // 最后分页返回的数据是存有房间数和 Building 中所有属性的 buildingVOList 集合
            pageResult.setData(buildingVOList);
            return Result.success(BizCode.SUCCESS, pageResult);
        }
        return Result.fail(BizCode.FAIL);
    }

    /**
     * 添加楼宇信息
     */
    @Override
    public Result<Void> addBuilding(Building building) {
        // 设置添加楼宇信息的当前系统时间
        building.setCreateTime(new Date());
        if (building.getIsDelete() == null) {
            building.setIsDelete(0);
        }
        if (save(building)) {
            return Result.success(BizCode.SUCCESS);
        }
        return Result.fail(BizCode.FAIL);
    }

    /**
     * 修改楼宇信息
     */
    @Override
    public Result<Void> updateBuilding(Building building) {
        if (updateById(building)) {
            return Result.success(BizCode.SUCCESS);
        }
        return Result.fail(BizCode.FAIL);
    }

    /**
     * 根据 id 逻辑删除楼宇
     */
    @Override
    public Result<Void> deleteBuilding(Integer id) {
        if (removeById(id)) {
            return Result.success(BizCode.SUCCESS);
        }
        return Result.fail(BizCode.FAIL);
    }
}
