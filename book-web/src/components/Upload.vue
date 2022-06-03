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
      :file-list="fileList"
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
      disable: false,
      fileList: []
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
    },
    baseurl: {
      type: String,
      require: true
    }
  },
  methods: {
    handleRemove(file) {
      this.fileList = []
      this.$emit('remove')
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    uploadSuccess(response, file) {
      this.$emit('success', file)
    },
    setPic() {
      if (!this.picUrl) return

      this.fileList.push({ url: this.baseurl + this.picUrl })
    }
  },
  mounted() {
    this.setPic()
  },

  watch: {
    picUrl: {
      handler(newUrl, oldUrl) {
        if (!newUrl) return
        this.setPic()
      }
    }
  }
}
</script>

<style scoped>
</style>