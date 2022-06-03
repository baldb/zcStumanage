<template>
  <div class="container">
    <div class="student-table__box">
      <el-button
        type="primary"
        @click="
          visiableForm = true
          isEdit = false
          row = null
        "
        class="add-btn"
        >添加学生</el-button
      >
      <my-table
        :tableData="tableData.records"
        :header="header"
        :total="tableData.total"
        @pagination="pageChange"
        :loading="tableLoading"
        :offset="offset"
        :page="page"
        @handleEdit="handleEdit"
        @handleDelete="handleDelete"
      >
        <template #pic="{ row }">
          <el-avatar size="medium" :src="setImagUrl(row.pic)">
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
          {{ transTime(row.Birth) }}
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
      @success="addStuSuccess"
      :is-edit="isEdit"
      :row="row"
    />
  </div>
</template>

<script>
import MyTable from '@/components/Table'
import editAndCreayeStudent from './editAndCreayeStudent.vue'
import { getStudent, deleteStudent } from '@/api'
import moment from 'moment'

export default {
  name: 'studentmange',
  components: { MyTable, editAndCreayeStudent },
  data() {
    return {
      header: [
        {
          prop: 'pic',
          label: '头像',
          fixed: true,
          width: 60
        },
        {
          prop: 'stuNo',
          label: '学号',
          fixed: true
        },
        {
          prop: 'stuName',
          label: '姓名'
        },
        {
          prop: 'sex',
          label: '性别',
          width: 80
        },
        {
          prop: 'phone',
          label: '手机号'
        },
        {
          prop: 'email',
          label: '邮箱'
        },
        {
          prop: 'address',
          label: '地址'
        },
        {
          prop: 'Birth',
          label: '年龄',
          width: 50
        },
        {
          prop: 'enroTime',
          label: '入学时间'
        }
      ],
      tableData: {},
      tableLoading: false,
      visiableForm: false,
      offset: 9,
      page: 1,
      isEdit: false,
      row: {} // 保存选中行
    }
  },
  mounted() {
    this.getStudentList()
  },
  methods: {
    async getStudentList(info = {}) {
      try {
        this.tableLoading = true
        const { resultSet } = await getStudent(info)
        console.log('resultSet: ', resultSet)
        this.tableData = resultSet
        this.tableLoading = false
      } catch (error) {
        this.tableLoading = false
      }
    },
    transTime(time) {
      return moment().diff(moment(time), 'year')
    },
    pageChange({ page, offset }) {
      this.page = page
      this.getStudentList({ pn: page, offset })
    },
    setImagUrl(url) {
      return process.env.VUE_APP_BASURL + '/images/' + url
    },
    handleEdit(row) {
      this.row = row
      this.visiableForm = true
      this.isEdit = true
    },
    // 添加或编辑成功的回调
    addStuSuccess() {
      this.page = 1
      this.getStudentList()
    },
    async handleDelete(row) {
      console.log(row)
      if (!row.stuNo) return
      try {
        await deleteStudent({ stuNo: row.stuNo })
        this.$message.success('删除成功!!')
        const lat = this.tableData.records.length === 1 && this.page !== 1

        // 如果是最后一条数据页码返回上一页
        lat && (this.page = this.page - 1)
        await this.getStudentList({ pn: this.page })
      } catch (error) {
        this.$message.error('删除失败！！')
      }
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
}
.add-btn {
  margin-bottom: 10px;
}
</style>