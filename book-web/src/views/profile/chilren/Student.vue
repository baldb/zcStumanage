<template>
  <el-descriptions class="margin-top" title="用户信息" :column="2" border>
    <template slot="extra">
      <!-- <el-button type="primary" size="small">编辑</el-button> -->
    </template>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        学号
      </template>
      {{ getuserinfo.stuNo }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        姓名
      </template>
      {{ getuserinfo.stuName }}
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
        出生日期
      </template>
      {{ getuserinfo.Birth }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        邮箱
      </template>
      {{ getuserinfo.email }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        入学日期
      </template>
      {{ getuserinfo.enroTime }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        手机号
      </template>
      {{ getuserinfo.phone }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        家庭地址
      </template>
      {{ getuserinfo.address }}
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