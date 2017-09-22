<template>
    <el-row>
        <el-menu theme="dark" mode="horizontal" router>
            <el-menu-item v-for="item in $router.options.routes"
                          :default-active="$route.path"
                          :index="item.path"
                          :key="item.path"
                          v-if="!item.hidden">
                <i :class="item.iconCls"></i>{{item.name}}
            </el-menu-item>
        </el-menu>
    </el-row>
</template>

<script>
    export default {
        data() {
            return {
            }
        },
        methods: {},
        mounted() {

        }
    }
</script>
