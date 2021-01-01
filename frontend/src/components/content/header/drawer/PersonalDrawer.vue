<template>
    <el-drawer direction="rtl" size="20%" title="个人中心">
        <el-form ref="personal" :model="user" status-icon style="margin-left: 2rem;margin-right: 2rem">
            <el-form-item label="用户名">
                <el-input :model-value="getUsername" clearable disabled prefix-icon="el-icon-s-custom">
                </el-input>
            </el-form-item>
            <el-form-item label="昵称">
                <el-input v-model="user.nickname" :disabled="notModifying" clearable
                          prefix-icon="el-icon-coin"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="user.email" :disabled="notModifying" clearable
                          prefix-icon="el-icon-message"></el-input>
            </el-form-item>
            <el-form-item label="密码">
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
import URL from "../../../../js/constant/URL";
import Utils from "../../../../js/utils/Utils";

export default {
    name: "PersonalDrawer",
    data() {
        return {
            user: {
                nickname: '',
                email: '',
                password: ''
            },
            notModifying: true,
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
        showDrawer() {
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
                    if(res[0].data === 1017 && res[1].data === 1015)
                    {
                        axios.all([axios.get(URL.userData+res[0].data),axios.get(URL.userData+res[1].data)])
                        .then(res=>{
                            this.user.email = res[0].data
                            this.user.nickname = res[0].data
                        })
                    }
                }
            })
            this.user.password = localStorage.getItem('password')
        }
    },
    emits: ['success']
}
</script>

<style scoped>

</style>