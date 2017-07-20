<template>
    <div id="app">
        <header-bar></header-bar>
        <el-row>
            <transition name="fade" mode="out-in">
                <router-view></router-view>
            </transition>
        </el-row>
        <!--<footer-bar></footer-bar>-->
    </div>
</template>

<script>
    import HeaderBar from './views/HeaderBar.vue'
    import FooterBar from './views/FooterBar.vue'
    export default {
        components: {
            HeaderBar, FooterBar
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