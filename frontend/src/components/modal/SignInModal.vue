<template>
    <div>
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
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "Modal",
    data() {
        return{
            username:'',
            password:'',
            code:''
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
            this.verificationCodeImage = await axios.get("http://localhost:8080/verification/code").then(function (res){
                return 'data:image/png;base64,'+res.data
            })
        },
        signIn(){
            axios.post("http://localhost:8080/sign/in",{
                username:this.username,
                password:this.password,
                code:this.code
            }).then(res=>{
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
</script>

<style scoped>

</style>