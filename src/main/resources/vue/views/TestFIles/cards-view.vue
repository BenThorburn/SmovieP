
<template id="movie-overview">
    <app-frame>
        <h1>Titles:</h1>
        <ul class="title-overview-list">
            <li v-for="title in titles.data">
                <a>{{title.title}}</a>
            </li>
        </ul>
    </app-frame>
</template>
<script>
    import axios from 'axios'
    Vue.component("movie-overview", {
        template: "#movie-overview",
        data: () => ({
            titles: [],
        }),
        mounted() {
            axios.get('/api/titles').then(response => {
                this.titles = response.data
            })
        }
    });
</script>
<script src="https://unpkg.com/vue"></script>
<style>
    ul.title-overview-list {
        padding: 0;
        list-style: none;
    }
    ul.title-overview-list a {
        display: block;
        padding: 16px;
        border-bottom: 1px solid #ddd;
    }
    ul.title-overview-list a:hover {
        background: #00000010;
    }
</style>