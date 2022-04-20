<template>
  <section id="signup-container">
    <account-header title="다키에 오신 것을 환영합니다" />
    <article class="signup-body">
      <account-progress :step="step" :direction="direction" @change-step="tar => changeStep(Number(tar))" />
      <first-credentials v-if="step == 1" @next-step="p => {step = p; direction = true}" />
      <second-credentials v-if="step == 2" @next-step="p => {step = p; direction = true}" />
      <account-complete v-if="step == 3" />
    </article>
  </section>
</template>

<script>
import AccountHeader from '@/components/AccountHeader.vue'
import AccountProgress from '@/components/Signup/AccountProgress.vue'
import FirstCredentials from '@/components/Signup/FirstCredentials.vue'
import SecondCredentials from '@/components/Signup/SecondCredentials.vue'
import AccountComplete from '@/components/Signup/AccountComplete.vue'

export default {
  data: () => {
    return {
      step: 1,
      direction: true,
      firstCredentials: {
        email: null,
        nickname: null,
        password: null,
        passwordConf: null
      },
      secondCredentials: {
        birth: null,
        gender: null,
        character: null
      }
    }
  },
  methods: {
    changeStep: function(tar){
      if (tar - this.step > 0){
        this.direction = true
      }
      else {
        this.direction = false
      }
      this.step = tar
    },
  },
  components: {
    AccountHeader,
    AccountProgress,
    FirstCredentials,
    SecondCredentials,
    AccountComplete
  }
}
</script>

<style lang="scss">
  #signup-container {
    display: flex;
    flex-direction: column;
    border-left: 1px #cccccc solid;
    border-right: 1px #cccccc solid;
    width: 70%;
    min-height: 100vh;

    .signup-body {
      min-height: 65vh;
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
        margin: 5rem 0 0;

        label {
          width: 70%;
          display: flex;
          justify-content: space-between;
          align-items: center;
          margin: 1.5rem;

          h5 {
            width: 30%;
            font-size: 1.5rem;
            font-weight: bold;
            margin: 0;
          }

          span {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 70%;
            padding: 0.25rem 0.5rem;
            margin: 0 1rem;
            border-bottom: 1px #cccccc solid;

            input {
              border: none;
              margin: 0;
              padding: 0;
            }

            button {
              box-sizing: content-box;
              min-width: 64px;
              margin: 0;
              padding: 0.25rem 0.5rem;
              font-size: 1rem;
            }
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

        &:hover, &:disabled {
          background-color: #93D9CE;
          color: white;
        }
      }

      a {
        margin: 2rem 0;
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