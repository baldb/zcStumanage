<template>
  <!-- 缓存组件 -->
  <keep-alive :include="getTagList.map((i) => i.name)">
    <router-view></router-view
  ></keep-alive>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'infomain',
  computed: {
    ...mapGetters(['getTagList'])
  }
}
</script>

<style scoped>
</style>