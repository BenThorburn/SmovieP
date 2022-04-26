<template id="mov-overview">
    <app-frame>
        <div class="container">
            <div class="search-box">
                <i class="bx bx-search"></i>
                <input class="frost" type="text" placeholder="Search a movie" v-model="searchQuery">
            </div>
            <div class="images">
                <div class="image-box" data-name="spiderman" v-for="mov in b">
                    <img src="images/spiderman.jpg" alt="">
                    <h6><a :href="`profile/${mov.id}`">{{mov.title}}</a></h6>
                </div>
            </div>
        </div>
    </app-frame>
</template>
        <!--
        <div class="title-overview-list">
            <li v-for="mov in movies.data">
                <a :href="`/profile/${mov.title}`">{{mov.title}}</a>
            </li>
        </div>
        -->
<script>
    Vue.component("mov-overview", {
        template: "#mov-overview",
        data: () => ({
            searchQuery: null,
            movies: {},
        }),
        created() {
            fetch(`/api/titles`)
                .then(res => res.json())
                .then(res => this.movies = res)
                .catch(() => alert("Error while fetching movies"));
        },
        computed: {
            resultQuery() {
              if(this.searchQuery) {
              return this.movies.data.filter((item) => {
                return this.searchQuery.toLowerCase().split(' ').every(v => item.title.toLowerCase().includes(v))
              })
              } else {
                return this.movies.data;
              }
            },
            b() {
                if(this.searchQuery) {
                    return this.movies.data.filter((item) => {
                        var columns = item.title + item.id;
                        return this.searchQuery.toLowerCase().split(' ').every(v => columns.toLowerCase().includes(v));
                    })
                } else { return this.movies.data; }
            }
        },
    });
</script>

<style scoped>
</style>
