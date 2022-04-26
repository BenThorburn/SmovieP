
<template id="fbg-view">
    <div>
        <h1>Smovie</h1>
        <ul class="user-overview-list">
            <li v-for="g in genres.data">
                <a>{{g.name}}</a>
            </li>
        </ul>
    </div>
</template>
<script>
    Vue.component("fbg-view", {
        template: "#fbg-view",
        data: () => ({
            genres: [],
        }),
        created() {
            const movieTitle = this.$javalin.pathParams["movie-title"];
            fetch(`/api/genres/${movie-title}`)
                .then(res => res.json())
                .then(res => this.genres = res)
                .catch(() => alert("Error while fetching genres"));
        }
    });
</script>
<style>
    ul.user-overview-list {
        padding: 0;
        list-style: none;
    }
    ul.user-overview-list a {
        display: block;
        padding: 16px;
        border-bottom: 1px solid #ddd;
    }
    ul.user-overview-list a:hover {
        background: #00000010;
    }
</style>