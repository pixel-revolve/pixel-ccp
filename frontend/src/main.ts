import { createApp,toRaw } from 'vue'
import './index.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import * as VueRouter from 'vue-router'
import AntDesign from 'ant-design-vue'
import routes from "./router/routes";
import 'ant-design-vue/dist/antd.less';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import {createPinia,PiniaPluginContext, PiniaPlugin} from "pinia";

const app = createApp(App);


const router = VueRouter.createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: VueRouter.createWebHashHistory(),
    routes, // `routes: routes` 的缩写
})
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

app.use(pinia)
app.use(AntDesign)
app.use(router)
app.use(ElementPlus)
app.mount('#app')
