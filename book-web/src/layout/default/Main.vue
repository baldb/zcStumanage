<template>
  <el-container class="layout_box">
    <!-- 第一块 -->
    <el-header height="100px">Header</el-header>

    <el-container>
      <!-- 第二块 -->
      <el-aside :width="isCollapse ? '65px' : '200px'">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          :collapse="isCollapse"
          :collapse-transition="false"
        >
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">导航二</span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-document"></i>
            <span slot="title">导航三</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">导航四</span>
          </el-menu-item>
        </el-menu>

        <div class="menu-fot" @click="hideMenu">
          <i class="el-icon-s-fold"></i>
        </div>
      </el-aside>

      <!-- 第三块 -->
      <el-main><router-view></router-view></el-main>
      <!-- <el-footer height="100px">Footer</el-footer> -->
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      isCollapse: false,
    };
  },
  mounted() {},

  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    hideMenu() {
      this.isCollapse = !this.isCollapse;
    },
  },
};
</script>

<style scoped>
.layout_box {
  min-width: 100vw;
  min-height: 100vh;
  overflow: hidden;
}

.el-aside {
  text-align: center;
  display: flex;
  flex-direction: column;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
}

/* .el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 300px;
  min-height: 100vh;
} */

.el-menu-vertical-demo {
  height: 100%;
  padding-left: 0;
  text-align: left;
  flex: 1;
}
.menu-fot {
  border-top: 1px solid #333;
  line-height: 39px;
  cursor: pointer;
  height: 40px;
}
</style>