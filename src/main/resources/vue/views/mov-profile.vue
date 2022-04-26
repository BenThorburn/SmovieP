



<template id="mov-profile">
    <app-frame>
        <div class="title-overview-list">
            <li v-for="mov in movie.data">
                <a>{{mov.title}}</a>
            </li>
        </div>
    </app-frame>
</template>

<script>
    Vue.component("mov-profile", {
        template: "#mov-profile",
        data: () => ({
            movie: [],
        }),
        created() {
            const t = this.$javalin.pathParams["title-id"];
            fetch(`/api/titles/profile/${t}`)
                .then(res => res.json())
                .then(res => this.movie = res)
                .catch(() => alert("Error while fetching movie"));
        },
    });
</script>