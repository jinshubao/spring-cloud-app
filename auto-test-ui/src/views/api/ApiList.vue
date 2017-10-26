<template>
    <section>
        <search-bar @search="handleSearch">
            <el-button type="success" @click="handleAdd">新增</el-button>
        </search-bar>
        <!--列表-->
        <el-table :data="RESULT_DATA.data"
                  highlight-current-row
                  v-loading="LIST_LOADING"
                  border
                  style="width: 100%;">
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

        <add-api-dialog :visible="ADD_DIALOG_OPEN_FLAG"
                        @afterSubmit="addSubmit"
                        @cancel="addCancel">
        </add-api-dialog>
        <edit-api-dialog :visible="EDIT_DIALOG_OPEN_FLAG"
                         :formData="editFormData"
                         @afterSubmit="editSubmit"
                         @cancel="editCancel">
        </edit-api-dialog>

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
                CUSTOMER_PARAMS: {
                    project_id: '',
                    module_id: ''
                },
                editFormData: {},
            }
        },
        methods: {
            ...ApiApi,
            ...BaseMethod,

            handleEdit(index, row) {
                this[EDIT_DIALOG_OPEN_FLAG] = true;
                this.editFormData = Object.assign({}, row);
                this.editFormData.projectModule = [row.module.project.id, row.module.id]
            }
        },
        computed: {},
        mounted() {
            this.loadData();
        }
    }
</script>
