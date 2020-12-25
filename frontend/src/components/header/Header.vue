<template>
    <HeaderSearchBarAndLogo></HeaderSearchBarAndLogo>
    <Navbar></Navbar>
</template>

<script>
import HeaderSearchBarAndLogo from "./HeaderSearchBarAndLogo.vue";
import Navbar from "./Navbar.vue";
export default {
    name: "Header",
    components: {Navbar, HeaderSearchBarAndLogo},
}
</script>

<style scoped>

</style>