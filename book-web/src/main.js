import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUi from './plugins/ElementUi'
import 'element-ui/lib/theme-chalk/index.css'
import moment from 'moment'
Vue.config.productionTip = false

Vue.filter('hostPath', (url) => {
  return process.env.VUE_APP_BASURL + '/images/' + url
})
Vue.filter('transTimeAge', (time) => {
  return moment().diff(moment(time), 'year')
})

new Vue({
  render: (h) => h(App),
  router,
  store,
}).$mount('#app')
