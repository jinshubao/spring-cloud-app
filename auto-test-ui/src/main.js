import Vue from "vue";
import Vuex from "vuex";
import App from "./App.vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-default/index.css";
import VueRouter from "vue-router";
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import routes from "./router/routes";
import store from "./vuex/store";

Vue.use(ElementUI);
Vue.use(Vuex);
Vue.use(VueRouter);

const router = new VueRouter({routes});

router.beforeEach((to, from, next) => {
    console.info(to.path);
    if (to.path === '/login') {
        sessionStorage.removeItem('user');
    }
    let user = JSON.parse(sessionStorage.getItem('user'));
    if (!user && to.path !== '/login') {
        next({path: '/login'})
    } else {
        NProgress.start();
        next()
    }
});

router.afterEach(transition => {
    NProgress.done()
});

let vm = new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');





