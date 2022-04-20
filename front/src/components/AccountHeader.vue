<template>
  <header class="login-header">
    <div class="header-content">
      <img src="../assets/character.png" alt="">
      <h1>{{ title }}</h1>
    </div>
    <note-top/>
  </header>
</template>

<script>
import NoteTop from './NoteTop.vue'

export default {
  components: {
    NoteTop
  },
  props: {
    title: String
  }
}
</script>

<style lang="scss">
  header {
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    background-image: url('../assets/bg.png');
    background-size: cover;
    height: 35vh;

    .header-content {
      display: flex;
      align-items: flex-end;
      margin: 0 3rem;

      img {
        width: 150px;
        aspect-ratio: 3/4;
      }

      h1 {
        font-size: 4rem;
        color: white;
        text-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
        word-break: keep-all;
        width: 50%;
      }
    }
  }
</style>