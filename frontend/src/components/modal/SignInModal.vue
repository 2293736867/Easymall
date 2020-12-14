<template>
    <div>
        <b-modal
            title="登录"
            @show="reset"
            id="signInModal"
            @ok="signIn"
        >
            <b-form>
                <b-form-group>
                    <b-input-group prepend="用户名">
                        <b-form-input v-model="username" @blur.prevent="checkUsername"></b-form-input>
                    </b-input-group>
                    <br>
                    <b-input-group prepend="密码">
                        <b-form-input v-model="password" type="password" @blur.prevent="checkPassword"></b-form-input>
                    </b-input-group>
                    <br>
                    <b-input-group prepend="验证码">
                        <b-form-input v-model="code" @blur.prevent="checkCode">
                        </b-form-input>
                        <b-input-group-append>
                            <b-img :src="verificationCodeImage" @click="resetVerificationCode"></b-img>
                        </b-input-group-append>
                    </b-input-group>
                </b-form-group>
            </b-form>
        </b-modal>

        <b-modal id="signInMessageModal" @close="close" @hidden="close" @cancel="close" title="提示信息">
            {{message}}
        </b-modal>
    </div>
</template>

<script>
import axios from "axios";
import URL from "@/js/URL";

export default {
    name: "SignInModal",
    data() {
        return{
            username:'',
            password:'',
            code:'',
            verificationCodeImage:'',
            hasMessageModal:false,
            message:'',
        }
    },
    methods: {
        reset() {
            this.username = ''
            this.password = ''
            this.code = ''
            this.resetVerificationCode()
        },
        async resetVerificationCode() {
            this.verificationCodeImage = await axios.get(URL.code).then(function (res) {
                return 'data:image/png;base64,' + res.data
            })
        },
        signIn(bvHandler) {
            if(this.checkUsername() && this.checkPassword() && this.checkCode())
            {
                axios.post(URL.signIn, {
                    username: this.username,
                    password: this.password,
                    code: this.code
                }).then(res => {
                    if (res.data === 1000)
                    {
                        this.showMessage('登录成功')
                        this.$bvModal.hide('signInModal')
                    }
                    else if (res.data === 1001)
                        this.showMessage('登录失败，用户名或密码错误')
                    else if (res.data === 1002)
                        this.showMessage('验证码错误')
                })
            }
            bvHandler.preventDefault()
        },
        checkUsername(){
            return this.checkIsNotEmpty(this.username,'用户名为空')
        },
        checkPassword(){
            return this.checkIsNotEmpty(this.password,'密码为空')
        },
        checkCode(){
            return this.checkIsNotEmpty(this.code,'验证码为空')
        },
        checkIsNotEmpty(str, message){
            if(!str)
            {
                if(!this.hasMessageModal)
                {
                    this.showMessage(message)
                }
                return false
            }
            return true
        },
        close(){
            this.hasMessageModal = false
        },
        showMessage(message){
            this.message = message
            this.hasMessageModal = true
            this.$bvModal.show('signInMessageModal')
        }
    }
}
</script>

<style scoped>

</style>