<template>
  <div class="container">
    <div class="student-table__box">
      <my-table
        :tableData="tableData.records"
        :header="header"
        :total="tableData.total"
        @pagination="pageChange"
        :loading="tableLoading"
        :offset="offset"
        :page="page"
        @handleEdit="handleEdit"
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
    />
  </div>
</template>

<script>
import MyTable from '@/components/Table'
import editAndCreayeStudent from './editAndCreayeStudent.vue'
import { getStudent } from '@/api'
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
          label: '姓名',
          width: 80
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
      page: 1
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
      return process.env.VUE_APP_BASURL + '/' + url
    },
    handleEdit(row) {
      console.log(row)
      this.visiableForm = true
    },
    addStuSuccess() {
      this.page = 1
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
}
</style>