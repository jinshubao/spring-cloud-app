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
                  style="width: 100%;"
                  max-height="600">
            <el-table-column type="index" width="60">
            </el-table-column>
            <el-table-column prop="project.name" label="项目名称" min-width="60">
            </el-table-column>
            <el-table-column prop="name" label="模块名称" min-width="60">
            </el-table-column>
            <el-table-column prop="description" label="描述" min-width="60">
            </el-table-column>
            <el-table-column prop="remark" label="备注" min-width="60">
            </el-table-column>
            <el-table-column prop="createdTime" label="创建时间" width="180" :formatter="formatDate" sortable>
            </el-table-column>
            <el-table-column prop="modifiedTime" label="最后更新时间" width="180" :formatter="formatDate" sortable>
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)" icon="edit"></el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)" icon="delete"></el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-pagination layout="total, sizes, prev, pager, next, jumper"
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :page-size="QUERY_PARAMS.size"
                       :page-sizes="[10, 20, 50, 100]"
                       :total="RESULT_DATA.total"
                       style="float:right;">
        </el-pagination>

        <!--新增界面-->
        <el-dialog title="新增模块" v-model="ADD_DIALOG_OPEN_FLAG" :close-on-click-modal="false">
            <el-form :model="formData" :rules="formRules" label-width="80px" ref="formData">
                <el-form-item label="所属项目" prop="project">
                    <el-select v-model="formData.projectId" placeholder="请选择项目">
                        <el-option v-for="item in projects" :key="item.id" :label="item.name" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="模块名称" prop="name">
                    <el-input v-model="formData.name" auto-complete="off"></el-input>
                </el-form-item>

                <el-form-item label="描述" prop="description">
                    <el-input v-model="formData.description"
                              type="textarea"
                              :autosize="{ minRows: 2, maxRows: 4}"
                              placeholder="请输入项目描述"
                              auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="备注" prop="remark">
                    <el-input v-model="formData.remark" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="ADD_DIALOG_OPEN_FLAG = false">取消</el-button>
                <el-button type="primary" @click.native="customAdd" :loading="ADD_BUTTON_LOADING">提交</el-button>
            </div>
        </el-dialog><!--编辑界面-->
        <el-dialog title="编辑模块" v-model="EDIT_DIALOG_OPEN_FLAG" :close-on-click-modal="false">
            <el-form :model="EDIT_FORM_DATA" :rules="EDIT_FORM_RULES" label-width="80px" ref="EDIT_FORM_DATA">
                <el-form-item label="模块名称" prop="name">
                    <el-input v-model="EDIT_FORM_DATA.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="description">
                    <el-input v-model="EDIT_FORM_DATA.description"
                              type="textarea"
                              :autosize="{ minRows: 2, maxRows: 4}"
                              placeholder="请输入项目描述"
                              auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="EDIT_FORM_DATA.remark" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="EDIT_DIALOG_OPEN_FLAG = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="EDIT_BUTTON_LOADING">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>

    import SearchBar from '../../components/SearchBar.vue'
    import moduleApi from '../../api/moduleApi';
    import projectApi from '../../api/projectApi';
    import BaseMethod from '../../common/BaseMethod';
    import BaseData from '../../common/BaseData';

    import {
        ADD_BUTTON_LOADING,
        ADD_DIALOG_OPEN_FLAG,
        BEFORE_ADD_DIALOG_OPEN,
        ADD_FORM_DATA,
        ADD_FORM_RULES,
        EDIT_BUTTON_LOADING,
        EDIT_DIALOG_OPEN_FLAG,
        EDIT_FORM_DATA,
        EDIT_FORM_RULES,
        LIST_LOADING,
        QUERY_PARAMS,
        RESULT_DATA
    } from '../../common/constant'

    export default {
        components: {SearchBar},
        data() {
            return {
                ...BaseData,
                formData:{
                    name: '',
                    description: '',
                    remark: '',
                    projectId: ''
                },
                formRules:{
                    projectId: [
                        {required: true, message: '请选择项目'}
                    ],
                    description: [
                        {required: true, message: '请输入模块名称', trigger: 'blur'}
                    ],
                    name: [
                        {required: true, message: '请输入模块描述', trigger: 'blur'}
                    ]
                },
                projects: [],
            }
        },
        methods: {
            ...moduleApi,
            ...BaseMethod,
            customAdd:function(){
                this.$refs.formData.validate((valid) => {
                    if (valid) {
                        this.addSubmit(this.formData)
                    }
                })
            },
            [BEFORE_ADD_DIALOG_OPEN]: function () {
                projectApi.allProjectNames().then((res) => {
                    this.projects = res.data.data
                    console.info(this.projects);
                })
            }
        },
        mounted() {
            this.loadData();
        }
    }

</script>
