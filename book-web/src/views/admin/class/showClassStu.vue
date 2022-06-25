<template>
  <div>
    <el-button v-if="!!classId" type="primary" @click="addClassStu"
      >添加学生</el-button
    >
    <div v-if="!!classId && tableData.length !== 0" class="table-Box">
      <my-table
        :tableData="tableData"
        :header="header"
        :total="tableData.length"
        :loading="tableLoading"
        :offset="1000"
        :page="1"
        :height="550"
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

    <div v-else><el-empty description="此班级没有学生！！！"></el-empty></div>
    <el-drawer
      title="添加学生"
      :visible.sync="drawer"
      direction="rtl"
      :before-close="handleClose"
      destroy-on-close
    >
      <el-checkbox-group class="checkBox" v-model="checkStu">
        <el-checkbox v-for="e in studenList" :key="e.stuNo" :label="e.stuNo"
          >{{ e.stuName }}({{ e.stuNo }})</el-checkbox
        >
      </el-checkbox-group>
      <div class="check-footer">
        <el-button @click="submit">确认</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import MyTable from '@/components/Table'
import { classStuColumn } from './column'
import myTableMixin from '@/mixin/myTableMixin'
import { getcalssstu, delcalssstu, addcalssstu, noclass } from '@/api'
export default {
  data() {
    return {
      header: classStuColumn,
      tableData: [],
      visiableForm: false,
      isEdit: false,
      row: {},
      drawer: false,
      studenList: [],
      checkStu: []
    }
  },
  mixins: [myTableMixin],
  components: {
    MyTable
  },
  props: {
    classId: {
      type: [Number, Boolean],
      default: null
    }
  },
  methods: {
    async handleDelete({ stuNo }) {
      await delcalssstu({ stuNo })
      this.getCalssStu()
    },
    getCalssStu() {
      this.tableLoading = true
      getcalssstu({ classId: this.classId }).then(({ resultSet }) => {
        this.tableData = resultSet
        this.tableLoading = false
      })
    },
    addClassStu() {
      this.drawer = true
      noclass().then(({ resultSet }) => {
        console.log('resultSet: ', resultSet)
        this.studenList = resultSet
      })
    },
    submit() {
      addcalssstu({ classId: this.classId, stuNoList: this.checkStu })
        .then((res) => {
          this.$message({
            message: '添加成功!!',
            type: 'success'
          })
          this.checkStu = []
          this.getCalssStu()
        })
        .finally(() => {
          this.drawer = false
        })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then((_) => {
          done()
          this.checkStu = []
        })
        .catch((_) => {})
    }
  },
  watch: {
    classId(nval) {
      if (!nval) {
        this.tableData = []
        return
      }
      this.getCalssStu()
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