export default {
  data() {
    return {
      tableLoading: false,
      offset: 8,
      page: 1,
    }
  },
  methods: {
    pageChange({ page }) {
      this.page = page
    },
  },
}
