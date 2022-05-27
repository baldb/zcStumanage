<template>
  <el-container class="layout_box">
    <!-- 第一块 -->
    <el-header height="60px">Header</el-header>

    <el-container>
      <!-- 第二块 -->
      <el-aside width="200px">
        <global-side-bar :menu="getAccessRouter"
      /></el-aside>

      <!-- 第三块 -->
      <el-main><router-view></router-view></el-main>
      <!-- <el-footer height="100px">Footer</el-footer> -->
    </el-container>
  </el-container>
</template>

<script>
import GlobalSideBar from './global/sidebar/GlobalSideBar.vue'
import { mapGetters } from 'vuex'
export default {
  components: { GlobalSideBar },
  data() {
    return {
      isCollapse: false
    }
  },
  computed: {
    ...mapGetters(['getAccessRouter'])
  },
  methods: {}
}
</script>

<style scoped>
.layout_box {
  min-width: 100vw;
  min-height: 100vh;
  overflow: hidden;
}
.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
}
</style>