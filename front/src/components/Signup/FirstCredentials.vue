<template>
  <form id="first-form" @submit.prevent="nextStep()">
    <label for="email">
      <h5>이메일</h5>
      <span>
        <input type="email" id="email" placeholder="이메일을 입력하세요">
        <button>중복확인</button>
      </span>
    </label>
    <label for="nickname">
      <h5>닉네임</h5>
      <span>
        <input type="text" id="nickname" placeholder="이메일을 입력하세요">
        <button>중복확인</button>
      </span>
    </label>
    <label for="password">
      <h5>비밀번호</h5>
      <span>
        <input type="password" id="password" placeholder="이메일을 입력하세요">
      </span>
    </label>
    <label for="password-conf">
      <h5>비밀번호 확인</h5>
      <span>
        <input type="password" id="password-conf" placeholder="이메일을 입력하세요">
      </span>
    </label>
    <button>다음</button>
    <router-link to="/login">
      <p>로그인</p>으로 이동
    </router-link>
  </form>
</template>

<script>
export default {
  methods: {
    nextStep: function(){
      console.log('me')
      this.$emit('next-step', 2)
    }
  }
}
</script>