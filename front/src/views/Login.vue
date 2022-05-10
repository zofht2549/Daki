<template>
  <section id="login-container">
    <account-header title="다이어리 키우기" />
    <article class="login-body">
      <form action="#">
        <label for="email">
          <span>Email</span>
          <input 
            type="email" 
            id="email" 
            placeholder="이메일을 입력하세요"
            v-model="credentials.email">
        </label>
        <label for="password">
          <span>Password</span>
          <input
            type="password"
            id="password" 
            placeholder="비밀번호를 입력하세요"
            v-model="credentials.password">
        </label>
        <button @click="onLogin">로그인</button>
        <router-link to="/signup">
          회원가입
        </router-link>
      </form>
      <div class="social-login-box">
        <kakao-login/>
        <google-login/>
      </div>
    </article>
  </section>
</template>

<script>
import AccountHeader from '../components/AccountHeader'
import KakaoLogin from '../components/Login/KakaoLogin'
import GoogleLogin from '../components/Login/GoogleLogin'


export default {
  data:function(){
    return{
      credentials:{
        email: null,
        password: null,
      }
    }
  },
  components: {
    AccountHeader,
    KakaoLogin,
    GoogleLogin
  },
  methods:{
    onLogin(){
      this.$store.dispatch('login',this.credentials)
      console.log('login click')
    },
  }
}
</script>

<style lang="scss">
  #login-container {
    border-left: 1px #cccccc solid;
    border-right: 1px #cccccc solid;
    width: 70%;
    min-height: 100vh;

    & > * {
      width: 100%;
    }

    .login-body {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 65%;

      form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 70%;
        margin: 4rem 0 0;

        label {
          width: 70%;
          display: flex;
          justify-content: space-between;
          margin: 1.5rem;

          span {
            width: 30%;
            font-size: 1.5rem;
            font-weight: bold;
          }

          input {
            width: 70%;
            border: none;
            border-bottom: 1px #cccccc solid;
            font-size: 1.5rem;
            margin: 0 1rem;
            padding: 0.25rem 0.5rem;

            &:focus {
              outline: none;

               &::-webkit-input-placeholder{
                color: transparent;
              }
            }

            &[type='password'] {
              font-family: Verdana;
              letter-spacing: 0.125rem;
            }

            &::placeholder {
              font-size: 1.25rem;
              color: #cccccc;
            }
          }
        }

        button {
          font-size: 1.25rem;
          font-weight: bold;
          border-radius: 10px;
          border: 1px #93D9CE solid;
          color: #93D9CE;
          background-color: white;
          box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
          margin: 3rem 0 0;
          padding: 1rem 5rem;
          cursor: pointer;

          &:hover {
            background-color: #93D9CE;
            color: white;
          }
        }
      }

      a {
        margin: 2rem;
        font-size: 1.25rem;
        font-weight: bold;
        text-decoration: none;
        color: #555555;
      }

      .social-login-box {
        display: flex;
        margin: 1rem;
        width: 50%;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
      }

      .social-login {
        margin: 0.5rem;
        width: 220px;
        height: 50px;
        border-radius: 12px;
        display: flex;
        justify-content: center;
        align-items: center;
        border: none;
        cursor: pointer;

        img {
          width: 18px;
          aspect-ratio: 1/1;
          margin: 0.5rem;
        }

        p {
          font-size: 0.9rem;
          letter-spacing: 0;
          color: rgba(0, 0, 0, 0.85);
          padding: 10px;
          margin: 0;
          line-height: 0.9rem;
        }
      }
    }
  }
</style>