<template>
    <div class="box">
        <span @click="changeCollapse">
            <el-icon v-if="collapse"><span class="iconfont icon-zhankai"></span></el-icon>
            <el-icon v-else="!collapse"><span class="iconfont icon-shousuo"></span></el-icon>
        </span>
        <div>
            <h2>康养中心管理系统</h2>
        </div>
        <el-dropdown split-button type="primary" @click="handleClick">
            欢迎：admin
            <template #dropdown>
                <el-dropdown-menu>
                    <el-dropdown-item>Action 1</el-dropdown-item>
                    <el-dropdown-item>Action 2</el-dropdown-item>
                    <el-dropdown-item>Action 3</el-dropdown-item>
                    <el-dropdown-item>Action 4</el-dropdown-item>
                    <el-dropdown-item>Action 5</el-dropdown-item>
                </el-dropdown-menu>
            </template>
        </el-dropdown>
    </div>
</template>

<script setup>
import { computed } from "vue";
import { useCollapseStore } from "@/stores/collapse";
const collapseStore = useCollapseStore();
let collapse = computed(() => collapseStore.isCollapse);
const changeCollapse = () => {
    collapseStore.changeCollapse();
};
const handleClick = () => {};
</script>

<style lang="less" scoped>
.box {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 100%;
    color: #fff;
    .iconfont {
        font-size: 26px;
        font-weight: bold;
        cursor: pointer;
    }
    h2 {
        letter-spacing: 0.5em;
    }
}
</style>
