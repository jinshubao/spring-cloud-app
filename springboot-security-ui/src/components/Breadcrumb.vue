<template>
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item v-for="item in Breadcrumb" :key="item.name" :to="{ path: item.path }">{{item.name}}
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
  export default {
    name: 'Breadcrumb',
    data() {
      return {
        Breadcrumb: []
      }

    },
    methods: {
      routeChange() {
        this.Breadcrumb = this.$route.matched
      }
    },

    watch: {
      '$route': 'routeChange'
    },
    mounted() {
      this.Breadcrumb = this.$route.matched
    }
  }
</script>
<style>
  .el-breadcrumb {
    margin-bottom: 10px;
  }
</style>
