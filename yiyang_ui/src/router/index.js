import { createRouter, createWebHashHistory } from "vue-router";

const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "layout",
            component: () => import("@/views/layout/Layout.vue"),
            redirect: "/home",
            children: [
                {
                    path: "/home",
                    name: "home",
                    component: () => import("@/views/layout/Home.vue"),
                },
                // 设施管理菜单路由
                // 楼宇管理
                {
                    path: "/building",
                    name: "building",
                    component: () => import("@/views/facility/Building.vue"),
                },
                // 房间管理
                {
                    path: "/room",
                    name: "floor",
                    component: () => import("@/views/facility/Room.vue"),
                },
                // 床位管理
                {
                    path: "/bed",
                    name: "bed",
                    component: () => import("@/views/facility/Bed.vue"),
                },
            ],
        },
    ],
});

export default router;
