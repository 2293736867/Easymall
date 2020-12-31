<template>
    <el-drawer title="个人中心" v-model="showDrawer" direction="rtl" size="20%">
        <el-form ref="personal" :model="user" status-icon style="margin-left: 2rem;margin-right: 2rem">
            <el-form-item label="用户名">
                <el-input :model-value="getUsername" disabled clearable prefix-icon="el-icon-s-custom">
                </el-input>
            </el-form-item>
            <el-form-item label="昵称">
                <el-input v-model="user.nickname" :disabled="notModifying" clearable prefix-icon="el-icon-coin"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="user.email" :disabled="notModifying" clearable prefix-icon="el-icon-message"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="user.password" :disabled="notModifying" clearable type="password" prefix-icon="el-icon-s-cooperation"></el-input>
            </el-form-item>
        </el-form>
        <el-row type="flex" justify="center">
            <el-col>
                <el-button type="primary" @click="modify" v-if="notModifying">
                    <i class="el-icon-edit"></i>
                    修改
                </el-button>
                <el-button type="primary" @click="undoModify" v-else>
                    <i class="el-icon-back"></i>
                    取消修改
                </el-button>
                <el-button type="primary" @click="commit">
                    <i class="el-icon-top"></i>
                    提交
                </el-button>
            </el-col>
        </el-row>
    </el-drawer>

<!--    <el-form ref="form" :model="form" :rules="rules" status-icon style="margin-left: 2rem;margin-right: 2rem;">-->
<!--        <el-form-item label="用户名" prop="username">-->
<!--            <el-input v-model="form.username" clearable prefix-icon="el-icon-s-custom">-->
<!--            </el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="电子邮箱" prop="email">-->
<!--            <el-input v-model="form.email" clearable prefix-icon="el-icon-message"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="密码" prop="password">-->
<!--            <el-input v-model="form.password" clearable prefix-icon="el-icon-s-cooperation" show-password-->
<!--                      type="password"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="验证码" prop="code">-->
<!--            <br>-->
<!--            <el-row>-->
<!--                <el-col :span="10">-->
<!--                    <el-input v-model="form.code" clearable prefix-icon="el-icon-aim">-->
<!--                    </el-input>-->
<!--                </el-col>-->
<!--                <el-col :span="14">-->
<!--                    <el-image :src="verificationCodeImage" style="margin-top: 0.3rem"-->
<!--                              @click="getVerificationCode"></el-image>-->
<!--                </el-col>-->
<!--            </el-row>-->
<!--        </el-form-item>-->
<!--    </el-form>-->
</template>

<script>
import axios from "axios";
import URL from "../../../js/constant/URL";
import Utils from "../../../js/utils/Utils";

export default {
    name: "Personal",
    data(){
        return{
            showDrawer:true,
            user:{
                nickname:'',
                email:'',
                password:'',
            },
            notModifying: true,
        }
    },
    computed:{
        getUsername(){
            return localStorage.getItem('username')
        }
    },
    mounted(){
        axios.get('')
    },
    methods:{
        commit(){
            if(this.notModifying)
            {
                this.$message.warning('请确保修改了信息')
            }
            else
            {
                axios.put(URL.userUpdate,{
                    nickname:this.user.nickname,
                    password:Utils.sha3(this.user.password),
                    email:this.user.email
                },{
                    headers:{
                        token:localStorage.getItem('userToken')
                    }
                }).then(res=>{
                    console.log(res)
                    if(res.data === 1012)
                    {
                        this.$message.success('修改成功')
                    }
                    else if(res.data === 1014)
                    {
                        this.$message.error('请重新登录')
                    }
                    this.notModifying = true
                })
            }
        },
        modify(){
            this.notModifying = false
        },
        undoModify(){
            this.notModifying = true
        }
    },
}
</script>

<style scoped>

</style>