<template>
    <el-row justify="space-between" type="flex">
        <el-col :span="2">
            <el-image src="/logo/Logo.jpg" @click="toIndex" style="padding-top: 0.4rem"></el-image>
        </el-col>
        <el-col v-if="isUserSignIn" :span="6">
            <el-tooltip content="返回首页" effect="light" placement="bottom" style="margin-right: 1rem">
                <el-button circle plain type="primary" icon="el-icon-s-home" @click="toIndex"></el-button>
            </el-tooltip>
            <el-badge>
                <el-tooltip content="我的订单" effect="light" placement="bottom" style="margin-right: 1rem">
                    <el-button circle icon="el-icon-s-order" plain type="primary" @click="showOrdersDrawer"></el-button>
                </el-tooltip>
            </el-badge>
            <el-badge style="margin-right: 1rem" value="13">
                <el-tooltip content="购物车" effect="light" placement="bottom">
                    <el-button circle icon="el-icon-shopping-cart-full" plain
                               type="primary" @click="showShoppingCardDrawer"></el-button>
                </el-tooltip>
            </el-badge>
            <el-badge style="margin-right: 1rem" value="13">
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

    <ShoppingCardDrawer ref="shoppingCard" v-model="shoppingCardDrawer" @checkout="checkout"></ShoppingCardDrawer>
    <FavouriteDrawer ref="favourite" v-model="favouriteDrawer"></FavouriteDrawer>
    <OrdersIndexDrawer ref="orders" v-model="ordersDrawer"></OrdersIndexDrawer>
</template>

<script>
import router from "../../../../../js/router/router";
import ShoppingCardDrawer from "../../index/header/drawer/ShoppingCardDrawer.vue";
import FavouriteDrawer from "../../index/header/drawer/FavouriteDrawer.vue";
import OrdersIndexDrawer from "../../index/header/drawer/orders/OrdersIndexDrawer.vue";

export default {
    name: "UserTopBar",
    components: {OrdersIndexDrawer, FavouriteDrawer, ShoppingCardDrawer},
    data() {
        return {
            searchBar: '',
            signInDrawer: false,
            signUpDrawer: false,
            personalDrawer: false,
            shoppingCardDrawer: false,
            favouriteDrawer: false,
            ordersDrawer: false,
        }
    },
    computed: {
        isUserSignIn() {
            return this.$store.getters.isUserSignIn
        }
    },
    methods: {
        logout() {
            this.$store.commit('signOut')
            this.toIndex()
        },

        toIndex() {
            router.push({
                path: '/'
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
            this.ordersDrawer = true
            this.$refs.orders.init()
        },
        checkout() {
            this.showOrdersDrawer()
            this.shoppingCardDrawer = false
        },
        signUpSuccess() {
            this.signUpDrawer = false;
            this.showSignInDrawer()
        },
    },
}
</script>

<style scoped>
</style>