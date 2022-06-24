<template>
  <div class="profile-box">
    <div class="demo-image__preview">
      <el-image
        style="width: 150px; height: 150px"
        :src="getUrl"
        :preview-src-list="[getUrl]"
      >
      </el-image>
    </div>
    <div class="user-info">
      <component :is="rendComp"></component>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Teacher from './chilren/Teacher.vue'
import Student from './chilren/Student.vue'
export default {
  name: 'profile',
  components: {
    Teacher,
    Student
  },
  data() {
    return {
      hostPath: process.env.VUE_APP_BASURL + '/images/'
    }
  },
  computed: {
    ...mapGetters(['indentity', 'getuserinfo']),
    rendComp() {
      return this.indentity === 'teacher' ? 'Teacher' : 'Student'
    },
    getUrl() {
      if (this?.indentity === 'admin')
        return 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
      const isTeachr = this.indentity === 'teacher' ? 'teachPic' : 'pic'
      return this.hostPath + this.getuserinfo[isTeachr]
    }
  }
}
</script>
 
<style scoped >
.profile-box {
  padding-top: 20px;
  width: 80%;
  margin: 0 auto;
}
.demo-image__preview {
  display: flex;
  justify-content: center;
}
.demo-image__preview >>> .el-image {
  padding: 10px;
  outline: 1px solid orange;
  outline-offset: 10px;
}

.user-info {
  padding-top: 15px;
}
</style>