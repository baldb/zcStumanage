<template>
  <div class="table-box">
    <el-table
      border
      :data="tableData"
      :style="{ width: width }"
      v-loading="loading"
      :height="height"
    >
      <el-table-column
        v-for="item in header"
        :label="item.label"
        :width="item.width || '180'"
        :key="item.prop"
        :formatter="item.formatter || ((row) => row[item.prop])"
        show-overflow-tooltip
        :fixed="item.fixed || false"
        align="center"
      >
        <template slot-scope="scope">
          <slot :row="scope.row" :name="item.prop">{{
            scope.row[item.prop] || '-'
          }}</slot>
        </template>
      </el-table-column>
      <el-table-column :label="editText" v-if="isEdit" fixed="right">
        <template slot-scope="scope">
          <slot name="edit" :edit="scope.row">
            <el-button
              size="mini"
              @click="handleEdit(scope.row)"
              style="margin-right: 10px"
              >编辑</el-button
            >
            <el-popconfirm
              confirm-button-text="好的"
              cancel-button-text="不用了"
              icon="el-icon-info"
              icon-color="red"
              title="确定删除？"
              @confirm="handleDelete(scope.row)"
            >
              <el-button slot="reference" size="mini" type="danger"
                >删除</el-button
              >
            </el-popconfirm>
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
    editText: { type: String, require: true, default: '操作' },
    height: { type: Number, default: 400 },
    tableData: { type: Array, require: true, default: () => [] },
    isEdit: { type: Boolean, default: true },
    loading: { type: Boolean, default: false },
    total: { type: Number, default: 0 },
    offset: { type: Number },
    page: { type: Number },
    width: {
      type: [Number, String],
      default: '100%'
    }
  },
  data() {
    return {}
  },
  methods: {
    handleEdit(row) {
      this.$emit('handleEdit', row)
    },
    handleDelete(row) {
      this.$emit('handleDelete', row)
    }
  }
}
</script>

<style scoped>
.table-box {
  /* width: 100%; */
}

.table-box .el-table {
  margin: 0 auto;
}
</style>