<template>
  <div class="reight">
    <el-dropdown @command="handleCommand">
      <span class="el-dropdown-link">
        <span class="avatar-tip">
          <el-avatar
            icon="el-icon-user-solid"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          ></el-avatar
        ></span>
        蔡夏柠 <i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item :command="1">个人信息</el-dropdown-item>
        <el-dropdown-item :command="2">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: 'rightContent',
  methods: {
    handleCommand(command) {
      switch (command) {
        case 1:
          this.$router.push({ name: 'profile', replace: true })
          break

        case 2:
          this.$router.push({ path: '/', replace: true })
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