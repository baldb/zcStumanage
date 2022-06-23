<template>
  <div>
    <el-dialog
      :title="(isEdit ? '修改' : '添加') + '班级信息'"
      :visible.sync="dialogFormVisible"
      destroy-on-close
    >
      <el-form :model="form" label-width="100px" :rules="rules" ref="ruleForm">
        <el-form-item label="课程编号" prop="courseId">
          <el-input
            v-model.number="form.courseId"
            autocomplete="off"
            :disabled="this.isEdit"
          ></el-input>
        </el-form-item>
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="form.courseName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="任课老师" prop="techNo">
          <el-select v-model="form.techNo" placeholder="选择任课老师">
            <el-option
              v-for="item in teacherList"
              :key="item.teachNo"
              :label="item.teachName"
              :value="item.teachNo"
              filterable
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <div slot="footer" course="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Upload from '@/components/Upload'
import { addCourse, editCourse, getTeacher } from '@/api'

export default {
  name: 'editAndCreayeStudent',
  data() {
    return {
      dialogFormVisible: this.visible,
      form: {
        courseId: null,
        courseName: '',
        techNo: ''
      },
      formLabelWidth: '120px',
      rules: {
        courseId: [
          { required: true, message: '请输入班级编号', trigger: 'blur' },
          { type: 'number', message: '班级编号必须是数字' }
        ],
        courseName: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        techNo: [{ required: true, message: '请选择老师', trigger: 'click' }]
      },
      teacherList: []
    }
  },
  components: { Upload },
  props: {
    visible: { type: Boolean, default: false },
    closeDestory: { type: Boolean, default: true },
    isEdit: { type: Boolean, default: false },
    row: { type: Object }
  },
  mounted() {},
  watch: {
    dialogFormVisible(isv, old) {
      if (isv !== old) {
        this.$emit('update:visible', isv)
      }

      if (!isv) {
        this.$refs['ruleForm'].resetFields()
        this.teacherList = []
      } else {
        Object.assign(
          this.form,

          this.row || {
            courseId: null,
            courseName: '',
            techNo: ''
          }
        )

        this.getTeacher()
      }
    },
    visible(isv) {
      this.dialogFormVisible = isv
    }
  },

  methods: {
    submitForm() {
      this.$refs['ruleForm'].validate(async (valid) => {
        if (valid) {
          try {
            const ismethod = this.isEdit ? editCourse : addCourse
            await ismethod(this.form)
            this.$message.success((this.isEdit ? '修改' : '添加') + '成功!!')
            this.$emit('success')
            this.dialogFormVisible = false
          } catch (error) {
            this.$message.error((this.isEdit ? '修改' : '添加') + '失败')
            this.dialogFormVisible = false
          }
        }
      })
    },
    async getTeacher() {
      const { resultSet } = await getTeacher({
        pn: 1,
        offset: 1000
      })
      this.teacherList = resultSet.records
    }
  }
}
</script>

<style scoped>
</style>