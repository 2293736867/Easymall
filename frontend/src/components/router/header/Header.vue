<template>
    <TopBar></TopBar>
    <Navbar></Navbar>
</template>

<script>
import TopBar from "../../content/header/content/TopBar.vue";
import Navbar from "../../content/header/content/Navbar.vue";
export default {
    name: "Header",
    components: {Navbar, TopBar},
}
</script>

<style scoped>

</style>