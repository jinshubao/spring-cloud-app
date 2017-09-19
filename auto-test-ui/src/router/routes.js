import Login from "./../views/Login.vue";
import Error from "../views/error/error.vue";
import Error403 from "../views/error/403.vue";
import Error404 from "../views/error/404.vue";
import Error500 from "../views/error/500.vue";

import Project from "../views/api/Project.vue";
import Module from "../views/api/Module.vue";
import ApiModule from "../views/api/ApiModule.vue";
import TestModule from "../views/test/TestModule.vue";

let routes = [
    {
        path: '/api',
        component: ApiModule,
        name: '接口管理',
        children: [
            {
                path: 'project',
                component: Project,
                name: '项目'
            },
            {
                path: 'module',
                component: Module,
                name: '模块'
            }
        ]
    },
    {
        path: '/test',
        component: TestModule,
        name: '测试管理',
        children: [
            {
                path: 'case',
                component: Module,
                name: '测试用例'
            },
            {
                path: 'unit',
                component: Module,
                name: '单元测试'
            }
        ]
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