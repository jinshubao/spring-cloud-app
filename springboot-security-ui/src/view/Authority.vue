<template>
  <section>
    <search-bar @search="handleSearch">
      <template>
        <el-button type="success" @click="handleAdd">新增</el-button>
      </template>
    </search-bar>
    <el-table :data="tableData.list"
              max-height="650"
              border>
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column prop="name" label="权限名" width="120"></el-table-column>
      <el-table-column prop="role" label="角色代码" width="120"></el-table-column>
      <el-table-column prop="authorities" label="权限">
        <template slot-scope="scope">
          <el-tag v-for="item in scope.row.authorities"
                  :key="item.authority"
                  closable
                  :disable-transitions="true"
                  @close="handleCloseTag(scope.row, item)">
            {{item.name}}
          </el-tag>
          <el-tooltip content="增加角色">
            <el-button size="small" icon="el-icon-circle-plus-outline"
                       @click="openAddRoleDialog(scope.row)"></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="enabled" label="是否禁用" width="120">
        <template slot-scope="scope">
          <el-tag :type="scope.row.enabled ? 'success' : 'danger'" close-transition>
            {{scope.row.enabled ? '否' : '是'}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="modifiedTime" label="最后修改日期" width="200"></el-table-column>
      <el-table-column prop="description" label="备注" width="100"></el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="tableData.page"
      :page-size="tableData.size"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next"
      :total="tableData.totalCount">
    </el-pagination>
    <el-dialog title="添加新角色"
               :visible.sync="dialogFormVisible"
               :close-on-click-modal="closeOnClickModal"
               width="30%">
      <el-form :model="form"
               :rules="rules"
               ref="form"
               label-position="right"
               label-width="80px">
        <el-form-item label="角色名" prop="name">
          <el-input v-model="form.name" auto-complete="off" placeholder="请输入角色名">
            <i slot="prefix" class="el-input__icon el-icon-service"></i>
          </el-input>
        </el-form-item>
        <el-form-item label="角色代码" prop="role">
          <el-input v-model="form.role" auto-complete="off" placeholder="请输入角色代码">
            <i slot="prefix" class="el-input__icon el-icon-star-off"></i>
          </el-input>
        </el-form-item>
        <el-form-item label="备注" prop="description">
          <el-input v-model="form.description" auto-complete="off" placeholder="请输入备注">
            <i slot="prefix" class="el-input__icon el-icon-tickets"></i>
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogFormVisible = false;resetForm('form')">取消</el-button>
        <el-button type="primary" @click="addRole(form,'form')" :loading="dialogLoading">添加</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
  import SearchBar from './../components/SearchBar'
  import {roleApi} from './../api'

  export default {
    name: 'Roles',
    components: {SearchBar},
    data() {
      return {
        tableData: {},
        params: {
          page: 1,
          size: 20,
          keyword: ''
        },
        dialogFormVisible: false,
        closeOnClickModal: false,
        dialogLoading: false,
        form: {
          name: '',
          role: '',
          description: ''
        },
        rules: {
          name: [
            {required: true, message: '请输入角色名', trigger: 'blur'},
            {min: 3, max: 255, message: '请输入长度在3~255个字符的角色名', trigger: 'change'}
          ],
          role: [
            {required: true, message: '请输入角色代码', trigger: 'blur'},
            {max: 255, message: '角色代码最长255个字符', trigger: 'change'}
          ],
          description: [
            {max: 500, message: '备注最长500个字符', trigger: 'change'}
          ]
        }
      }
    },
    methods: {
      handleSearch(kw) {
        this.params.keyword = kw
        this.loadData(this.params)
      },
      handleAdd() {
        this.dialogFormVisible = true
      },
      handleSizeChange(val) {
        this.params.size = val
        this.loadData(this.params)
      },
      handleCurrentChange(val) {
        this.params.page = val
        this.loadData(this.params)
      },
      loadData(params) {
        let _params = Object.assign({}, params);
        roleApi.list(_params).then((res) => {
          this.tableData = res
          this.params.page = res.page
          this.params.size = res.size
        })
      },
      addRole(form, formName) {
        if (!this.dialogLoading) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.dialogLoading = true;
              let _params = Object.assign({}, form);
              roleApi.add(_params).then(res => {
                this.dialogLoading = false
                if (res.resCode === '0000') {
                  this.dialogFormVisible = false
                  this.resetForm(formName)
                  this.loadData(this.params)
                } else {
                  this.$message.error(res.resDesc);
                }
              }, err => {
                this.dialogLoading = false
                this.$message.error(err.errors[0].defaultMessage);
              })
            }
          });
        }
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    },
    mounted: function () {
      this.loadData(this.params)
    }
  }
</script>
