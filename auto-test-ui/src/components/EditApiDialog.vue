<template>
    <el-dialog title="修改接口" v-model="dialogOpenFlag" :close-on-click-modal="false">
        <el-form :model="formData" label-width="80px" :rules="formRules" ref="formData">
            <el-form-item label="模块" prop="projectModule">
                <el-cascader :options="projectNames" v-model="formData.projectModule" disabled >
                </el-cascader>
            </el-form-item>
            <el-form-item label="接口名称" prop="name">
                <el-input v-model="formData.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="URL" prop="url">
                <el-input v-model="formData.url" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="协议" prop="protocol">
                <el-select v-model="formData.protocol" placeholder="请选择">
                    <el-option v-for="item in protocols" :key="item" :index="item" :label="item"
                               :value="item"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="方法" prop="method">
                <el-select v-model="formData.method" placeholder="请选择">
                    <el-option v-for="item in methods" :key="item" :index="item" :label="item"
                               :value="item"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="域名/IP" prop="host">
                <el-input v-model="formData.host" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="端口" prop="port">
                <el-input v-model.number="formData.port" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="描述" prop="description">
                <el-input v-model="formData.description" auto-complete="off"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click.native="cancel">取消</el-button>
            <el-button type="primary" @click.native="addSubmit">提交</el-button>
        </div>
    </el-dialog>
</template>

<script>
    export default {
        props: {
            dialogOpenFlag: Boolean,
            submitButtonLoading: Boolean,
            projectNames: Array,
            formData: Object
        },
        data() {
            return {
                methods: ["GET", "POST", "DELETE", "PUT", "PATCH", "OPTION"],
                protocols: ["http", "https", "file"],
                formRules: {
                    name: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                        {max: 100, message: '长度 100 个字符', trigger: 'blur'}
                    ],
                    url: [
                        {required: true, message: '请输入URL', trigger: 'blur'},
                        {max: 255, message: '长度 255 个字符', trigger: 'blur'}
                    ],
                    //数字类型的验证，如果值以数字开头，验证规则失效
                    port: [{type: 'number', message: '端口必须为数字值', trigger: 'blur'}],
                    host: [
                        {required: true, message: '请输入域名/IP', trigger: 'blur'},
                        {max: 255, message: '长度 255 个字符', trigger: 'blur'}
                    ],
                    description: [{max: 255, message: '长度 255 个字符', trigger: 'blur'}],
                    projectModule: [{required: true, message: '请选择模块', type: 'array', trigger: 'blur'}]
                }
            }
        },
        methods: {
            cancel: function () {
                this.$emit('cancel');
            },
            addSubmit: function () {
                this.$refs.formData.validate((valid) => {
                    if (valid) {
                        this.formData.moduleId = this.formData.projectModule[1];
                        this.$emit('submit', Object.assign({}, this.formData));
                        this.$refs.formData.resetFields();
                    }
                });
            }
        }
    }

</script>
