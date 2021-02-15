<template>
    <el-form ref="item" :model="form" :rules="rules" status-icon>
        <el-form-item :label="label" prop="value">
            <el-input v-if="password" v-model="form.value" :prefix-icon="prefixIcon" clearable show-password
                      type="password" @change="inputChange">
                <template v-if="$slots.append" #append>
                    <slot name="append"></slot>
                </template>
            </el-input>
            <el-input v-if="!password && !verificationCode" v-model="form.value" :disabled="disabled"
                      clearable @change="inputChange" prefix-icon="el-icon-s-cooperation">
                <template v-if="$slots.append" #append>
                    <slot name="append"></slot>
                </template>
            </el-input>
            <div v-if="verificationCode">
                <br>
                <el-row>
                    <el-col :span="10">
                        <el-input v-model="form.value" clearable prefix-icon="el-icon-aim">
                        </el-input>
                    </el-col>
                    <el-col :span="14">
                        <el-image v-loading="loadingVerificationCodeImage" :src="verificationCodeImage"
                                  element-loading-spinner="el-icon-loading" style="margin-top: 0.3rem"
                                  @click="resetVerificationCode"></el-image>
                    </el-col>
                </el-row>
            </div>
        </el-form-item>
    </el-form>
</template>

<script>
import {defineComponent, ref} from 'vue'
import emitter from "../../js/utils/Emitter";
import Validation from "../../js/utils/Validation";
import Event from "../../js/constant/Event";
import axios from 'axios'
import URL from "../../js/constant/URL";
import Utils from "../../js/utils/Utils";
import REG from "../../js/constant/REG";
import {ElNotification} from 'element-plus'

export default defineComponent({
    name: "FormItem",
    props: {
        label: String,
        prefixIcon: String,
        checkFunction: Function,
        noCheck: Boolean,
        maxNum: String,
        numCheck: Boolean,
        password: Boolean,
        disabled: Boolean,
        verificationCode: Boolean,
    },
    setup(props) {
        let label = props.label
        let prefixIcon = props.prefixIcon
        let checkFunction = props.checkFunction
        let noCheck = props.noCheck
        let maxNum = props.maxNum
        let numCheck = props.numCheck
        let password = props.password
        let disabled = props.disabled
        let verificationCode = props.verificationCode

        const verificationCodeImage = ref('')
        const loadingVerificationCodeImage = ref(true)

        let check = new Function()
        const item = ref(null)
        const form = ref({
            value: ''
        })

        const get = _ => {
            return form.value.value
        }

        const inputChange = _ => {
            emitter.emit(Event.FORM_ITEM_CHANGED)
        }

        const resetVerificationCode = _ => {
            loadingVerificationCodeImage.value = true
            axios.get(URL.code).then(res => {
                verificationCodeImage.value = 'data:image/png;base64,' + Utils.getResponseData(res)
                loadingVerificationCodeImage.value = false
            })
        }

        resetVerificationCode()
        if (password) {
            label = '密码'
            checkFunction = value => {
                let result = false
                let message = ''
                if (!value) {
                    message = '请输入密码'
                } else if (!REG.password.test(value)) {
                    ElNotification.warning({
                        title: '密码最少6位',
                        message: '最少1数字+1特殊字符+1大写+1小写',
                        position: 'top-left'
                    })
                    message = '密码过于简单'
                } else {
                    result = true
                }
                return Utils.validatedResult(result,message)
            }
        } else if (verificationCode) {
            label = '验证码'
            checkFunction = value => {
                let result = false
                let message = ''
                if (!value) {
                    message = '请输入验证码'
                } else if (value.length !== 4) {
                    message = '请输入4位验证码'
                } else {
                    result = true
                }
                return Utils.validatedResult(result, message)
            }
        } else if (!label) {
            label = '内容'
        }
        if (!prefixIcon) {
            prefixIcon = 'el-icon-remove-outline'
        }
        if (!checkFunction) {
            if (noCheck) {
                check = (rule, value, callback) => {
                    callback()
                }
            } else {
                check = function (rule, value, callback) {
                    if (value.isEmpty())
                        callback(new Error('请输入' + label))
                    if (numCheck || typeof maxNum !== 'undefined') {
                        if (Validation.isNum(value)) {
                            if (Validation.isNegative(value)) {
                                callback(new Error('请输入非负数'))
                            } else {
                                if (typeof maxNum === 'undefined') {
                                    callback()
                                } else {
                                    if (Validation.isLessEqualThan(value, maxNum)) {
                                        callback()
                                    } else {
                                        callback(new Error('请输入合适范围内的数字'))
                                    }
                                }
                            }
                        } else {
                            callback(new Error('请输入数字'))
                        }
                    }
                    callback()
                }
            }
        } else {
            check = (rule, value, callback) => {
                const checkResult = checkFunction(value)
                if (typeof checkResult.valid === 'undefined') {
                    checkResult.catch(err => {
                        callback(new Error(err))
                    }).then(_ => {
                        callback()
                    })
                } else {
                    if (checkResult.valid) {
                        callback()
                    } else {
                        callback(new Error(checkResult.message))
                    }
                }
            }
        }

        emitter.emit(Event.FORM_ITEM_ADD, item)

        return {
            //data
            form,
            label,
            prefixIcon,
            password,
            disabled,
            verificationCode,
            loadingVerificationCodeImage,
            verificationCodeImage,
            rules: {
                value: [{validator: check, trigger: 'blur'}],
            },

            //components
            item,

            //methods
            get,
            inputChange,
            resetVerificationCode,
        }
    },
    emits: ['add']
})
</script>

<style scoped>
</style>