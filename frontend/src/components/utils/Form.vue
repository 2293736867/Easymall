<template>
    <div>
        <slot></slot>
    </div>
</template>

<script>
import {defineComponent, onErrorCaptured} from 'vue'
import emitter from "../../js/utils/Mitt";

export default defineComponent({
    name: "Form",
    setup() {
        let items = []
        let values = []
        let result = true
        const valid = (callback) => {
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
            valid,
            get
        }
    }
})
</script>

<style scoped>

</style>