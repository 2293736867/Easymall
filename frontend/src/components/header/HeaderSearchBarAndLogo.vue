<template>
    <el-header>
        <el-row justify="space-between" type="flex">
            <el-col :md="3" :xs="2">
                <el-image src="logo.jpg"></el-image>
            </el-col>
            <el-col :md="10" :xs="6">
                <el-input v-model="searchBar" placeholder="请输入内容" suffix-icon="el-icon-search">
                </el-input>
            </el-col>
            <el-col :md="3" :xs="2">
                <el-button icon="el-icon-user" type="primary" @click="signInDrawerShow = true">
                    登录
                </el-button>
                <el-button icon="el-icon-edit" type="primary" @click="signUpDrawerShow = true">
                    注册
                </el-button>
            </el-col>
        </el-row>
    </el-header>

    <el-drawer v-model="signInDrawerShow" destroy-on-close direction="rtl" size="20%" title="用户登录">
        <el-form style="margin-left: 2rem;margin-right: 2rem;">
            <el-form-item label="用户名">
                <el-input clearable v-model="username" prefix-icon="el-icon-s-custom"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input clearable show-password v-model="password" prefix-icon="el-icon-s-cooperation" type="password"></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="">
            <i class="el-icon-arrow-right"></i>
            登录
            <i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>

    <el-drawer v-model="signUpDrawerShow" direction="rtl" size="20%" title="注册">
        <el-form style="margin-left: 2rem;margin-right: 2rem;">
            <el-form-item label="用户名">
                <el-input clearable v-model="username" prefix-icon="el-icon-s-custom">
                </el-input>
            </el-form-item>
            <el-form-item label="电子邮箱">
                <el-input clearable v-model="email" prefix-icon="el-icon-message"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input clearable show-password v-model="password" prefix-icon="el-icon-s-cooperation" type="password"></el-input>
            </el-form-item>
            <el-form-item label="验证码">
                <br>
                <el-row>
                    <el-col :span="10">
                        <el-input clearable v-model="code" prefix-icon="el-icon-aim">
                        </el-input>
                    </el-col>
                    <el-col :span="14">
                        <el-image style="margin-top: 0.3rem" :src="verificationCodeImage" @click="getVerificationCode"></el-image>
                    </el-col>
                </el-row>
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="signUp">
            <i class="el-icon-arrow-right"></i>
            注册
            <i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>
</template>

<script>

import axios from "axios"
import URL from "../../store/gloabl"
import ElMessage from 'element-plus'

export default {
    name: "HeaderSearchBarAndLogo",
    data() {
        return {
            searchBar: '',
            signInDrawerShow: false,
            signUpDrawerShow: false,
            username: '',
            password: '',
            email: '',
            code:'',
            verificationCodeImage:'',
        }
    },
    mounted(){
        this.getVerificationCode()
    },
    methods: {
        signIn(){
            axios.post()
        },
        getVerificationCode(){
            const that = this
            axios.get(URL.code).then(function (res) {
                that.verificationCodeImage = 'data:image/png;base64,' + res.data
            })
        },
        signUp() {
            if(this.checkUsername() && this.checkPassword() && this.checkCode() && this.checkEmail())
            {
                var result = true
                axios.get(URL.checkUsername+this.username).then(res=>{
                    if(res.data === 3001) {
                        ElMessage.error({
                            message:'错误，用户名已存在',
                            type:'error'
                        })
                        result = false
                    }
                })
                if(result)
                {
                    axios.post(URL.signUp, {
                        username: this.username,
                        password: this.password,
                        email:this.email,
                        code: this.code
                    }).then(res => {
                        if (res.data === 1003)
                        {
                            ElMessage.success({
                                message:'注册成功',
                                type:'success'
                            })
                        }
                        else if (res.data === 1004)
                        {
                            ElMessage.error({
                                message:'验证码错误',
                                type:'error'
                            })
                        }
                    })
                }
            }
        },
        checkUsername() {
            return this.checkIsNotEmpty(this.username,'用户名为空')
        },
        checkPassword() {
            return this.checkIsNotEmpty(this.password,'密码为空')
        },
        checkCode() {
            return this.checkIsNotEmpty(this.code,'验证码为空')
        },
        checkEmail() {
            return this.checkIsNotEmpty(this.email,'邮箱为空')
        },
        checkIsNotEmpty(str,message){
            if(!str)
            {
                ElMessage.error('ccc')
                // ElMessage.error({
                //     message:message,
                //     type:'error'
                // })
                return false
            }
        }
    }
}
</script>

<style scoped>
</style>