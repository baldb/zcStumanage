<template>
  <div class="container">
    <div class="Class-table__box">
      <el-button type="primary" @click="handleEdit(null, false)" class="add-btn"
        >添加班级</el-button
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
          <el-button type="primary" size="mini">查看</el-button>
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
            title="确实删除？"
            @confirm="handleDelete(edit)"
          >
            <el-button slot="reference" size="mini" type="danger"
              >删除</el-button
            >
          </el-popconfirm>
        </template>
      </my-table>
    </div>
    <edit-and-creaye-class
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
import editAndCreayeClass from './editAndCreayeClass.vue'
import { getClass, deleteClass } from '@/api'
import myTableMixin from '@/mixin/myTableMixin'

export default {
  name: 'Classmange',
  components: { MyTable, editAndCreayeClass },
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
    this.getClassList()
  },
  methods: {
    async getClassList() {
      try {
        this.tableLoading = true

        const { page: resultSet } = await getClass({
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
      this.getClassList()
    },

    async handleDelete(row) {
      console.log(row)
      if (!row.classId) return
      try {
        await deleteClass({ classId: row.classId })
        this.$message.success('删除成功!!')
        // 获取页码
        const lat = this.tableData.records.length === 1 && this.page !== 1
        // 如果是最后一条数据页码返回上一页
        lat && (this.page = this.page - 1)
        await this.getClassList()
      } catch (error) {
        this.$message.error('删除失败！！')
      }
    }
  },
  watch: {
    page() {
      // 重新回去数据
      this.getClassList()
    }
  }
}
</script>

<style scoped>
.container {
  padding: 20px 0 0;
  box-sizing: border-box;
}
.Class-table__box {
  margin: 0 auto;
  max-width: 90%;
  overflow: hidden;
}
.add-btn {
  margin-bottom: 10px;
}
</style>