<template>
    <el-row class="toolbar">
        <el-col :span="8">
            <el-input v-model="keyword" placeholder="关键字">
                <el-button slot="append" icon="search" @click="searchByKeyword(keyword)"></el-button>
            </el-input>
        </el-col>
        <el-col :span="16">
            <slot></slot>
        </el-col>
    </el-row>
</template>
<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    export default {
        components: {
            ElCol,
            ElRow
        },
        data() {
            return {
                keyword: ''
            }
        },
        methods: {
            searchByKeyword(keyword){
                this.$emit('search', keyword)
            }
        },
        mounted() {
        }
    }
</script>
<style scoped>
    .toolbar {
        padding-top: 20px;
        padding-bottom: 20px;
    }

    .toolbar .el-col{
        padding-right: 20px;
    }

</style>