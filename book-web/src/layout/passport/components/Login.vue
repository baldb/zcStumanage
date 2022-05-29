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
        <el-form-item prop="name" label=" " label-width="15px">
          <el-input
            v-model.trim="ruleForm.name"
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
          <!--     <router-link
            to="forgotpwd"
            tag="span"
            v-slot="{ href, navigate }"
            custom
          >
            <span :href="href" @click="navigate" :style="{ cursor: 'pointer' }"
              >找回密码</span
            >
          </router-link> -->
        </div>
        <div class="submit-btn">
          <el-button
            :loading="isLoading"
            type="primary"
            @click="submitForm('ruleForm')"
            style="width: 150px"
            >登录</el-button
          >
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  data() {
    return {
      isLoading: false,
      ruleForm: {
        password: '',
        name: ''
      },
      rules: {
        // 验证用户名是否合法
        name: [
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
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.isLoading = true
          await this.$store.dispatch('user/login', this.ruleForm)
          this.$router.push('/passed').catch(() => {})
          this.isLoading = false
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

.submit-btn {
  margin-top: 20px;
  text-align: center;
}
</style>