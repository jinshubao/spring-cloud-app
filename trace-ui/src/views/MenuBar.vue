<template>
    <el-row class="menubar">
        <el-col :span="4">
            <el-menu :default-active="activeIndex" router>
                <el-menu-item :index="item.path" v-for="(item,index) in $router.options.routes" :key="item.path" v-show="!item.hidden">
                    <i :class="item.iconCls"></i>{{item.name}}
                </el-menu-item>
            </el-menu>
        </el-col>
        <el-col :span="20" class="content">
            <slot name="content"></slot>
        </el-col>
    </el-row>

</template>
<script>
    export default {
        data() {
            return {
                activeIndex: '0'
            }
        },
        methods: {},
        mounted() {

        }
    }
</script>

<style scoped>
    .content{
        // padding-left: 10px;
    }
    .menubar{
        height: 100%;
    }

</style>