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
      <el-table-column prop="name" label="登录名" width="120"></el-table-column>
      <el-table-column prop="realName" label="姓名" width="120"></el-table-column>
      <el-table-column prop="roles" label="角色">
        <template slot-scope="scope">
          <el-tag v-for="role in scope.row.roles"
                  :key="role.role"
                  closable
                  :disable-transitions="true"
                  @close="handleCloseTag(scope.row,role)">
            {{role.name}}
          </el-tag>
          <el-tooltip content="增加角色">
            <el-button size="small" icon="el-icon-circle-plus-outline"
                       @click="openAddRoleDialog(scope.row)"></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="accountNonExpired" label="账户是否过期" width="120">
        <template slot-scope="scope">
          <el-tag :type="scope.row.accountNonExpired ? 'success' : 'danger'">
            {{scope.row.accountNonExpired ? '否' : '是'}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="accountNonLocked" label="账户是否锁定" width="120">
        <template slot-scope="scope">
          <el-tag :type="scope.row.accountNonLocked ? 'success' : 'danger'">
            {{scope.row.accountNonLocked ? '否' : '是'}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="credentialsNonExpired" label="密码是否过期" width="120">
        <template slot-scope="scope">
          <el-tag :type="scope.row.credentialsNonExpired ? 'success' : 'danger'">
            {{scope.row.credentialsNonExpired ? '否' : '是'}}
          </el-tag>
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
      :current-page="tableData.page"
      :page-size="tableData.size"
      :page-sizes="[20, 50, 100]"
      layout="total, sizes, prev, pager, next"
      :total="tableData.totalCount">
    </el-pagination>


    <el-dialog title="添加新用户"
               :visible.sync="dialogFormVisible"
               :close-on-click-modal="closeOnClickModal"
               width="30%">
      <el-form :model="form"
               :rules="rules"
               ref="form"
               label-position="right"
               label-width="80px">
        <el-form-item label="用户名" prop="name">
          <el-input v-model="form.name" auto-complete="off" placeholder="请输入用户名">
            <i slot="prefix" class="el-input__icon el-icon-service"></i>
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" auto-complete="off" type="password" placeholder="请输入密码">
            <i slot="prefix" class="el-input__icon el-icon-more"></i>
          </el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="realName">
          <el-input v-model="form.realName" auto-complete="off" placeholder="请输入姓名">
            <i slot="prefix" class="el-input__icon el-icon-star-off"></i>
          </el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" auto-complete="off" placeholder="请输入邮箱">
            <i slot="prefix" class="el-input__icon el-icon-message"></i>
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
        <el-button type="primary" @click="addUser(form,'form')" :loading="dialogLoading">添加</el-button>
      </div>
    </el-dialog>

    <el-dialog title="添加角色"
               :visible.sync="addRoleDialogVisible"
               :close-on-click-modal="false"
               width="30%">
      <el-table
        :data="allRoles"
        tooltip-effect="dark"
        style="width: 100%"
        max-height="550"
        @selection-change="handleRoleSelectionChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          label="角色名">
          <template slot-scope="scope">
            <el-tag>{{scope.row.name}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="role"
          label="角色代码">
        </el-table-column>
      </el-table>
      <div slot="footer">
        <el-button @click="addRoleDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addRole()" :loading="addRoleDialogLoading">添加</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
  import SearchBar from './../components/SearchBar'
  import {userApi, roleApi} from './../api'

  export default {
    name: 'Users',
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
          password: '',
          realName: '',
          description: '',
          mail: ''
        },
        rules: {
          name: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {min: 3, max: 20, message: '请输入长度在3~20个字符的用户名', trigger: 'change'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 6, max: 20, message: '请输入6~20位的密码', trigger: 'change'}
          ],
          realName: [
            {max: 255, message: '姓名最长255位', trigger: 'change'}
          ],
          description: [
            {max: 500, message: '备注最长500个字符', trigger: 'change'}
          ],
          email: [
            {max: 255, message: '邮箱最长255个字符', trigger: 'change'}
          ]
        },
        allRoles: [],
        addRoleDialogVisible: false,
        addRoleDialogLoading: false,
        roleMultipleSelection: [],
        currentUser: {}
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
      },
      loadData(params) {
        let _params = Object.assign({}, params);
        userApi.list(_params).then((res) => {
          this.tableData = res
          this.params.page = res.page
          this.params.size = res.size
        })
      },
      addUser(form, formName) {
        if (!this.dialogLoading) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.dialogLoading = true;
              let _params = Object.assign({}, form);
              userApi.add(_params).then(res => {
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
      },
      handleCloseTag(user, role) {
        user.roles.splice(user.roles.indexOf(role), 1)
      },
      openAddRoleDialog(user) {
        this.addRoleDialogVisible = true
        this.currentUser = user
        roleApi.all().then(res => {
          this.allRoles = res.list
        })
      },
      handleRoleSelectionChange(val) {
        this.roleMultipleSelection = val
      },
      addRole() {
        if (!this.addRoleDialogLoading && this.roleMultipleSelection.length > 0) {
          this.addRoleDialogLoading = true
          userApi.updateRole(this.currentUser.id, this.roleMultipleSelection).then(res => {
            this.addRoleDialogVisible = false
            this.addRoleDialogLoading = false
            this.currentUser.roles = res.list
          })
        }
      }
    },
    mounted: function () {
      this.loadData(this.params)
    }
  }
</script>
