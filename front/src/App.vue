<template>
  <div id="app">
    <!-- <nav>
      <router-link to="/">Home</router-link> |
    </nav> -->
    <router-view/>
  </div>
</template>

<style lang="scss">
#app {
  width: 100vw;
  min-height: 100vh;
  overflow: auto;
  display: flex;
  justify-content: center;
  overflow: auto;
  position: relative;

  & * {
    box-sizing: border-box;
  }
}
</style>
