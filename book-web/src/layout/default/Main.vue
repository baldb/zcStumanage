<template>
  <el-container class="layout_box">
    <!--头部 -->
    <el-header height="80px"
      ><global-header title="学生管理系统-304"></global-header
    ></el-header>
    <el-container class="contexnt-box">
      <!--侧边栏 -->
      <el-aside width="230px">
        <global-side-bar :menu="getAccessRouter"
      /></el-aside>
      <!-- 主体-->
      <el-main
        ><header-crumbs tags=""></header-crumbs>
        <div class="layout-view">
          <!-- 缓存组件 -->
          <Transition name="slide-fade" mode="out-in">
            <keep-alive :include="getTagList.map((i) => i.name)">
              <router-view></router-view
            ></keep-alive>
          </Transition></div
      ></el-main>
      <!-- <el-footer height="100px">Footer</el-footer> -->
    </el-container>
  </el-container>
</template>

<script>
import GlobalSideBar from './global/sidebar/GlobalSideBar.vue'
import GlobalHeader from './global/header/GlobalHeader.vue'
import HeaderCrumbs from './global/header/HeaderCrumbs.vue'
import { mapGetters } from 'vuex'
export default {
  components: { GlobalSideBar, GlobalHeader, HeaderCrumbs },
  data() {
    return {
      isCollapse: false
    }
  },
  computed: {
    ...mapGetters(['getAccessRouter', 'getTagList', 'getActiveMenu'])
  },
  methods: {},
  watch: {
    $route: {
      handler(newRoute) {
        this.$store.commit('menu/SET_AVTIVE_MENU', newRoute)
      },
      immediate: true
    }
  }
}
</script>

<style scoped>
.layout_box {
  min-width: 100vw;
  min-height: 100vh;
  overflow: hidden;
  box-sizing: border-box;
}
.el-main {
  color: #333;
  box-sizing: border-box;
}
.el-main .layout-view {
  background: white;

  height: calc(100% - 40px);
}
.el-header {
  background: url('~assets/8dbc96051e7bc028511bb1a5c48bf7d8.png') no-repeat
    center;
  background-size: cover;
}

.contexnt-box {
  padding: 0 10px 10px;
  box-sizing: border-box;
}

.slide-fade-enter-active {
  transition: all 0.3s ease-out;
}
.slide-fade-leave-active {
  transition: all 0.3s ease-in-out;
}
.slide-fade-enter,
.slide-fade-leave-to {
  transform: translateX(10px);
  opacity: 0;
}
</style>