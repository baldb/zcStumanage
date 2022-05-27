<template>
  <el-container class="layout_box">
    <!--头部 -->
    <el-header height="80px"
      ><global-header title="学生管理系统-304"></global-header
    ></el-header>
    <el-container class="contexnt-box">
      <!--侧边栏 -->
      <el-aside width="260px">
        <global-side-bar :menu="getAccessRouter"
      /></el-aside>
      <!-- 主体-->
      <el-main> <router-view></router-view></el-main>
      <!-- <el-footer height="100px">Footer</el-footer> -->
    </el-container>
  </el-container>
</template>

<script>
import GlobalSideBar from './global/sidebar/GlobalSideBar.vue'
import GlobalHeader from './global/header/GlobalHeader.vue'
import { mapGetters } from 'vuex'
export default {
  components: { GlobalSideBar, GlobalHeader },
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
  color: #333;
  text-align: center;
  padding-top: 40px;
}
.el-main div {
  background: white;
  height: 100%;
}
.el-header {
  background: url('~assets/8dbc96051e7bc028511bb1a5c48bf7d8.png') no-repeat
    center;
  background-size: cover;
}

.contexnt-box {
  padding: 0 10px 10px;
}
</style>