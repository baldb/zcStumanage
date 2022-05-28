<template>
  <div>
    请假条
    <input type="text" v-model="ttt" />
  </div>
</template>

<script>
export default {
  name: 'askleave',
  data() {
    return {
      ttt: ''
    }
  },
  destroyed() {
    console.log(2)
  }
}
</script>

<style scoped>
</style>