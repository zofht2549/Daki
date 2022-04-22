<template>
  <div id="popup-container" @click="closePopUp">
    <div class="popup-box">
      <img src="../../assets/close.png" class="close"
      @click="closePopUp">
      <span class="popup-header">
        <h3>{{ target }}</h3>
        <h3>OOO님의 일기</h3>
      </span>
      <ol class="popup-list">
        <li v-for="diary of diaries" :key="diary.title" class="list-item">
          {{ diary.title }}
        </li>
      </ol>
    </div>
  </div>
</template>

<script>
import Dummy from './Dummy.js'

export default {
  props: {
    target: String
  },
  computed: {
    diaries: function(){
      return Dummy[this.target]
    }
  },
  methods: {
    closePopUp: function(e){
      if (e.target.id == 'popup-container' || e.target.className == 'close'){
        this.$emit('close-pop-up')
      }
    }
  }
}
</script>

<style lang="scss">
  #popup-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.35);
    z-index: 3;

    .popup-box {
      background-color: white;
      width: 50%;
      height: 60vh;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      padding: 3rem;
      border-radius: 10px;
      position: relative;

      .close {
        width: 20px;
        aspect-ratio: 1/1;
        position: absolute;
        top: 10px;
        right: 10px;
        cursor: pointer;
      }

      .popup-header {
        align-self: flex-start;

        h3 {
          font-size: 2rem;
          margin: 0.5rem;
        }
      }

      .popup-list {
        width: 100%;
        height: 80%;
        box-shadow: inset 1px 2px 4px rgba(0, 0, 0, 0.35);
        border-radius: 10px;
        overflow: auto;

        .list-item {
          padding: 0.75rem;
          border-bottom: 2px #cccccc solid;
          font-size: 1.25rem;
          cursor: pointer;

          &:hover {
            background-color: #cccccc;
            color: white;
          }
        }
      }
    }
  }
</style>