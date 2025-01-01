<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px"
      label-position="left">
      <el-form-item label="路线内容" prop="field101">
        <el-input v-model="formData.field101" type="textarea" placeholder="请输入旅游路线内容"
          :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-row gutter="15">
        <el-col :span="11">
          <el-row gutter="15">
          </el-row>
        </el-col>
        <el-col :span="10">
          <el-form-item label="生成" prop="field102">
            <el-button type="primary" size="medium" @click="handleGenerate"> 生成路线 </el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="生成结果">
        <div class="generated-route-content">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>推荐路线详情</span>
            </div>
            <div class="route-text">{{ generatedRoute || '暂无生成结果' }}</div>
          </el-card>
        </div>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { addAddRoute, generateRoute } from '@/api/system/add_route'
import { chatWithAI } from '@/api/tool/ai'

export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        field101: undefined,
        field102: undefined,
      },
      generatedRoute: '',
      rules: {
        field101: [{
          required: true,
          message: '请输入旅游路线内容',
          trigger: 'blur'
        }],
      },
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    handleGenerate() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        const loading = this.$loading({
          lock: true,
          text: '正在生成路线...',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        })
        
        // 调用AI接口生成路线
        chatWithAI({
          systemPrompt: "你是一个专业的旅游路线规划助手，请根据用户输入的内容，生成详细的旅游路线规划。包括出发地、目的地、行程安排等信息。",
          userMessage: this.formData.field101
        }).then(response => {
          loading.close()
          if (response.code === 200) {
            this.$message.success('路线生成成功')
            if (response.data) {
              this.formData.field101 = response.data
              this.generatedRoute = response.data
            }
          } else {
            this.$message.error(response.msg || '生成失败')
          }
        }).catch(error => {
          loading.close()
          console.error('生成路线失败:', error)
          this.$message.error('系统错误，请稍后重试')
        })
      })
    },
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        addAddRoute({
          content: this.formData.field101
        }).then(response => {
          if (response.code === 200) {
            this.$message.success('添加成功')
            this.resetForm()
          } else {
            this.$message.error(response.msg || '添加失败')
          }
        }).catch(error => {
          console.error('添加路线失败:', error)
          this.$message.error('系统错误，请稍后重试')
        })
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
      this.generatedRoute = ''
    },
  }
}
</script>
<style>
.generated-route-content {
  margin-top: 20px;
  width: 100%;
}
.route-text {
  line-height: 1.6;
  white-space: pre-wrap;
  word-wrap: break-word;
}
.box-card {
  width: 100%;
  margin-bottom: 20px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
</style>
