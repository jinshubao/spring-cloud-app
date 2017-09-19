import util from './js/util'
import {
    RESULT_DATA,
    LIST_LOADING,
    ADD_DIALOG_OPEN_FLAG,
    BEFORE_ADD_DIALOG_OPEN,
    EDIT_DIALOG_OPEN_FLAG,
    ADD_BUTTON_LOADING,
    EDIT_BUTTON_LOADING,
    ADD_FORM_DATA,
    EDIT_FORM_DATA,
    QUERY_PARAMS,
    ADD_FORM_RULES,
    EDIT_FORM_RULES
} from './constant'

export default {

    handleSizeChange: function (val) {
        this[QUERY_PARAMS].size = val;
        this.loadData();
    },
    handleCurrentChange: function (val) {
        this[QUERY_PARAMS].page = val;
        this.loadData();
    },
    loadData: function () {
        this[LIST_LOADING] = true;
        this.load(this[QUERY_PARAMS]).then((res) => {
            this[RESULT_DATA] = res.data;
            this[LIST_LOADING] = false;
        })
    },
    handleSearch: function (kw) {
        this[QUERY_PARAMS].keyword = kw;
        this.loadData();
    },
    handleAdd: function () {
        this[ADD_DIALOG_OPEN_FLAG] = true;
        this[ADD_FORM_DATA] = {};
        if(this[BEFORE_ADD_DIALOG_OPEN]){
            this[BEFORE_ADD_DIALOG_OPEN]()
        }
    },

    addSubmit: function (params) {
        this[ADD_BUTTON_LOADING] = true;
        let para = Object.assign({}, params);
        console.log(params)
        this.add(para).then((res) => {
            this[ADD_BUTTON_LOADING] = false;
            this.$message({
                message: '提交成功',
                type: 'success'
            });
            this[ADD_DIALOG_OPEN_FLAG] = false;
            this.loadData();
        },(err) => {
            this[ADD_BUTTON_LOADING] = false;
            console.log(err) 
        });
    },

    handleEdit: function (index, row) {
        this[EDIT_DIALOG_OPEN_FLAG] = true;
        this[EDIT_FORM_DATA] = Object.assign({}, row);
    },

    editSubmit: function () {
        this.$refs[EDIT_FORM_DATA].validate((valid) => {
            if (valid) {
                this[EDIT_BUTTON_LOADING] = true;
                let para = Object.assign({}, this[EDIT_FORM_DATA]);
                this.modify(para).then((res) => {
                    this[EDIT_BUTTON_LOADING] = false;
                    this.$message({
                        message: '提交成功',
                        type: 'success'
                    });
                    this.$refs[EDIT_FORM_DATA].resetFields();
                    this[EDIT_DIALOG_OPEN_FLAG] = false;
                    this.loadData();
                });
            }
        });
    },
    handleDel: function (index, row) {
        this.$confirm(`确认删除 ${row['name']} 吗?`, '提示', {
            type: 'warning'
        }).then(() => {
            let para = {id: row.id};
            this.remove(para).then((res) => {
                this.$message({
                    message: '删除成功',
                    type: 'success'
                });
                this.loadData();
            });
        }).catch((err) => {

        });
    },
    formatDate: function (row, column) {
        return util.formatDate.format(new Date(row[column.property]), 'yyyy-MM-dd hh:mm:ss')
    }
}
