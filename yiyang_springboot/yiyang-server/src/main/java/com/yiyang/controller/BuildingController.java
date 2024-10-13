package com.yiyang.controller;

import com.yiyang.pojo.Building;
import com.yiyang.pojo.vo.BuildingVO;
import com.yiyang.pojo.vo.PageResult;
import com.yiyang.pojo.vo.Result;
import com.yiyang.service.BuildingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/building")
@Tag(name = "BuildingController", description = "楼宇信息")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    /**
     * 根据 id 查询楼宇信息
     */
    @GetMapping("/{id}")
    public Result<Building> findById(Integer id) {
        return buildingService.findById(id);
    }

    /**
     * 分页查询、条件查询楼宇信息
     */
    @GetMapping
    public Result<PageResult<List<BuildingVO>>> findAll(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "color", defaultValue = "") String color) {
        return buildingService.findAll(currentPage, pageSize, name, color);
    }

    /**
     * 添加楼宇信息
     */
    @PostMapping
    public Result<Void> addBuilding(@RequestBody Building building) {
        return buildingService.addBuilding(building);
    }

    /**
     * 修改楼宇信息
     */
    @PutMapping
    public Result<Void> updateBuilding(@RequestBody Building building) {
        return buildingService.updateBuilding(building);
    }

    /**
     * 根据 id 逻辑删除楼宇
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteBuilding(Integer id) {
        return buildingService.deleteBuilding(id);
    }
}
