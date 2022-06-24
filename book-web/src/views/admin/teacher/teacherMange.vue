<template>
  <div class="container">
    <div class="Teacher-table__box">
      <el-button type="primary" @click="handleEdit(null, false)" class="add-btn"
        >添加老师</el-button
      >
      <my-table
        :tableData="tableData.records"
        :header="header"
        :total="tableData.total"
        :loading="tableLoading"
        :offset="offset"
        :page="page"
        width="850px"
        @pagination="pageChange"
        @handleEdit="(row) => handleEdit(row, true)"
        @handleDelete="handleDelete"
      >
        <template #teachPic="{ row }">
          <el-avatar size="medium" :src="row.teachPic | hostPath">
            <img src="~assets/pic.png"
          /></el-avatar>
        </template>

        <template #sex="{ row }">
          <el-tag :type="row.sex === '男' ? 'success' : 'danger'">
            {{ row.sex || '未知' }}</el-tag
          >
        </template>
      </my-table>
    </div>
    <edit-and-creaye-Teacher
      :visible.sync="visiableForm"
      @success="editSuccess"
      :is-edit="isEdit"
      :row="row"
    />
  </div>
</template>

<script>
import column from './column'
import MyTable from '@/components/Table'
import editAndCreayeTeacher from '../../../components/editAndCreayeTeacher.vue'
import { getTeacher, deleteTeacher } from '@/api'
import myTableMixin from '@/mixin/myTableMixin'

export default {
  name: 'Teachermange',
  components: { MyTable, editAndCreayeTeacher },
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
    this.getTeacherList()
  },
  methods: {
    async getTeacherList() {
      try {
        this.tableLoading = true
        const { resultSet } = await getTeacher({
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
      console.log('row: ', row)
      this.row = row
      this.visiableForm = true
      this.isEdit = isEdit
    },
    editSuccess() {
      // this.page = 1
      this.getTeacherList()
    },

    // 删除老师
    async handleDelete(row) {
      console.log(row)
      if (!row.teachNo) return
      try {
        await deleteTeacher({ teachNo: row.teachNo })
        this.$message.success('删除成功!!')
        // 获取页码
        const lat = this.tableData.records.length === 1 && this.page !== 1
        // 如果是最后一条数据页码返回上一页
        lat && (this.page = this.page - 1)
        await this.getTeacherList()
      } catch (error) {
        this.$message.error('删除失败！！')
      }
    }
  },
  watch: {
    page() {
      // 重新回去数据
      this.getTeacherList()
    }
  }
}
</script>

<style scoped>
.container {
  padding: 20px 0 0;
  box-sizing: border-box;
}
.Teacher-table__box {
  margin: 0 auto;
  max-width: 90%;
  overflow: hidden;
}
.add-btn {
  margin-bottom: 10px;
}
</style>