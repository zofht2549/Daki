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
  height: 100vh;
  display: flex;
  justify-content: center;
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
