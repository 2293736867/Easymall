<template>
    <div>
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
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "SignUpModal",
    data(){
        return{
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
    method:{
        resetSignUpModel(){
            this.resetSignInModal()
            this.nickname = ''
            this.email = ''
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
    }
}
</script>

<style scoped>

</style>