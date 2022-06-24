<template>
  <div>
    <component :is="rendComp"></component>
  </div>
</template>

<script>
import StudentHomeVue from './chilren/student/StudentHome.vue'
import TeacherHomeVue from './chilren/teacher/TeacherHome.vue'
import AdminHomeVue from './chilren/admin/AdminHome.vue'
import { mapGetters } from 'vuex'
export default {
  name: 'home',
  components: {
    StudentHomeVue,
    TeacherHomeVue,
    AdminHomeVue
  },
  data() {
    return {
      cc: ''
    }
  },
  computed: {
    ...mapGetters(['indentity']),
    rendComp() {
      const conps = {
        teacher: 'TeacherHomeVue',
        student: 'StudentHomeVue',
        admin: 'AdminHomeVue'
      }
      return conps[this.indentity]
    }
  }
}
</script>

<style scoped>
</style>