<template>
  <div class="container">
    <div class="course-table__box">
      <el-button type="primary" @click="handleEdit(null, false)" class="add-btn"
        >添加课程</el-button
      >
      <my-table
        :tableData="tableData.records"
        :header="header"
        :total="tableData.total"
        :loading="tableLoading"
        :offset="offset"
        :page="page"
        width="700px"
        @pagination="pageChange"
      >
        <template #teachName="{ row }">
          {{ (row.teachMessage && row.teachMessage['teachName']) || '-' }}
        </template>
        <template #edit="{ edit }">
          <el-button
            type="primary"
            size="mini"
            @click="selectCourse(edit.courseId)"
            >查看</el-button
          >
          <el-button
            size="mini"
            @click="handleEdit(edit, true)"
            style="margin-right: 10px"
            >编辑</el-button
          >
          <el-popconfirm
            confirm-button-text="好的"
            cancel-button-text="不用了"
            icon="el-icon-info"
            icon-color="red"
            title="确定删除？"
            @confirm="handleDelete(edit)"
          >
            <el-button slot="reference" size="mini" type="danger"
              >删除</el-button
            >
          </el-popconfirm>
        </template>
      </my-table>
    </div>
    <div class="class-stu__box">
      <show-course-stu :courseId="selectId"></show-course-stu>
    </div>
    <edit-and-creaye-course
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
import editAndCreayeCourse from './editAndCreayeCourse.vue'
import { getCourse, deleteCourse } from '@/api'
import myTableMixin from '@/mixin/myTableMixin'
import showCourseStu from './showCourseStu.vue'

export default {
  name: 'coursemange',
  components: { MyTable, editAndCreayeCourse, showCourseStu },
  data() {
    return {
      header: column,
      tableData: {},
      visiableForm: false,
      isEdit: false,
      row: {}, // 保存选中行
      selectId: null
    }
  },
  mixins: [myTableMixin],
  mounted() {
    this.getcourseList()
  },
  methods: {
    async getcourseList() {
      try {
        this.tableLoading = true

        const { page: resultSet } = await getCourse({
          pn: this.page,
          offset: this.offset
        })

        console.log('resultSet: ', resultSet)
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
      this.getcourseList()
    },

    async handleDelete(row) {
      console.log(row)
      if (!row.courseId) return
      try {
        await deleteCourse({ courseId: row.courseId })
        this.$message.success('删除成功!!')
        // 获取页码
        const lat = this.tableData.records.length === 1 && this.page !== 1
        this.selectId === row.courseId && (this.selectId = null)
        // 如果是最后一条数据页码返回上一页
        lat && (this.page = this.page - 1)
        await this.getcourseList()
      } catch (error) {
        this.$message.error('删除失败！！')
      }
    },
    selectCourse(courseId) {
      this.selectId = courseId
    }
  },
  watch: {
    page() {
      // 重新回去数据
      this.getcourseList()
    }
  }
}
</script>

<style scoped>
.container {
  padding: 20px 20px 0;
  box-sizing: border-box;
  display: flex;
  height: 90%;
  align-items: center;
}
.course-table__box {
  margin: 0 auto;
  max-width: 45%;
  overflow: hidden;
}
.add-btn {
  margin-bottom: 10px;
}

.class-stu__box {
  flex: 1;
  height: 100%;
  box-sizing: border-box;
  box-shadow: 1px 1px 10px 4px #ccc inset;
  margin-left: 10px;
}
</style>