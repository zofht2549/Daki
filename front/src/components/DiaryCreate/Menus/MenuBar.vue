<template>
  <div class="menu-bar">
    <label :class="[{'clicked': menu == 'text'}, 'text']" title="텍스트" for="text"
     @click="menuClickHandler" />
    <input type="radio" id="text" value="text" v-model="menu">

    <label :class="[{'clicked': menu == 'mic'}, 'mic']" title="음성 녹음" for="mic"
     @click="menuClickHandler" />
    <input type="radio" id="mic" value="mic" v-model="menu">

    <label :class="[{'clicked': menu == 'image'}, 'image']" title="이미지 업로드" for="image"
     @click="menuClickHandler" />
    <input type="radio" id="image" value="image" v-model="menu">

    <label :class="[{'clicked': menu == 'sticker'}, 'sticker']" title="스티커" for="sticker"
     @click="menuClickHandler" />
    <input type="radio" id="sticker" value="sticker" v-model="menu">
    
    <text-options v-if="menu == 'text'" :selected="selected" />
  </div>
</template>

<script>
import TextOptions from './TextOptions.vue'

export default {
  data: function(){
    return {
      menu: null,
      isActive: false
    }
  },
  props: {
    selected: Object
  },
  components: {
    TextOptions
  },
  methods: {
    menuClickHandler: function(e){
      if (this.menu == e.target.htmlFor){
        e.preventDefault();
        this.menu = null
        this.isActive = false
      }
      else {
        this.isActive = true
      }
    }
  },
  watch: {
    selected: function(){
      if (this.selected){
        this.menu = this.selected.type
      }
      else {
        this.menu = null
        this.isActive = false
      }
    },
    menu: function(){
      if (this.menu){
        this.$emit('active-menu', {menu: this.menu, isActive: this.isActive})
      }
      else {
        this.$emit('active-menu', {menu: this.menu, isActive: this.isActive})
      }
    }
  }
}
</script>

<style lang="scss">
  .menu-bar {
    width: 100%;
    height: 70px;
    background-color: white;
    border-bottom: 1px #cccccc solid;
    display: flex;
    align-items: center;

    label {
      width: 40px;
      height: 40px;
      background-size: 30px 30px;
      background-repeat: no-repeat;
      background-color: transparent;
      background-position: center;
      border: none;
      margin: 1rem;
      border-radius: 10px;
      position: relative;
      cursor: pointer;

      &:active {
        background-color: #777777;
      }
      
      &.clicked {
        background-color: #cccccc;
      }
    }

    input[type="radio"], input[type="checkbox"]{
      display: none;
    }

    .side-options {
      height: 100%;
      display: flex;
      align-items: center;
      border-left: 1px #cccccc solid;
    }

    .text {
      background-image: url('../../../assets/Editor/text.png');
    }

    .mic {
      background-image: url('../../../assets/Editor/mic.png');
    }

    .image {
      background-image: url('../../../assets/Editor/image.png');
    }

    .sticker {
      background-image: url('../../../assets/Editor/sticker.png');
    }
  }
</style>