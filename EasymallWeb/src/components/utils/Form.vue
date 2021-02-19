<template>
    <div>
        <slot></slot>
    </div>
</template>

<script>
import {defineComponent} from 'vue'
import emitter from "../../js/utils/Emitter";
import Event from "../../js/constant/Event";

export default defineComponent({
    name: "Form",
    setup() {
        //data
        let items = []
        let values = []
        let result = true
        let changed = false

        //methods
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

        const set = value => {
            let i = 0
            for (const k in value) {
                if (value.hasOwnProperty(k)) {
                    if (items.hasOwnProperty(i)) {
                        items[i++].value.model.value = value[k]
                    }
                }
            }
        }

        const ifChanged = _ => {
            return changed
        }

        const get = _ => {
            for (const i in items) {
                if (items.hasOwnProperty(i)) {
                    values.push(items[i].value.model.value)
                }
            }
            return values
        }

        //logic
        emitter.on(Event.FORM_ITEM_ADD, formItem => {
            items.push(formItem)
        })

        emitter.on(Event.FORM_ITEM_CHANGED, _ => {
            changed = true
        })

        return {
            //methods
            validate, get, set, ifChanged
        }
    }
})
</script>

<style scoped>

</style>