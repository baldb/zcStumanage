<template>
  <div>
    <el-dialog
      :title="(isEdit ? '修改' : '添加') + '学生信息'"
      :visible.sync="dialogFormVisible"
      destroy-on-close
    >
      <el-form :model="form" label-width="100px" :rules="rules" ref="ruleForm">
        <el-form-item label="学号" prop="stuNo">
          <el-input
            v-model.number="form.stuNo"
            autocomplete="off"
            :disabled="this.isEdit"
          ></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="stuName">
          <el-input v-model="form.stuName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出生日期" prop="Birth">
          <el-date-picker
            type="date"
            placeholder="选择日期"
            v-model="form.Birth"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model.number="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮件" prop="email">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="头像" prop="pic">
          <upload
            @success="uploadSuccess"
            @remove="uploadRemove"
            :picUrl="form.pic"
            :baseurl="hostPath"
          ></upload>
        </el-form-item>
        <el-form-item label="入学日期" prop="enroTime">
          <el-date-picker
            type="date"
            placeholder="选择日期"
            v-model="form.enroTime"
            style="width: 100%"
          ></el-date-picker>
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
import Upload from './Upload'
import { addStudent, editStudent } from '@/api'

export default {
  name: 'editAndCreayeStudent',
  data() {
    return {
      dialogFormVisible: this.visible,
      form: {
        stuNo: null,
        stuName: '',
        sex: '',
        phone: '',
        email: '',
        address: '',
        Birth: '',
        pic: '',
        enroTime: ''
      },
      formLabelWidth: '120px',
      rules: {
        stuNo: [
          { required: true, message: '请输入学号', trigger: 'blur' },
          { type: 'number', message: '学号必须是数字' }
        ],
        stuName: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        sex: [{ required: true, message: '请选择性别', trigger: 'click' }],
        phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
        email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }],
        address: [{ required: true, message: '请输入地址', trigger: 'blur' }],
        enroTime: [
          { required: true, message: '请输入学日期', trigger: 'blur' }
        ],
        Birth: [{ required: true, message: '请选择出生日期', trigger: 'blur' }]
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
        this.form.pic = ''
      } else {
        Object.assign(
          this.form,
          this.row || {
            stuNo: null,
            stuName: '',
            sex: '',
            phone: '',
            email: '',
            address: '',
            Birth: '',
            pic: '',
            enroTime: ''
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
            const ismethod = this.isEdit ? editStudent : addStudent
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
      this.form.pic = response.resultSet
    },
    uploadRemove() {
      this.form.pic = undefined
    }
  }
}
</script>

<style scoped>
</style>