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
          systemPrompt: "你是一个专业的旅游路线规划助手，请根据用户输入的内容，总结出详细的旅游路线规划。包括路线名称、起点城市名称、起点景点名称、终点城市名称、终点景点名称、行程安排等信息。以json形式返回数据，生成标准的json格式数据不允许有其他内容。格式如下：{\n  \"tour_name\": \"Sakamoto Ryoma Historical Tour\",\n  \"departure_city\": \"Kochi, Japan\",\n  \"departure_spot\": \"Sakamoto Ryoma Memorial Museum\",\n  \"destination_city\": \"Kochi, Japan\",\n  \"destination_spot\": \"Ryoma Birthplace Sites\",\n  \"itinerary\": [\n    {\n      \"time\": \"8:30 AM - 10:30 AM\",\n      \"activity\": \"Visit Sakamoto Ryoma Memorial Museum\",\n      \"details\": [\n        \"Renovated facility with interactive exhibits (2018)\",\n        \"Comprehensive exhibits about Ryoma's life and influence\",\n        \"Museum shop for traditional crafts and memorabilia\"\n      ]\n    },\n    {\n      \"time\": \"10:30 AM - 11:30 AM\",\n      \"activity\": \"Katsurahama Ryoma Statue\",\n      \"details\": [\n        \"Iconic statue overlooking the Pacific Ocean\",\n        \"Photo opportunities with ocean views\",\n        \"Walking along Katsurahama beach\"\n      ]\n    },\n    {\n      \"time\": \"1:00 PM - 3:30 PM\",\n      \"activity\": \"Explore Kochi Castle & Historical Museum\",\n      \"details\": [\n        \"Explore the historic castle complex\",\n        \"Visit the modern Historical Museum (opened 2017)\",\n        \"Learn about the role of Yamanouchi Toyoshige\",\n        \"Panoramic city views from castle tower\"\n      ]\n    },\n    {\n      \"time\": \"3:30 PM - 4:30 PM\",\n      \"activity\": \"Ryoma Birthplace Sites\",\n      \"details\": [\n        \"Historical stone monument at Kamimachi 2-chome\",\n        \"Visit Kochi City Ryoma Birthplace Memorial Museum\",\n        \"Local historical neighborhood exploration\"\n      ]\n    }\n  ],\n  \"package_price\": \"$200 per person\"\n}",
          userMessage: this.formData.field101+"内容请用中文"
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

          // 使用测试数据
          const routeData1 = {
            "departure_location": "Beijing, China",
            "destination": "Tokyo, Japan",
            "tour_name": "Beijing to Tokyo Cultural Experience",
            "itinerary": [
              {
                "time": "Day 1: Morning",
                "activity": "Flight from Beijing to Tokyo",
                "details": [
                  "Depart from Beijing Capital International Airport",
                  "Arrival at Narita International Airport or Haneda Airport"
                ]
              },
              {
                "time": "Day 1: Afternoon",
                "activity": "Check into Tokyo Accommodation",
                "details": [
                  "Stay at a centrally located hotel",
                  "Rest and refresh after the flight"
                ]
              },
              {
                "time": "Day 1: Evening",
                "activity": "Explore Shibuya and Shinjuku",
                "details": [
                  "Visit the iconic Shibuya Crossing",
                  "Enjoy dinner at a local izakaya",
                  "Experience the vibrant nightlife in Shinjuku"
                ]
              },
              {
                "time": "Day 2: Morning",
                "activity": "Visit Sensoji Temple",
                "details": [
                  "Discover Tokyo's oldest temple in Asakusa",
                  "Shop for souvenirs at Nakamise Shopping Street",
                  "Enjoy Tokyo Skytree views"
                ]
              },
              {
                "time": "Day 2: Afternoon",
                "activity": "Explore Akihabara",
                "details": [
                  "Experience the electronic and anime culture hub",
                  "Visit Maid Cafes for a unique cultural experience",
                  "Shop for electronics and anime merchandise"
                ]
              },
              {
                "time": "Day 2: Evening",
                "activity": "Dinner in Ginza",
                "details": [
                  "Dine at a sushi or kaiseki restaurant",
                  "Stroll through the upscale shopping district"
                ]
              },
              {
                "time": "Day 3: All Day",
                "activity": "Day Trip to Mount Fuji",
                "details": [
                  "Take a guided tour to Lake Ashi and Hakone",
                  "Enjoy breathtaking views of Mount Fuji",
                  "Ride the Hakone Ropeway for aerial views"
                ]
              },
              {
                "time": "Day 4: Morning",
                "activity": "Explore Harajuku and Meiji Shrine",
                "details": [
                  "Walk through the trendy Harajuku district",
                  "Visit the serene Meiji Shrine",
                  "Participate in a sake tasting experience"
                ]
              },
              {
                "time": "Day 4: Afternoon",
                "activity": "Return Flight to Beijing",
                "details": [
                  "Check out from the hotel",
                  "Depart from Narita or Haneda Airport",
                  "Arrive back in Beijing"
                ]
              }
            ],
            "package_price": "$1500 per person"
          };
          
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
