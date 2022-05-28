<template>
  <div>
    fdsgfdg
    <input type="text" v-model="cc" />
  </div>
</template>

<script>
export default {
  name: 'home',
  data() {
    return {
      cc: ''
    }
  },
  mounted() {
    // console.log(this.$store.state.count);
    // console.log(this.$route);
    // console.log(this.$router);
    console.log(1)
  },
  methods: {
    send() {}
  }
}
</script>

<style scoped>
</style>