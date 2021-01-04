<template>
    <el-drawer destroy-on-close ref="drawer" direction="rtl" size="20%" title="注册">
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
import axios from "axios";
import URL from "../../../../../js/constant/URL";
import Utils from "../../../../../js/utils/Utils";
import REG from "../../../../../js/constant/REG";

export default {
    name: "SignUpDrawer",
    data() {
        var usernameCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入用户名'))
            if (!REG.username.test(value))
                callback(new Error('用户名非法'))
            axios.get(URL.userCheckUsername + this.form.username).then(res => {
                if (res.data === 1006) {
                    callback(new Error('用户名已存在'))
                } else {
                    callback()
                }
            })
        }

        var passwordCheck = (rule, value, callback) => {
            if (!value) {
                callback(new Error('请输入密码'))
            }
            if (!REG.password.test(value)) {
                this.$notify({
                    title: '密码最少6位',
                    message: '最少1数字+1特殊字符+1大写+1小写',
                    type: 'warning',
                    position: 'top-left'
                })
                callback(new Error('密码过于简单'))
            }
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
    methods: {
        signUp() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    axios.post(URL.userSignUp, {
                        username: this.form.username,
                        password: Utils.sha3(this.form.password),
                        email: this.form.email,
                        code: this.form.code
                    }).then(res => {
                        if (res.data === 1003) {
                            this.$notify({
                                title: '注册成功',
                                message: '即将跳转到登录',
                                type: 'success',
                                position: 'top-left'
                            })
                            this.$emit('success')
                            this.$refs.drawer.handleClose()
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
        init() {
            this.getVerificationCode()
        },
    },
    emits: ['success']
}
</script>

<style scoped>

</style>