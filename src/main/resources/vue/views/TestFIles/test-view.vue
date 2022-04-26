<template id="test-view">
    <app-frame>
        <section class="section">
            <nav-bar></nav-bar>
            <div class="container">
                <nav class="panel">
                  <p class="panel-heading">
                    Repositories
                  </p>
                  <div class="panel-block">
                    <p class="control has-icons-left">
                      <input class="input" type="text" placeholder="Search">
                      <span class="icon is-left">
                        <i class="fas fa-search" aria-hidden="true"></i>
                      </span>
                    </p>
                  </div>
                  <p class="panel-tabs">
                    <a class="is-active">All</a>
                    <a>Public</a>
                    <a>Private</a>
                    <a>Sources</a>
                    <a>Forks</a>
                  </p>
                  <a class="panel-block is-active" v-for="t in titles.data">
                    <img class="visual"></img>
                    <span class="panel-icon">
                      <i class="fas fa-book" aria-hidden="true"></i>
                    </span>
                      {{t.title}} ({{t.id}})
                  </a>
                </div>
            </nav>
        </section>
    </app-frame>
</template>

<style scoped>
.visual {
    position: relative;
    float: left;
    width: 10px;
    height: 15px;
}

.container, .panel-heading {
    background-color: rgba(255, 255, 255);
    backdrop-filter: blur(5px);
    width: 30vw;
    position: relative;
    float: left;
}
</style>

<script>
    Vue.component("test-view", {
        template: "#test-view",
        data: () => ({
            titles: {},
        }),
        mounted() {
            fetch("/api/titles")
                .then(res => res.json())
                .then(res => this.titles = res)
                .catch(() => alert("Error while fetching titles"));
        },
    });
</script>