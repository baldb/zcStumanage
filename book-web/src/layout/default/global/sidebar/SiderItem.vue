<template>
  <div v-if="isShow">
    <el-menu-item
      v-if="!item.children"
      :index="item.path"
      class="is-active-menuitem"
    >
      <i v-show="item.meta.icon" :class="item.meta.icon"></i>

      <span slot="title">
        <el-badge class="badge-item" is-dot :hidden="!item.meta.showBadge">{{
          item.meta.title
        }}</el-badge></span
      >
    </el-menu-item>

    <el-submenu v-else :index="item.path">
      <template slot="title">
        <i v-show="item.meta.icon" :class="item.meta.icon"> </i>

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

  computed: {
    isShow() {
      return !this.item?.meta?.hide
    }
  },
  methods: {}
}
</script>

<style scoped>
.badge-item {
  display: inline;
  padding-right: 5px;
}
</style>