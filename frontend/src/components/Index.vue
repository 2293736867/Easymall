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
                <b-col offset="3">
                    <b-button variant="success" @click="signUpModal = !signUpModal">注册</b-button>
                </b-col>
                <b-col>
                    <b-button variant="success" @click="signInModal = !signInModal">登录</b-button>
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
            <b-row>
                <b-col>
                    <b-carousel controls indicators>
                        <b-carousel-slide caption="111" text="222" img-src=""></b-carousel-slide>
                    </b-carousel>
                </b-col>
            </b-row>
            <b-modal
                title="登录"
                @show="resetSignInModal"
                v-model="signInModal"
                @ok="signIn"
            >
                <b-form>
                    <b-form-group>
                        <b-input-group prepend="用户名">
                            <b-form-input v-model="username" required ></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="密码">
                            <b-form-input v-model="password" required type="password"></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="验证码">
                            <b-form-input v-model="code" required>
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
                            <b-form-input v-model="username" required ></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="密码">
                            <b-form-input v-model="password" required type="password"></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="昵称">
                            <b-form-input v-model="nickname" required></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="邮箱">
                            <b-form-input v-model="email" required></b-form-input>
                        </b-input-group>
                        <br>
                        <b-input-group prepend="验证码">
                            <b-form-input v-model="code" required ></b-form-input>
                            <b-input-group-append>
                                <b-img :src="verificationCodeImage" @click="resetVerificationCode"></b-img>
                            </b-input-group-append>
                        </b-input-group>
                    </b-form-group>
                </b-form>
            </b-modal>

            <b-modal v-model="signInSuccess">
                登录成功
            </b-modal>
            <b-modal v-model="signInFailedCodeError">
                登录失败，验证码错误
            </b-modal>
            <b-modal v-model="signInFailedUsernameOrPasswordIncorrect">
                登录失败，用户名或密码错误
            </b-modal>
            <b-modal v-model="signUpSuccess">
                注册成功
            </b-modal>
        </b-container>
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
            axios.post("http://localhost:8080/login?username="+this.username+"&password="+this.password+"&code="+this.code).then(res=>{
                console.log(res.data)
                if(res.data == "10000")
                    this.signInSuccess = true
            }).catch(e=>{
                console.log(e.data)
            })
        },
        signUp(){
            axios.post("http://localhost:8080/save",{
                username:this.username
            }).then(res=>{
                console.log(res.data)
            })
        },
    }
}
</script>

<style scoped>

</style>