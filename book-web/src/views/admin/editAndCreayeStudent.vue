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
        <el-form-item label="手机号" prop="Phone">
          <el-input v-model="form.Phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮件" prop="Email">
          <el-input v-model="form.Email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="Address">
          <el-input v-model="form.Address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出生日期" prop="entoTime">
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
        Pic: '',
        entoTime: ''
      },
      formLabelWidth: '120px',
      rules: {
        stuNo: [{ required: true, message: '请输入学号', trigger: 'blur' }],
        stuName: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        Sex: [{ required: true, message: '请选择性别', trigger: 'blur' }],
        Phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
        Email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }],
        Address: [{ required: true, message: '请输入地址', trigger: 'blur' }],
        entoTime: [
          { required: true, message: '请输入出生日期', trigger: 'blur' }
        ]
      }
    }
  },
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
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          console.log(valid)
          //  this.dialogFormVisible = false
        }
      })
    }
  }
}
</script>

<style scoped>
</style>