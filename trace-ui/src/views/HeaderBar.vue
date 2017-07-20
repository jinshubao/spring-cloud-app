<template>
    <el-row>
        <el-menu theme="dark" :default-active="activeIndex" mode="horizontal" router>
            <el-menu-item :index="item.path" v-for="(item,index) in $router.options.routes" :key="item.path"  v-show="!item.hidden">
                <i :class="item.iconCls"></i>{{item.name}}
            </el-menu-item>
        </el-menu>
    </el-row>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {ElRow},
        data() {
            return {
                activeIndex: '0'
            }
        },
        methods: {

        },
        mounted() {
        }
    }
</script>
