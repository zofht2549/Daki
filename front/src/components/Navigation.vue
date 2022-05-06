<template>
  <nav id="nav-container">
    <router-link to="/main" class="logo" />
    <ul class="menu-box">
      <li :class="{'menu':true, 'now': true}">
        <router-link to="">
          일기쓰기
        </router-link>
      </li>
      <li :class="{'menu':true, 'now': true}">
        <router-link to="">
          일기장
        </router-link>
      </li>
      <li :class="{'menu':true, 'now': true}">
        <router-link to="/mypage">
          마이페이지
        </router-link>
      </li>
      <li class="menu">
        로그아웃
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  data: function(){
    return {
      isTop: true,
      flag: false
    }
  },
}
</script>

<style lang="scss">
  #nav-container {
    width: 100vw;
    height: 100px;
    position: fixed;
    top: 0;
    left: 0;
    padding: 3rem;
    border-bottom: 1px #cccccc solid;
    background-color: white;
    display: flex;
    justify-content: space-between;
    align-items: center;
    z-index: 98765432;

    .logo {
      width: 75px;
      aspect-ratio: 1/1;
      background-image: url('../assets/logo.png');
      background-size: cover;
      cursor: pointer;
    }

    .menu-box {
      width: 30vw;
      padding: 0;
      display: flex;
      justify-content: space-between;
      align-items: center;
      list-style: none;

      & a {
        text-decoration: none;
        color: #777777;
      }

      .menu {
        font-size: 1.5rem;
        color: #777777;
        cursor: pointer;
      }

      .now {
        color: #93D9CE;
      }
    }
  }
</style>