<template>
  <div id="app">
<<<<<<< HEAD

=======
    <!-- <nav>
      <router-link to="/">Home</router-link> |
    </nav> -->
>>>>>>> 0ee2e2f88778e5047548294ab4d8701e88b4fe22
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
