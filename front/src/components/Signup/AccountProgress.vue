<template>
  <div class="progress-box">
    <hr class="progress-bar">
    <div :class="{'progress':true, 'done': progress > 0}">
      <p :class="{ 'done-txt': progress > 0 }">기본 정보</p>
    </div>
    <div :class="{'progress':true, 'done': progress > 1}">
      <p :class="{ 'done-txt': progress > 1 }">개인 정보</p>
    </div>
    <div :class="{'progress':true, 'done': progress > 2}">
      <p :class="{ 'done-txt': progress > 2 }">가입 완료</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    progress: Number
  },
  mounted: function(){
    const dots = document.querySelectorAll('.progress')
    const bar = document.querySelectorAll('.progress-bar')[0]

    dots.forEach((ele, idx) => {
      ele.style.left = `${50 * idx}%`
    })
    bar.style.width = `${50 * (this.progress - 1)}%`
  }
}
</script>

<style lang="scss">
  .progress-box {
    display: flex;
    width: 50%;
    justify-content: space-between;
    align-items: center;
    margin-top: 5%;
    border-top: 1px #93D9CE solid;
    position: relative;

    .progress-bar {
      width: 100%;
      position: absolute;
      border: 1px #93D9CE solid;
      top: 0;
      margin: 0;
      z-index: 1;
    }

    .progress {
      box-sizing: border-box;
      width: 20px;
      aspect-ratio: 1/1;
      outline: 1px #93D9CE solid;
      background-color: white;
      border-radius: 50%;
      z-index: 2;
      position: absolute;

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
      border: 5px white solid;
      background-color: #93D9CE;
    }
  }
</style>