<template>
  <el-descriptions class="margin-top" title="用户信息" :column="2" border>
    <template slot="extra">
      <!-- <el-button type="primary" size="small">编辑</el-button> -->
    </template>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        教师编号
      </template>
      {{ getuserinfo.teachNo }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        姓名
      </template>
      {{ getuserinfo.teachName }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        性别
      </template>
      {{ getuserinfo.sex }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        年龄
      </template>
      {{ getuserinfo.age }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        备注
      </template>
      <el-tag :type="indentity | istype" hit style="vertical-align: middle">{{
        indentity | tansiIndentity
      }}</el-tag>
    </el-descriptions-item>
  </el-descriptions>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  computed: {
    ...mapGetters(['indentity', 'getuserinfo'])
  }
}
</script>

<style scoped>
</style>