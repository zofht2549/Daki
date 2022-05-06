<template>
  <section id="diary-create-container">
    <navigation />
    <editor />
  </section>
</template>

<script>
import Navigation from '../components/Navigation.vue'
import Editor from '../components/DiaryCreate/Editor.vue'

export default {
  components: {
    Navigation,
    Editor
  }
}
</script>

<style lang="scss">
  #diary-create-container {
    border-left: 1px #cccccc solid;
    border-right: 1px #cccccc solid;
    width: 70%;
    min-height: 100vh;
    padding-top: 100px;
    display: flex;
    justify-content: center;
  }
</style>