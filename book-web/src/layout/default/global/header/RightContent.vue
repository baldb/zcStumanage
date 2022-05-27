<template>
  <div class="reight">
    <el-dropdown>
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
        <el-dropdown-item>个人信息</el-dropdown-item>
        <el-dropdown-item>退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: 'rightContent'
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