<template>
  <div>
    <div v-if="!!courseId && tableData.length !== 0" class="table-Box">
      <my-table
        :tableData="tableData"
        :header="header"
        :total="tableData.length"
        :loading="tableLoading"
        :offset="1000"
        :page="1"
        :height="550"
        editText="成绩"
      >
        <template #pic="{ row }">
          <el-avatar size="medium" :src="row.pic | hostPath">
            <img src="~assets/pic.png"
          /></el-avatar>
        </template>

        <template #sex="{ row }">
          <el-tag :type="row.sex === '男' ? 'success' : 'danger'">
            {{ row.sex || '未知' }}</el-tag
          >
        </template>
        <template #edit="{ edit }">
          <el-popover
            placement="top"
            width="200"
            trigger="click"
            @hide="editScourse(edit)"
            @show="score = edit.sourceS.score || ''"
          >
            <el-input
              v-model="score"
              placeholder="请输入内容"
              type="number"
            ></el-input>
            <span slot="reference">
              {{ edit.sourceS.score || '无' }} <i class="el-icon-edit"></i
            ></span>
          </el-popover>
        </template>
      </my-table>
    </div>

    <div v-else><el-empty description="此专业没有学生！！！"></el-empty></div>
  </div>
</template>

<script>
import MyTable from '@/components/Table'
import { classStuColumn } from './column'
import myTableMixin from '@/mixin/myTableMixin'
import { getCourseStu, updateScore } from '@/api'
export default {
  data() {
    return {
      header: classStuColumn,
      tableData: [],
      row: {},
      score: ''
    }
  },
  mixins: [myTableMixin],
  components: {
    MyTable
  },
  props: {
    courseId: {
      type: [Number, Boolean],
      default: null
    }
  },
  methods: {
    getCourseStu() {
      this.tableLoading = true
      getCourseStu({ courseId: this.courseId }).then(({ resultSet }) => {
        this.tableData = resultSet
        this.tableLoading = false
      })
    },
    editScourse(edit) {
      if (this.score) {
        console.log(edit)
        updateScore({
          courseId: this.courseId,
          stuId: edit.stuNo,
          score: this.score
        }).then(() => {
          this.getCourseStu()
        })
      }
      // updateScore
      console.log('我隐藏了！！')
    }
  },
  watch: {
    courseId(nval) {
      if (!nval) {
        this.tableData = []
        return
      }
      console.log('nval: ', nval)
      this.getCourseStu()
    }
  }
}
</script>

<style scoped>
.table-Box {
  width: 95%;
  margin: 10px auto;
}
.checkBox {
  display: flex;
  flex-direction: column;
}
.checkBox >>> .el-checkbox {
  margin: 7px;
}
.check-footer {
  position: absolute;
  bottom: 0;
}
</style>