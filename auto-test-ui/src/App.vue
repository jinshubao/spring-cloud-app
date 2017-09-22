<template>
    <div id="app">
        <menu-bar></menu-bar>
        <transition name="fade" mode="out-in">
            <router-view></router-view>
        </transition>
        <!--<sider-bar></sider-bar>-->
        <!--<footer-bar></footer-bar>-->
    </div>
</template>

<script>
    import MenuBar from './views/MenuBar.vue'
    import FooterBar from './views/FooterBar.vue'

    export default {
        components: {
            FooterBar, MenuBar
        }
    }
</script>