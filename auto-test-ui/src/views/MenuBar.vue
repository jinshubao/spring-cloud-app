<template>
    <section>
        <el-row>
            <el-menu theme="dark" :default-active="activeIndex" mode="horizontal" router>
                <el-menu-item :index="item.path" v-for="(item,index) in $router.options.routes" :key="item.path"
                              v-if="!item.hidden">
                    <i :class="item.iconCls"></i>{{item.name}}
                </el-menu-item>
            </el-menu>
        </el-row>
        <el-row>
            <slot name="content"></slot>
        </el-row>
    </section>
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
