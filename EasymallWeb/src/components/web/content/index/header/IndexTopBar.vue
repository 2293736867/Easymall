<template>
    <el-row justify="space-between" type="flex">
        <el-col :span="2">
            <el-image src="/logo/Logo.jpg" style="margin-top: 0.3rem" @click="toIndex"></el-image>
        </el-col>
        <el-col :offset="3" :span="7">
            <el-input v-model="searchBar" placeholder="请输入内容" suffix-icon="el-icon-search">
            </el-input>
        </el-col>
        <el-col v-if="!isUserSignIn" :span="4">
            <el-button icon="el-icon-user" type="primary" @click="signIn">
                登录
            </el-button>
            <el-button icon="el-icon-edit" type="primary" @click="signUp">
                注册
            </el-button>
        </el-col>
        <el-col v-if="isUserSignIn" :span="5">
            <el-tooltip content="管理面板" effect="light" placement="bottom" style="margin-right: 0.2rem">
                <el-button circle icon="el-icon-odometer" plain type="primary" @click="toAdmin"></el-button>
            </el-tooltip>
            <el-tooltip content="个人中心" effect="light" placement="bottom" style="margin-right: 1rem">
                <el-button circle icon="el-icon-user" plain type="primary" @click="toUser"></el-button>
            </el-tooltip>
            <el-badge style="margin-right: 1rem">
                <el-tooltip content="我的订单" effect="light" placement="bottom">
                    <el-button circle icon="el-icon-s-order" plain type="primary" @click="showOrders"></el-button>
                </el-tooltip>
            </el-badge>
            <el-badge :value="shoppingCardNum" style="margin-right: 1rem">
                <el-tooltip content="购物车" effect="light" placement="bottom">
                    <el-button circle icon="el-icon-shopping-cart-full" plain
                               type="primary" @click="showShoppingCard"></el-button>
                </el-tooltip>
            </el-badge>
            <el-badge :value="favouriteNum" style="margin-right: 1rem">
                <el-tooltip content="收藏夹" effect="light" placement="bottom">
                    <el-button circle icon="el-icon-star-off" plain type="primary"
                               @click="showFavourite"></el-button>
                </el-tooltip>
            </el-badge>
            <el-tooltip content="退出" effect="light" placement="bottom">
                <el-button circle icon="el-icon-arrow-right" plain type="primary" @click="logout"></el-button>
            </el-tooltip>
        </el-col>
    </el-row>

    <SignInDrawer v-model="showSignInDrawer" @success="signInSuccessCallback"></SignInDrawer>
    <SignUpDrawer v-model="showSignUpDrawer" @success="signUpSuccessCallback"></SignUpDrawer>
    <ShoppingCardDrawer ref="shoppingCardDrawer" v-model="showShoppingCardDrawer"></ShoppingCardDrawer>
    <FavouriteDrawer ref="favouriteDrawer" v-model="showFavouriteDrawer"></FavouriteDrawer>
    <OrdersIndexDrawer ref="ordersDrawer" v-model="showOrdersDrawer"></OrdersIndexDrawer>
</template>

<script>
import SignUpDrawer from "./drawer/SignUpDrawer.vue";
import SignInDrawer from "./drawer/SignInDrawer.vue";
import ShoppingCardDrawer from "./drawer/ShoppingCardDrawer.vue";
import FavouriteDrawer from "./drawer/FavouriteDrawer.vue";
import OrdersIndexDrawer from "./drawer/orders/OrdersIndexDrawer.vue";
import axios from "axios";
import URL from "../../../../../js/constant/URL";
import RouterUtils from "../../../../../js/utils/RouterUtils";
import {defineComponent, ref, computed} from 'vue'
import {useStore} from 'vuex'
import Utils from "../../../../../js/utils/Utils";

export default defineComponent({
    name: "IndexTopBar",
    components: {OrdersIndexDrawer, FavouriteDrawer, ShoppingCardDrawer, SignInDrawer, SignUpDrawer},
    setup() {
        //data
        const store = useStore()
        const searchBar = ref('')
        const showSignInDrawer = ref(false)
        const showSignUpDrawer = ref(false)
        const showShoppingCardDrawer = ref(false)
        const showOrdersDrawer = ref(false)
        const showFavouriteDrawer = ref(false)
        const favouriteNum = ref(0)

        const ordersDrawer = ref(null)
        const shoppingCardDrawer = ref(null)
        const favouriteDrawer = ref(null)

        //methods
        const isUserSignIn = computed(_ => {
            return store.getters.isUserSignIn
        })

        const shoppingCardNum = computed(_ => {
            return store.getters.shoppingCardNum
        })

        const logout = _ => {
            store.commit('signOut')
        }

        const toIndex = _ => {
            RouterUtils.toIndex()
        }

        const toAdmin = _ => {
            RouterUtils.toAdmin()
        }

        const toUser = _ => {
            RouterUtils.toUser()
        }

        const signIn = _ => {
            showSignInDrawer.value = true
        }

        const signUp = _ => {
            showSignUpDrawer.value = true
        }

        const showShoppingCard = _ => {
            shoppingCardDrawer.value.init()
            showShoppingCardDrawer.value = true
        }

        const showFavourite = _ => {
            favouriteDrawer.value.init()
            showFavouriteDrawer.value = true
        }

        const showOrders = _ => {
            ordersDrawer.value.init()
            showOrdersDrawer.value = true
        }

        const signUpSuccessCallback = _ => {
            signIn()
        }

        const signInSuccessCallback = _=>{
            axios.get(URL.shoppingCardGet, {
                headers: {
                    userToken: localStorage.getItem('userToken')
                }
            }).then(res => {
                if(Utils.responseCodeEquals(res,120100)){
                    store.commit('addShoppingCardNum', Utils.getResponseDataLength(res))
                }
            })
        }

        return {
            //data
            searchBar, showSignInDrawer, showSignUpDrawer, showShoppingCardDrawer, showOrdersDrawer, showFavouriteDrawer, favouriteNum, isUserSignIn, shoppingCardNum,

            //components
            ordersDrawer, shoppingCardDrawer,favouriteDrawer,

            //methods
            logout,toIndex,toAdmin,toUser,signIn,signUp,showOrders,showShoppingCard,showFavourite,signInSuccessCallback,signUpSuccessCallback,
        }
    },
})
</script>

<style scoped>
</style>