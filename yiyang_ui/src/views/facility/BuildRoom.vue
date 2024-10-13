<template>
    <div class="box">
        <el-card>
            <el-collapse v-model="activeNames" @change="handleChange">
                <el-collapse-item v-for="item in buildRoomArr" :key="item.floor" :title="item.floor + '楼'" :name="item.floor">
                    <div class="room">
                        <el-descriptions v-for="room in item.buildingRoomVOList" :column="2" border>
                            <el-descriptions-item label="房间编号">{{ room.roomNum }}</el-descriptions-item>
                            <el-descriptions-item label="房间户型">{{ room.type }}</el-descriptions-item>
                            <el-descriptions-item label="房间朝向">{{ room.direction }}</el-descriptions-item>
                            <el-descriptions-item label="房间图片">
                                <img :src="room.imgUrl" width="100" />
                            </el-descriptions-item>
                            <el-descriptions-item label="房间描述">{{ room.description }}</el-descriptions-item>
                            <el-descriptions-item label="房间状态">
                                <el-switch v-model="room.status" inline-prompt active-text="使用" inactive-text="弃用" />
                            </el-descriptions-item>
                            <el-descriptions-item label="操作">
                                <el-button type="primary">编辑</el-button>
                                <el-button type="danger">删除</el-button>
                                <el-button type="default">床位信息</el-button>
                            </el-descriptions-item>
                        </el-descriptions>
                    </div>
                </el-collapse-item>
            </el-collapse>
        </el-card>
    </div>
</template>

<script>
import * as buildRoomAPI from "@/api/build";
export default {
    data() {
        return {
            activeNames: "1",
            buildRoomArr: [],
        };
    },
    methods: {
        loadBuildRoomData() {
            let bid = this.$route.query.bid;
            buildRoomAPI.buildRoomAPI({ bid }).then((resp) => {
                if (resp.data.code == 2000) {
                    for (let i = 0; i < resp.data.data.length; i++) {
                        for (let j = 0; j < resp.data.data[i].buildingRoomVOList.length; j++) {
                            console.log(resp.data.data[i].buildingRoomVOList[j]);
                            resp.data.data[i].buildingRoomVOList[j] = {
                                ...resp.data.data[i].buildingRoomVOList[j],
                                status: resp.data.data[i].buildingRoomVOList[j].isDelete == "0" ? true : false,
                            };
                        }
                    }
                    this.buildRoomArr = resp.data.data;
                }
            });
        },
    },
    created() {
        this.loadBuildRoomData();
    },
};
</script>

<style lang="less" scoped>
.box {
    width: 100%;
    height: 100%;
    .el-card {
        margin-bottom: 20px;

        .room {
            display: flex;
            flex-wrap: wrap;
            .el-descriptions {
                width: 47%;
                margin-bottom: 20px;
                margin-right: 20px;
            }
        }
    }
}
</style>
