<template>
  <div class="crumbs-content">
    <el-tag closable :disable-transitions="false"> 首页 </el-tag>
    <router-link to="/home" custom v-slot="{ isActive }">
      <el-tag closable :disable-transitions="false">
        {{ isActive }}
      </el-tag>
    </router-link>

    <el-tag closable :disable-transitions="false"> 活动列表 </el-tag>

    <el-tag closable :disable-transitions="false"> 活动详情 </el-tag>
  </div>
</template>

<script>
export default {
  name: 'HeaderCrumbs'
}
</script>

<style scoped>
.crumbs-content {
  width: 100%;
  height: 40px;
  background: yellow;
}
</style>