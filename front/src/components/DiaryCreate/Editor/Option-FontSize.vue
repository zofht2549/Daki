<template>
  <div :class="[{'clicked': active}, 'font-size-container']"
   :title="active? '':'글자크기'">
    <input class="curr-font-size" type="number" v-model="size" min="1" max="200"
     @click="() => activate(true)">
    <div class="font-size-controller" v-if="active">
      <hr class="controller-rail">
      <span class="controller-btn" />
    </div>

    <div class="popup-container" v-if="active" @click="() => activate(false)" />
  </div>
</template>

<script>
export default {
  data: function(){
    return {
      size: 10,
      active: false
    }
  },
  methods: {
    activate: function(payload){
      this.active = payload
    }
  }
}
</script>

<style lang="scss">
  .font-size-container {
    width: 50px;
    position: relative;
    z-index: 1;

    &.clicked {
      z-index: 2;
    }

    .curr-font-size {
      width: 100%;
      padding: 0.25rem;
      font-size: 1rem;
      border: none;
      cursor: pointer;

      &::-webkit-outer-spin-button, &::-webkit-inner-spin-button {
        appearance: none;
      }

      &:focus {
        border-radius: 10px;
        outline: 1px #cccccc solid;
      }
    }

    .font-size-controller {
      position: absolute;
      bottom: -30px;
      left: 0;
      width: 300px;
      height: 20px;
      display: flex;
      align-items: center;
      background-color: rgba(204, 204, 204, 0.5);
      border-radius: 10px;

      .controller-rail {
        width: 280px;
        border: 1px rgba(141, 141, 141, 0.5) solid;
        border-radius: 10px;
      }

      .controller-btn {
        width: 12px;
        height: 12px;
        background-color: #d9d9d9;
        background-image: linear-gradient(315deg, #d9d9d9 0%, #f6f2f2 74%);
        border-radius: 50%;
        position: absolute;
      }
    }

    .popup-container {
      position: fixed;
      top: 0;
      left: 0;
      width: 100vw;
      height: 100vh;
      z-index: 1;
    }
  }
</style>