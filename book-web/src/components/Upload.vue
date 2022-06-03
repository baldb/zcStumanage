<template>
  <div style="display: inline-block">
    <el-upload
      :action="action"
      list-type="picture-card"
      :name="name"
      :on-success="uploadSuccess"
      :limit="1"
      ref="upload"
      :disabled="disable"
      :on-remove="handleRemove"
    >
      <i slot="default" class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="" />
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogImageUrl: '',
      dialogVisible: false,
      disable: false
    }
  },
  props: {
    name: {
      type: String,
      default: 'headerImg'
    },
    action: {
      type: String,
      default: '/api/supers/upload'
    },
    picUrl: {
      type: String,
      default: ''
    }
  },
  methods: {
    handleRemove(file) {
      // const idx = this.fileList.findIndex((f) => f.uid === file.uid)
      // if (idx === -1) return
      // this.fileList.splice(idx, 1)

      this.$emit('remove')
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    uploadSuccess(response, file) {
      // this.fileList.push(file)
      this.$emit('success', file)
    }
  },
  watch: {}
}
</script>

<style scoped>
</style>