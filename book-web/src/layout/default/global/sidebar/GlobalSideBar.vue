<template>
  <div class="aside-box">
    <div class="user-info">
      <el-avatar
        icon="el-icon-user-solid"
        :src="getUrl"
        :size="100"
      ></el-avatar>
      <div>
        {{ getusername }}
        <el-tag :type="istype" hit style="vertical-align: middle">{{
          indentity | tansiIndentity
        }}</el-tag>
      </div>
    </div>
    <div class="menu-container">
      <el-menu
        router
        background-color="#4d58b5"
        text-color="#fff"
        active-text-color="#0397fe"
        :default-active="getActiveMenu"
        class="el-menu-vertical-demo"
        :collapse-transition="false"
      >
        <sider-item v-for="(item, index) in menu" :key="index" :item="item" />
      </el-menu>
    </div>
  </div>
</template>

<script>
import SiderItem from './SiderItem'
import { mapGetters } from 'vuex'
import { IDENT_ENUM } from '@/constant/auth'
export default {
  name: 'sirdebar',
  data() {
    return {
      hostPath: process.env.VUE_APP_BASURL + '/images/'
    }
  },
  props: {
    isCollapse: { tyee: Boolean, default: false },
    menu: { type: Array, default: () => [] }
  },
  components: {
    SiderItem
  },
  computed: {
    ...mapGetters(['getActiveMenu', 'username', 'indentity', 'getuserinfo']),
    istype() {
      // 获取状态
      for (const k in IDENT_ENUM) {
        if (IDENT_ENUM[k].value === this.indentity) {
          return IDENT_ENUM[k].status
        }
      }
      return ''
    },
    getusername() {
      if (this?.indentity === 'admin') return this.username
      const isTeachr = this.indentity === 'teacher' ? 'teachName' : 'stuName'
      return this.getuserinfo[isTeachr]
    },
    getUrl() {
      if (this?.indentity === 'admin')
        return 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
      const isTeachr = this.indentity === 'teacher' ? 'teachPic' : 'pic'
      return this.hostPath + this.getuserinfo[isTeachr]
    }
  },
  mounted() {},
  filters: {
    tansiIndentity(value) {
      const t = {
        admin: '管理员',
        teacher: '教师',
        student: '学生'
      }

      return t[value]
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
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
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
  margin-right: 10px;
  padding-top: 10px;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}
.user-info {
  flex: 0 1 160px;
  background-color: #4d58b5;
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
  border-bottom: 2px solid white;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  padding: 10px 0;
  box-sizing: border-box;
  font-size: 20px;
  font-weight: 700;
  color: white;
}

.menu-container {
  flex: 1;
}
</style>