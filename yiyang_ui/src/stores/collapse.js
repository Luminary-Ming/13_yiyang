import { ref } from "vue";
import { defineStore } from "pinia";

// 全局的菜单折叠状态
export const useCollapseStore = defineStore("collapse", () => {
    const isCollapse = ref(true);
    function changeCollapse() {
        isCollapse.value = !isCollapse.value;
    }

    return { isCollapse, changeCollapse };
});
