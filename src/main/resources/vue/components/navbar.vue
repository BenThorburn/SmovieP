<template id="nav-bar">
    <header class="navbar">
        <h1>Yoooo</h1>
    </header>
</template>
<script>
    Vue.component("nav-bar", {
        template: "#nav-bar",
        methods: {
        },
    });
</script>
<style scoped>
    .navbar {
        background: #f5f5f5;
        border-bottom: 1px solid #ddd;
    }

    .navbar .logo-img {
        height: 44px;
        display: block;
        user-select: none;
    }

    .navbar .logo-text {
        margin-left: 16px;
        font-family: 'Lobster Two', cursive;
        font-weight: 400;
        color: #444;
        font-size: 28px;
        user-select: none;
    }
</style>
