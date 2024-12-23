<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-row gutter="15">
        <el-col :span="12">
          <el-form-item label="课程编号" prop="code">
            <el-input v-model="formData.code" placeholder="请输入课程编号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="课程学科" prop="subject">
            <el-select v-model="formData.subject" placeholder="请选择课程学科" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in subjectOptions" :key="index" :label="item.label"
                :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row gutter="15">
        <el-col :span="12">
          <el-form-item label="课程名称" prop="field107">
            <el-input v-model="formData.field107" placeholder="请输入课程名称" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="单行文本" prop="field108">
            <el-input v-model="formData.field108" placeholder="请输入单行文本" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row gutter="15">
        <el-col :span="12">
          <el-form-item label="下拉选择" prop="field110">
            <el-select v-model="formData.field110" placeholder="请选择下拉选择" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in field110Options" :key="index" :label="item.label"
                :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="日期范围" prop="field111">
            <el-date-picker type="daterange" v-model="formData.field111" format="yyyy-MM-dd"
              value-format="yyyy-MM-dd" :style="{width: '100%'}" start-placeholder="开始日期"
              end-placeholder="结束日期" range-separator="至" clearable></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row gutter="15">
        <el-col :span="12">
          <el-form-item label="上传" prop="field113" required>
            <el-upload ref="field113" :file-list="field113fileList" :action="field113Action"
              :before-upload="field113BeforeUpload">
              <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
            </el-upload>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评分" prop="field114">
            <el-rate v-model="formData.field114"></el-rate>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row gutter="15">
        <el-col :span="18">
          <el-form-item label="多行文本" prop="field116">
            <el-input v-model="formData.field116" type="textarea" placeholder="请输入多行文本"
              :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        code: undefined,
        subject: undefined,
        field107: undefined,
        field108: undefined,
        field110: undefined,
        field111: null,
        field113: null,
        field114: 0,
        field116: undefined,
      },
      rules: {
        code: [{
          required: true,
          message: '请输入课程编号',
          trigger: 'blur'
        }],
        subject: [{
          required: true,
          message: '请选择课程学科',
          trigger: 'change'
        }],
        field107: [{
          required: true,
          message: '请输入课程名称',
          trigger: 'blur'
        }],
        field108: [{
          required: true,
          message: '请输入单行文本',
          trigger: 'blur'
        }],
        field110: [{
          required: true,
          message: '请选择下拉选择',
          trigger: 'change'
        }],
        field111: [{
          required: true,
          message: '日期范围不能为空',
          trigger: 'change'
        }],
        field114: [{
          required: true,
          message: '评分不能为空',
          trigger: 'change'
        }],
        field116: [{
          required: true,
          message: '请输入多行文本',
          trigger: 'blur'
        }],
      },
      field113Action: 'https://jsonplaceholder.typicode.com/posts/',
      field113fileList: [],
      subjectOptions: [{
        "label": "选项一",
        "value": 1
      }, {
        "label": "选项二",
        "value": 2
      }],
      field110Options: [{
        "label": "选项一",
        "value": 1
      }, {
        "label": "选项二",
        "value": 2
      }],
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
    field113BeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 2
      if (!isRightSize) {
        this.$message.error('文件大小超过 2MB')
      }
      return isRightSize
    },
  }
}

</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}

.el-rate {
  display: inline-block;
  vertical-align: text-top;
}

</style>
