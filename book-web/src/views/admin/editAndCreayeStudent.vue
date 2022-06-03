<template>
  <div>
    <el-dialog
      title="添加学生信息"
      :visible.sync="dialogFormVisible"
      destroy-on-close
    >
      <el-form :model="form" label-width="100px" :rules="rules" ref="ruleForm">
        <el-form-item label="学号" prop="stuNo">
          <el-input v-model="form.stuNo" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="stuName">
          <el-input v-model="form.stuName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="Sex">
          <el-radio-group v-model="form.Sex">
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
        <el-form-item label="手机号" prop="Phone">
          <el-input v-model="form.Phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮件" prop="Email">
          <el-input v-model="form.Email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="Address">
          <el-input v-model="form.Address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="头像" prop="Pic">
          <upload
            @success="uploadSuccess"
            @remove="uploadRemove"
            :picUrl="form.Pic"
          ></upload>
        </el-form-item>
        <el-form-item label="入学日期" prop="entoTime">
          <el-date-picker
            type="date"
            placeholder="选择日期"
            v-model="form.entoTime"
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
import Upload from '@/components/Upload'
import { addStudent } from '@/api'

export default {
  name: 'editAndCreayeStudent',
  data() {
    return {
      dialogFormVisible: this.visible,
      form: {
        stuNo: '',
        stuName: '',
        Sex: '',
        Phone: '',
        Email: '',
        Address: '',
        // classId: '',
        Birth: '',
        Pic: '',
        entoTime: ''
      },
      formLabelWidth: '120px',
      rules: {
        stuNo: [{ required: true, message: '请输入学号', trigger: 'blur' }],
        stuName: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        Sex: [{ required: true, message: '请选择性别', trigger: 'click' }],
        Phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
        Email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }],
        Address: [{ required: true, message: '请输入地址', trigger: 'blur' }],
        entoTime: [
          { required: true, message: '请输入学日期', trigger: 'blur' }
        ],
        Birth: [{ required: true, message: '请选择出生日期', trigger: 'blur' }]
      }
    }
  },
  components: { Upload },
  props: {
    visible: { type: Boolean, default: false },
    closeDestory: { type: Boolean, default: true }
  },
  watch: {
    dialogFormVisible(isv, old) {
      if (isv !== old) {
        this.$emit('update:visible', isv)
      }

      if (!isv) {
        this.$refs['ruleForm'].resetFields()
      }
    },
    visible(isv) {
      this.dialogFormVisible = isv
    }
  },
  methods: {
    submitForm() {
      console.log(1)
      this.$refs['ruleForm'].validate(async (valid) => {
        if (valid) {
          try {
            const data = await addStudent(this.form)
            this.$message.error('添加成功!!')
            console.log('data: ', data)
          } catch (error) {
            this.$message.error('添加失败')
            this.dialogFormVisible = false
          }
        }
      })
    },
    uploadSuccess(res) {
      console.log(res)
      const { response } = res
      this.form.Pic = this.setImagUrl(response.resultSet)
      console.log(' this.form.Pic: ', this.form.Pic)
    },
    uploadRemove() {
      console.log('ds')
      this.form.Pic = ''
    },
    setImagUrl(url) {
      return process.env.VUE_APP_BASURL + '/' + url
    }
  }
}
</script>

<style scoped>
</style>