package com.yiyang.controller;

import com.yiyang.pojo.BuildRoom;
import com.yiyang.pojo.vo.BuildFloorVO;
import com.yiyang.pojo.vo.BuildRoomVO;
import com.yiyang.pojo.vo.PageResult;
import com.yiyang.pojo.vo.Result;
import com.yiyang.service.BuildRoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buildRoom")
@Tag(name = "BuildRoomController", description = "楼宇与房间关系的信息")
public class BuildRoomController {
    @Autowired
    private BuildRoomService buildRoomService;

    /**
     * 根据 id 查询房间与楼宇关系的信息
     */
    @GetMapping("/{id}")
    public Result<BuildRoomVO> findById(@PathVariable("id") Integer id) {
        return buildRoomService.findById(id);
    }

    /**
     * 分页查询、条件查询房间与楼宇关系的信息
     */
    @GetMapping
    public Result<PageResult<List<BuildRoomVO>>> findAll(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "type", defaultValue = "") String type,
            @RequestParam(name = "direction", defaultValue = "") String direction) {
        return buildRoomService.findAll(currentPage, pageSize, type, direction);
    }

    /**
     * 添加房间与楼宇关系的信息
     */
    @PostMapping
    public Result<Void> addBuildRoom(@RequestBody BuildRoom buildRoom) {
        return buildRoomService.addBuildRoom(buildRoom);
    }

    /**
     * 修改房间与楼宇关系的信息
     */
    @PutMapping
    public Result<Void> updateBuildRoom(@RequestBody BuildRoom buildRoom) {
        return buildRoomService.updateBuildRoom(buildRoom);
    }

    /**
     * 根据 id 删除房间与楼宇关系的信息
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteBuildRoom(@PathVariable("id") Integer id) {
        return buildRoomService.deleteBuildRoom(id);
    }

    @GetMapping("/bid")
    public Result<List<BuildFloorVO>> findBuildAndRoom(Integer bid) {
        return buildRoomService.findBuildAndRoom(bid);
    }
}