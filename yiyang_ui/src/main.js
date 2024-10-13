import "./assets/main.css";

import "@/assets/iconfonts/iconfont.css";

import { createApp } from "vue";
import { createPinia } from "pinia";
import ElementPlus from "element-plus";
import zhCn from "element-plus/es/locale/lang/zh-cn";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
import App from "./App.vue";
import router from "./router";

const pinia = createPinia();
const app = createApp(App);
app.use(pinia);
app.use(router);
app.use(ElementPlus, { locale: zhCn, size: "default", zIndex: 3000 });
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}

app.mount("#app");
