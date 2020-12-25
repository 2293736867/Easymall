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
                <el-input v-model="username" prefix-icon="el-icon-s-custom"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="password" prefix-icon="el-icon-s-cooperation" type="password"></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary">
            <i :class="signInUpButtonLeftIcon"></i>
            登录
            <i :class="signInUpButtonRightIcon"></i>
        </el-button>
    </el-drawer>

    <el-drawer v-model="signUpDrawerShow" direction="rtl" size="20%" title="注册">
        <el-form style="margin-left: 2rem;margin-right: 2rem;">
            <el-form-item label="用户名">
                <el-input v-model="username" prefix-icon="el-icon-s-custom">
                </el-input>
            </el-form-item>
            <el-form-item label="电子邮箱">
                <el-input v-model="email" prefix-icon="el-icon-message"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="password" prefix-icon="el-icon-s-cooperation" type="password"></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary">
            <i :class="signInUpButtonLeftIcon"></i>
            注册
            <i :class="signInUpButtonRightIcon"></i>
        </el-button>
    </el-drawer>
</template>

<script>

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
            signInUpButtonLeftIcon: 'el-icon-arrow-right',
            signInUpButtonRightIcon: 'el-icon-arrow-left'
        }
    },
    methods: {}
}
</script>

<style scoped>
</style>