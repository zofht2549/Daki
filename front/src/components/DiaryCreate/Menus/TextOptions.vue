<template>
  <div class="side-options">
    <option-color-picker :color="color" />

    <div class="option-box">
      <option-font-family :family="family" />
      <option-font-size :size="size" />
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
      bold: false
    }
  },
  props: {
    selected: Object
  },
  components: {
    OptionColorPicker,
    OptionFontFamily,
    OptionFontSize
  },
  computed: {
    color: function(){
      if (this.selected){
        return this.selected.fontStyle.color
      }
      return null
    },
    family: function(){
      if (this.selected){
        return this.selected.fontStyle.family
      }
      return null
    },
    size: function(){
      if (this.selected){
        return this.selected.fontStyle.size
      }
      return null
    }
  },
  methods: {
    optionClickHandler: function(e){
      if (this.align == e.target.htmlFor){
        e.preventDefault();
      }
    },
    getter: function(){
      if (this.selected){
        this.align = this.selected.fontStyle.align
        this.bold = this.selected.fontStyle.weight == 'bold' ? true:false
      }
    },
    setter: function(){
      this.$parent.$parent.$emit('value-change', {align: this.align})
    }
  },
  watch: {
    selected: function(){
      this.getter()
    },
    align: function(){
      if (this.align !== this.selected.fontStyle.align){
        this.setter()
      }
    }
  },
  mounted: function(){
    this.getter()
  }
}
</script>

<style lang="scss">
  .side-options {

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