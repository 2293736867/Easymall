<template>
    <el-drawer direction="rtl" size="40%" title="用户登录">
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
</template>

<script>
import axios from "axios";
import URL from "../../../../../../js/constant/URL";
import Utils from "../../../../../../js/utils/Utils";

export default {
    name: "SignInDrawer",
    data() {
        let usernameCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入用户名'))
            callback()
        }

        let passwordCheck = (rule, value, callback) => {
            if (!value) {
                callback(new Error('请输入密码'))
            }
            callback()
        }

        let codeCheck = (rule, value, callback) => {
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
                code: '',
            }],
            verificationCodeImage:'',
            rules: {
                username: [{validator: usernameCheck, trigger: 'blur'}],
                password: [{validator: passwordCheck, trigger: 'blur'}],
                code: [{validator: codeCheck, trigger: 'blur'}]
            },
        }
    },
    methods: {
        signIn() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    axios.post(URL.userSignIn, {
                        username: this.form.username,
                        password: Utils.sha3(this.form.password),
                        code: this.form.code
                    }).then(res => {
                        if (parseInt(res.data.code) === 100100) {
                            this.$message.success('登录成功')
                            let user = {
                                username:this.form.username,
                                password:this.form.password,
                                email:res.data.data.email,
                                nickname:res.data.data.nickname,
                            }
                            this.$store.commit('signIn',{
                                user:user,
                                userToken:res.data.data.token
                            })
                            this.$emit('success')
                        } else if (parseInt(res.data.code) === 100101)
                            this.$message.error('登录失败，用户名或密码错误')
                        else if (parseInt(res.data.code) === 100102)
                            this.$message.error('验证码错误')
                    })
                } else {
                    this.$message.error('请输入合法信息再进行登录')
                }
            })
        },
        getVerificationCode() {
            const that = this
            axios.get(URL.code).then(res=>{
                that.verificationCodeImage = 'data:image/png;base64,' + res.data.data
            })
        },
        init(){
            this.getVerificationCode()
        }
    },
    emits:['success']
}
</script>

<style scoped>

</style>