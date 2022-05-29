<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column
        v-for="item in header"
        :label="item.label"
        :width="item.width || '180'"
        :key="item.prop"
      >
        <template slot-scope="scope">
          <slot :row="scope.row" :name="item.prop">{{
            scope.row[item.prop]
          }}</slot>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180" v-if="isEdit">
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
    <my-pagination></my-pagination>
  </div>
</template>

<script>
import MyPagination from './Pagination.vue'
export default {
  components: { MyPagination },
  props: {
    header: { type: Array, require: true, default: () => [] },
    tableData: { type: Array, require: true, default: () => [] },
    isEdit: { type: Boolean, default: true }
  },
  data() {
    return {}
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    }
  }
}
</script>

<style scoped>
</style>