package com.yiyang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyang.pojo.Building;
import com.yiyang.pojo.vo.BuildingVO;
import com.yiyang.pojo.vo.PageResult;
import com.yiyang.pojo.vo.Result;

import java.util.List;

public interface BuildingService extends IService<Building> {
    /**
     * 根据 id 查询楼宇信息
     */
    public Result<Building> findById(Integer id);

    /**
     * 分页查询、条件查询楼宇信息
     */
    public Result<PageResult<List<BuildingVO>>> findAll(Integer currentPage, Integer pageSize, String name, String color);

    /**
     * 添加楼宇信息
     */
    public Result<Void> addBuilding(Building building);

    /**
     * 修改楼宇信息
     */
    public Result<Void> updateBuilding(Building building);

    /**
     * 根据 id 逻辑删除楼宇
     */
    public Result<Void> deleteBuilding(Integer id);
}
