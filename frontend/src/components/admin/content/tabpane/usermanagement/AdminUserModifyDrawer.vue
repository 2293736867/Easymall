<template>
    <el-drawer ref="drawer" direction="rtl" size="25%" title="用户编辑">
        <el-form ref="form" :model="form" :rules="rules" status-icon style="margin-left: 2.5rem;margin-right: 2.5rem;">
            <el-form-item label="Id" prop="id">
                <el-input v-model="form.id" disabled prefix-icon="el-icon-s-flag"></el-input>
            </el-form-item>
            <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username" clearable prefix-icon="el-icon-s-custom">
                </el-input>
            </el-form-item>
            <el-form-item label="昵称" prop="nickname">
                <el-input v-model="form.nickname" clearable prefix-icon="el-icon-message"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱" prop="email">
                <el-input v-model="form.email" clearable prefix-icon="el-icon-chat-round"></el-input>
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
import REG from "../../../../../js/constant/REG";
import axios from "axios"
import URL from "../../../../../js/constant/URL";

export default {
    name: "AdminUserModifyDrawer",
    data() {
        let usernameCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入用户名'))
            if (!REG.username.test(value))
                callback(new Error('用户名非法'))
            callback()
        }

        let nicknameCheck = (rule, value, callback) => {
            if (!value) {
                callback(new Error('请输入昵称'))
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
            form: {
                id: '',
                username: '',
                nickname: '',
                email: '',
            },
            user: '',
            rules: {
                username: [{validator: usernameCheck, trigger: 'blur'}],
                nickname: [{validator: nicknameCheck, trigger: 'blur'}],
                email: [{validator: emailCheck, trigger: 'blur'}],
            },
            oldUsername:'',
        }
    },
    methods: {
        init(user) {
            this.form.id = user.id
            this.form.username = user.username
            this.form.nickname = user.nickname
            this.form.email = user.email
            this.oldUsername = user.username
        },
        commit() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    if(this.form.username !== this.oldUsername){
                        axios.get(URL.userCheckUsername + this.form.username).then(res => {
                            if (parseInt(res.data.code) === 100104) {
                                this.$message.warning('用户名已存在')
                            } else {
                                this.userUpdate()
                            }
                        })
                    }
                    else {
                        this.userUpdate()
                    }
                } else {
                    this.$message.warning('请输入合法信息')
                }
            })
        },
        userUpdate(){
            axios.put(URL.userUpdate, {
                id: this.form.id,
                username: this.form.username,
                nickname: this.form.nickname,
                email: this.form.email,
            }).then(res => {
                if (parseInt(res.data.code) === 100200) {
                    this.$message.success('修改成功')
                    this.$emit('success', this.form)
                    this.$refs.drawer.handleClose()
                } else {
                    this.$message.warning('未知原因修改失败')
                }
            })
        }
    },
    emits: ['success']
}
</script>

<style scoped>

</style>