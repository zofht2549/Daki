<template>
  <section id="login-container">
    <header class="login-header">
      <div class="header-content">
        <img src="../assets/Login/character.png" alt="">
        <h1>다이어리키우기</h1>
      </div>
      <note-top/>
    </header>
    <article class="login-body">
      <form action="#">
        <label for="email">
          <span>Email</span>
          <input type="email" id="email" placeholder="이메일을 입력하세요">
        </label>
        <label for="password">
          <span>Password</span>
          <input type="password" id="password" placeholder="비밀번호를 입력하세요">
        </label>
        <button>로그인</button>
      </form>
      <router-link to="/signup">
        회원가입
      </router-link>
      <div class="social-login-box">
        <kakao-login/>
        <google-login/>
      </div>
    </article>
  </section>
</template>

<script>
import NoteTop from '@/components/NoteTop'
import KakaoLogin from '../components/Login/KakaoLogin'
import GoogleLogin from '../components/Login/GoogleLogin'


export default {
  components: {
    NoteTop,
    KakaoLogin,
    GoogleLogin
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

    header {
      display: flex;
      flex-direction: column;
      justify-content: flex-end;
      background-image: url('../assets/Login/bg.png');
      background-size: cover;
      height: 35%;

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
        }
      }
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
        margin: 4rem 0 2rem;

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
        margin: 1rem;
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