<template>
    <div>
        <b-img src="../assets/logo.png"></b-img>
        <br>
        <b-container>
            <b-row>
                <b-col offset="3" cols="6">
                    <b-input-group size="lg">
                        <b-input-group-text>用户名</b-input-group-text>
                        <b-form-input type="text" v-model="username"></b-form-input>
                    </b-input-group>
                </b-col>
            </b-row>
            <br>
            <b-row>
                <b-col offset="3" cols="6">
                    <b-input-group size="lg">
                        <b-input-group-text>密码</b-input-group-text>
                        <b-form-input type="password" v-model="password"></b-form-input>
                    </b-input-group>
                </b-col>
            </b-row>
            <br>
            <b-row>
                <b-col offset="3" cols="6">
                    <b-button variant="success" @click="login">
                        一键注册/登录
                    </b-button>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
import axios from 'axios'
import router from "@/router"

export default {
    name: "Login.vue",
    data:function (){
        return{
            username:'',
            password:''
        }
    },
    methods:{
        login:function(){
            axios.post("http://localhost:8080/login",{
                username:this.username,
                password:this.password
            }).then(function (res){
                router.push({
                    name:"index",
                    params:{
                        val:res.data.code === 1
                    }
                })
            })
        }
    }
}
</script>

<style scoped>

</style>