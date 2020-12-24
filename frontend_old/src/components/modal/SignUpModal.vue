<template>
    <div>
        <b-modal
            title="注册"
            @show="reset"
            id="signUpModal"
            @ok="signUp"
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
                    <b-input-group prepend="昵称">
                        <b-form-input v-model="nickname" @blur.prevent="checkNickname"></b-form-input>
                    </b-input-group>
                    <br>
                    <b-input-group prepend="邮箱">
                        <b-form-input v-model="email" @blur.prevent="checkEmail"></b-form-input>
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

        <b-modal id="signUpMessageModal" title="提示信息" @cancel="close" @close="close" @hide="close">
            {{ message }}
        </b-modal>
    </div>
</template>

<script>
import axios from "axios";
import URL from "@/js/URL";

export default {
    name: "SignUpModal",
    data() {
        return{
            username:'',
            password:'',
            email:'',
            nickname:'',
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
            this.email = ''
            this.nickname = ''
            this.resetVerificationCode()
        },
        async resetVerificationCode() {
            this.verificationCodeImage = await axios.get(URL.code).then(function (res) {
                return 'data:image/png;base64,' + res.data
            })
        },
        signUp(bvHandler) {
            if(this.checkUsername() && this.checkPassword() && this.checkCode() && this.checkEmail() && this.checkNickname())
            {
                var result = true
                axios.get(URL.checkUsername+this.username).then(res=>{
                    if(res.data === 3001) {
                        this.showMessage('用户名已存在')
                        result = false
                    }
                })

                if(result)
                {
                    axios.post(URL.signUp, {
                        username: this.username,
                        password: this.password,
                        nickname:this.nickname,
                        email:this.email,
                        code: this.code
                    }).then(res => {
                        if (res.data === 1003)
                        {
                            this.showMessage('注册成功')
                            this.$bvModal.hide('signUpModal')
                        }
                        else if (res.data === 1004)
                            this.showMessage('验证码错误')
                    })
                }
            }
            bvHandler.preventDefault()
        },
        checkUsername() {
            return this.checkIsNotEmpty(this.username,'用户名为空')
        },
        checkPassword() {
            return this.checkIsNotEmpty(this.password,'密码为空')
        },
        checkCode() {
            return this.checkIsNotEmpty(this.code,'验证码为空')
        },
        checkNickname() {
            return this.checkIsNotEmpty(this.nickname,'昵称为空')
        },
        checkEmail() {
            return this.checkIsNotEmpty(this.email,'邮箱为空')
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
            this.$bvModal.show('signUpMessageModal')
        }
    }
}
</script>

<style scoped>

</style>