<template>
    <el-header>
        <el-row justify="space-between" type="flex">
            <el-col :span="2">
                <el-image src="/logo/Logo.jpg" @click="toIndex"></el-image>
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
                <el-button icon="el-icon-user" type="primary" @click="showPersonalDrawer" v-if="isUserSignIn">
                    个人中心
                </el-button>
                <el-button icon="el-icon-edit" type="primary" @click="logout" v-if="isUserSignIn">
                    退出
                </el-button>
            </el-col>
        </el-row>
    </el-header>

    <SignInDrawer v-model="signInDrawer" ref="signIn" @success="signInDrawer = false"></SignInDrawer>
    <SignUpDrawer v-model="signUpDrawer" ref="signUp" @success="signUpSuccess"></SignUpDrawer>
    <PersonalDrawer v-model="personalDrawer" ref="personal" @success="personalDrawer = false"></PersonalDrawer>
</template>

<script>
import router from "../../../../js/router/router";
import SignUpDrawer from "../drawer/SignUpDrawer.vue";
import SignInDrawer from "../drawer/SignInDrawer.vue";
import PersonalDrawer from "../drawer/PersonalDrawer.vue";

export default {
    name: "TopBar",
    components: {PersonalDrawer, SignInDrawer, SignUpDrawer},
    data() {
        return {
            searchBar: '',
            signInDrawer: false,
            signUpDrawer: false,
            personalDrawer: false,
        }
    },
    computed: {
        isUserSignIn(){
            return this.$store.getters.isUserSignIn
        }
    },
    methods: {
        logout(){
            this.$store.commit('signOut')
        },
        toIndex(){
            router.push({
                path:'/'
            })
        },
        showSignInDrawer(){
            this.$refs.signIn.showDrawer()
            this.signInDrawer = true
        },
        showSignUpDrawer(){
            this.$refs.signUp.showDrawer()
            this.signUpDrawer = true
        },
        showPersonalDrawer(){
            this.$refs.personal.showDrawer()
            this.personalDrawer = true
        },
        signUpSuccess(){
            this.signUpDrawer = false;
            this.showSignInDrawer()
        }
    },
}
</script>

<style scoped>
</style>