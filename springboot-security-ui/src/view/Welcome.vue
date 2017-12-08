<template>
  <el-carousel height="750px">
    <el-carousel-item v-for="item in images" :key="item" :name="item">
      <img :src="item">
      <h3>{{ item }}</h3>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
  export default {
    name: 'Welcome',
    data () {
      return {
        images: [
          'http://i1.bvimg.com/618389/5f24405cd50d289a.jpg',
          'http://i1.bvimg.com/618389/fad5c78e321eab64.jpg',
          'http://i1.bvimg.com/618389/5aa084f0cc8a39d1.jpg',
          'http://i1.bvimg.com/618389/26eed42f621ccb89.jpg',
          'http://i1.bvimg.com/618389/4b814430e898189a.jpg',
          'http://i1.bvimg.com/618389/65200c0e9089cc11.jpg'
        ]
      }
    }
  }
</script>
