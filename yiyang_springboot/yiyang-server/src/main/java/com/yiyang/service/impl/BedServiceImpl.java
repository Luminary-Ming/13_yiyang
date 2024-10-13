package com.yiyang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.mapper.BedMapper;
import com.yiyang.pojo.Bed;
import com.yiyang.pojo.vo.PageResult;
import com.yiyang.pojo.vo.Result;
import com.yiyang.service.BedService;
import com.yiyang.utils.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedServiceImpl extends ServiceImpl<BedMapper, Bed> implements BedService {
    @Autowired
    private BedMapper bedMapper;

    /**
     * 根据 id 查询床位
     */
    @Override
    public Result<Bed> findById(Integer id) {
        Bed bed = bedMapper.selectById(id);
        if (bed != null) {
            return Result.success(BizCode.SUCCESS, bed);
        }
        return Result.fail(BizCode.FAIL);
    }

    /**
     * 条件查询、分页查询床位
     */
    @Override
    public Result<PageResult<List<Bed>>> findAll(Integer currentPage, Integer pageSize, String num, Integer brid, Integer status) {
        Page<Bed> page = new Page<>();
        QueryWrapper<Bed> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(num), "num", num)
                .like(brid > 0, "brid", brid)
                .like(status == 0 || status == 1 || status == 2, "status", status);
        Page<Bed> bedPage = bedMapper.selectPage(page, wrapper);
        if (bedPage.getRecords() != null) {
            PageResult<List<Bed>> pageResult = new PageResult<>();
            pageResult.setTotal(bedPage.getTotal());
            pageResult.setData(bedPage.getRecords());
            return Result.success(BizCode.SUCCESS, pageResult);
        }
        return Result.fail(BizCode.FAIL);
    }

    /**
     * 添加床位
     */
    @Override
    public Result<Void> addBed(Bed bed) {
        if (bed.getStatus() == null) {
            bed.setStatus(1);
        }
        if (bed.getIsDelete() == null) {
            bed.setIsDelete(0);
        }
        if (save(bed)) {
            return Result.success();
        }
        return Result.fail(BizCode.FAIL);
    }

    /**
     * 修改床位
     */
    @Override
    public Result<Void> updateBed(Bed bed) {
        if (updateById(bed)) {
            return Result.success();
        }
        return Result.fail(BizCode.FAIL);
    }

    /**
     * 根据 id 逻辑删除床位
     */
    @Override
    public Result<Void> deleteBed(Integer id) {
        if (removeById(id)) {
            return Result.success();
        }
        return Result.fail(BizCode.FAIL);
    }
}
