<template>
  <article id="editor">
    <menu-bar />
  </article>
</template>

<script>
import MenuBar from './Editor/MenuBar.vue'

export default {
  components: {
    MenuBar
  }
}
</script>

<style lang="scss">
  #editor {
    width: 100%;
    min-height: 100vh;
    margin: 3rem;
    border: 1px #cccccc solid;
  }
</style>