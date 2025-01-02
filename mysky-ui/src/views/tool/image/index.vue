<template>
  <div class="app-container">
    <el-card class="box-card">
      <!-- 图片显示区域 -->
      <div class="image-container" @click="triggerUpload">
        <img v-if="imageUrl" :src="imageUrl" class="preview-image"/>
        <div v-else class="empty-image">
          <i class="el-icon-picture-outline"></i>
          <span>点击上传图片</span>
        </div>
        <!-- 隐藏的文件上传输入框 -->
        <input
          type="file"
          ref="fileInput"
          accept="image/*"
          style="display: none"
          @change="handleFileChange"
        />
      </div>

      <!-- 文本输入区域 -->
      <el-input
        type="textarea"
        v-model="inputText"
        :rows="4"
        placeholder="请输入文本"
        class="input-area"
      ></el-input>

      <!-- 按钮 -->
      <el-button type="primary" class="submit-button" @click="handleSubmit">
        提交
      </el-button>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'ImageTool',
  data() {
    return {
      imageUrl: '',
      inputText: ''
    }
  },
  methods: {
    // 触发文件选择
    triggerUpload() {
      this.$refs.fileInput.click()
    },
    // 处理文件选择变化
    handleFileChange(event) {
      const file = event.target.files[0]
      if (!file) {
        return
      }

      // 验证文件类型
      if (!file.type.startsWith('image/')) {
        this.$message.error('请选择图片文件')
        return
      }

      // 创建文件预览URL
      this.imageUrl = URL.createObjectURL(file)

      // 清空文件输入框，这样同一个文件可以重复选择
      event.target.value = ''
    },
    handleSubmit() {
      // TODO: 处理提交逻辑
      console.log('提交的文本:', this.inputText)
      console.log('图片URL:', this.imageUrl)
    }
  },
  // 组件销毁时清理资源
  beforeDestroy() {
    if (this.imageUrl) {
      URL.revokeObjectURL(this.imageUrl)
    }
  }
}
</script>

<style scoped>
.image-container {
  width: 100%;
  height: 300px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
  background-color: #fafafa;
  cursor: pointer;
  transition: border-color 0.3s;
}

.image-container:hover {
  border-color: #409EFF;
}

.empty-image {
  text-align: center;
  color: #909399;
}

.empty-image i {
  font-size: 64px;
  margin-bottom: 10px;
}

.empty-image span {
  display: block;
}

.preview-image {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}

.input-area {
  margin-bottom: 20px;
}

.submit-button {
  width: 100%;
}
</style>
