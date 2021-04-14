<template>
    <div>
        <div>
            <el-input v-model="jl.name" placeholder="添加职称等级" prefix-icon="el-icon-plus" style="width: 300px"></el-input>
              <el-select v-model="jl.titleLevel" placeholder="职称等级" style="margin-left: 5px; margin-right: 5px">
                <el-option
                    v-for="item in titleLevels"
                    :key="item"
                    :label="item"
                    :value="item">
                </el-option>
            </el-select>
            <el-button type="primary" icon="el-icon-plus" @click="addJobLevel">添加</el-button>
        </div>
        <div style="margin-top: 10px">
            <el-table
                    :data="jls"
                    stripe
                    border
                    @selection-change="handleSelectionChange"
                    style="width: 70%">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="id"
                        label="编号"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="职称名称"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="titleLevel"
                        label="职称等级"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="createDate"
                        label="创建日期"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="enabled"
                        label="是否启用"
                        width="150">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.enabled" type="success">已启用</el-tag>
                            <el-tag v-else type="danger">未启用</el-tag>
                        </template>
                </el-table-column>
                <el-table-column
                        label="操作">
                        <template slot-scope="scope">
                            <el-button size="small" @click="showEditView(scope.row)">编辑</el-button>
                            <el-button size="small" type="danger" @click="deleteHandler(scope.row)">删除</el-button>
                        </template>
                </el-table-column>
            </el-table>
            <el-button size="small" style="margin-top: 10px" type="danger" :disabled="this.multipleSelection.length==0"
                            @click="deleteMany">批量删除</el-button>
        </div>
        <el-dialog
                title="编辑职称"
                :visible.sync="dialogVisible"
                width="30%">
                <table>
                    <tr>
                        <td>
                            <el-tag>职称名称</el-tag>
                        </td>
                        <td>
                            <el-input v-model="updateJl.name" size="small" style="margin-left: 5px"></el-input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <el-tag>职称等级</el-tag>
                        </td> 
                        <td>
                            <el-select size="small" v-model="updateJl.titleLevel" placeholder="职称等级" 
                                        style="margin-left: 5px">
                                <el-option
                                        v-for="item in titleLevels"
                                        :key="item"
                                        :label="item"
                                        :value="item">
                                </el-option>
                            </el-select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <el-tag>是否启用</el-tag>
                        </td>
                        <td>
                            <el-switch style="margin-left: 5px"
                                v-model="updateJl.enabled"
                                active-color="#13ce66">
                            </el-switch>
                        </td>
                    </tr>
                </table>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="doUpdate">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: "JobLevelMana",
    data(){
        return{
            jl:{
                name:'',
                titleLevel:''
            },
            updateJl:{
                name:'',
                titleLevel:'',
                enabled: false
            },
            titleLevels:[
                '正高级',
                '副高级',
                '中级',
                '初级',
                '员级'
            ],
            jls:[],
            dialogVisible: false,
            multipleSelection: []
        }
    },
    mounted(){
        this.initJls()
    },
    methods:{
        deleteMany(){
            this.$confirm('是否删除['+this.multipleSelection.length+']条职称', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    let ids = '?'
                    this.multipleSelection.forEach(item =>{
                        ids += 'ids=' + item.id + '&'
                    })
                    this.deleteRequest('/system/basic/job-level/'+ids).then(resp =>{
                        if(resp){
                            this.initJls()
                        }
                    })
                }).catch(() =>{

                })
        },
        handleSelectionChange(val){
            this.multipleSelection = val
        },
        doUpdate(){
            this.putRequest('/system/basic/job-level/',this.updateJl).then(resp =>{
                if(resp){
                    this.initJls()
                    this.dialogVisible = false
                }
            })
        },
        showEditView(data){
            Object.assign(this.updateJl, data)
            this.updateJl.createDate = ''
            this.dialogVisible = true
        },
        deleteHandler(data){
            this.$confirm('是否删除['+data.name+']职称', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    this.deleteRequest('/system/basic/job-level/'+data.id).then(resp =>{
                        if(resp){
                            this.initJls()
                        }
                    })
                }).catch(() =>{

                })
        },
        addJobLevel(){
            if(this.jl.name && this.jl.titleLevel){
                this.postRequest('/system/basic/job-level/',this.jl).then(resp=>{
                    if(resp){
                        this.initJls()
                    }
                })
            }else{
                this.$message.error('字段不能为空')
            }
        },
        initJls(){
            this.getRequest('/system/basic/job-level/').then(resp =>{
                if(resp){
                    this.jls = resp
                    this.jl.name = ''
                    this.jl.titleLevel = ''
                }
            })
        }
    }
}
</script>

<style scoped>

</style>