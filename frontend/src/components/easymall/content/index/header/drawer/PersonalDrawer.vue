<template>
    <el-drawer direction="rtl" size="20%" title="个人中心">
        <el-form ref="personal" :rules="rules" :model="user" status-icon style="margin-left: 2rem;margin-right: 2rem">
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
                <el-input v-model="user.password" :disabled="notModifying" clearable prefix-icon="el-icon-s-cooperation"
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
    </el-drawer>
</template>

<script>
import axios from "axios";
import URL from "../../../../../../js/constant/URL";
import Utils from "../../../../../../js/utils/Utils";
import REG from "../../../../../../js/constant/REG";

export default {
    name: "PersonalDrawer",
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
                    username: this.user.username,
                    nickname: this.user.nickname,
                    password: Utils.sha3(this.user.password),
                    email: this.user.email
                }, {
                    headers: {
                        userToken: localStorage.getItem('userToken')
                    }
                }).then(res => {
                    if (parseInt(res.data.code) === 100200) {
                        this.$message.success('修改成功')
                    } else if (parseInt(res.data.code) === 100400) {
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
            let u = JSON.parse(localStorage.getItem('user'))
            this.user.email = u.email
            this.user.nickname = u.nickname
            this.user.password = u.password
        }
    },
    emits: ['success']
}
</script>

<style scoped>

</style>