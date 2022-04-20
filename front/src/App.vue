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
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
