<template id="mov-overview">
    <app-frame>



<div id="minmax">
<input id="min"  placeholder="min" type="number"/>
<input id="max"  placeholder="max" type="number"/>
<button id="subminmax"  v-on:click="minmax">Submit</button>
<h1>{{min}} </h1>


</div>



        <div class="container">


         <select class="form-control" @change="changeJobTitle($event)">
                <option value="" selected disabled>Choose</option>
                <option v-for="jobTitle in jobTitles" :value="jobTitle.id" :key="jobTitle.id">{{ jobTitle.name }}</option>
              </select>




            <div class="search-box">
                <i class="bx bx-search"></i>
                <input class="frost" type="text" placholder="search by title" v-model="searchQuery">
            </div>

            <div class="images">
             <div class="image-box" v-for="mov in c.slice(0,12)">


             <div id="ifelse">
                        <h2 v-if="result === 'date'">




                                              <clazy-load>
                                                  <img src="https://unsplash.it/500">
                                                  <h6><a :href="`profile/${mov.id}`">{{mov.title}}</a></h6>
                                              </clazy-load>
                        </h2>
                        <h2 v-else>


                                              <clazy-load>
                                                  <img src="https://unsplash.it/500">
                                                  <h6><a :href="`profile/${mov.id}`">{{mov.title}}</a></h6>
                                              </clazy-load>
                                          </div>


                        </h2>
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
              { name: "title", id: 1 },
              { name: "actor", id: 2 },
              { name: "budget", id: 3 },
              { name: "date", id: 4 }
            ],
            selectedJobTitle: null,
            result: "title",
            min:"",
            max:"",
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
                        var columns = item.title + item.id + item.director ;
                        return this.searchQuery.toLowerCase().split(' ').every(v => columns.toLowerCase().includes(v));
                    })
                } else { return this.movies.data; }
            },

            c() {
                            if(this.searchQuery) {
                                return this.movies.data.filter((item) => {
                                    var columns = item.title + item.id + item.release_date;
                                    return this.searchQuery.toLowerCase().split(' ').every(v => columns.toLowerCase().includes(v));
                                })
                            } else { return this.movies.data; }
                        },






        },
        methods: {
         	changeJobTitle (event) {
             this.result = event.target.options[event.target.options.selectedIndex].text;
           },
           changeJobTitle (event) {
                 this.result = event.target.options[event.target.options.selectedIndex].text
               },
               minmax(){
               this.min = min.value;
               this.max = max.value;
               }


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