<template>
  <div>
    <div
      :class="{ hidden: hidden }"
      class="pagination-container"
      style="text-align: right"
    >
      <el-pagination
        :background="background"
        :current-page.sync="currentPage"
        :page-size.sync="pageSize"
        layout="prev, pager, next"
        :total="total"
        v-bind="$attrs"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        hide-on-single-page
      />
    </div>
  </div>
</template>

<script>
export default {
  name: 'Pagination',
  props: {
    total: {
      required: true,
      type: Number
    },
    page: {
      type: Number,
      default: 1
    },
    offset: {
      type: Number,
      default: 10
    },
    layout: {
      type: String,
      default: 'prev, pager, next'
    },
    background: {
      type: Boolean,
      default: true
    },
    hidden: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    currentPage: {
      get() {
        return this.page
      },
      set(val) {
        this.$emit('update:page', val)
      }
    },
    pageSize: {
      get() {
        return this.offset
      },
      set(val) {
        this.$emit('update:offset', val)
      }
    }
  },
  methods: {
    handleSizeChange(val) {
      this.$emit('pagination', { page: this.currentPage, offset: val })
    },
    handleCurrentChange(val) {
      this.$emit('pagination', { page: val, offset: this.pageSize })
    }
  }
}
</script>

<style scoped>
.pagination-container {
  background: #fff;
  padding: 16px 0;
}
.pagination-container.hidden {
  display: none;
}
</style>