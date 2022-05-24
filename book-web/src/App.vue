<template>
  <div id="app">
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
</style>
