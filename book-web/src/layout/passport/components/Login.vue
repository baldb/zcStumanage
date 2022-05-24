<template>
  <div class="login-form">
    <div class="login-title">欢迎登录</div>
    <div class="login-input">
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item prop="username" label=" " label-width="15px">
          <el-input
            v-model.trim="ruleForm.username"
            prefix-icon="el-icon-user"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password" label=" " label-width="15px">
          <el-input
            prefix-icon="el-icon-lock"
            type="password"
            v-model="ruleForm.password"
            autocomplete="off"
            show-password
          ></el-input>
        </el-form-item>
        <div class="login-check">
          <router-link
            to="forgotpwd"
            tag="span"
            v-slot="{ href, navigate }"
            custom
          >
            <span :href="href" @click="navigate" :style="{ cursor: 'pointer' }"
              >找回密码</span
            >
          </router-link>

          <el-checkbox v-model="checked">记住密码</el-checkbox>
        </div>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  data() {
    return {
      checked: true,
      ruleForm: {
        password: '',
        username: ''
      },
      rules: {
        // 验证用户名是否合法
        username: [
          { required: true, message: '请输入登录名称', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        // 验证密码是否合法
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$router.push('/')
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
.login-title {
  text-align: center;
  color: #242527;
  font-size: 24px;
}

.login-input {
  margin: 20px 0;
}

.login-check {
  margin: -10px 0 10px;
  display: flex;
  justify-content: space-between;
}

.login-check a {
  color: rgba(0, 0, 0, 0.65);
}

.login-button {
  margin-top: 32px;
}

.login-button span {
  width: 100%;
}

.login-button-content {
  display: inline-block;
  margin-top: 25px;
  padding: 10px 128px;
  border-radius: 5px;
  background-color: #35abed;
  color: #ffffff;
  cursor: pointer;
}
</style>