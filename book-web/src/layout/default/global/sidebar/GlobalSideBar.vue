<template>
  <div class="aside-box">
    <el-menu
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      default-active="2"
      class="el-menu-vertical-demo"
      :collapse="isCollapse"
      :collapse-transition="false"
      router
    >
      <sider-item v-for="(item, index) in menu" :key="index" :item="item" />
    </el-menu>

    <div class="menu-fot" @click="hideMenu">
      <i class="el-icon-s-fold"></i>
    </div>
  </div>
</template>

<script>
import SiderItem from './SiderItem'
export default {
  name: 'sirdebar',
  props: {
    isCollapse: { tyee: Boolean, default: false },
    menu: { type: Array, default: () => [] }
  },
  components: {
    SiderItem
  },
  mounted() {
    console.log(this.menu)
  },

  methods: {
    hideMenu() {
      this.$emit('hideMenu', !this.isCollapse)
    }
  }
}
</script>

<style scoped>
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
.aside-box {
  height: 100%;
  text-align: center;
  display: flex;
  flex-direction: column;
}
</style>