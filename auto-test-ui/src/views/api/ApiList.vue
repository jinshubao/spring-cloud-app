<template>
    <section>
        <search-bar @search="handleSearch">
            <el-button type="success" @click="handleAdd">新增</el-button>
            <!--<el-select v-model="CUSTOMER_PARAMS.project_id" placeholder="项目" clearable @change="projectChange">
                <el-option v-for="item in projectNames"
                           :key="item.id"
                           :label="item.name"
                           :value="item.id"></el-option>
            </el-select>
            <el-select v-model="CUSTOMER_PARAMS.module_id" placeholder="模块" clearable>
                <el-option v-for="item in moduleNames"
                           :key="item.id"
                           :label="item.name"
                           :value="item.id"></el-option>
            </el-select>-->
        </search-bar>
        <!--列表-->
        <el-table :data="RESULT_DATA.data"
                  highlight-current-row
                  v-loading="LIST_LOADING"
                  border
                  style="width: 100%;"
                  max-height="600">
            <el-table-column type="index" width="60">
            </el-table-column>
            <el-table-column prop="name" label="接口名称" min-width="50">
            </el-table-column>
            <el-table-column prop="method" label="方法" min-width="30">
            </el-table-column>
            <el-table-column prop="protocol" label="协议" min-width="30">
            </el-table-column>
            <el-table-column prop="host" label="主机" min-width="50">
            </el-table-column>
            <el-table-column prop="port" label="端口" min-width="30">
            </el-table-column>
            <el-table-column prop="url" label="URL" min-width="50">
            </el-table-column>
            <el-table-column prop="description" label="描述" min-width="50">
            </el-table-column>
            <el-table-column prop="module.project.name" label="所属项目" min-width="50" sortable>
            </el-table-column>
            <el-table-column prop="module.name" label="所属模块" min-width="50" sortable>
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template scope="scope">
                    <el-button type="primary" size="small" @click="handleEdit(scope.$index, scope.row)"
                               icon="edit"></el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)"
                               icon="delete"></el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-pagination layout="total, sizes, prev, pager, next, jumper"
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :page-size="COMMON_PARAMS.size"
                       :page-sizes="[10, 20, 50, 100]"
                       :total="RESULT_DATA.total"
                       style="float:right;">
        </el-pagination>

        <add-api-dialog :projectNames="dialogProjectNames"
                        :submitButtonLoading="ADD_BUTTON_LOADING"
                        :dialogOpenFlag="ADD_DIALOG_OPEN_FLAG"
                        @submit="addSubmit"
                        @cancel="handleAddCancel">
        </add-api-dialog>
        <edit-api-dialog :projectNames="dialogProjectNames"
                         :submitButtonLoading="EDIT_BUTTON_LOADING"
                         :dialogOpenFlag="EDIT_DIALOG_OPEN_FLAG"
                         :formData="editFormData"
                         @submit="editSubmit"
                         @cancel="handleEditCancel">
        </edit-api-dialog>

        <!--新增接口界面-->
        <!--<el-dialog title="新增接口" v-model="ADD_DIALOG_OPEN_FLAG" :close-on-click-modal="false">
            <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
                <el-form-item label="模块" prop="projectModule">
                    <el-cascader :options="addDialogProjectNames" v-model="addForm.projectModule">
                    </el-cascader>
                </el-form-item>
                <el-form-item label="接口名称" prop="name">
                    <el-input v-model="addForm.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="URL" prop="url">
                    <el-input v-model="addForm.url" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="协议" prop="protocol">
                    <el-select v-model="addForm.protocol" placeholder="请选择">
                        <el-option index="http" label="http" value="http"></el-option>
                        <el-option index="https" label="https" value="https"></el-option>
                        <el-option index="file" label="file" value="file"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="方法" prop="method">
                    <el-select v-model="addForm.method" placeholder="请选择">
                        <el-option index="GET" label="GET" value="GET"></el-option>
                        <el-option index="POST" label="POST" value="POST"></el-option>
                        <el-option index="DELETE" label="DELETE" value="DELETE"></el-option>
                        <el-option index="PUT" label="DELETE" value="PUT"></el-option>
                        <el-option index="PATCH" label="PATCH" value="PATCH"></el-option>
                        <el-option index="OPTION" label="PATCH" value="OPTION"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="域名/IP" prop="host">
                    <el-input v-model="addForm.host" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="端口" prop="port">
                    <el-input v-model.number="addForm.port" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="description">
                    <el-input v-model="addForm.description" auto-complete="off"></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="ADD_DIALOG_OPEN_FLAG = false">取消</el-button>
                <el-button type="primary" @click.native="customerAddSubmit" :loading="ADD_BUTTON_LOADING">提交</el-button>
            </div>
        </el-dialog>-->

    </section>
</template>

<script>

    import SearchBar from '../../components/SearchBar'
    import ApiApi from '../../api/ApiApi';
    import projectApi from '../../api/projectApi';
    import moduleApi from '../../api/moduleApi';
    import BaseMethod from '../../common/BaseMethod';
    import BaseData from '../../common/BaseData';
    import AddApiDialog from '../../components/AddApiDialog';
    import EditApiDialog from '../../components/EditApiDialog';

    import {
        ADD_BUTTON_LOADING,
        ADD_DIALOG_OPEN_FLAG,
        BEFORE_ADD_DIALOG_OPEN,
        AFTER_ADD_SUBMIT,
        EDIT_BUTTON_LOADING,
        EDIT_DIALOG_OPEN_FLAG,
        LIST_LOADING,
        COMMON_PARAMS,
        RESULT_DATA
    } from '../../common/constant'

    export default {
        components: {SearchBar, AddApiDialog, EditApiDialog},
        data() {
            return {
                ...BaseData,
                projectNames: [],
                CUSTOMER_PARAMS: {
                    project_id: '',
                    module_id: ''
                },
                editFormData: {}
            }
        },
        methods: {
            ...ApiApi,
            ...BaseMethod,

            handleEdit: function (index, row) {
                this[EDIT_DIALOG_OPEN_FLAG] = true;
                this.editFormData = Object.assign({}, row);
                this.editFormData.projectModule = [row.module.project.id, row.module.id]
            },
        },
        computed: {
            dialogProjectNames: function () {
                return this.projectNames.map((project) => {
                    return {
                        value: project.id,
                        label: project.name,
                        description: project.description,
                        children: project.modules.map((module) => {
                            return {label: module.name, value: module.id}
                        })
                    }
                })
            }
        },
        mounted() {
            projectApi.allProjectNames().then((res) => {
                this.projectNames = res.data.data;
                console.log(res.data);
                this.loadData();
            }, (err) => {

            });
        }
    }
</script>
