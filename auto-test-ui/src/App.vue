<template>
    <div id="app">
        <el-row>
            <el-menu theme="dark" mode="horizontal" router>
                <el-menu-item :index="item.path" v-for="(item,index) in $router.options.routes" :key="item.path"
                              v-if="!item.hidden">
                    <i :class="item.iconCls"></i>{{item.name}}
                </el-menu-item>
            </el-menu>
        </el-row>
        <el-row  slot="content">
            <transition name="fade" mode="out-in">
                <router-view></router-view>
            </transition>
        </el-row>
        <!--<footer-bar></footer-bar>-->
    </div>
</template>

<script>
    import FooterBar from './views/FooterBar.vue'

    export default {
        components: {
            FooterBar
        },
        data() {
            return {}
        },
        methods: {},
        mounted() {
            console.info('App mounted');
        }
    }
</script>