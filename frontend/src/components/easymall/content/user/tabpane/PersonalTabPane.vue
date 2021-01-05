<template>
    <el-row justify="center" type="flex">
        <el-col :span="10">
            <el-image v-if="!ifUpload" src="/default/User.png" @click="ifUpload = true"></el-image>
            <el-upload v-if="ifUpload" drag :action="uploadAvatar">
                <template #default>
                    <i class="el-icon-upload2 avatar-uploader-icon"></i>
                    <br>
                    请上传，支持拖拽
                </template>
            </el-upload>
            <el-button v-if="ifUpload" type="primary" @click="ifUpload = false">取消上传</el-button>
            <el-form ref="personal" :model="user" :rules="rules" status-icon
                     style="margin-left: 2rem;margin-right: 2rem">
                <el-form-item label="用户名">
                    <el-input :model-value="getUsername" clearable disabled prefix-icon="el-icon-s-custom">
                    </el-input>
                </el-form-item>
                <el-form-item label="昵称" prop="nickname">
                    <el-input v-model="user.nickname" :disabled="notModifying" clearable
                              prefix-icon="el-icon-coin"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="user.email" :disabled="notModifying" clearable
                              prefix-icon="el-icon-message"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="user.password" :disabled="notModifying" clearable
                              prefix-icon="el-icon-s-cooperation"
                              type="password"></el-input>
                </el-form-item>
            </el-form>
            <el-row justify="center" type="flex">
                <el-col>
                    <el-button v-if="notModifying" type="primary" @click="modify">
                        <i class="el-icon-edit"></i>
                        修改
                    </el-button>
                    <el-button v-else type="primary" @click="undoModify">
                        <i class="el-icon-back"></i>
                        取消修改
                    </el-button>
                    <el-button :disabled="notModifying" type="primary" @click="commit">
                        <i class="el-icon-top"></i>
                        提交
                    </el-button>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
</template>

<script>
import REG from "../../../../../js/constant/REG";
import axios from 'axios';
import URL from "../../../../../js/constant/URL";
import Utils from "../../../../../js/utils/Utils";

export default {
    name: "PersonalTabPane",
    data() {
        let nicknameCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入昵称'))
            callback()
        }

        let passwordCheck = (rule, value, callback) => {
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

        let emailCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入邮箱'))
            if (!REG.email.test(value))
                callback(new Error('请输入合法的邮箱'))
            callback()
        }

        return {
            user: {
                nickname: '',
                email: '',
                password: ''
            },
            notModifying: true,
            rules: {
                nickname: [{validator: nicknameCheck, trigger: 'blur'}],
                password: [{validator: passwordCheck, trigger: 'blur'}],
                email: [{validator: emailCheck, trigger: 'blur'}],
            },
            ifUpload: false,
        }
    },
    computed: {
        getUsername() {
            return localStorage.getItem('username')
        }
    },
    methods: {
        commit() {
            if (this.notModifying) {
                this.$message.warning('请确保修改了信息')
            } else {
                axios.put(URL.userUpdate, {
                    nickname: this.user.nickname,
                    password: Utils.sha3(this.user.password),
                    email: this.user.email
                }, {
                    headers: {
                        userToken: localStorage.getItem('userToken')
                    }
                }).then(res => {
                    if (res.data === 1012) {
                        this.$message.success('修改成功')
                    } else if (res.data === 1014) {
                        this.$message.error('请重新登录')
                    }
                    this.notModifying = true
                    this.$emit('success')
                })
            }
        },
        modify() {
            this.notModifying = false
        },
        undoModify() {
            this.notModifying = true
        },
        init() {
            axios.all([axios.get(URL.userGetEmail, {
                headers: {
                    userToken: localStorage.getItem('userToken')
                }
            }), axios.get(URL.userGetNickname, {
                headers: {
                    userToken: localStorage.getItem('userToken')
                }
            })]).then(res => {
                {
                    if (res[0].data === 1017 && res[1].data === 1015) {
                        axios.all([axios.get(URL.userData + res[0].data), axios.get(URL.userData + res[1].data)])
                            .then(res => {
                                console.log(res)
                                this.user.email = res[0].data
                                this.user.nickname = res[1].data
                            })
                    }
                }
            })
            this.user.password = localStorage.getItem('password')
        },
        uploadAvatar(){
            console.log('upload')
        }
    },
    emits: ['success']
}
</script>

<style scoped>
</style>