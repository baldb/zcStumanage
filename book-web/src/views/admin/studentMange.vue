<template>
  <div>
    <my-table :tableData="tableData" :header="header">
      <template #name="{ row }">
        <el-popover trigger="hover" placement="top">
          <p>姓名: {{ row.name }}</p>
          <p>住址: {{ row.address }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium"> {{ row.name }}</el-tag>
          </div>
        </el-popover>
      </template>
    </my-table>
  </div>
</template>

<script>
import MyTable from '@/components/Table'

export default {
  components: { MyTable },
  data() {
    return {
      header: [
        {
          label: '日期',
          prop: 'date',
          isTemp: false,
          width: 180,
          icon: '',
          showColumn: true
        },
        { label: '姓名', prop: 'name' },
        { label: '地址', prop: 'address', isTemp: true, showColumn: true }
      ],
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        },
        {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        },
        {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }
      ]
    }
  }
}
</script>

<style scoped>
</style>