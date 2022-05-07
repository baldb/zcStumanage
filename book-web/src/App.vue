<template>
  <div id="app">
    <input type="text" v-model="msg" /> 账号
    <input type="password" v-model="pwd" /> 密码
    <button @click="send">发送请求</button>
    <router-view></router-view>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'App',
  components: {},
  data() {
    return {
      msg: 'cxn',
      pwd: '111111'
    }
  },
  mounted() {},
  methods: {
    send() {
      axios.get('/api/test', {
        params: {
          name: this.msg,
          password: this.pwd
        }
      })
    }
  }
}
</script>

<style lang="less">
</style>
