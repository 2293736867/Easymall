<template>
    <el-drawer direction="rtl" size="25%" title="用户编辑">
        <el-form ref="form" :model="form" :rules="rules" status-icon style="margin-left: 2.5rem;margin-right: 2.5rem;">
            <el-form-item label="Id" prop="id">
                <el-input disabled prefix-icon="el-icon-s-user" v-model="form.id"></el-input>
            </el-form-item>
            <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username" clearable prefix-icon="el-icon-s-custom">
                </el-input>
            </el-form-item>
            <el-form-item label="昵称" prop="nickname">
                <el-input v-model="form.email" clearable prefix-icon="el-icon-message"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱" prop="email">
                <el-input v-model="form.email" clearable prefix-icon="el-icon-message"></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="commit">
            <i class="el-icon-arrow-right"></i>
            修改
            <i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>
</template>

<script>
export default {
    name: "AdminUserModifyDrawer",
    data(){
        return{
            form:{
                id:'',
                username:'',
                nickname:'',
                email:'',
            },
            user:''
        }
    },
    methods:{
        init(user){
            this.form.id = user.id
            this.form.username = user.username
            this.form.nickname = user.nickname
            this.form.email = user.email
        },
        commit(){

        }
    }
}
</script>

<style scoped>

</style>