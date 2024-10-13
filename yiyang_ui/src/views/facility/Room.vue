<template>
    <div class="room">
        <el-card>
            <!-- 标题 -->
            <template #header>
                <h3>房间列表</h3>
            </template>
            <!-- 搜索区域 -->
            <el-form inline>
                <el-form-item label="房间号">
                    <el-input v-model="num"></el-input>
                </el-form-item>
                <el-form-item label="楼层">
                    <el-input v-model="floor"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="searchData">搜索</el-button>
                    <el-button type="success" @click="addRoom">添加</el-button>
                </el-form-item>
            </el-form>
            <!-- 数据区域 -->
            <el-table :data="roomArr" border stripe>
                <el-table-column type="index" label="序号"></el-table-column>
                <el-table-column prop="num" label="房间编号"></el-table-column>
                <el-table-column prop="floor" label="楼层"></el-table-column>
                <el-table-column label="操作">
                    <template #default="scope">
                        <el-button type="primary" size="small" @click="updateRoom(scope.row)"> 修改 </el-button>
                        <el-button type="danger" size="small" @click="deleteRoom(scope.row)"> 删除 </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 分页区域 -->
            <template #footer>
                <el-pagination
                    v-model:current-page="currentPage"
                    v-model:page-size="pageSize"
                    :page-sizes="[5, 10, 20, 30, 50]"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                />
            </template>
        </el-card>
        <el-dialog v-model="dialogFormVisible" :title="title" width="500">
            <el-form :model="roomForm" label-width="80px">
                <el-form-item label="房间号">
                    <el-input v-model="roomForm.num" />
                </el-form-item>
                <el-form-item label="楼层">
                    <el-input v-model="roomForm.floor" />
                </el-form-item>
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取消</el-button>
                    <el-button type="primary" @click="submitData">
                        {{ title }}
                    </el-button>
                </div>
            </template>
        </el-dialog>
    </div>
</template>

<script>
import * as roomAPI from "@/api/room";
export default {
    data() {
        return {
            // 弹窗的参数
            dialogFormVisible: false,
            title: "添加房间号",
            roomForm: {},
            // 搜索的参数
            num: "",
            floor: "",
            // 分页相关的参数
            currentPage: 1,
            pageSize: 5,
            total: 0,
            // 数据列表
            roomArr: [],
        };
    },
    methods: {
        handleSizeChange() {
            this.loadRoomData();
        },
        handleCurrentChange() {
            this.loadRoomData();
        },
        // 点击搜索
        searchData() {
            this.loadRoomData();
        },
        // 点击添加按钮
        addRoom() {
            this.dialogFormVisible = true;
            this.title = "添加房间号";
            this.roomForm = {};
        },
        // 点击修改按钮
        updateRoom(row) {
            this.dialogFormVisible = true;
            this.title = "修改房间号";
            // 应给将传递进来的对象进行克隆
            this.roomForm = { ...row };
        },
        // 点击弹窗中的提交数据按钮
        submitData() {
            if (this.title == "添加房间号") {
                roomAPI.addRoomAPI(this.roomForm).then((resp) => {
                    if (resp.data.code == 2000) {
                        this.dialogFormVisible = false;
                        this.loadRoomData();
                    }
                });
            } else {
                roomAPI.updateRoomAPI(this.roomForm).then((resp) => {
                    if (resp.data.code == 2000) {
                        this.dialogFormVisible = false;
                        this.loadRoomData();
                    }
                });
            }
        },
        // 点击删除按钮
        deleteRoom(row) {
            roomAPI.deleteRoomAPI(row.id).then((resp) => {
                if (resp.data.code == 2000) {
                    this.loadRoomData();
                }
            });
        },
        // 加载数据的函数
        loadRoomData() {
            // 在ES6中，可以将异步请求改为同步，使用async和await
            roomAPI
                .findAllRoomAPI({
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    num: this.num,
                    floor: this.floor,
                })
                .then((resp) => {
                    if (resp.data.code == 2000) {
                        console.log(resp.data.data);
                        this.roomArr = resp.data.data.data;
                        this.total = resp.data.data.total;
                    }
                });
        },
    },
    created() {
        // 加载数据
        this.loadRoomData();
    },
};
</script>

<style lang="less" scoped>
.room {
    width: 100%;
    height: 100%;
}
</style>
