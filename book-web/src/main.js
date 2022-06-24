import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUi from './plugins/ElementUi'
import 'element-ui/lib/theme-chalk/index.css'
import { IDENT_ENUM } from '@/constant/auth'
import moment from 'moment'
Vue.config.productionTip = false

Vue.filter('hostPath', (url) => {
  return process.env.VUE_APP_BASURL + '/images/' + url
})
Vue.filter('transTimeAge', (time) => {
  return moment().diff(moment(time), 'year')
})
Vue.filter('tansiIndentity', (value) => {
  const t = {
    admin: '管理员',
    teacher: '教师',
    student: '学生',
  }

  return t[value]
})
Vue.filter('istype', (value) => {
  // 获取状态
  for (const k in IDENT_ENUM) {
    if (IDENT_ENUM[k].value === value) {
      return IDENT_ENUM[k].status
    }
  }
  return ''
})

new Vue({
  render: (h) => h(App),
  router,
  store,
}).$mount('#app')
