<template>
  <div class="side-options">
    <option-color-picker />

    <div class="option-box">
      <option-font-family />
      <option-font-size />
    </div>

    <label :class="[{'clicked': align == 'left'}, 'align-left']" title="왼쪽정렬" for="align-left"
     @click="optionClickHandler">
      <input type="radio" id="align-left" value="left" v-model="align">
     </label>

    <label :class="[{'clicked': align == 'center'}, 'align-center']" title="중앙정렬" for="align-center"
     @click="optionClickHandler">
      <input type="radio" id="align-center" value="center" v-model="align">
     </label>

    <label :class="[{'clicked': align == 'right'}, 'align-right']" title="오른쪽정렬" for="align-right"
     @click="optionClickHandler">
      <input type="radio" id="align-right" value="right" v-model="align">
     </label>
  </div>
</template>

<script>
import OptionColorPicker from './Option-ColorPicker.vue'
import OptionFontFamily from './Option-FontFamily.vue'
import OptionFontSize from './Option-FontSize.vue'

export default {
  data: function(){
    return {
      align: 'left',
      bold: false,
      italic: false,
      underLine: false,
    }
  },
  components: {
    OptionColorPicker,
    OptionFontFamily,
    OptionFontSize
  },
  methods: {
    optionClickHandler: function(e){
      if (this.align == e.target.htmlFor.slice(6, )){
        e.preventDefault();
        this.align = 'left'
      }
    }
  }
}
</script>

<style lang="scss">
  .side-options {
    .popup-container {
      position: fixed;
      top: 0;
      left: 0;
      width: 100vw;
      height: 100vh;
      z-index: 1;
    }

    .option-box {
      height: 75%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }

    .align-left {
      background-image: url('../../../assets/Editor/align-left.png');
    }
  
    .align-center {
      background-image: url('../../../assets/Editor/align-center.png');
    }
  
    .align-right {
      background-image: url('../../../assets/Editor/align-right.png');
    }
  }
</style>