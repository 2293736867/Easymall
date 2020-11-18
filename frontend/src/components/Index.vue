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
                <b-col offset="2">
                    <b-button variant="success" v-if="showSignUpButton" id="signUp" @click="signUpModal = !signUpModal">注册</b-button>
                </b-col>
                <b-col>
                    <b-button variant="success" id="signIn" @click="signInButtonClick">{{ signInButtonText }}</b-button>
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
            <br>
            <br>
            <b-modal
                title="登录"
                @show="resetSignInModal"
                v-model="signInModal"
                @ok="signIn"
            >
                <b-form>
                    <b-form-group>
                        <b-input-group prepend="用户名">
                            <b-form-input v-model="username" required @blur.prevent="signInUsernameCheck"></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="密码">
                            <b-form-input v-model="password" required type="password" @blur.prevent="signInPasswordCheck"></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="验证码">
                            <b-form-input v-model="code" required @blur.prevent="signInCodeCheck">
                            </b-form-input>
                            <b-input-group-append>
                                <b-img :src="verificationCodeImage" @click="resetVerificationCode"></b-img>
                            </b-input-group-append>
                        </b-input-group>
                    </b-form-group>
                </b-form>
            </b-modal>
            <b-modal
                title="注册"
                @show="resetSignUpModel"
                v-model="signUpModal"
                @ok="signUp"
            >
                <b-form>
                    <b-form-group>
                        <b-input-group prepend="用户名">
                            <b-form-input v-model="username" required @blur.prevent="signUpUsernameCheck"></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="密码">
                            <b-form-input v-model="password" required type="password" @blur.prevent="signUpPasswordCheck"></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="昵称">
                            <b-form-input v-model="nickname" required @blur.prevent="signUpNicknameCheck"></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="邮箱">
                            <b-form-input v-model="email" required @blue.prevent="signUpEmailCheck"></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="验证码">
                            <b-form-input v-model="code" required @blur.prevent="signUpCodeCheck"></b-form-input>
                            <b-input-group-append>
                                <b-img :src="verificationCodeImage" @click="resetVerificationCode"></b-img>
                            </b-input-group-append>
                        </b-input-group>
                    </b-form-group>
                </b-form>
            </b-modal>

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
            signInSuccess:false,
            signInFailedCodeError:false,
            signInFailedUsernameOrPasswordIncorrect:false,
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
        resetSignInModal() {
            this.username = ''
            this.password = ''
            this.code = ''
            this.resetVerificationCode()
        },
        async resetVerificationCode(){
            this.verificationCodeImage = await this.getVerificationCode()
        },
        resetSignUpModel(){
            this.resetSignInModal()
            this.nickname = ''
            this.email = ''
        },
        getVerificationCode(){
            return axios.get("http://localhost:8080/verification/code").then(function (res) {
                    return 'data:image/png;base64,' + res.data
                })
        },
        signIn(){
            axios.post("http://localhost:8080/sign/in?username="+this.username+"&password="+this.password+"&code="+this.code).then(res=>{
                if(res.data === 10000)
                    this.signInSuccess = true
                else if(res.data === 10001)
                    this.signInFailedUsernameOrPasswordIncorrect = true
                else if(res.data === 10002)
                    this.signInFailedCodeError = true
            })
        },
        signInUsernameCheck(){
            if(!this.username)
            {
                this.signInUsernameEmpty = true
            }
        },
        signInPasswordCheck(){
            if(!this.password)
            {
                this.signInPasswordEmpty = true
            }
        },
        signInCodeCheck(){
            if(!this.code)
            {
                this.signInCodeEmpty = true
            }
        },
        signUp(){
            axios.post("http://localhost:8080/sign/up?code="+this.code,{
                username:this.username,
                password:this.password,
                nickname:this.nickname,
                email:this.email
            }).then(res=>{
                if(res.data === 20000)
                {
                    this.signUpSuccess = true
                }
                if(res.data === 20001)
                {
                    this.signUpFailedCodeError = true
                }
            })
        },
        signUpUsernameCheck(){
            if(!this.username)
            {
                this.signUpUsernameEmpty = true
                return
            }
            axios.get("http://localhost:8080/check/username/"+this.username).then(res=>{
                if(res.data === 30001) {
                    this.usernameExists = true
                }
            })
        },
        signUpPasswordCheck(){
            if(!this.password)
            {
                if(this.usernameExists === false)
                    this.signUpPasswordEmpty = true
            }
        },
        signUpEmailCheck(){
            if(!this.email)
            {
                this.signUpEmailEmpty = true
            }
        },
        signUpNicknameCheck(){
            if(!this.nickname)
            {
                this.signUpNicknameEmpty = true
            }
        },
        signUpCodeCheck(){
            if(!this.code)
            {
                this.signUpCodeEmpty = true
            }
        },
        signInSuccessHandle(){
            this.signInButtonText = '退出'
            this.showSignUpButton = false
        },
        signInButtonClick(){
            if(this.showSignUpButton)
            {
                this.signInModal = true
            }
            else
            {
                this.signInButtonText = '登录'
                this.showSignUpButton = true
            }
        }
    }
}
</script>

<style scoped>

</style>