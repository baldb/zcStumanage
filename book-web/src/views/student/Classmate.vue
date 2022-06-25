<template>
  <div class="container">
    <div class="student-table__box">
      <my-table
        :tableData="tableData"
        :header="header"
        :loading="tableLoading"
        :offset="1000"
        :page="1"
        :isEdit="false"
        @pagination="pageChange"
      >
        <template #pic="{ row }">
          <el-avatar size="medium" :src="row.pic | hostPath">
            <img src="~assets/pic.png"
          /></el-avatar>
        </template>

        <!-- 姓名插槽 -->
        <template #stuName="{ row }">
          <el-popover trigger="hover" placement="top">
            <p>姓名: {{ row.stuName }}</p>
            <p>住址: {{ row.address }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag type="warning"> {{ row.stuName }}</el-tag>
            </div>
          </el-popover>
        </template>

        <template #Birth="{ row }">
          {{ row.Birth | transTimeAge }}
        </template>

        <template #sex="{ row }">
          <el-tag :type="row.sex === '男' ? 'success' : 'danger'">
            {{ row.sex || '未知' }}</el-tag
          >
        </template>
      </my-table>
    </div>
  </div>
</template>

<script>
import column from './column'
import MyTable from '@/components/Table'

import { getSameClass } from '@/api'
import myTableMixin from '@/mixin/myTableMixin'
import { mapGetters } from 'vuex'
export default {
  name: 'classmate',
  components: { MyTable },
  data() {
    return {
      header: column,
      tableData: [],
      visiableForm: false,
      isEdit: false,
      row: {} // 保存选中行
    }
  },
  mixins: [myTableMixin],
  mounted() {
    this.getStudentList()
  },
  computed: {
    ...mapGetters(['username'])
  },
  methods: {
    async getStudentList() {
      const { resultSet } = await getSameClass({
        stuId: this.username
      })

      this.tableData = resultSet
    }
  }
}
</script>

<style scoped>
.container {
  padding: 20px 0 0;
  box-sizing: border-box;
}
.student-table__box {
  margin: 0 auto;
  max-width: 90%;
  overflow: hidden;
}
.add-btn {
  margin-bottom: 10px;
}
</style>