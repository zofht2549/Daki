<template>
  <div class="color-picker-container">
    <label :class="[{'clicked': active}, 'text-color']" title="글자색" for="text-color">
      <input type="checkbox" id="text-color" :value="true" v-model="active">
      <span class="curr-color" :style="{backgroundColor: color}" :title="color" />
      <color-panel v-if="active" v-model="color" />
    </label>
    <div class="popup-container" v-if="active" @click="close" />
  </div>
</template>

<script>
import { ColorPanel } from 'one-colorpicker'

export default {
  data: function(){
    return {
      active: false,
      color: '#000000',
    }
  },
  components: {
    ColorPanel
  },
  methods: {
    close: function(e){
      console.log(e.target)
      this.active = false
    }
  }
}
</script>

<style lang="scss">
  .color-picker-container {
    display: flex;
    
    .text-color {
      background-image: url('../../../assets/Editor/text-color.png');

      .curr-color {
        position: absolute;
        bottom: 0;
        left: 10%;
        width: 80%;
        height: 10px;
        border-radius: 10px;
      }

      &.clicked {
        z-index: 2;
      }

      .popup-container {
        position: fixed;
        top: 0;
        left: 0;
        width: 100vw;
        height: 100vh;
      }
    }
  }
</style>