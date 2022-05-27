<template>
  <div id="app">
    <!-- 占位符 -->
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
    // send() {
    //   axios.get('/api/test', {
    //     params: {
    //       name: this.msg,
    //       password: this.pwd
    //     }
    //   })
    // }
  }
}
</script>

<style lang="less">
body {
  margin: 0;
  padding: 0;
  background-color: #e9eef3;
}
</style>
