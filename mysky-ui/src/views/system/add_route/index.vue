<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px"
      label-position="left">
      <el-form-item label="路线内容" prop="field101">
        <el-input v-model="formData.field101" type="textarea" placeholder="请输入旅游路线内容，例如输入：从景山公园到朝阳公园"
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
          userMessage: this.formData.field101
        }).then(response => {
          loading.close()
          if (response.code === 200) {
            try {
              const aiResponse = JSON.parse(response.data);
              if (aiResponse.choices && aiResponse.choices[0]) {
                this.$message.success('路线生成成功');
                let content = aiResponse.choices[0].message.content;
                // 如果内容以 ```json 开头，去掉这个标记
                if (content.startsWith('```json')) {
                  content = content.substring(7);
                }
                // 如果内容以 ``` 结尾，去掉这个标记
                if (content.endsWith('```')) {
                  content = content.substring(0, content.length - 3);
                }
                this.generatedRoute = content.trim();
              } else {
                this.$message.error('生成失败：AI 响应格式错误');
              }
            } catch (error) {
              console.error('解析 AI 响应失败:', error);
              this.$message.error('解析响应失败');
            }
          } else {
            this.$message.error(response.msg || '生成失败');
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
        try {
          // 解析生成的路线数据
          const routeData = JSON.parse(this.generatedRoute);

          // 准备路线数据
          const routeInfo = {
            name: routeData.tour_name,
            startPointName: routeData.departure_spot,
            endPointName: routeData.destination_spot,
            startCity: routeData.departure_city.split(',')[0],
            endCity: routeData.destination_city.split(',')[0],
            startPointId: 1,
            endPointId: 1,
            totalDistance: "100",
            // 添加行程详情
            description: routeData.itinerary.map(item =>
              `${item.time}: ${item.activity}\n${item.details.join('\n')}`
            ).join('\n\n')
          };

          // 存储路线数据到 sessionStorage
          sessionStorage.setItem('newRouteInfo', JSON.stringify(routeInfo));

          // 使用 replace 替换当前页面
          this.$router.replace({
            path: '/route',
            // path: '/system/route',
            query: { openAdd: 'true', _t: Date.now() }  // 添加时间戳确保路由变化
          });

        } catch (error) {
          console.error('解析路线数据失败:', error);
          this.$message.error('解析路线数据失败，请确保已生成有效的路线数据');
        }
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
