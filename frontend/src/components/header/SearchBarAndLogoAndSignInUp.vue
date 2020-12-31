<template>
    <el-header>
        <el-row justify="space-between" type="flex">
            <el-col :span="2">
                <el-image src="/Logo.jpg" @click="toIndex"></el-image>
            </el-col>
            <el-col :span="8">
                <el-input v-model="searchBar" placeholder="请输入内容" suffix-icon="el-icon-search">
                </el-input>
            </el-col>
            <el-col :span="3">
                <el-button icon="el-icon-user" type="primary" @click="showSignInDrawer" v-if="!isUserSignIn">
                    登录
                </el-button>
                <el-button icon="el-icon-edit" type="primary" @click="showSignUpDrawer" v-if="!isUserSignIn">
                    注册
                </el-button>
                <el-button icon="el-icon-user" type="primary" v-if="isUserSignIn">
                    个人中心
                </el-button>
                <el-button icon="el-icon-edit" type="primary" @click="logout" v-if="isUserSignIn">
                    退出
                </el-button>
            </el-col>
        </el-row>
    </el-header>

    <el-drawer v-model="signInDrawerShow" destroy-on-close direction="rtl" size="20%" title="用户登录">
        <el-form ref="form" :model="form" :rules="rules" status-icon style="margin-left: 2rem;margin-right: 2rem;">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username" clearable prefix-icon="el-icon-s-custom"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="form.password" clearable prefix-icon="el-icon-s-cooperation" show-password
                          type="password"></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="code">
                <br>
                <el-row>
                    <el-col :span="10">
                        <el-input v-model="form.code" clearable prefix-icon="el-icon-aim">
                        </el-input>
                    </el-col>
                    <el-col :span="14">
                        <el-image :src="verificationCodeImage" style="margin-top: 0.3rem"
                                  @click="getVerificationCode"></el-image>
                    </el-col>
                </el-row>
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="signIn">
            <i class="el-icon-arrow-right"></i>
            登录
            <i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>

    <el-drawer v-model="signUpDrawerShow" direction="rtl" size="20%" title="注册">
        <el-form ref="form" :model="form" :rules="rules" status-icon style="margin-left: 2rem;margin-right: 2rem;">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username" clearable prefix-icon="el-icon-s-custom">
                </el-input>
            </el-form-item>
            <el-form-item label="电子邮箱" prop="email">
                <el-input v-model="form.email" clearable prefix-icon="el-icon-message"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="form.password" clearable prefix-icon="el-icon-s-cooperation" show-password
                          type="password"></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="code">
                <br>
                <el-row>
                    <el-col :span="10">
                        <el-input v-model="form.code" clearable prefix-icon="el-icon-aim">
                        </el-input>
                    </el-col>
                    <el-col :span="14">
                        <el-image :src="verificationCodeImage" style="margin-top: 0.3rem"
                                  @click="getVerificationCode"></el-image>
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
import URL from "../../js/constant/URL"
import REG from "../../js/constant/REG"
import Utils from "../../js/utils/Utils";
import router from "../../js/router/router";

export default {
    name: "SearchBarAndLogoAndSignInUp",
    data() {
        var usernameCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入用户名'))
            if (!REG.username.test(value))
                callback(new Error('用户名非法'))
            if (this.signUpDrawerShow) {
                axios.get(URL.checkUsername + this.form.username).then(res => {
                    if (res.data === 1006) {
                        callback(new Error('用户名已存在'))
                    } else {
                        callback()
                    }
                })
            } else
                callback()
        }

        var passwordCheck = (rule, value, callback) => {
            if (!value) {
                callback(new Error('请输入密码'))
            }
            if (this.signUpDrawerShow) {
                if (!REG.password.test(value)) {
                    this.$notify({
                        title: '密码最少6位',
                        message: '最少1数字+1特殊字符+1大写+1小写',
                        type: 'warning',
                        position: 'top-left'
                    })
                    callback(new Error('密码过于简单'))
                }
            } else
                callback()
        }

        var emailCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入邮箱'))
            if (!REG.email.test(value))
                callback(new Error('请输入合法的邮箱'))
            callback()
        }

        var codeCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入验证码'))
            if (value.length !== 4)
                callback(new Error('请输入4位验证码'))
            callback()
        }

        return {
            searchBar: '',
            signInDrawerShow: false,
            signUpDrawerShow: false,
            form: [{
                username: '',
                password: '',
                email: '',
                code: '',
            }],
            verificationCodeImage: '',
            rules: {
                username: [{validator: usernameCheck, trigger: 'blur'}],
                password: [{validator: passwordCheck, trigger: 'blur'}],
                email: [{validator: emailCheck, trigger: 'blur'}],
                code: [{validator: codeCheck, trigger: 'blur'}]
            },
        }
    },
    computed: {
        isUserSignIn(){
            return this.$store.getters.isUserSignIn
        }
    },
    methods: {
        showSignInDrawer() {
            this.getVerificationCode()
            this.signInDrawerShow = true
        },
        showSignUpDrawer() {
            this.getVerificationCode()
            this.signUpDrawerShow = true
        },
        signIn() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    axios.post(URL.signIn, {
                        username: this.form.username,
                        password: Utils.sha3(this.form.password),
                        code: this.form.code
                    }).then(res => {
                        if (res.data === 1000) {
                            this.$message.success('登录成功')
                            axios.get(URL.userData + res.data).then(res => {
                                this.$store.commit('signIn', {
                                    username:this.form.username,
                                    token:res.data
                                })
                            })
                            this.signInDrawerShow = false
                        } else if (res.data === 1001)
                            this.$message.error('登录失败，用户名或密码错误')
                        else if (res.data === 1002)
                            this.$message.error('验证码错误')
                    })
                } else {
                    this.$message.error('请输入合法信息再进行登录')
                }
            })
        },
        signUp() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    axios.post(URL.signUp, {
                        username: this.form.username,
                        password: Utils.sha3(this.form.password),
                        email: this.form.email,
                        code: this.form.code
                    }).then(res => {
                        if (res.data === 1003) {
                            this.$message.success('注册成功,转到登录页面')
                            this.signUpDrawerShow = false
                            this.signInDrawerShow = true
                        } else if (res.data === 100001) {
                            this.$message.error('验证码错误')
                        }
                    })
                } else {
                    this.$message.error('请输入合法信息再进行注册')
                }
            })
        },
        getVerificationCode() {
            const that = this
            axios.get(URL.code).then(function (res) {
                that.verificationCodeImage = 'data:image/png;base64,' + res.data
            })
        },
        logout(){
            this.$store.commit('signOut')
        },
        toIndex(){
            router.push({
                path:'/'
            })
        }
    }
}
</script>

<style scoped>
</style>