import Vue from "vue";
import App from "./App.vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-default/index.css";
import VueRouter from "vue-router";
import store from "./vuex/store";
import Vuex from "vuex";
import routes from "./routes";
import Mock from "./mock";
Mock.bootstrap();

Vue.use(ElementUI);
Vue.use(VueRouter);
Vue.use(Vuex);


const router = new VueRouter({
    routes
});

router.beforeEach((to, from, next) => {
    if (to.path == '/login') {
        sessionStorage.removeItem('user');
    }
    let user = JSON.parse(sessionStorage.getItem('user'));
    if (!user && to.path != '/login') {
        next({path: '/login'})
    } else {
        next()
    }
});

let vm = new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');

let notify = function (message, type, duration) {
    vm.$notify({
        title: "DEV LOG",
        message: message,
        type: type,
        duration: duration || 4500
    });
};

console.log = function () {
    notify(JSON.stringify(arguments), "success")
};

console.info = function () {
    vm.$notify.info({
        title: 'DEV LOG',
        message: JSON.stringify(arguments)
    });
};

console.warn = function () {
    notify(JSON.stringify(arguments), "warning", 0)
};

console.error = function () {
    this.$notify.error({
        title: 'DEV LOG',
        message: JSON.stringify(arguments),
        duration: 0
    });
};





