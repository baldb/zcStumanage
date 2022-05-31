<template>
  <div>
    <el-table border :data="tableData" style="width: 100%" v-loading="loading">
      <el-table-column
        v-for="item in header"
        :label="item.label"
        :width="item.width || '180'"
        :key="item.prop"
        :formatter="item.formatter || ((row) => row[item.prop])"
        show-overflow-tooltip
        :fixed="item.fixed || false"
        element-loading-text="加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
      >
        <template slot-scope="scope">
          <slot :row="scope.row" :name="item.prop">{{
            scope.row[item.prop]
          }}</slot>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150" v-if="isEdit" fixed="right">
        <template slot-scope="scope">
          <slot name="edit" :edit="scope.row">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </slot>
        </template>
      </el-table-column>
    </el-table>
    <my-pagination
      :total="total"
      @pagination="(pagechange) => $emit('pagination', pagechange)"
      :page="page"
      :offset="offset"
    ></my-pagination>
  </div>
</template>

<script>
import MyPagination from './Pagination.vue'
export default {
  components: { MyPagination },
  props: {
    header: { type: Array, require: true, default: () => [] },
    tableData: { type: Array, require: true, default: () => [] },
    isEdit: { type: Boolean, default: true },
    loading: { type: Boolean, default: false },
    total: { type: Number, default: 0 },
    offset: { type: Number },
    page: { type: Number }
  },
  data() {
    return {}
  },
  methods: {
    handleEdit(index, row) {
      this.$emit('$handleEdit', row)
    },
    handleDelete(index, row) {
      this.$emit('$handleDelete', row)
    }
  }
}
</script>

<style scoped>
</style>