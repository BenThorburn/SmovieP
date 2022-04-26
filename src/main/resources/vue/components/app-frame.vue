<template id="app-frame">
    <v-app>
        <v-content>
            <v-container>
                <slot></slot>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    Vue.component("app-frame", {template: "#app-frame"});
</script>

<style scoped>
v-container {
    display:flex;
    flex-wrap: wrap;
}

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}
.container{
    position: relative;
    min-height: 100vh;
    max-width: 1000px;
    width: 100%;
    margin: 0 auto;
    padding: 40px 20px;
}
.container .search-box {
    position: relative;
    height: 42px;
    max-width: 400px;
    margin: 0 auto;
    margin-bottom: 40px;
}
.search-box input {
    position: absolute;
    height: 100%;
    width: 100%;
    outline: none;
    border: none;
    padding: 0 15px 0 45px;
    border-radius: 6px;
}
.search-box i {
    position: absolute;
    z-index: 2;
    color: #999;
    top: 50%;
    left: 15px;
    font-size: 20px;
    transform: translateY(-50%);
}

.frost {
    -webkit-backdrop-filter: blur(8px);
    backdrop-filter: blur(8px);
    box-shadow: 0px 10px 15px 10px rgba(0 ,0 ,0 ,0.15);
    background-color: rgba(228 ,228 ,228 ,0.10);
}
.search-box:target input {
    transform: scale(1.05);
}
.container .images .image-box {
    position: relative;
    height: 300px;
    width: 210px;
}
.images {
    width: 100%;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
}
.images .image-box {
    margin: 8px;
}
.images .image-box img {
    height: 100%;
    width: 100%;
    border-radius: 6px;
    transition: transform 0.2s linear;
}
.image-box:hover img {
    transform: scale(1.05);
}
.image-box h6 {
    position: absolute;
    bottom: 10px;
    left: 10px;
    color: #fff;
    font-size: 12px;
    font-weight: 400;
    text-transform: capitalize;
}
</style>
