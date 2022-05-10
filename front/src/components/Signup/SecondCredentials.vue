<template>
  <form id="second-form" @submit.prevent="nextStep()">
    <span class="message">
      <h6>거의 다 왔습니다!</h6>
      <h6>개인 정보를 입력해주세요</h6>
    </span>
    <label for="birth">
      <h5>생년월일</h5>
      <span>
        <input type="date" id="birth" v-model="credentials.birth">
      </span>
    </label>
    <label for="gender-box">
      <h5>성별</h5>
      <span id="gender-box">
        <label for="male">
          남
          <input type="radio" name="gender" value="male" id="male" v-model="credentials.gender">
        </label>
        <label for="female">
          여
          <input type="radio" name="gender" value="female" id="female" v-model="credentials.gender">
        </label>
      </span>
    </label>
    <label for="character-box">
      <h5>캐릭터</h5>
      <span id="character-box">
        <div class="character">
          <input type="radio" name="character" value="char-1" id="char-1" v-model="credentials.character">
          <label for="char-1"/>
        </div>
        <div class="character">
          <input type="radio" name="character" value="char-2" id="char-2" v-model="credentials.character">
          <label for="char-2"/>
        </div>
        <div class="character">
          <input type="radio" name="character" value="char-3" id="char-3" v-model="credentials.character">
          <label for="char-3"/>
        </div>
      </span>
    </label>
    <button>가입</button>
    <router-link to="/login">
      <p>로그인</p>페이지로 이동
    </router-link>
  </form>
</template>

<script>
export default {
  data: function(){
    return {
      credentials: {...this.secondCredentials},
      isValid: {...this.validData}
    }
  },
  props: {
    secondCredentials: Object,
    validData: Object
  },
  methods: {
    nextStep: function(){
      this.$emit('get-credentials', this.credentials)
      this.$emit('next-step', 3)
    }
  },
  watch: {
    'isValid.birth': function(){
      if (this.credentials.birth){
        this.isValid.birth = true
      }
      else {
        this.isValid.birth = false
      }
    },
    'isValid.gender': function(){
      if (this.credentials.gender){
        this.isValid.gender = true
      }
      else {
        this.isValid.gender = false
      }
    },
    'isValid.character': function(){
      if (this.credentials.character){
        this.isValid.character = true
      }
      else {
        this.isValid.character = false
      }
    }
  },
  destroyed: function(){
    this.$emit('get-credentials', this.credentials)
    this.$emit('get-valid-data', this.isValid)
  }
}
</script>

<style lang="scss">
#signup-container {

  #second-form{
    align-items: flex-start;
    width: 60%;

    .message {
      margin: 2rem 0;

      h6 {
        font-size: 1.5rem;
        font-weight: bold;
        margin: 0.25rem 0;
      }
    }

    input[type="date"]{
      font-size: 1rem;
      width: 100%;
    }

    label {
      width: 60%;

      span {
        width: 60%;
        display: flex;
        justify-content: space-between;
        align-items: center;

        label {
          display: inline-block;
          margin: 0;
        }
      }
    }

    #gender-box {
      border: none;
      min-width: 180px;

      input {
        width: 20px;
        margin: 0 2rem 0 1rem;
      }
    }

    & > :nth-child(4) {
      align-items: flex-start;
      width: 100%;

      h5 {
        width: 20%;
      }
    }

    #character-box {
      border: none;
      width: 100%;

      .character {
        box-sizing: content-box;
        width: 30%;
        aspect-ratio: 1/1;
        position: relative;
        box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
        margin: 0 1rem;
      }

      input[type="radio"] {
        display: none;
        cursor: pointer;
      }

      label {
        width: 100%;
        aspect-ratio: 1/1;
        background-image: url('../../assets/character.png');
        background-size: contain;
        background-position: center;
        background-repeat: no-repeat;
        cursor: pointer;
      }

      input[type="radio"]:checked + label {
        outline: 10px #93D9CE solid;
      }
    }

    button, a {
      align-self: center;
    }
  }
}
</style>