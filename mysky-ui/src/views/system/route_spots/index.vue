<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="中间景点名称" prop="spotName">
        <el-input
          v-model="queryParams.spotName"
          placeholder="请输入中间景点名称"
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
          v-hasPermi="['system:route_spots:add']"
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
          v-hasPermi="['system:route_spots:edit']"
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
          v-hasPermi="['system:route_spots:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:route_spots:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="route_spotsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录唯一标识" align="center" prop="id" />
      <el-table-column label="关联路线ID" align="center" prop="routeId" />
      <el-table-column label="景点ID" align="center" prop="spotId" />
      <el-table-column label="中间景点名称" align="center" prop="spotName" />
      <el-table-column label="景点顺序" align="center" prop="sequence" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:route_spots:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:route_spots:remove']"
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

    <!-- 添加或修改旅游路线中间景点对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联路线ID" prop="routeId">
          <el-input v-model="form.routeId" placeholder="请输入关联路线ID" />
        </el-form-item>
        <el-form-item label="景点ID" prop="spotId">
          <el-input v-model="form.spotId" placeholder="请输入景点ID" />
        </el-form-item>
        <el-form-item label="中间景点名称" prop="spotName">
          <el-input v-model="form.spotName" placeholder="请输入中间景点名称" />
        </el-form-item>
        <el-form-item label="景点顺序" prop="sequence">
          <el-input v-model="form.sequence" placeholder="请输入景点顺序" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRoute_spots, getRoute_spots, delRoute_spots, addRoute_spots, updateRoute_spots } from "@/api/system/route_spots";

export default {
  name: "Route_spots",
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
      // 旅游路线中间景点表格数据
      route_spotsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        spotName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        routeId: [
          { required: true, message: "关联路线ID不能为空", trigger: "blur" }
        ],
        spotId: [
          { required: true, message: "景点ID不能为空", trigger: "blur" }
        ],
        spotName: [
          { required: true, message: "中间景点名称不能为空", trigger: "blur" }
        ],
        sequence: [
          { required: true, message: "景点顺序不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询旅游路线中间景点列表 */
    getList() {
      this.loading = true;
      listRoute_spots(this.queryParams).then(response => {
        this.route_spotsList = response.rows;
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
        routeId: null,
        spotId: null,
        spotName: null,
        sequence: null,
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
      this.title = "添加旅游路线中间景点";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRoute_spots(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改旅游路线中间景点";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRoute_spots(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRoute_spots(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除旅游路线中间景点编号为"' + ids + '"的数据项？').then(function() {
        return delRoute_spots(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/route_spots/export', {
        ...this.queryParams
      }, `route_spots_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
