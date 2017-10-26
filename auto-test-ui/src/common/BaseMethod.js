import util from './js/util'
import {
    ADD_BUTTON_LOADING,
    ADD_DIALOG_OPEN_FLAG,
    AFTER_ADD_SUBMIT,
    BEFORE_ADD_DIALOG_OPEN,
    COMMON_PARAMS,
    CUSTOMER_PARAMS,
    EDIT_BUTTON_LOADING,
    EDIT_DIALOG_OPEN_FLAG,
    LIST_LOADING,
    RESULT_DATA,
} from './constant'

export default {

    handleSizeChange: function (val) {
        this[COMMON_PARAMS].size = val;
        this.loadData();
    },
    handleCurrentChange: function (val) {
        this[COMMON_PARAMS].page = val;
        this.loadData();
    },
    loadData: function () {
        this[LIST_LOADING] = true;
        let cus = Object.assign({}, this[CUSTOMER_PARAMS]);
        let comm = Object.assign({}, this[COMMON_PARAMS]);
        let para = Object.assign(cus, comm);
        console.info('COMMON_PARAMS', this[COMMON_PARAMS]);
        console.info('CUSTOMER_PARAMS', this[CUSTOMER_PARAMS]);
        this.load(para).then((res) => {
            this[LIST_LOADING] = false;
            if (res.data && res.data.code === '0000') {
                this[RESULT_DATA] = res.data;
            }
            console.log("res.data", res.data);
        }, (err) => {
            console.error("error", err)
        }).catch((err) => {
            console.error("catch", err)
        })
    },
    handleSearch: function (kw) {
        this[COMMON_PARAMS].keyword = kw;
        this.loadData();
    },
    handleAdd: function () {
        this[ADD_DIALOG_OPEN_FLAG] = true;
        if (this[BEFORE_ADD_DIALOG_OPEN]) {
            this[BEFORE_ADD_DIALOG_OPEN]()
        }
    },


    addSubmit: function (params) {
        this[ADD_DIALOG_OPEN_FLAG] = false;
        this.loadData();
    },

    handleEdit: function (index, row) {
        this[EDIT_DIALOG_OPEN_FLAG] = true;
    },

    editSubmit: function (params) {
        this[EDIT_DIALOG_OPEN_FLAG] = false;
        this.loadData();
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
    addCancel() {
        this[ADD_DIALOG_OPEN_FLAG] = false;
    },
    editCancel() {
        this[EDIT_DIALOG_OPEN_FLAG] = false;
    },

    formatDate: function (row, column) {
        return util.formatDate.format(new Date(row[column.property]), 'yyyy-MM-dd hh:mm:ss')
    },
    [BEFORE_ADD_DIALOG_OPEN]: function () {
    },
    [AFTER_ADD_SUBMIT]: function () {
    }
}
