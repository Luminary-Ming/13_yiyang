<template>
    <!-- 整个项目的全局的布局页面 -->
    <div class="common-layout">
        <el-container>
            <el-aside width="200px">
                <Menu></Menu>
            </el-aside>
            <el-container>
                <el-header>
                    <Top></Top>
                </el-header>
                <el-main>
                    <RouterView></RouterView>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import Menu from "@/views/layout/components/Menu.vue";
import Top from "@/views/layout/components/Top.vue";
import { RouterView } from "vue-router";
</script>

<style scoped lang="less">
.common-layout {
    width: 100%;
    height: 100%;
    .el-container {
        width: 100%;
        height: 100%;
        .el-aside {
            background-color: #545c64;
            height: 100%;
        }
        .el-header {
            background-color: #409eff;
        }
        .el-main {
            background-color: #e9eef3;
        }
    }
}
</style>
