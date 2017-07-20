<template>
    <el-card class="box-card">
        <span style="line-height: 36px;">{{title}}</span>
        <el-table :data="properties"
                  :stripe="true"
                  :show-header="false"
                  @cell-mouse-enter="cellMouseEnter"
                  @cell-mouse-leave="cellMouseLeave"
                  highlight-current-row
                  empty-text="暂无数据"
                  border row-key="name">
            <el-table-column
                    prop="name" width="300">
            </el-table-column>
            <el-table-column
                    prop="value">
            </el-table-column>
            <el-table-column width="200">
                <template scope="scope">
                    <el-button v-show="mouseEnterRow == scope.row"
                               size="small"
                               @click="handleEdit(scope.row)">编辑
                    </el-button>
                    <el-button v-show="mouseEnterRow == scope.row"
                               size="small"
                               type="danger"
                               @click="handleDelete(scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-card>
</template>
<script>
    export default {
        props: ['title', 'properties'],
        data() {
            return {
                mouseEnterRow: {}
            }
        },
        methods: {
            cellMouseEnter: function (row, column, cell, event) {
                this.mouseEnterRow = row;
            },
            cellMouseLeave: function (row, column, cell, event) {
                this.mouseEnterRow = {};
            },
            handleEdit: function (row) {
                this.$emit('editProperty', {title: this.title, name: row.name, value: row.value})
            },
            handleDelete: function (row) {
                this.$emit('deleteProperty', {title: this.title, name: row.name, value: row.value})
            }
        },
        mounted() {

        }
    }
</script>
