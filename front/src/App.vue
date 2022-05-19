<template>
  <div id="app" ref="app">
    <router-view />
    <scrollbar :height="height" />
  </div>
</template>

<script>
import store from './store/index'
import Scrollbar from './components/ScrollBar.vue'

export default {
  data: function(){
    return {
      height: 0,
      flag: false
    }
  },
  components: {
    Scrollbar
  },
  computed: {
    isEnd: function(){
      return store.state.isEnd
    },
    path: function(){
      return this.$route.path
    }
  },
  watch: {
    isEnd: function(){
      if (!this.isEnd){
        setTimeout(() => {
          this.getHeight()
        }, 1000)
      }
    },
    path: function(){
      window.scrollTo({top: 0, behavior: 'smooth'})
    }
  },
  methods: {
    getHeight: function(){
      this.$nextTick(() => {
        this.height = this.$refs.app.clientHeight
      })
    }
  },
  created: function(){
    this.$store.dispatch('getUser')
  },
  mounted: function(){
    this.getHeight()
  },
  updated: function(){
    this.getHeight()
  }
}
</script>

<style lang="scss">
  body {
    &::-webkit-scrollbar {
      display: none;
    }
  }
  
  #app {
    width: 100vw;
    min-height: 100vh;
    display: flex;
    justify-content: center;
    overflow: hidden;

    & * {
      box-sizing: border-box;
    }
  }

  .swal2-container {
    z-index: 987654321 !important;
  }
</style>
