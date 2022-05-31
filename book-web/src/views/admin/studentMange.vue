<template>
  <div class="container">
    <div class="student-table__box">
      <my-table
        :tableData="tableData.records"
        :header="header"
        :total="tableData.total"
        @pagination="pageChange"
        :loading="tableLoading"
      >
        <template #pic="{ row }">
          <el-avatar
            size="medium"
            :src="'http://192.168.137.116:8088/' + row.pic"
          >
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
            {{ row.sex }}</el-tag
          >
        </template>
      </my-table>
    </div>
  </div>
</template>

<script>
import MyTable from '@/components/Table'
import { getStudent } from '@/api'
import moment from 'moment'

export default {
  name: 'studentmange',
  components: { MyTable },
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
          width: 70
        },
        {
          prop: 'sex',
          label: '性别',
          width: 60
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
          label: '年龄'
        },
        {
          prop: 'enroTime',
          label: '入学时间'
        }
      ],
      tableData: [],
      tableLoading: false
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
      this.getStudentList({ pn: page, offset })
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
  display: inline-block;
  max-width: 90%;
}
</style>