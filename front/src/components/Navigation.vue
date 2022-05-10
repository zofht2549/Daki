<template>
  <nav id="nav-container">
    <router-link to="/main" class="logo" />
    <ul class="menu-box">
      <li :class="['menu', {'now': path == '/diary-create'}]">
        <router-link to="/diary-create">
          일기쓰기
        </router-link>
      </li>
      <li :class="['menu', {'now': false}]">
        <router-link to="">
          일기장
        </router-link>
      </li>
      <li :class="['menu', {'now': path == '/mypage'}]">
        <router-link to="/mypage">
          마이페이지
        </router-link>
      </li>
      <li class="menu">
        <a>
          로그아웃
        </a>
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
  computed: {
    path: function(){
      return this.$route.path
    }
  }
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

      .menu > a {
        font-size: 1.5rem;
        color: #777777;
        text-decoration: none;
        cursor: pointer;
      }

      .now > a {
        font-weight: bold;
        color: #93D9CE;
      }

    }
  }
</style>