<template>
    <el-menu class="el-menu-vertical-demo" active-text-color="#ffd04b" background-color="#545c64" :default-active="currentPath" text-color="#fff" router :collapse="collapse">
        <el-menu-item index="/">
            <el-icon><HomeFilled /></el-icon>
            <span>系统首页</span>
        </el-menu-item>
        <el-sub-menu index="1">
            <template #title>
                <el-icon><OfficeBuilding /></el-icon>
                <span>设施管理</span>
            </template>
            <el-menu-item index="/building">
                <template #default>
                    <el-icon><School /></el-icon>
                    <span>楼宇管理</span>
                </template>
            </el-menu-item>
            <el-menu-item index="/room">
                <template #default>
                    <el-icon><House /></el-icon>
                    <span>房间管理</span>
                </template>
            </el-menu-item>
            <el-menu-item index="/bed">
                <template #default>
                    <el-icon><MessageBox /></el-icon>
                    <span>床位管理</span>
                </template>
            </el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="2">
            <template #title>
                <el-icon><Bowl /></el-icon>
                <span>膳食管理</span>
            </template>
            <el-menu-item index="">
                <template #default>
                    <el-icon><EditPen /></el-icon>
                    <span>供应商管理</span>
                </template>
            </el-menu-item>
            <el-menu-item index="">
                <template #default>
                    <el-icon><Files /></el-icon>
                    <span>采购管理</span>
                </template>
            </el-menu-item>
            <el-menu-item index="">
                <template #default>
                    <el-icon><Bowl /></el-icon>
                    <span>食材管理</span>
                </template>
            </el-menu-item>
            <el-menu-item index="">
                <template #default>
                    <el-icon><Printer /></el-icon>
                    <span>菜谱管理</span>
                </template>
            </el-menu-item>
            <el-menu-item index="">
                <template #default>
                    <el-icon><GoldMedal /></el-icon>
                    <span>膳食计划</span>
                </template>
            </el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="3">
            <template #title>
                <el-icon><UserFilled /></el-icon>
                <span>客户管理</span>
            </template>
            <el-menu-item index="/">
                <template #title>
                    <el-icon><CirclePlus /></el-icon>
                    <span>入住登记</span>
                </template>
            </el-menu-item>
            <el-menu-item index="/">
                <template #title>
                    <el-icon><User /></el-icon>
                    <span>客户信息</span>
                </template>
            </el-menu-item>
            <el-menu-item index="/">
                <template #title>
                    <el-icon><AddLocation /></el-icon>
                    <span>外出登记</span>
                </template>
            </el-menu-item>
            <el-menu-item index="/">
                <template #title>
                    <el-icon><ForkSpoon /></el-icon>
                    <span>用餐记录</span>
                </template>
            </el-menu-item>
            <el-menu-item index="/">
                <template #title>
                    <el-icon><Clock /></el-icon>
                    <span>来访登记</span>
                </template>
            </el-menu-item>
            <el-menu-item index="/">
                <template #title>
                    <el-icon><Edit /></el-icon>
                    <span>来访记录</span>
                </template></el-menu-item
            >
        </el-sub-menu>
        <el-sub-menu index="4">
            <template #title>
                <el-icon><Menu /></el-icon>
                <span>护理管理</span>
            </template>
            <el-menu-item index="/">
                <template #default>
                    <el-icon><GoldMedal /></el-icon>
                    <span>护理记录</span>
                </template>
            </el-menu-item>
            <el-menu-item index="/">
                <template #default>
                    <el-icon><FolderChecked /></el-icon>
                    <span>护理培训</span>
                </template>
            </el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="5">
            <template #title>
                <el-icon><FirstAidKit /></el-icon>
                <span>体检管理</span>
            </template>
            <el-menu-item index="/">
                <template #default>
                    <el-icon><QuartzWatch /></el-icon>
                    <span>体健预约</span>
                </template>
            </el-menu-item>
            <el-menu-item index="/">
                <template #default>
                    <el-icon><Magnet /></el-icon>
                    <span>体健查询</span>
                </template>
            </el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="6">
            <template #title>
                <el-icon><Tools /></el-icon>
                <span>系统管理</span>
            </template>
            <el-menu-item index="">
                <template #default>
                    <el-icon><UserFilled /></el-icon>
                    <span>员工管理</span>
                </template>
            </el-menu-item>
            <el-menu-item index="">
                <template #default>
                    <el-icon><Magnet /></el-icon>
                    <span>员工培训</span>
                </template>
            </el-menu-item>
            <el-menu-item index="">
                <template #default>
                    <el-icon><Calendar /></el-icon>
                    <span>考勤管理</span>
                </template>
            </el-menu-item>
            <el-menu-item index="">
                <template #default>
                    <el-icon><SwitchFilled /></el-icon>
                    <span>系统菜单</span>
                </template>
            </el-menu-item>
            <el-menu-item index="">
                <template #default>
                    <el-icon><Wallet /></el-icon>
                    <span>权限管理</span>
                </template>
            </el-menu-item>
        </el-sub-menu>
    </el-menu>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useRoute } from "vue-router";
import { useCollapseStore } from "@/stores/collapse";
const collapseStore = useCollapseStore();
let collapse = computed(() => collapseStore.isCollapse);

let currentPath = ref("/");
// 页面加载完成时获取路径地址
onMounted(() => {
    currentPath.value = useRoute().path;
});
</script>

<style scoped>
.el-menu {
    border-right: none;
}
:deep(.el-menu-vertical-demo:not(.el-menu--collapse)) {
    width: 200px;
}
</style>
