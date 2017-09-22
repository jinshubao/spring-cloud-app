import Login from "./../views/Login.vue";
import Error from "../views/error/error.vue";
import Error403 from "../views/error/403.vue";
import Error404 from "../views/error/404.vue";
import Error500 from "../views/error/500.vue";

import ApiList from "../views/api/ApiList.vue";
import TestCaseList from "../views/api/TestCaseList.vue"
import echarts from "../views/charts/echarts.vue"

let routes = [
    {
        path: '/api',
        component: ApiList,
        name: '接口列表'
    },
    {
        path: '/test',
        component: TestCaseList,
        name: '测试用例'
    },
    {
        path: '/report',
        component: echarts,
        name: '测试报告'
    },
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/error',
        name: '错误页面',
        component: Error,
        hidden: true,
        children: [
            {path: '403', name: '无权限', component: Error403},
            {path: '404', name: '页面找不到', component: Error404},
            {path: '500', name: '系统错误', component: Error500}
        ]
    },
    {
        path: '*',
        hidden: true,
        // redirect: {path: '/error/404'}
    }
];

export default routes;