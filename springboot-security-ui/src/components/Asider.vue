<template>
  <el-menu
    default-active="0"
    class="el-menu-vertical-demo"
    :collapse="isCollapse" router>
    <el-menu-item index="/">
      <i class="el-icon-star-on"></i>
      <span slot="title">首页</span>
    </el-menu-item>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-setting"></i>
        <span>系统权限管理</span>
      </template>
      <el-menu-item index="/users">
        <i class="el-icon-setting"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/roles">
        <i class="el-icon-setting"></i>
        <span slot="title">角色管理</span>
      </el-menu-item>
      <el-menu-item index="/resources">
        <i class="el-icon-setting"></i>
        <span slot="title">资源和权限管理</span>
      </el-menu-item>
    </el-submenu>
    <el-menu-item index="5">
      <i class="el-icon-menu"></i>
      <span slot="title">导航二</span>
    </el-menu-item>
    <el-menu-item index="6">
      <i class="el-icon-setting"></i>
      <span slot="title">导航三</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
  export default {
    name: 'HelloWorld',
    data() {
      return {
        isCollapse: false
      }
    }
  }
</script>
