<template>
  <section id="signup-container">
    <account-header title="다키에 오신 것을 환영합니다" />
    <article class="signup-body">
      <account-progress :progress="1" />
      <form action="#">
        <label for="email">
          <span>이메일</span>
          <input type="email" id="email" placeholder="이메일을 입력하세요">
        </label>
        <label for="nickname">
          <span>닉네임</span>
          <input type="text" id="nickname" placeholder="이메일을 입력하세요">
        </label>
        <label for="password">
          <span>비밀번호</span>
          <input type="password" id="password" placeholder="이메일을 입력하세요">
        </label>
        <label for="password-conf">
          <span>비밀번호 확인</span>
          <input type="password" id="password-conf" placeholder="이메일을 입력하세요">
        </label>
        <button>다음</button>
      </form>
      <router-link to="/login">
        <p>로그인</p>으로 이동
      </router-link>
    </article>
  </section>
</template>

<script>
import AccountHeader from '@/components/AccountHeader.vue'
import AccountProgress from '@/components/Signup/AccountProgress.vue'

export default {
  components: {
    AccountHeader,
    AccountProgress
  }
}
</script>

<style lang="scss">
  #signup-container {
    border-left: 1px #cccccc solid;
    border-right: 1px #cccccc solid;
    width: 70%;
    min-height: 100vh;
    overflow: auto;

    .signup-body {
      min-height: 65%;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;

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
        margin: 2rem 0 3rem;
        font-size: 1.25rem;
        font-weight: bold;
        text-decoration: none;
        color: #555555;
        display: flex;

        p {
          font-size: 1.25rem;
          color: #93D9CE;
          margin: 0
        }
      }
    }
  }
</style>