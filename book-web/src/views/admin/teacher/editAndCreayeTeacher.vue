<template>
  <div>
    <el-dialog
      :title="(isEdit ? '修改' : '添加') + '老师信息'"
      :visible.sync="dialogFormVisible"
      destroy-on-close
    >
      <el-form :model="form" label-width="100px" :rules="rules" ref="ruleForm">
        <el-form-item label="教师号" prop="teachNo">
          <el-input
            v-model.number="form.teachNo"
            autocomplete="off"
            :disabled="this.isEdit"
          ></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="teachName">
          <el-input v-model="form.teachName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model.number="form.age" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="头像" prop="teachPic">
          <upload
            @success="uploadSuccess"
            @remove="uploadRemove"
            :picUrl="form.teachPic"
            :baseurl="hostPath"
          ></upload>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Upload from '@/components/Upload'
import { addTeacher, editTeacher } from '@/api'

export default {
  name: 'editAndCreayeStudent',
  data() {
    return {
      dialogFormVisible: this.visible,
      form: {
        teachNo: null,
        teachName: '',
        sex: '',
        age: '',
        teachPic: ''
      },
      formLabelWidth: '120px',
      rules: {
        stuNo: [
          { required: true, message: '请输入教室编号', trigger: 'blur' },
          { type: 'number', message: '教室编号必须是数字' }
        ],
        stuName: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        sex: [{ required: true, message: '请选择性别', trigger: 'click' }],
        age: [{ required: true, message: '请输入年龄', trigger: 'click' }]
      },
      hostPath: process.env.VUE_APP_BASURL + '/images/'
    }
  },
  components: { Upload },
  props: {
    visible: { type: Boolean, default: false },
    closeDestory: { type: Boolean, default: true },
    isEdit: { type: Boolean, default: false },
    row: { type: Object }
  },
  watch: {
    dialogFormVisible(isv, old) {
      if (isv !== old) {
        this.$emit('update:visible', isv)
      }

      if (!isv) {
        this.$refs['ruleForm'].resetFields()
        this.form.teachPic = ''
      } else {
        Object.assign(
          this.form,
          this.row || {
            teachNo: null,
            teachName: '',
            sex: '',
            age: '',
            teachPic: ''
          }
        )
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
            const ismethod = this.isEdit ? editTeacher : addTeacher
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
    uploadSuccess(res) {
      const { response } = res
      this.form.teachPic = response.resultSet
    },
    uploadRemove() {
      this.form.teachPic = undefined
    }
  }
}
</script>

<style scoped>
</style>