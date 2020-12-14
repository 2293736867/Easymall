<template>
    <div>
        <Header></Header>
        <b-row>
            <b-col cols="8" offset="2">
                <b-carousel controls indicators>
                    <b-carousel-slide caption="111" text="222" img-src="../assets/banner.jpg"></b-carousel-slide>
                </b-carousel>
            </b-col>
        </b-row>
    </div>
</template>

<script>
import Header from "@/components/Header";
export default {
    name: "Index.vue",
    components: {Header},
    methods:{
    }
}
</script>

<style scoped>

</style>