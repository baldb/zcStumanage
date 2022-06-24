<template>
  <div class="home-container">
    <student-course
      :courses="courseList"
      @success="addSuccess"
    ></student-course>
    <dashboard></dashboard>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import studentCourse from './StudentCourse'
import dashboard from './dashboard'
import { getStuCourse } from '@/api'
export default {
  data() {
    return {
      courseList: null
    }
  },
  components: {
    studentCourse,
    dashboard
  },
  computed: {
    ...mapGetters(['indentity', 'username'])
  },
  mounted() {
    this.getCourseList()
  },
  methods: {
    getCourseList() {
      getStuCourse({ stuId: this.username }).then(({ resultSet }) => {
        this.courseList = resultSet
        console.log('resultSet: ', resultSet)
      })
    },
    addSuccess() {
      this.getCourseList()
    }
  }
}
</script>

<style scoped>
.home-container {
  display: flex;
  padding: 10px;
  height: 500px;
  align-items: center;
}
</style>