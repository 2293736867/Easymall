import ElementPlus from 'element-plus'
import 'element-plus/lib/theme-chalk/index.css'
import {createApp} from 'vue'
import App from './App.vue'
import router from "./js/router/Router";
import './index.css'
import store from "./store/Store";

createApp(App).use(ElementPlus).use(router).use(store).mount('#app')