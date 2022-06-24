<template>
  <div class="b">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>已选课({{ getTotal }})</span>
        <el-button
          v-if="getTotal < 5"
          style="float: right; padding: 0.0375rem 0"
          type="text"
          @click="addCourse"
          >添加选课</el-button
        >
      </div>
      <div v-for="l in courses" :key="l.courseId" class="text item">
        <div>{{ l.courseName }}({{ l.teacher.teachName }})</div>
        <div>{{ l.tsource.score || '未知' }}</div>
      </div>
    </el-card>

    <el-drawer
      :title="`选课列表(可选${5 - getTotal})`"
      :visible.sync="drawer"
      direction="rtl"
      :before-close="handleClose"
      destroy-on-close
    >
      <el-checkbox-group
        class="checkBox"
        v-model="courseList"
        :max="5 - getTotal"
      >
        <el-checkbox v-for="e in expCour" :key="e.courseId" :label="e.courseId"
          >{{ e.courseName }}({{ e.teacher.teachName }})</el-checkbox
        >
      </el-checkbox-group>
      <div class="check-footer">
        <el-button @click="submit">确认</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { getCourseNo, addStuCourse } from '@/api'
import { mapGetters } from 'vuex'

export default {
  props: { courses: Array },
  data() {
    return {
      drawer: false,
      expCour: [],
      courseList: []
    }
  },
  computed: {
    ...mapGetters(['username']),
    getTotal() {
      return this.courses?.length || 0
    }
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then((_) => {
          done()
        })
        .catch((_) => {})
    },
    addCourse() {
      this.drawer = true
      getCourseNo({ stuId: this.username }).then(({ resultSet }) => {
        this.expCour = resultSet
      })
    },
    submit() {
      addStuCourse({ stuId: this.username, courseList: this.courseList })
        .then((res) => {
          this.$message({
            message: '选课成功!!',
            type: 'success'
          })
          this.$emit('success')
        })
        .finally(() => {
          this.drawer = false
        })
    }
  }
}
</script>

<style scoped>
.b {
  flex-basis: 400px;
}
.text {
  font-size: 16px;
}

.item {
  margin-bottom: 17px;
  display: flex;
  justify-content: space-between;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}
.clearfix:after {
  clear: both;
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