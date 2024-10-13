package com.yiyang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyang.pojo.Bed;
import com.yiyang.pojo.vo.PageResult;
import com.yiyang.pojo.vo.Result;

import java.util.List;

public interface BedService extends IService<Bed> {
    /**
     * 根据 id 查询床位
     */
    Result<Bed> findById(Integer id);

    /**
     * 条件查询、分页查询床位
     */
    Result<PageResult<List<Bed>>> findAll(Integer currentPage, Integer pageSize, String num, Integer brid, Integer status);

    /**
     * 添加床位
     */
    Result<Void> addBed(Bed bed);

    /**
     * 修改床位
     */
    Result<Void> updateBed(Bed bed);

    /**
     * 根据 id 逻辑删除床位
     */
    Result<Void> deleteBed(Integer id);
}
