package com.yiyang.controller;

import com.yiyang.pojo.Bed;
import com.yiyang.pojo.vo.PageResult;
import com.yiyang.pojo.vo.Result;
import com.yiyang.service.BedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bed")
@Tag(name = "BedController", description = "床位信息")
public class BedController {
    @Autowired
    private BedService bedService;

    @GetMapping("/{id}")
    public Result<Bed> findById(@PathVariable Integer id) {
        return bedService.findById(id);
    }

    @GetMapping
    public Result<PageResult<List<Bed>>> findAll(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "num", required = false) String num,
            @RequestParam(name = "brid", required = false) Integer brid,
            @RequestParam(name = "status", required = false) Integer status) {
        return bedService.findAll(currentPage, pageSize, num, brid, status);
    }

    @PostMapping
    public Result<Void> addBed(@RequestBody Bed bed) {
        return bedService.addBed(bed);
    }

    @PutMapping
    public Result<Void> updateBed(@RequestBody Bed bed) {
        return bedService.updateBed(bed);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteBed(@PathVariable Integer id) {
        return bedService.deleteBed(id);
    }
}
