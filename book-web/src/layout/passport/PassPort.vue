<template>
  <div class="passport-content">
    <div class="passport-card">
      <div class="passport-title">
        <!-- <i nz-icon nzType="cloud" nzTheme="outline"></i> -->
        <i class="el-icon-cloudy"></i>
        学生管理系统
      </div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: 'passport'
}
</script>

<style scoped>
.passport-content {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-image: url('~assets/passport.png');
  background-repeat: no-repeat;
  background-color: white;
  /*background-size:100%;*/
}

.passport-card {
  width: 500px;
  margin: 10% 55%;
  padding: 35px 70px;
  box-shadow: 0px 2px 10px rgba(24, 144, 255, 0.6);
  border-radius: 5px;
  background-color: white;
}

.passport-title {
  text-align: center;
  color: #3e92f8;
  font-size: 27px;
  margin-bottom: 30px;
}
</style>