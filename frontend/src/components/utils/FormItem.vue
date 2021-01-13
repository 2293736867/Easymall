<template>
    <el-form ref="item" :model="form" :rules="rules" status-icon>
        <el-form-item :label="label" prop="value">
            <el-input v-model="form.value" :prefix-icon="prefixIcon" clearable v-if="password" type="password" show-password>
                <template v-if="$slots.append" #append>
                    <slot name="append"></slot>
                </template>
            </el-input>
            <el-input v-model="form.value" :prefix-icon="prefixIcon" clearable v-else :disabled="disabled">
                <template v-if="$slots.append" #append>
                    <slot name="append"></slot>
                </template>
            </el-input>
        </el-form-item>
    </el-form>
</template>

<script>
import {defineComponent, ref} from 'vue'
import emitter from "../../js/utils/Emitter";
import Validation from "../../js/utils/Validation";

export default defineComponent({
    name: "FormItem",
    props: {
        label: String,
        prefixIcon: String,
        checkFunction: Function,
        noCheck: Boolean,
        maxNum: String,
        numCheck: Boolean,
        password:Boolean,
        disabled:Boolean,
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
        let check = new Function()

        const item = ref(null)
        const form = ref({
            value: ''
        })

        const get = _ => {
            return form.value.value
        }

        const getType = _=>{
            return password ? 'password' : ''
        }

        if (!label) {
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
                    if (!value)
                        callback(new Error('请输入' + label))
                    if (numCheck || typeof maxNum !== 'undefined') {
                        if (Validation.isNum(value)) {
                            if (Validation.isNegative(value)) {
                                callback(new Error('请输入非负数'))
                            } else {
                                if (typeof maxNum === 'undefined') {
                                    callback()
                                } else {
                                    if (Validation.isLessEqualThan(value,maxNum)) {
                                        callback()
                                    } else {
                                        callback(new Error('请输入合适范围内的数字'))
                                    }
                                }
                            }
                        }else{
                            callback(new Error('请输入数字'))
                        }
                    }
                    callback()
                }
            }
        } else {
            check = (rule, value, callback) => {
                const checkResult = checkFunction(value)
                if(typeof checkResult.valid === 'undefined'){
                    checkResult.catch(err=>{
                        callback(new Error(err))
                    }).then(_=>{
                        callback()
                    })
                }else{
                    if(checkResult.valid){
                        callback()
                    }else{
                        callback(new Error(checkResult.message))
                    }
                }
            }
        }

        emitter.emit('add', item)

        return {
            //data
            form, label, prefixIcon,password,disabled,
            rules: {
                value: [{validator: check, trigger: 'blur'}],
            },

            //components
            item,

            //methods
            get,getType
        }
    },
    emits: ['add']
})
</script>

<style scoped>
</style>