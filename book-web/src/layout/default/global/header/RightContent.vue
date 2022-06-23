<template>
  <div class="reight">
    <el-dropdown @command="handleCommand">
      <span class="el-dropdown-link">
        <span class="avatar-tip">
          <el-avatar icon="el-icon-user-solid" :src="getUrl"></el-avatar
        ></span>
        {{ getusername }} <i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item
          :command="1"
          v-if="['teacher', 'student'].includes(indentity)"
          >个人信息</el-dropdown-item
        >
        <el-dropdown-item :command="2">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'rightContent',
  data() {
    return {
      hostPath: process.env.VUE_APP_BASURL + '/images/'
    }
  },
  computed: {
    ...mapGetters(['username', 'indentity', 'getuserinfo']),
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
  methods: {
    handleCommand(command) {
      switch (command) {
        case 1:
          this.$router.push({ name: 'profile', replace: true })
          break

        case 2:
          this.$store.dispatch('user/logout').then(() => {
            this.$router.go(0)
          })
          break
      }
    }
  }
}
</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: white;
  font-size: 17px;
  font-weight: 700;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.demonstration {
  display: block;
  color: #8492a6;
  font-size: 14px;
  margin-bottom: 20px;
}
.avatar-tip {
  vertical-align: middle;
}
</style>