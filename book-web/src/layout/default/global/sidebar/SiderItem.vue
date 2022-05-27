<template>
  <div v-if="isShow">
    <el-menu-item
      v-if="!item.children"
      :index="item.path"
      class="is-active-menuitem"
    >
      <i v-show="item.meta.icon" :class="item.meta.icon"></i>

      <span slot="title">{{ item.meta.title }}</span>
    </el-menu-item>

    <el-submenu v-else :index="item.path">
      <template slot="title">
        <i v-show="item.meta.icon" :class="item.meta.icon"></i>
        <span slot="title">{{ item.meta.title }}</span>
      </template>

      <sider-item v-for="(child, i) in item.children" :item="child" :key="i" />
    </el-submenu>
  </div>
</template>

<script>
export default {
  name: 'SiderItem',
  props: {
    item: {
      type: Object,
      default: () => ({})
    }
  },
  mounted() {},
  computed: {
    isShow() {
      return !this.item?.meta?.hide
    }
  }
}
</script>

<style scoped>
</style>