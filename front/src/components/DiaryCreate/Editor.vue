<template>
  <article id="editor">
    <menu-bar @active-menu="params => activate(params)" :selected="selected" />
    <Canvas :type="type" :isActive="isActive" @deactivate="deactivate" @select="tar => select(tar)" />
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
      selected: null
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
    }
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