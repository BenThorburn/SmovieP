
<template id="movie-overviewx">
    <app-frame>
        <h1>Titles:</h1>
        <ul class="title-overview-list">
            <li v-for="title in titles.data" :key="title.id">
                <a>{{title.id}}</a>
            </li>
        </ul>
    </app-frame>
</template>
<script>
    Vue.component("movie-overviewx", {
        template: "#movie-overviewx",
        data: () => ({
            titles: [],
        }),
        created() {
            fetch("/api/titles")
                .then(res => res.json())
                .then(res => this.titles = res)
                .catch(() => alert("Error while fetching titles"));
        }
    });
</script>
<style>
    ul.title-overview-list {
        padding: 0;
        list-style: none;
        height: 100px;
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