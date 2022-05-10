<template>
  <nav>
    <div class="nav-mobile">
      <div class="mobile-bar">
        <router-link to="/main">
          <img src="../assets/logo.png" alt="">
        </router-link>
        
        <button
          @click="menuClick()">
          메뉴버튼
        </button>
      </div>
    </div>
    <div id="nav-container" :class="{ active : onClick }">
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
    </div>
  </nav>
</template>

<script>
export default {
  data: function(){
    return {
      isTop: true,
      flag: false,

      onClick: false,
    }
  },
  methods:{
    menuClick(){
      if(this.onClick == false){
        this.onClick = true
      }else{
        this.onClick = false
      }
    }
  }
}
</script>

<style lang="scss">
@media only screen and (min-width:800px){
  .nav-mobile{
    button{
      display: none;
    }
    img{
      display: none;
    }
  }
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
    z-index: 10;

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
}
@media only screen and (max-width:799px){
  .nav-mobile{
    width: 100vw;
    height: 80px;
    position: fixed;
    top: 0;
    left: 0;
    background-color: #FED771;
    text-align: right;
    & > .mobile-bar{
      display: flex;
      justify-content: space-between;
      height: 100%;

        & > button{
        margin: 10px;
        padding: 10px;
        //
        img{
          height: 100%;
        }
      }
    }

  }

  #nav-container{
    & {
      opacity: 0;
      display: none;
      visibility: hidden;
    }
    &.active{
      &{
        opacity: 1;
        display: block;
        visibility: visible;
        height: 100vw;
        float: right;
        top: 60px;
        right: 0px;
        position: fixed;
        z-index: 3;
        margin: 0px;

        @keyframes fadeinRight{
          0%{
            opacity: 0;
            transform: translate3d(100%,0,0);
          }
          to{
            opacity: 1;
            transform: translateZ(0);
          }
        }
        & > .menu-box{
          background: #FED771;
          height: 100vw;
          // height: 100%;
          top: 0px;
          list-style: none;
          text-align: center;
          animation: fadeinRight 1s;
          vertical-align: middle;

          & a {
            text-decoration: none;
            color: #333;
          }
          & > .menu{
            width: 200px;
            height: 50px;
            text-align: center;
            vertical-align: middle;
          }
        }
        & a {
          text-decoration: none;
        }
      }
    }
  }

}

</style>