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
            <el-col :span="8">
                <el-button icon="el-icon-user" type="primary" plain @click="showSignInDrawer" v-if="!isUserSignIn">
                    登录
                </el-button>
                <el-button icon="el-icon-edit" type="primary" plain @click="showSignUpDrawer" v-if="!isUserSignIn">
                    注册
                </el-button>
                <el-badge value="13" style="margin-right: 1rem" v-if="isUserSignIn">
                    <el-button icon="el-icon-shopping-card-1" type="primary" plain @click="showShoppingCardDrawer">购物车</el-button>
                </el-badge>
                <el-badge value="13" style="margin-right: 1rem" v-if="isUserSignIn">
                    <el-button icon="el-icon-shopping-card-1" type="primary" plain @click="showFavouriteDrawer">收藏</el-button>
                </el-badge>
                <el-button icon="el-icon-user" type="primary" plain @click="showPersonalDrawer" v-if="isUserSignIn">
                    个人中心
                </el-button>
                <el-button icon="el-icon-edit" type="primary" plain @click="logout" v-if="isUserSignIn">
                    退出
                </el-button>
            </el-col>
        </el-row>
    </el-header>

    <SignInDrawer v-model="signInDrawer" ref="signIn" @success="signInDrawer = false"></SignInDrawer>
    <SignUpDrawer v-model="signUpDrawer" ref="signUp" @success="signUpSuccess"></SignUpDrawer>
    <PersonalDrawer v-model="personalDrawer" ref="personal" @success="personalDrawer = false"></PersonalDrawer>
    <ShoppingCardDrawer v-model="shoppingCardDrawer" ref="shoppingCard"></ShoppingCardDrawer>
    <FavouriteDrawer v-model="favouriteDrawer" ref="favourite"></FavouriteDrawer>
</template>

<script>
import router from "../../../../js/router/router";
import SignUpDrawer from "../drawer/SignUpDrawer.vue";
import SignInDrawer from "../drawer/SignInDrawer.vue";
import PersonalDrawer from "../drawer/PersonalDrawer.vue";
import ShoppingCardDrawer from "../drawer/ShoppingCardDrawer.vue";
import FavouriteDrawer from "../drawer/FavouriteDrawer.vue";

export default {
    name: "TopBar",
    components: {FavouriteDrawer, ShoppingCardDrawer, PersonalDrawer, SignInDrawer, SignUpDrawer},
    data() {
        return {
            searchBar: '',
            signInDrawer: false,
            signUpDrawer: false,
            personalDrawer: false,
            shoppingCardDrawer:false,
            favouriteDrawer:false,
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
        showShoppingCardDrawer(){
            this.$refs.shoppingCard.showDrawer()
            this.shoppingCardDrawer = true
        },
        showFavouriteDrawer(){
            this.$refs.favourite.showDrawer()
            this.favouriteDrawer = true
        },
        signUpSuccess(){
            this.signUpDrawer = false;
            this.showSignInDrawer()
        },
    },
}
</script>

<style scoped>
</style>