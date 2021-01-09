<template>
    <el-row justify="space-between" type="flex">
        <el-col :span="2">
            <el-image src="/logo/Logo.jpg" @click="toIndex" style="margin-top: 0.3rem"></el-image>
        </el-col>
        <el-col :offset="3" :span="7">
            <el-input v-model="searchBar" placeholder="请输入内容" suffix-icon="el-icon-search">
            </el-input>
        </el-col>
        <el-col v-if="!isUserSignIn" :span="4">
            <el-button icon="el-icon-user" type="primary" @click="showSignInDrawer">
                登录
            </el-button>
            <el-button icon="el-icon-edit" type="primary" @click="showSignUpDrawer">
                注册
            </el-button>
        </el-col>
        <el-col v-if="isUserSignIn" :span="5">
            <el-tooltip content="管理面板" effect="light" placement="bottom" style="margin-right: 0.2rem">
                <el-button circle plain type="primary" icon="el-icon-odometer" @click="toAdmin"></el-button>
            </el-tooltip>
            <el-tooltip content="个人中心" effect="light" placement="bottom" style="margin-right: 1rem">
                <el-button circle icon="el-icon-user" plain type="primary" @click="toPersonal"></el-button>
            </el-tooltip>
            <el-badge style="margin-right: 1rem">
                <el-tooltip content="我的订单" effect="light" placement="bottom">
                    <el-button circle icon="el-icon-s-order" plain type="primary"></el-button>
                </el-tooltip>
            </el-badge>
            <el-badge style="margin-right: 1rem" :value="shoppingCardNum">
                <el-tooltip content="购物车" effect="light" placement="bottom">
                    <el-button circle icon="el-icon-shopping-cart-full" plain
                               type="primary" @click="showShoppingCardDrawer"></el-button>
                </el-tooltip>
            </el-badge>
            <el-badge style="margin-right: 1rem" :value="favouriteNum">
                <el-tooltip content="收藏夹" effect="light" placement="bottom">
                    <el-button circle icon="el-icon-star-off" plain type="primary"
                               @click="showFavouriteDrawer"></el-button>
                </el-tooltip>
            </el-badge>
            <el-tooltip content="退出" effect="light" placement="bottom">
                <el-button circle icon="el-icon-arrow-right" plain type="primary" @click="logout"></el-button>
            </el-tooltip>
        </el-col>
    </el-row>

    <SignInDrawer ref="signIn" v-model="signInDrawer" @success="signInSuccess"></SignInDrawer>
    <SignUpDrawer ref="signUp" v-model="signUpDrawer" @success="signUpSuccess"></SignUpDrawer>
    <PersonalDrawer ref="personal" v-model="personalDrawer" @success="personalDrawer = false"></PersonalDrawer>
    <ShoppingCardDrawer ref="shoppingCard" v-model="shoppingCardDrawer" @checkout="checkout"></ShoppingCardDrawer>
    <FavouriteDrawer ref="favourite" v-model="favouriteDrawer"></FavouriteDrawer>
    <OrdersIndexDrawer ref="orders" v-model="ordersDrawer"></OrdersIndexDrawer>
</template>

<script>
import router from "../../../../../js/router/router";
import SignUpDrawer from "./drawer/SignUpDrawer.vue";
import SignInDrawer from "./drawer/SignInDrawer.vue";
import PersonalDrawer from "./drawer/PersonalDrawer.vue";
import ShoppingCardDrawer from "./drawer/ShoppingCardDrawer.vue";
import FavouriteDrawer from "./drawer/FavouriteDrawer.vue";
import OrdersIndexDrawer from "./drawer/orders/OrdersIndexDrawer.vue";
import axios from "axios";
import URL from "../../../../../js/constant/URL";

export default {
    name: "IndexTopBar",
    components: {OrdersIndexDrawer, FavouriteDrawer, ShoppingCardDrawer, PersonalDrawer, SignInDrawer, SignUpDrawer},
    data() {
        return {
            searchBar: '',
            signInDrawer: false,
            signUpDrawer: false,
            personalDrawer: false,
            shoppingCardDrawer: false,
            favouriteDrawer: false,
            ordersDrawer: false,
            favouriteNum:0,
        }
    },
    computed: {
        isUserSignIn() {
            return this.$store.getters.isUserSignIn
        },
        shoppingCardNum(){
            return this.$store.getters.shoppingCardNum
        }
    },
    methods: {
        logout() {
            this.$store.commit('signOut')
        },
        toIndex() {
            router.push({
                path: '/'
            })
        },
        toAdmin(){
            router.push({
                path:'/admin'
            })
        },
        showSignInDrawer() {
            this.$refs.signIn.init()
            this.signInDrawer = true
        },
        showSignUpDrawer() {
            this.$refs.signUp.init()
            this.signUpDrawer = true
        },
        showPersonalDrawer() {
            this.$refs.personal.init()
            this.personalDrawer = true
        },
        showShoppingCardDrawer() {
            this.$refs.shoppingCard.init()
            this.shoppingCardDrawer = true
        },
        showFavouriteDrawer() {
            this.$refs.favourite.init()
            this.favouriteDrawer = true
        },
        showOrdersDrawer() {
            this.$refs.orders.init()
            this.ordersDrawer = true
        },
        checkout() {
            this.showOrdersDrawer()
            this.shoppingCardDrawer = false
        },
        signUpSuccess() {
            this.signUpDrawer = false;
            this.showSignInDrawer()
        },
        signInSuccess(){
            this.signInDrawer = false
            axios.get(URL.shoppingCardGet,{
                headers:{
                    userToken:localStorage.getItem('userToken')
                }
            }).then(res=>{
                if(parseInt(res.data.code) === 120100) {
                    this.$store.commit('addShoppingCardNum',res.data.data.length)
                }
            })
        },
        toPersonal() {
            router.push('/user')
        }
    },
}
</script>

<style scoped>
</style>