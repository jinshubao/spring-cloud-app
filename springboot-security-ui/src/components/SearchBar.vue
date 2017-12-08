<template>
  <el-row class="toolbar">
    <el-col :span="16">
      <slot></slot>
    </el-col>
    <el-col :span="8">
      <el-input v-model="keyword" placeholder="关键字">
        <el-button slot="append" @click="searchByKeyword(keyword)" icon="el-icon-search"></el-button>
      </el-input>
    </el-col>
  </el-row>
</template>
<script>
  export default {
    data() {
      return {
        select: '',
        keyword: ''
      }
    },
    methods: {
      searchByKeyword(keyword) {
        this.$emit('search', keyword)
      }
    },
    mounted() {
    }
  }
</script>
<style scoped>
  .toolbar {
    padding-top: 10px;
    padding-bottom: 10px;
  }
</style>
