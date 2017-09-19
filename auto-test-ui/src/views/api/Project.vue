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
            <el-table-column prop="name" label="项目名称" min-width="60">
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
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)"
                               icon="delete"></el-button>
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
        <el-dialog title="新增项目" v-model="ADD_DIALOG_OPEN_FLAG" :close-on-click-modal="false">
            <el-form :model="ADD_FORM_DATA" label-width="80px" :rules="ADD_FORM_RULES" ref="ADD_FORM_DATA">
                <el-form-item label="项目名称" prop="name">
                    <el-input v-model="ADD_FORM_DATA.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input v-model="ADD_FORM_DATA.description" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="ADD_FORM_DATA.remark" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="ADD_DIALOG_OPEN_FLAG = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="ADD_BUTTON_LOADING">提交</el-button>
            </div>
        </el-dialog><!--编辑界面-->
        <el-dialog title="编辑项目" v-model="EDIT_DIALOG_OPEN_FLAG" :close-on-click-modal="false">
            <el-form :model="EDIT_FORM_DATA" label-width="80px" :rules="EDIT_FORM_RULES" ref="EDIT_FORM_DATA">
                <el-form-item label="项目名称" prop="name">
                    <el-input v-model="EDIT_FORM_DATA.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input v-model="EDIT_FORM_DATA.description" auto-complete="off"></el-input>
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
                //编辑界面数据
                [ADD_FORM_DATA]: {
                    name: '',
                    description: '',
                    remark: ''
                },
                [ADD_FORM_RULES]: {
                    name: [
                        {required: true, message: '请输入项目名称', trigger: 'blur'}
                    ]
                },
                [EDIT_FORM_RULES]: {
                    name: [
                        {required: true, message: '请输入项目名称', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            ...projectApi,
            ...BaseMethod
        },
        mounted() {
            this.loadData();
        }
    }

</script>
