import Login from "./views/Login.vue";
import NotFound from "./views/404.vue";

let routes = [
    {
        path: '/trace',
        component: Trace,
        name: 'Trace'
    },
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    {
        path: '*',
        hidden: true,
        redirect: {path: '/404'}
    }
];

export default routes;