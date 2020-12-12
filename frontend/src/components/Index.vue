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
                    <b-button variant="primary" class="float-right" id="signIn" @click="signInButtonClick">{{ signInButtonText }}</b-button>
                    <b-button variant="primary" class="float-right mr-2" v-if="showSignUpButton" id="signUp" @click="signUpModal = !signUpModal">注册</b-button>
                </b-col>
            </b-row>
            <br>
            <b-row>
                <b-col>
                    <b-navbar type="dark" variant="success">
                        <b-col><b-navbar-brand href="#">首页</b-navbar-brand></b-col>
                        <b-col><b-navbar-brand href="#">全部商品</b-navbar-brand></b-col>
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

            <b-modal v-model="signInSuccess" @ok="signInSuccessHandle">
                登录成功
            </b-modal>
            <b-modal v-model="signInFailedCodeError" @ok="signInModal = true">
                登录失败，验证码错误
            </b-modal>
            <b-modal v-model="signInFailedUsernameOrPasswordIncorrect" @ok="signInModal = true">
                登录失败，用户名或密码错误
            </b-modal>
            <b-modal v-model="signUpSuccess">
                注册成功
            </b-modal>
            <b-modal v-model="usernameExists" @ok="signUpModal = true">
                用户名已存在，请选择其他用户名
            </b-modal>
            <b-modal v-model="signUpFailedCodeError" @ok="signUpModal = true">
                注册失败，验证码错误
            </b-modal>
            <b-modal v-model="signInUsernameEmpty" @ok="signInModal = true">请输入用户名</b-modal>
            <b-modal v-model="signInPasswordEmpty" @ok="signInModal = true">请输入密码</b-modal>
            <b-modal v-model="signInCodeEmpty" @ok="signInModal = true">请输入验证码</b-modal>
            <b-modal v-model="signUpUsernameEmpty" @ok="signUpModal = true">请输入用户名</b-modal>
            <b-modal v-model="signUpPasswordEmpty" @ok="signUpModal = true">请输入密码</b-modal>
            <b-modal v-model="signUpNicknameEmpty" @ok="signUpModal = true">请输入昵称</b-modal>
            <b-modal v-model="signUpEmailEmpty" @ok="signUpModal = true">请输入邮箱</b-modal>
            <b-modal v-model="signUpCodeEmpty" @ok="signUpModal = true">请输入验证码</b-modal>
        </b-container>
        <b-row>
            <b-col cols="8" offset="2">
                <b-carousel controls indicators>
                    <b-carousel-slide caption="111" text="222" img-src="../assets/banner.jpg"></b-carousel-slide>
                </b-carousel>
            </b-col>
        </b-row>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: "Index.vue",
    props:['val'],
    data() {
        return {
            signInModal:false,
            signUpModal:false,
            username:'',
            password:'',
            nickname:'',
            email:'',
            code:'',
            verificationCodeImage:'',
            signUpSuccess:false,
            usernameExists:false,
            signUpFailedCodeError:false,
            signInUsernameEmpty:false,
            signInPasswordEmpty:false,
            signInCodeEmpty:false,
            signUpUsernameEmpty:false,
            signUpPasswordEmpty:false,
            signUpEmailEmpty:false,
            signUpNicknameEmpty:false,
            signUpCodeEmpty:false,
            signInButtonText:'登录',
            showSignUpButton:true
        }
    },
    methods:{

    }
}
</script>

<style scoped>

</style>