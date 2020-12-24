<template>
    <div>
        <b-container fluid="">
            <b-row>
                <b-col cols="1">
                    <b-img src="../assets/logo.jpg"></b-img>
                </b-col>
                <b-col cols="4" offset="3">
                    <b-input-group size="sm">
                        <b-form-input></b-form-input>
                        <b-input-group-append is-text>
                            <b-icon icon="search"></b-icon>
                        </b-input-group-append>
                    </b-input-group>
                </b-col>
                <b-col offset="2" cols="2">
                    <b-button variant="primary" class="float-right" v-b-modal.signUpModal>注册</b-button>
                    <b-button variant="primary" class="float-right mr-2" v-b-modal.signInModal>{{signInButtonText}}</b-button>
                </b-col>
            </b-row>
            <br>
            <b-row>
                <b-col>
                    <b-navbar type="dark" variant="success">
                        <b-col><b-navbar-brand href="/">首页</b-navbar-brand></b-col>
                        <b-col><b-navbar-brand href="/product">全部商品</b-navbar-brand></b-col>
                        <b-col><b-navbar-brand href="#">手机数码</b-navbar-brand></b-col>
                        <b-col><b-navbar-brand href="#">电脑平板</b-navbar-brand></b-col>
                        <b-col><b-navbar-brand href="#">家用电器</b-navbar-brand></b-col>
                        <b-col><b-navbar-brand href="#">汽车用品</b-navbar-brand></b-col>
                        <b-col><b-navbar-brand href="#">食品饮料</b-navbar-brand></b-col>
                        <b-col><b-navbar-brand href="#">图书杂志</b-navbar-brand></b-col>
                        <b-col><b-navbar-brand href="#">服装服饰</b-navbar-brand></b-col>
                        <b-col><b-navbar-brand href="#">理财产品</b-navbar-brand></b-col>
                    </b-navbar>
                </b-col>
            </b-row>
        </b-container>
        <SignUpModal></SignUpModal>
        <SignInModal></SignInModal>
    </div>
</template>

<script>
import router from "@/js/router";
import SignUpModal from "@/components/modal/SignUpModal";
import SignInModal from "@/components/modal/SignInModal";

export default {
    name: "Header",
    components: {SignInModal, SignUpModal},
    data() {
        return {
            signInButtonText:'登录',
        }
    },
    methods:{
        toIndex(){
            router.push({
                path:'/'
            })
        },
        toAllProduct(){
            router.push({
                path:'/product'
            })
        },
        toPhoneAndDigital(){
            router.push({
                path:'/'
            })
        },
        toComputerAndPad(){
            router.push({
                path:'/'
            })
        },
        toFoodAndDrinks(){
            router.push({
                path:'/'
            })
        },
        toBookAndMagazine(){
            router.push({
                path:'/'
            })
        },
        toCl(){
            router.push({
                path:'/'
            })
        },
        to(){
            router.push({
                path:'/'
            })
        },
    }
}
</script>

<style scoped>

</style>