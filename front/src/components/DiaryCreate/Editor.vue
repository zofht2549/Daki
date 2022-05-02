<template>
  <article id="editor" ref="editor">
    <menu-bar @active-menu="params => activate(params)" :selected="selected" />
    <Canvas :type="type" :isActive="isActive" :changes="changes" @deactivate="deactivate" @select="tar => select(tar)" />
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
    }
  },
  components: {
    MenuBar,
    Canvas
  },
  methods: {
    activate: function(params){
      this.type = params.menu
      this.isActive = params.isActive
    },
    deactivate: function(){
      this.isActive = false
    },
    select: function(tar){
      this.selected = tar
    },
    setter: function(payload){
      if (this.selected){
        this.changes = payload
      }
    }
  },
  mounted: function(){
    this.$on('value-change', payload => this.setter(payload))
  }
}
</script>

<style lang="scss">
  #editor {
    width: 100%;
    min-height: 100vh;
    margin: 3rem;
    border: 1px #cccccc solid;
    background-color: #cccccc;
    display: flex;
    flex-direction: column;
  }
</style>