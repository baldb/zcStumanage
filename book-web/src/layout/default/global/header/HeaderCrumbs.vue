<template>
  <div class="crumbs-content">
    <router-link
      v-for="item in getTagList"
      :key="item.path"
      :to="item.path"
      custom
      v-slot="{ isActive }"
    >
      <el-tag
        class="tag-item"
        :closable="getTagList.length > 1"
        :disable-transitions="false"
        @click="herdercrumbsClick(item.path)"
        :effect="isActive ? 'dark' : 'plain'"
        @close="closeTag(item.path)"
      >
        {{ item.meta.title }}
      </el-tag>
    </router-link>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'HeaderCrumbs',
  computed: {
    ...mapGetters(['getTagList', 'getActiveMenu'])
  },
  methods: {
    herdercrumbsClick(item) {
      this.$router.push({ path: item })
    },
    closeTag(path) {
      this.$store.commit('menu/REMOVE_TAG', path)
      if (this.$route.fullPath !== this.getActiveMenu) {
        // 如果删除了激活的选项就跳转到新路由
        this.$router.push({ path: this.getActiveMenu })
      }
    }
  }
}
</script>

<style scoped>
.crumbs-content {
  width: 100%;
  height: 40px;
  background: rgb(240, 240, 123);
  display: flex;
  align-items: center;
}

.crumbs-content .el-tag {
  margin-left: 5px;
  cursor: pointer;
}
</style>