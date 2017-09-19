<template>
    <el-row class="menubar">
        <el-col :span="4">
            <el-menu :default-active="activeIndex" router  :collapse="isCollapse">
                <el-menu-item :index="$router.options.routes[0].path + '/'+item.path" v-for="(item,index) in $router.options.routes[0].children" :key="item.path" v-show="!item.hidden">
                    <i :class="item.iconCls"></i>{{item.name}}
                </el-menu-item>
            </el-menu>
        </el-col>
        <el-col :span="20">
            <transition name="fade" mode="out-in">
                <router-view></router-view>
            </transition>
        </el-col>
    </el-row>
</template>
<script>
    export default {
        data() {
            return {
                activeIndex: '0',
                isCollapse :false
            }
        },
        methods: {},
        mounted() {

        }
    }
</script>