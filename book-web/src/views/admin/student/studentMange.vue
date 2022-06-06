<template>
  <div class="container">
    <div class="student-table__box">
      <el-button type="primary" @click="handleEdit(null, false)" class="add-btn"
        >添加学生</el-button
      >
      <my-table
        :tableData="tableData.records"
        :header="header"
        :total="tableData.total"
        :loading="tableLoading"
        :offset="offset"
        :page="page"
        @pagination="pageChange"
        @handleEdit="(row) => handleEdit(row, true)"
        @handleDelete="handleDelete"
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
    <edit-and-creaye-student
      :visible.sync="visiableForm"
      @success="page = 1"
      :is-edit="isEdit"
      :row="row"
    />
  </div>
</template>

<script>
import column from './column'
import MyTable from '@/components/Table'
import editAndCreayeStudent from './editAndCreayeStudent.vue'
import { getStudent, deleteStudent } from '@/api'
import myTableMixin from '@/mixin/myTableMixin'

export default {
  name: 'studentmange',
  components: { MyTable, editAndCreayeStudent },
  data() {
    return {
      header: column,
      tableData: {},
      visiableForm: false,
      isEdit: false,
      row: {} // 保存选中行
    }
  },
  mixins: [myTableMixin],
  mounted() {
    this.getStudentList()
  },
  methods: {
    async getStudentList() {
      try {
        this.tableLoading = true
        const { resultSet } = await getStudent({
          pn: this.page,
          offset: this.offset
        })
        this.tableData = resultSet
        this.tableLoading = false
      } catch (error) {
        this.tableLoading = false
      }
    },
    handleEdit(row, isEdit) {
      this.row = row
      this.visiableForm = true
      this.isEdit = isEdit
    },

    // 删除学生
    async handleDelete(row) {
      console.log(row)
      if (!row.stuNo) return
      try {
        await deleteStudent({ stuNo: row.stuNo })
        this.$message.success('删除成功!!')
        // 获取页码
        const lat = this.tableData.records.length === 1 && this.page !== 1
        // 如果是最后一条数据页码返回上一页
        lat && (this.page = this.page - 1)
        await this.getStudentList({ pn: this.page })
      } catch (error) {
        this.$message.error('删除失败！！')
      }
    }
  },
  watch: {
    page() {
      // 重新回去数据
      console.log(1)
      this.getStudentList()
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