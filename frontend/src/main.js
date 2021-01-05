import ElementPlus from 'element-plus'
import 'element-plus/lib/theme-chalk/index.css'
import {createApp} from 'vue'
import App from './App.vue'
import router from "./js/router/router";
import './index.css'
import store from "./store/store";

createApp(App).use(ElementPlus).use(router).use(store).mount('#app')