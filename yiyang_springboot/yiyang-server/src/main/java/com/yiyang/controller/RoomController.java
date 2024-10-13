package com.yiyang.controller;

import com.yiyang.pojo.Room;
import com.yiyang.pojo.vo.PageResult;
import com.yiyang.pojo.vo.Result;
import com.yiyang.service.RoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@Tag(name = "RoomController", description = "房间信息")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public Result<Room> findById(@PathVariable("id") Integer id) {
        return roomService.findById(id);
    }

    @GetMapping()
    public Result<PageResult<List<Room>>> findAll(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "num", defaultValue = "") String num,
            @RequestParam(name = "floor", defaultValue = "") String floor) {
        return roomService.findAll(currentPage, pageSize, num, floor);
    }

    @GetMapping("rooms")
    public Result<List<Room>> findRooms(String bid){
        return roomService.findRooms(bid);
    }

    @PostMapping
    public Result<Void> addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @PutMapping
    public Result<Void> updateRoom(@RequestBody Room room) {
        return roomService.updateRoom(room);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteRoom(@PathVariable("id") Integer id) {
        return roomService.deleteRoom(id);
    }
}
