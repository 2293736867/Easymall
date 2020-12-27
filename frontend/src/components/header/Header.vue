<template>
    <SearchBarAndLogoAndSignInUp></SearchBarAndLogoAndSignInUp>
    <Navbar></Navbar>
</template>

<script>
import SearchBarAndLogoAndSignInUp from "./SearchBarAndLogoAndSignInUp.vue";
import Navbar from "./Navbar.vue";
export default {
    name: "Header",
    components: {Navbar, SearchBarAndLogoAndSignInUp},
}
</script>

<style scoped>

</style>