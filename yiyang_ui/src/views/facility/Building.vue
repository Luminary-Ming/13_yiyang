<template>
    <div class="box">
        <div class="page-top">
            <el-button class="add-btn" @click="addBuild" type="primary">添加</el-button>
            <el-pagination
                v-model:current-page="currentPage"
                v-model:page-size="pageSize"
                :page-sizes="[5, 10, 20, 30, 50]"
                layout="total, prev, pager, next"
                :total="total"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
            />
        </div>
        <div class="build">
            <div class="build-item" v-for="item in buildArr">
                <div class="build-title">
                    <h3>{{ item.name }}</h3>
                </div>
                <div class="build-content">
                    <span class="iconfont icon-louyuguanli"></span>
                    <div class="build-info">
                        <p>房间数：{{ item.roomCount }}间</p>
                        <p>层高：{{ item.height }}层</p>
                        <p>颜色：{{ item.color }}</p>
                        <p>录入时间：{{ dataFormat(item.createTime) }}</p>
                    </div>
                </div>
                <div>
                    <el-button type="primary" @click="updateBuild(item)">修改</el-button>
                    <el-button type="danger" @click="deleteBuild(item)">删除</el-button>
                    <el-button type="success" @click="bindRoom(item)">绑定房间</el-button>
                    <el-button type="default" @click="goBuildRoom(item)">查询房间信息</el-button>
                </div>
            </div>
        </div>
        <el-pagination
            v-model:current-page="currentPage"
            v-model:page-size="pageSize"
            :page-sizes="[5, 10, 20, 30, 50]"
            layout="total, sizes, prev, pager, next"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
        />
        <el-dialog v-model="dialogFormVisible" :title="title" width="500">
            <el-form :model="buildForm" label-width="80px">
                <el-form-item label="楼宇名称">
                    <el-input v-model="buildForm.name" />
                </el-form-item>
                <el-form-item label="楼宇层数">
                    <el-input v-model="buildForm.height" />
                </el-form-item>
                <el-form-item label="楼宇颜色">
                    <el-input v-model="buildForm.color" />
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
        <!-- 绑定房间信息的弹窗 -->
        <el-drawer v-model="buildRoomDialog" title="绑定房间信息" direction="rtl" class="demo-drawer">
            <div class="demo-drawer__content">
                <el-form :model="buildRoomForm">
                    <el-form-item label="房间">
                        <el-select v-model="buildRoomForm.rid">
                            <el-option v-for="item in roomArr" :label="item.num" :value="item.id"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="户型">
                        <el-input v-model="buildRoomForm.type" />
                    </el-form-item>
                    <el-form-item label="朝向">
                        <el-input v-model="buildRoomForm.direction" />
                    </el-form-item>
                    <el-form-item label="图片">
                        <el-upload class="avatar-uploader" :action="UPLOAD_URL" :show-file-list="false" :on-success="handleAvatarSuccess">
                            <img v-if="buildRoomForm.imgUrl" :src="buildRoomForm.imgUrl" class="avatar" />
                            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="描述">
                        <el-input v-model="buildRoomForm.description" />
                    </el-form-item>
                </el-form>
                <div class="demo-drawer__footer">
                    <el-button @click="buildRoomDialog = false">取消</el-button>
                    <el-button type="primary" @click="onClick"> 确定 </el-button>
                </div>
            </div>
        </el-drawer>
    </div>
</template>

<script setup>
import * as buildAPI from "@/api/building";
import { findRoomsAPI } from "@/api/room";
import { reactive, ref } from "vue";
import { UPLOAD_URL } from "@/api/url";
// setup语法中，在使用路由对象时，必须先导入对应的路由的函数
import { useRouter, useRoute } from "vue-router";
const $router = useRouter();

import dayjs from "dayjs";
// 直接定义的变量失去的响应式，必须使用setup语法中提供的对应的函数来封装
//  ref 和 reative 函数
//  ref 函数用于让基本类型具备响应式
//  reative 函数用于让引用类型具备响应式
let buildArr = reactive([]);
// 分页数据
let currentPage = ref(1);
let pageSize = ref(5);
let total = ref(0);
let dialogFormVisible = ref(false);
let buildForm = reactive({
    name: "",
    height: "",
    color: "",
});
let title = ref("添加楼宇");

function handleSizeChange() {
    loadBuildData();
}
function handleCurrentChange() {
    loadBuildData();
}
// 加载楼宇数据
function loadBuildData() {
    buildAPI.findAllBuildingAPI().then((resp) => {
        if (resp.data.code == 2000) {
            //buildArr.push(...resp.data.data.data);
            buildArr.splice(0, buildArr.length, ...resp.data.data.data);
            total.value = resp.data.data.total;
        }
    });
}
loadBuildData();

// 点击添加按钮
const addBuild = () => {
    dialogFormVisible.value = true;
    title.value = "添加楼宇";
    buildForm.name = "";
    buildForm.height = "";
    buildForm.color = "";
};
// 点击修改按钮
const updateBuild = (build) => {
    dialogFormVisible.value = true;
    title.value = "修改楼宇";
    // 应给将传递进来的对象进行克隆
    buildForm = { ...build };
};

// 点击弹窗提交数据
const submitData = () => {
    if (title.value == "添加楼宇") {
        buildAPI.addBuildingAPI(buildForm).then((resp) => {
            if (resp.data.code == 2000) {
                dialogFormVisible.value = false;
                loadBuildData();
            }
        });
    } else {
        buildAPI.updateBuildingAPI(buildForm).then((resp) => {
            if (resp.data.code == 2000) {
                dialogFormVisible.value = false;
                loadBuildData();
            }
        });
    }
};

// 点击删除按钮
const deleteBuild = (build) => {
    buildAPI.deleteBuildingAPI(build.id).then((resp) => {
        if (resp.data.code == 2000) {
            loadBuildData();
        }
    });
};

// 绑定房间相关的参数逻辑代码区域
let buildRoomDialog = ref(false);
let buildRoomForm = reactive({
    bid: "",
    rid: "",
    type: "",
    direction: "",
    imgUrl: "",
    description: "",
});
let roomArr = reactive([]);
//加载房间数据
function loadRoomData(bid) {
    findRoomsAPI({ bid }).then((resp) => {
        if (resp.data.code == 2000) {
            roomArr.splice(0, roomArr.length, ...resp.data.data);
        }
    });
}

// 图片上传
function handleAvatarSuccess(resp) {
    buildRoomForm.imgUrl = resp.data;
}

// 绑定房间与楼宇的关系
function bindRoom(item) {
    loadRoomData(item.id);
    buildRoomDialog.value = true;
    // 绑定房间所属的楼宇的id
    buildRoomForm.rid = "";
    buildRoomForm.type = "";
    buildRoomForm.direction = "";
    buildRoomForm.imgUrl = "";
    buildRoomForm.description = "";
    buildRoomForm.bid = item.id;
}
// 提交绑定的房间数据信息
const onClick = () => {
    buildAPI.addBindingRoomAPI(buildRoomForm).then((resp) => {
        if (resp.data.code == 2000) {
            buildRoomDialog.value = false;
            loadBuildData();
        }
    });
};

// 点击查询楼宇与房间按钮
function goBuildRoom(item) {
    $router.push({
        path: "/buildRoom",
        query: {
            bid: item.id,
        },
    });
}
// 时间格式化函数
const dataFormat = (value) => {
    return dayjs(value).format("YYYY-MM-DD HH:mm:ss");
};
</script>

<style lang="less" scoped>
/* 
      css的预处理语言：less、sass、stylus，除可以正常书写css之外，还能在其中定义简单的变量，判断，循环等
      css的预处理语言允许css的样式进行嵌套书写
  */
@bColor: #0af;
.box {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    .page-top {
        display: flex;
        .add-btn {
            margin-right: 20px;
        }
        margin-bottom: 20px;
    }

    .build {
        width: 100%;
        height: 100%;
        display: flex;
        flex-wrap: wrap;
        justify-content: start;
        align-content: start;
        .build-item {
            background-color: #fff;
            width: 30%;
            margin-bottom: 20px;
            margin-right: 20px;
            padding: 20px;
            text-align: center;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            .build-title {
                font-size: 30px;
                color: deeppink;
            }
            .build-content {
                display: flex;
                .iconfont {
                    font-size: 200px;
                    color: @bColor;
                }
                p {
                    margin-top: 20px;
                    text-align: left;
                }
            }
            &:hover {
                cursor: pointer;
                background-color: aliceblue;
            }
        }
    }
    :deep(.avatar-uploader .el-upload) {
        border: 1px dashed var(--el-border-color);
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        transition: var(--el-transition-duration-fast);
    }

    :deep(.avatar-uploader .el-upload:hover) {
        border-color: var(--el-color-primary);
    }

    :deep(.el-icon.avatar-uploader-icon) {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        text-align: center;
    }
}
</style>
