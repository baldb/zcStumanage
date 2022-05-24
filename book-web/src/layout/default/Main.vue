<template>

    <el-container class="layout_box">
  <el-aside width="300px">Aside</el-aside>
  <el-container>
    <el-header height="100px">Header</el-header>
    <el-main><router-view></router-view></el-main>
    <!-- <el-footer height="100px">Footer</el-footer> -->
  </el-container>
</el-container>

</template>

<script>
export default {
  mounted(){

  }
}
</script>

<style scoped>
.layout_box{
  min-width: 100vw;
  min-height: 100vh;
  overflow: hidden;
}
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  
  body > .el-container {
    margin-bottom: 40px;

  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>