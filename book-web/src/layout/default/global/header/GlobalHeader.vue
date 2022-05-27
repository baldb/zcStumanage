<template>
  <div class="hearder-content">
    <h1><img src="~assets/zcxy.jpg" alt="" /> {{ title }}</h1>
    <right-content />
  </div>
</template>

<script>
import RightContent from './RightContent.vue'
export default {
  name: 'globalHeader',
  components: { RightContent },
  props: { title: String }
}
</script>

<style scoped>
.hearder-content {
  display: flex;
  align-items: center;
  height: 100%;
  justify-content: space-between;
}

h1 {
  margin: 0;
  padding: 0;
}
img {
  vertical-align: middle;
  border-radius: 50%;
  width: 60px;
}
</style>