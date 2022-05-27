<template>
  <div class="aside-box">
    <el-menu
      background-color="#4d58b5"
      text-color="#fff"
      active-text-color="#0397fe"
      default-active="/passed/home"
      class="el-menu-vertical-demo"
      :collapse-transition="false"
      router
    >
      <sider-item v-for="(item, index) in menu" :key="index" :item="item" />
    </el-menu>
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

  methods: {}
}
</script>

<style scoped>
.el-menu-vertical-demo {
  height: 100%;
  padding-left: 0;
  text-align: left;
  flex: 1;
  border-radius: 20px;
  overflow: hidden;
}

.menu-fot {
  border-top: 1px solid #333;
  line-height: 39px;
  cursor: pointer;
  height: 40px;
}
.aside-box {
  height: 100%;
  box-sizing: border-box;
  padding: 10px;
}
</style>