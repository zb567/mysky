<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="路线名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入路线名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="起点城市名称" prop="startCity">
        <el-input
          v-model="queryParams.startCity"
          placeholder="请输入起点城市名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="终点城市名称" prop="endCity">
        <el-input
          v-model="queryParams.endCity"
          placeholder="请输入终点城市名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="起点景点名称" prop="startPointName">
        <el-input
          v-model="queryParams.startPointName"
          placeholder="请输入起点景点名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="终点景点名称" prop="endPointName">
        <el-input
          v-model="queryParams.endPointName"
          placeholder="请输入终点景点名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:route:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:route:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:route:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:route:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="routeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="路线唯一标识" align="center" prop="id" />
      <el-table-column label="路线名称" align="center" prop="name" />
      <el-table-column label="起点景点ID" align="center" prop="startPointId" />
      <el-table-column label="起点景点名称" align="center" prop="startPointName" />
      <el-table-column label="起点城市名称" align="center" prop="startCity" />
      <el-table-column label="终点景点ID" align="center" prop="endPointId" />
      <el-table-column label="终点景点名称" align="center" prop="endPointName" />
      <el-table-column label="终点城市名称" align="center" prop="endCity" />
      <el-table-column label="路线总距离" align="center" prop="totalDistance" />
      <!-- <el-table-column label="预计旅行时间" align="center" prop="estimatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.estimatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:route:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:route:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改存储旅游者路线的基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="路线名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入路线名称" />
        </el-form-item>
        <el-form-item label="起点城市名称" prop="startCity">
          <el-input v-model="form.startCity" placeholder="请输入起点城市名称" />
        </el-form-item>
        <el-form-item label="终点城市名称" prop="endCity">
          <el-input v-model="form.endCity" placeholder="请输入终点城市名称" />
        </el-form-item>
        <el-form-item label="起点景点ID" prop="startPointId">
          <el-input v-model="form.startPointId" placeholder="请输入起点景点ID" />
        </el-form-item>
        <el-form-item label="起点景点名称" prop="startPointName">
          <el-input v-model="form.startPointName" placeholder="请输入起点景点名称" />
        </el-form-item>
        <el-form-item label="终点景点ID" prop="endPointId">
          <el-input v-model="form.endPointId" placeholder="请输入终点景点ID" />
        </el-form-item>
        <el-form-item label="终点景点名称" prop="endPointName">
          <el-input v-model="form.endPointName" placeholder="请输入终点景点名称" />
        </el-form-item>
        <el-form-item label="路线总距离" prop="totalDistance">
          <el-input v-model="form.totalDistance" placeholder="请输入路线总距离" />
        </el-form-item>
        <!-- <el-form-item label="预计旅行时间" prop="estimatedTime">
          <el-date-picker clearable
            v-model="form.estimatedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预计旅行时间">
          </el-date-picker>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRoute, getRoute, delRoute, addRoute, updateRoute } from "@/api/system/route";

export default {
  name: "Route",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 存储旅游者路线的基本信息表格数据
      routeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        startCity: null,
        endCity: null,
        startPointName: null,
        endPointName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "路线名称不能为空", trigger: "blur" }
        ],
        startCity: [
          { required: true, message: "起点城市名称不能为空", trigger: "blur" }
        ],
        endCity: [
          { required: true, message: "终点城市名称不能为空", trigger: "blur" }
        ],
        startPointId: [
          { required: true, message: "起点景点ID不能为空", trigger: "blur" }
        ],
        startPointName: [
          { required: true, message: "起点景点名称不能为空", trigger: "blur" }
        ],
        endPointId: [
          { required: true, message: "终点景点ID不能为空", trigger: "blur" }
        ],
        endPointName: [
          { required: true, message: "终点景点名称不能为空", trigger: "blur" }
        ],
        totalDistance: [
          { required: true, message: "路线总距离不能为空", trigger: "blur" }
        ],
        /* estimatedTime: [
          { required: true, message: "预计旅行时间不能为空", trigger: "blur" }
        ], */
      }
    };
  },
  created() {
    this.getList();
    this.initializeFromRoute();
  },
  watch: {
    // 监听路由变化
    '$route': {
      handler: 'initializeFromRoute',
      immediate: true
    }
  },
  methods: {
    // 从路由初始化数据
    initializeFromRoute() {
      // 检查是否有新的路线数据和打开新增窗口的参数
      const newRouteInfo = sessionStorage.getItem('newRouteInfo');
      const shouldOpenAdd = this.$route.query.openAdd === 'true';
      
      if (newRouteInfo && shouldOpenAdd) {
        try {
          const routeData = JSON.parse(newRouteInfo);
          // 打开新增窗口
          this.handleAdd();
          // 填充表单数据
          this.$nextTick(() => {
            this.form = {
              ...this.form,
              ...routeData
            };
          });
          // 清除 sessionStorage 中的数据
          sessionStorage.removeItem('newRouteInfo');
        } catch (error) {
          console.error('解析路线数据失败:', error);
        }
      }
    },
    /** 查询存储旅游者路线的基本信息列表 */
    getList() {
      this.loading = true;
      listRoute(this.queryParams).then(response => {
        this.routeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        startCity: null,
        endCity: null,
        startPointId: null,
        startPointName: null,
        endPointId: null,
        endPointName: null,
        totalDistance: null,
        // estimatedTime: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加存储旅游者路线的基本信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRoute(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改存储旅游者路线的基本信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRoute(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRoute(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除存储旅游者路线的基本信息编号为"' + ids + '"的数据项？').then(function() {
        return delRoute(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/route/export', {
        ...this.queryParams
      }, `route_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
