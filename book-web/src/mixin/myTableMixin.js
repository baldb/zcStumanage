export default {
  data() {
    return {
      tableLoading: false,
      offset: 9,
      page: 1,
    }
  },
  methods: {
    pageChange({ page }) {
      this.page = page
    },
  },
}
