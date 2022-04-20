<template>
  <div class="progress-box">
    <hr class="progress-bar">
    <div :class="{'progress':true, 'done': step > 0}" id="1" @click="e => changeStep(e)">
      <p :class="{ 'done-txt': step > 0 }">기본 정보</p>
    </div>
    <div :class="{'progress':true, 'done': step > 1}" id="2" @click="e => changeStep(e)">
      <p :class="{ 'done-txt': step > 1 }">개인 정보</p>
    </div>
    <div :class="{'progress':true, 'done': step > 2}" id="3" @click="e => changeStep(e)">
      <p :class="{ 'done-txt': step > 2 }">가입 완료</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    step: Number,
    direction: Boolean
  },
  methods: {
    changeStep: function(e){
      if (e.target.id){
        this.$emit('change-step', e.target.id)
      }
      else {
        this.$emit('change-step', e.target.parentElement.id)
      }
    },
    setProgressBar: function(){
      const bar = document.querySelectorAll('.progress-bar')[0]

      if (this.step == 1 && !this.direction){
        bar.style.animation = 'decrease2 0.5s ease-in both'
      }
      else if (this.step == 2){
        if (this.direction){
          bar.style.animation = 'increase1 0.5s ease-in both'
        }
        else {
          bar.style.animation = 'decrease1 0.5s ease-in both'
        }
      }
      else if (this.step == 3){
        bar.style.animation = 'increase2 0.5s ease-in both'
      }
    }
  },
  mounted: function(){
    const dots = document.querySelectorAll('.progress')

    dots.forEach((ele, idx) => {
      ele.style.left = `${50 * idx}%`
    })

    this.setProgressBar()
  },
  updated: function(){
    this.setProgressBar()
  }
}
</script>

<style lang="scss">
  .progress-box {
    display: flex;
    width: 50%;
    min-width: 500px;
    justify-content: space-between;
    align-items: center;
    margin-top: 5%;
    border-top: 1px #777777 solid;
    position: relative;

    @keyframes increase1 {
      from {width: 0%;}
      to {width: 50%;}
    }

    @keyframes increase2 {
      from {width: 50%;}
      to {width: 100%;}
    }

    @keyframes decrease1 {
      from {width: 100%;}
      to {width: 50%;}
    }

    @keyframes decrease2 {
      from {width: 50%;}
      to {width: 0%;}
    }

    .progress-bar {
      position: absolute;
      border: 4px #93D9CE solid;
      border-radius: 5px;
      top: -4px;
      margin: 0;
      z-index: 1;
    }

    .progress {
      box-sizing: border-box;
      width: 20px;
      aspect-ratio: 1/1;
      outline: 1px #777777 solid;
      background-color: white;
      border-radius: 50%;
      z-index: 2;
      position: absolute;
      cursor: pointer;

      p {
        min-width: 100px;
        color: #777777;
        font-size: 1.25rem;
        font-weight: bold;
        position: absolute;
        word-break: keep-all;
        top: 10px;
        left: -32px;
      }

      .done-txt {
        font-size: 1.35rem;
        left: -40px;
        color: #93D9CE;
      }
    }
    .done {
      outline: 1px #93D9CE solid;
      border: 5px white solid;
      background-color: #93D9CE;
    }
  }
</style>