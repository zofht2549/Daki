<template>
  <article id="editor" ref="editor">
    <menu-bar :selected="selected" :isCreated="isCreated" :historyInfo="historyInfo"
     @active-menu="params => activate(params)" @image-upload="file => fileSetter(file)" @history-change="payload => historyChange(payload)" />
    
    <Canvas :type="type" :isActive="isActive" :changes="changes" :file="file" :historyChangeFromMenu="historyChangeFromMenu"
     @deactivate="deactivate" @select="tar => select(tar)" @get-history-info="info => getHistoryInfo(info)" />
  </article>
</template>

<script>
import MenuBar from './Menus/MenuBar.vue'
import Canvas from './Canvas/Canvas.vue'

export default {
  data: function(){
    return {
      type: null,
      isActive: false,
      selected: null,
      changes: null,
      file: null,
      historyInfo: [0, 0],
      historyChangeFromMenu: null
    }
  },
  components: {
    MenuBar,
    Canvas
  },
  computed: {
    isCreated: function(){
      return !this.isActive
    }
  },
  methods: {
    activate: function(params){
      this.type = params.menu
      this.isActive = params.isActive
    },
    deactivate: function(){
      this.isActive = false
      this.changes = null
      this.file = null
    },
    select: function(tar){
      this.selected = tar
    },
    changeSetter: function(payload){
      if (this.selected){
        this.changes = payload
      }
    },
    fileSetter: function(file){
      this.file = file
    },
    getHistoryInfo: function(info){
      this.historyInfo = info
      this.historyChangeFromMenu = null
    },
    historyChange: function(payload){
      this.historyChangeFromMenu = payload
    }
  },
  mounted: function(){
    this.$on('value-change', payload => this.changeSetter(payload))
  }
}
</script>

<style lang="scss">
  #editor {
    width: 100%;
    min-height: 125vh;
    margin: 3rem;
    border: 1px #cccccc solid;
    background-color: #cccccc;
    display: flex;
    flex-direction: column;
  }
</style>