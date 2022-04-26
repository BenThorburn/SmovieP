<template id="mov-overview">
    <app-frame>


    <select class="form-control" @change="changeJobTitle($event)">
        <option value="" selected disabled>Choose</option>
        <option v-for="jobTitle in jobTitles" :value="jobTitle.id" :key="jobTitle.id">{{ jobTitle.name }}</option>
      </select>
      <br><br>
      <p><span>Selected job title: {{result}}</span></p>



        <div id="ifelse">
            <h2 v-if="result === 'budget'">


            </h2>
            <h2 v-else>
                ELSEEEEEEEEEEEEEE
            </h2>
        </div>


        <div class="container">
            <div class="search-box">
                <i class="bx bx-search"></i>
                <input class="frost" type="text" placeholder="Search a movie" v-model="searchQuery">
            </div>

            <div class="images">
                <div class="image-box" v-for="mov in b.slice(0,12)">
                    <img src="images/spiderman.jpg" alt="">
                    <h6><a :href="`profile/${mov.id}`">{{mov.title}}</a></h6>
                </div>
            </div>
        </div>
    </app-frame>
</template>

<script>
    Vue.component("mov-overview", {
        template: "#mov-overview",
        data: () => ({
            searchQuery: null,
            movies: {},
            jobTitles: [
              { name: "Product designer", id: 1 },
              { name: "Full-stack developer", id: 2 },
              { name: "Product manager", id: 3 },
              { name: "Senior front-end developer", id: 4 }
            ],
            selectedJobTitle: null,
            result: null,
        }),
        created() {
            fetch(`/api/titles`)
                .then(res => res.json())
                .then(res => this.movies = res)
                .catch(() => alert("Error while fetching movies"));
        },
        computed: {
            b() {
                if(this.searchQuery) {
                    return this.movies.data.filter((item) => {
                        var columns = item.title + item.id + item.director;
                        return this.searchQuery.toLowerCase().split(' ').every(v => columns.toLowerCase().includes(v));
                    })
                } else { return this.movies.data; }
            }
        },
        methods: {
         	changeJobTitle (event) {
             this.result = event.target.options[event.target.options.selectedIndex].text;
           },
       },
    });
</script>

<style scoped>
</style>




        <!--
        <div class="title-overview-list">
            <li v-for="mov in movies.data">
                <a :href="`/profile/${mov.title}`">{{mov.title}}</a>
            </li>
        </div>
        -->