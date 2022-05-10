<template>
  <form id="first-form" @submit.prevent="nextStep()">
    <label for="email">
      <h5>이메일</h5>
      <span>
        <input type="email" id="email" placeholder="이메일을 입력하세요" v-model="credentials.email">
        <button :disabled="isValid.email" @click.prevent="validator('email')">중복확인</button>
      </span>
      <div class="help-message-box" v-show="isActive.email">
        <p class="help-message corr" v-if="isValid.email">
          사용가능한 이메일입니다.
        </p>
        <p class="help-message warn" v-else>
          사용할 수 없는 이메일입니다.
        </p>
      </div>
    </label>

    <label for="nickname">
      <h5>닉네임</h5>
      <span>
        <input type="text" id="nickname" placeholder="닉네임은 2글자 이상, 8글자 이하입니다 " v-model="credentials.nickname">
        <button :disabled="isValid.nickname" @click.prevent="validator('nickname')">중복확인</button>
      </span>
      <div class="help-message-box" v-show="isActive.nickname">
        <p class="help-message corr" v-if="isValid.nickname">
          사용가능한 닉네임입니다.
        </p>
        <p class="help-message warn" v-else>
          사용할 수 없는 닉네임입니다.
        </p>
      </div>
    </label>

    <label for="password">
      <h5>비밀번호</h5>
      <span>
        <input type="password" id="password" placeholder="비밀번호는 8 ~ 16자, 숫자, 문자, 특수문자 중 두 가지를 포함합니다" v-model="credentials.password"
         @change="validator('password')">
      </span>
      <div class="help-message-box" v-show="isActive.password">
        <p class="help-message corr" v-if="isValid.password">
          사용가능한 비밀번호입니다.
        </p>
        <p class="help-message warn" v-else>
          사용할 수 없는 비밀번호입니다.
        </p>
      </div>
    </label>

    <label for="password-conf">
      <h5>비밀번호 확인</h5>
      <span>
        <input type="password" id="password-conf" placeholder="비밀번호를 다시 입력하세요" v-model="credentials.passwordConf"
         @change="validator('passwordConf')">
      </span>
      <div class="help-message-box" v-show="isActive.passwordConf">
        <p class="help-message corr" v-if="isValid.passwordConf">
          비밀번호가 확인되었습니다.
        </p>
        <p class="help-message warn" v-else>
          비밀번호가 맞지 않습니다.
        </p>
      </div>
    </label>

    <button>다음</button>

    <router-link to="/login">
      <p>로그인</p>으로 이동
    </router-link>
  </form>
</template>

<script>
import customAxios from '../../customAxios.js'

export default {
  data: function(){
    return {
      credentials: {...this.firstCredentials},
      isValid: {...this.validData},
      isActive: {
        email: false,
        nickname: false,
        password: false,
        passwordConf: false
      }
    }
  },
  props: {
    firstCredentials: Object,
    validData: Object
  },
  methods: {
    nextStep: function(){
      this.$emit('get-credentials', this.credentials)
      this.$emit('next-step', 2)
    },
    validator: function(payload){
      if (payload == 'email'){
        const emailValidator = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/
        if (emailValidator.exec(this.credentials.email)){
          return this.emailCheck()
        }
        this.isActive.email = true
      }
      else if (payload == 'nickname'){
        if (this.credentials.nickname.length > 1 && this.credentials.nickname.length < 9){
          return this.nicknameCheck()
        }
        this.isActive.nickname = true
      }
      else if (payload == 'password'){
        this.isActive.password = true
        const passValidator = /^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9!@#$%^&*]).{8,16}$/
        if (passValidator.exec(this.credentials.password)){
          this.isValid.password = true
        }
        else {
          this.isValid.password = false
        }
        if (this.credentials.passwordConf){
          this.validator('passwordConf')
        }
      }
      else if (payload == 'passwordConf'){
        this.isActive.passwordConf = true
        if (this.credentials.password == this.credentials.passwordConf){
          this.isValid.passwordConf = true
        }
        else {
          this.isValid.passwordConf = false
        }
      }
    },
    emailCheck: function(){
      customAxios({
        method: 'get',
        url: `/api/auth/emailCheck/${this.credentials.email}`
      })
      .then(() => this.isValid.email = true)
      .catch()
      .finally(() => this.isActive.email = true)
    },
    nicknameCheck: function(){
      customAxios({
        method: 'get',
        url: `/api/auth/nickNameCheck/${this.credentials.nickname}`
      })
      .then(() => this.isValid.nickname = true)
      .catch()
      .finally(() => this.isActive.nickname = true)
    }
  },
  watch: {
    'credentials.email': function(){
      if (this.isValid.email){
        this.isValid.email = false
      }
      this.isActive.email = false
    },
    'credentials.nickname': function(){
      if (this.isValid.nickname){
        this.isValid.nickname = false
      }
      this.isActive.nickname = false
    },
    'credentials.password': function(){
      this.isActive.password = false
    },
    'credentials.passwordConf': function(){
      this.isActive.passwordConf = false
    }
  },
  created: function(){
    for (const [key, value] of Object.entries(this.isValid)){
      this.isActive[key] = value
    }
  },
  destroyed: function(){
    this.$emit('get-credentials', this.credentials)
    this.$emit('get-valid-data', this.isValid)
  }
}
</script>

<style lang="scss">
  ::placeholder {
    font-size: 1rem !important;
    letter-spacing: 0;
  }

  .help-message-box {
    position: absolute;
    bottom: -0.25rem;
    left: 29%;

    .help-message {
      margin: 0;

      &.warn {
        color: rgb(250, 75, 75);
      }

      &.corr {
        color: #39bda9;
      }
    }
  }
</style>