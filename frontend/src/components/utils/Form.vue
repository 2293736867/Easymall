<template>
    <div>
        <slot></slot>
    </div>
</template>

<script>
import {defineComponent} from 'vue'
import emitter from "../../js/utils/Emitter";

export default defineComponent({
    name: "Form",
    setup() {
        let items = []
        let values = []
        let result = true
        const validate = (callback) => {
            for (const i in items) {
                if (items.hasOwnProperty(i)) {
                    items[i].value.validate().catch(_ => {
                        result = false
                    }).then(_ => {
                        if (parseInt(i) === items.length - 1) {
                            callback(result)
                            result = true
                        }
                    })
                }
            }
        }

        const set = value =>{
            let i = 0
            for(const k in value){
                if(value.hasOwnProperty(k)){
                    if(items.hasOwnProperty(i)){
                        items[i++].value.model.value = value[k]
                    }
                }
            }
        }

        const get = _ => {
            for (const i in items) {
                if (items.hasOwnProperty(i)) {
                    values.push(items[i].value.model.value)
                }
            }
            return values
        }

        emitter.on('add', formItem => {
            items.push(formItem)
        })

        return {
            //methods
            validate,get,set
        }
    }
})
</script>

<style scoped>

</style>