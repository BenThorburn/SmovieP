<div id="a">
<select class="form-control" @change="changeJobTitle($event)">
    <option value="" selected disabled>Choose</option>
    <option v-for="jobTitle in jobTitles" :value="jobTitle.id" :key="jobTitle.id">{{ jobTitle.name }}</option>
  </select>
  <br><br>
  <p><span>Selected job title: {{ selectedJobTitle  }}</span></p>
</div>



<template id="mov-test">
    <app-frame>
        <div class="container">

<div id="example">
  <button v-on:click="greet">Greet</button>
</div>

<div id="a">
<select class="form-control" @change="changeJobTitle($event)">
    <option value="" selected disabled>Choose</option>
    <option v-for="jobTitle in jobTitles" :value="jobTitle.id" :key="jobTitle.id">{{ jobTitle.name }}</option>
  </select>
  <br><br>
  <p><span>Selected job title: {{ selectedJobTitle  }}</span></p>
</div>

<template id = "navbar">






        <div class = "select-button">
        <select class="form-select" id="platform" v-model="options">
                <option value="title">Title</option>
                <option value="director">Director</option>
                <option value="actor">Actor</option>
                </select>
        </div>

            <div class="search-box">

            <select class="form-control" @change="changeJobTitle($event)">
                <option value="" selected disabled>Choose</option>
                <option v-for="jobTitle in jobTitles" :value="jobTitle.id" :key="jobTitle.id">{{ jobTitle.name }}</option>
              </select>
              <br><br>
              <p><span>Selected job title: {{ sel  }}</span></p>




                <i class="bx bx-search"></i>

                <input class="frost" type="text" placeholder= "[[sel]]" v-model="searchQuery">
            </div>
           </template>

            <div class="images">
                <div class="image-box" data-name="movie-placeholder" v-for="mov in filter.slice(0,15)">

                    <img src="images/spiderman.jpg" alt="">
                    <h6><a :href="`profile/${mov.id}`">{{mov.title}}</a></h6>
                </div>
            </div>



        </div>
    </app-frame>
</template>

<script>

new Vue({
  el: "#a",
  data: {
    jobTitles: [
      { name: "title", id: 1 },
      { name: "", id: 2 },
      { name: "Product manager", id: 3 },
      { name: "Senior front-end developer", id: 4 }
    ],
    selectedJobTitle: 1,
    sel: 1,
  },
  methods: {
  	changeJobTitle (event) {
      this.sel = event.target.options[event.target.options.selectedIndex].text
    }


  }
})

    Vue.component("mov-test", {
        template: "#mov-test",
