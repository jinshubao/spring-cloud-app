<template>
    <section>
        <search-bar @search="handleSearch">
            <el-button type="success" @click="handleAdd">新增</el-button>
            <!--<el-select v-model="CUSTOMER_PARAMS.project_id" placeholder="项目" clearable>
                <el-option v-for="item in projects"
                           :key="item.id"
                           :label="item.name"
                           :value="item.id"></el-option>
            </el-select>
            <el-select v-model="CUSTOMER_PARAMS.module_id" placeholder="模块" clearable>
                <el-option v-for="item in modules"
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
            <el-table-column prop="name" label="用例名称" min-width="50">
            </el-table-column>
            <el-table-column prop="api.name" label="接口名称" min-width="50">
            </el-table-column>
            <el-table-column prop="api.method" label="方法" min-width="30">
            </el-table-column>
            <el-table-column prop="api.protocol" label="协议" min-width="30">
            </el-table-column>
            <el-table-column prop="api.host" label="主机" min-width="50">
            </el-table-column>
            <el-table-column prop="api.port" label="端口" min-width="30">
            </el-table-column>
            <el-table-column prop="api.url" label="URL" min-width="50">
            </el-table-column>
            <el-table-column prop="description" label="描述" min-width="50">
            </el-table-column>
            <el-table-column prop="testUnit.module.project.name" label="所属项目" min-width="50" sortable>
            </el-table-column>
            <el-table-column prop="testUnit.module.name" label="所属模块" min-width="50" sortable>
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button type="primary" size="small" @click="executeTestCase(scope.row)" >
                        <i class="el-icon-caret-left"></i>
                    </el-button>
                    <el-button type="primary" size="small" @click="handleEdit(scope.$index, scope.row)" icon="edit"></el-button>
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

    </section>
</template>

<script>

    import SearchBar from '../../components/SearchBar.vue'
    import TestCaseApi from '../../api/TestCaseApi';
    import TestApi from '../../api/TestApi';
    import projectApi from '../../api/projectApi';
    import BaseMethod from '../../common/BaseMethod';
    import BaseData from '../../common/BaseData';

    import {
        ADD_BUTTON_LOADING,
        ADD_DIALOG_OPEN_FLAG,
        BEFORE_ADD_DIALOG_OPEN,
        EDIT_BUTTON_LOADING,
        EDIT_DIALOG_OPEN_FLAG,
        LIST_LOADING,
        COMMON_PARAMS,
        RESULT_DATA
    } from '../../common/constant'

    export default {
        components: {SearchBar},
        data() {
            return {
                ...BaseData,
                projects: [],
                modules: [],
                CUSTOMER_PARAMS: {
                    project_id: '',
                    test_unit_id: '',
                    module_id: ''
                }
            }
        },
        methods: {
            ...TestCaseApi,
            ...BaseMethod,
            executeTestCase: function (row) {
                TestApi.executeTestCase({id: row.id}).then((res) => {
                    console.log(res.data);
                    this.$notify({
                        title: row.name,
                        message: JSON.stringify(res.data),
                        duration: 0,
                        type: 'success'
                    });
                }, (err) => {

                })
            }
        },
        mounted() {
            projectApi.allProjectNames().then((res) => {
                this.projects = res.data.data;
            });
            this.loadData();
        }
    }

</script>
