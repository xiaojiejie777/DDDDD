<template>
    <div>
  <el-tabs :tab-position="tabPosition">
    <el-tab-pane label="部门管理"><DepMana></DepMana></el-tab-pane>
    <el-tab-pane label="职位管理"><PosMana></PosMana></el-tab-pane>
    <el-tab-pane label="职称管理"><JobLevelMana></JobLevelMana></el-tab-pane>
    <el-tab-pane label="奖惩规则"><EcMana></EcMana></el-tab-pane>
    <el-tab-pane label="权限组"><PermissMana></PermissMana></el-tab-pane>
  </el-tabs>
    </div>
</template>

<script>
    import DepMana from '../../components/sys/basic/DepMana'
    import PosMana from '../../components/sys/basic/PosMana'
    import JobLevelMana from '../../components/sys/basic/JobLevelMana'
    import EcMana from '../../components/sys/basic/EcMana'
    import PermissMana from '../../components/sys/basic/PermissMana'

export default {
    name: "SysBasic",
    data(){
        return{
            tabPosition: 'top'
        }
        
    },
    components:{
        DepMana,
        PosMana,
        JobLevelMana,
        EcMana,
        PermissMana
    }
}
</script>

<style scoped>

</style>