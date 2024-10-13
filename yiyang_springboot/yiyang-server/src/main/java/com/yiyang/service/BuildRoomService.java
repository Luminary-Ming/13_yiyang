package com.yiyang.service;

import com.yiyang.pojo.BuildRoom;
import com.yiyang.pojo.vo.BuildFloorVO;
import com.yiyang.pojo.vo.BuildRoomVO;
import com.yiyang.pojo.vo.PageResult;
import com.yiyang.pojo.vo.Result;

import java.util.List;

public interface BuildRoomService {
    /**
     * 根据 id 查询房间与楼宇关系的信息
     */
    public Result<BuildRoomVO> findById(Integer id);

    /**
     * 分页查询、条件查询房间与楼宇关系的信息
     */
    public Result<PageResult<List<BuildRoomVO>>> findAll(Integer currentPage, Integer pageSize, String type, String direction);

    /**
     * 添加房间与楼宇关系的信息
     */
    public Result<Void> addBuildRoom(BuildRoom buildRoom);

    /**
     * 修改房间与楼宇关系的信息
     */
    public Result<Void> updateBuildRoom(BuildRoom buildRoom);

    /**
     * 根据 id 删除房间与楼宇关系的信息
     */
    public Result<Void> deleteBuildRoom(Integer id);

    /**
     * 根据楼宇的 id 查询楼宇的所有房间信息
     */
    Result<List<BuildFloorVO>> findBuildAndRoom(Integer bid);
}
