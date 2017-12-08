<template>
  <el-container>
    <el-header>
      <my-header></my-header>
    </el-header>
    <el-container>
      <el-aside>
        <asider></asider>
      </el-aside>
      <el-container>
        <el-main>
          <breadcrumb></breadcrumb>
          <router-view/>
        </el-main>
        <el-footer>
          <my-footer></my-footer>
        </el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
  import asider from '@/components/Asider'
  import myHeader from '@/components/Header'
  import myFooter from '@/components/Footer'
  import breadcrumb from '@/components/Breadcrumb'

  export default {
    name: 'app',
    components: {asider, myHeader, myFooter, breadcrumb},
    data() {
      return {}
    }
  }
</script>
