<template>
    <el-form ref="item" :model="form" :rules="rules" status-icon>
        <el-form-item :label="label" prop="value">
            <el-input v-model="form.value" :prefix-icon="prefixIcon" clearable>
                <template #append v-if="$slots.append">
                    <slot name="append"></slot>
                </template>
            </el-input>
        </el-form-item>
    </el-form>
</template>

<script>
import {defineComponent, ref} from 'vue'
import emitter from "../../js/utils/Mitt";
import Validation from "../../js/utils/Validation";
import { ElMessage } from 'element-plus'

export default defineComponent({
    name: "FormItem",
    props: {
        label: String,
        prefixIcon: String,
        checkFunction:Function,
        noCheck:Boolean,
        maxNum:String,
        numCheck:Boolean,
    },
    setup(props) {
        let label = props.label
        let prefixIcon = props.prefixIcon
        let checkFunction = props.checkFunction
        let noCheck = props.noCheck
        let maxNum = props.maxNum
        let numCheck = props.numCheck
        let check = new Function()
        const item = ref(null)
        const form = ref({
            value: ''
        })

        const get = _ => {
            return form.value.value
        }

        if(!label){
            label = '内容'
        }
        if(!prefixIcon) {
            prefixIcon = 'el-icon-remove-outline'
        }
        if(!checkFunction){
            if(noCheck){
                check = (rule,value,callback)=>{
                    callback()
                }
            }
            else{
                check = function (rule,value,callback){
                    if(!value)
                        callback(new Error('请输入'+label))
                    if(numCheck){
                        if(typeof maxNum === 'undefined'){
                            if(!Validation.num(value)) {
                                callback(new Error(value.substr(0,1) === '-' ? '请不要输入负数' : '请输入数字'))
                            }
                        }else{
                            if(!Validation.num(value,parseFloat(maxNum))){
                                ElMessage.warning('111请输入0-'+maxNum+'内的数字')
                                callback(new Error('请输入合适范围数字'))
                            }
                        }
                    }
                    else if(typeof maxNum !== 'undefined') {
                        if(!Validation.num(value,parseFloat(maxNum))){
                            ElMessage.warning('www请输入0-'+maxNum+'内的数字')
                            callback(new Error('请输入合适范围数字'))
                        }
                    }
                    callback()
                }
            }
        } else{
            check = (rule,value,callback)=>{
                const checkResult = checkFunction(value)
                if(checkResult.valid)
                    callback()
                else
                    callback(new Error(checkResult.message))
            }
        }

        emitter.emit('add',item)

        return {
            form, label, prefixIcon, rules: {
                value: [{validator: check, trigger: 'blur'}],
            },item,

            get
        }
    },
    emits:['add']
})
</script>

<style scoped>

</style>