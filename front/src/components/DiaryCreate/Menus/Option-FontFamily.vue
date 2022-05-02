<template>
  <div :class="[{'clicked': active}, 'font-family-container']"
   :title="active ? '':'글자체'">
    <p class="curr-font-family" @click="() => activate(true)"
     :style="{fontFamily: currFamily}">
      {{ fonts[currFamily] }}
    </p>
    <ul class="font-family-list" v-if="active">
      <li :class="['font-family-item', {'chosen': font == currFamily}]" v-for="(name, font) in fonts"
       :key="font" :style="{ fontFamily: font }">
        {{ name }}
      </li>
    </ul>

    <div class="popup-container" v-if="active" @click="() => activate(false)" />
  </div>
</template>

<script>
import {Fonts} from './Fonts.js'
import './Fonts.scss'

export default {
  data: function(){
    return {
      currFamily: 'HallymMjo-Regular',
      active: false
    }
  },
  props: {
    family: String
  },
  computed: {
    fonts: function(){
      return Fonts
    }
  },
  methods: {
    activate: function(payload){
      this.active = payload
    },
    /// 선택된 요소의 값을 현재 값에 부여 ///
    getter: function(){
      if (this.family){
        this.currFamily = this.family
      }
    },
    /// 변경된 현재 값을 선택된 요소에 적용 ///
    setter: function(){
      this.$parent.$parent.$parent.$emit('value-change', {family: this.currFamily})
    }
  },
  watch: {
    family: function(){
      this.getter()
    },
    currFamily: function(){
      if (this.currFamily !== this.family){
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
  .font-family-container {
    width: 100px;
    position: relative;
    z-index: 1;

    &.clicked {
      z-index: 2;
    }

    .curr-font-family {
      font-size: 1rem;
      margin: 0;
      cursor: pointer;
    }

    .font-family-list {
      display: grid;
      max-height: 500px;
      grid-template-columns: max-content;
      background-color: white;
      border: 1px #cccccc solid;
      border-radius: 10px;
      box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
      position: absolute;
      top: -0.5rem;
      left: -0.5rem;
      margin: 0;
      padding: 0;
      overflow-x: hidden;
      overflow-y: auto;
      z-index: 2;

      .font-family-item {
        padding: 0.5rem;
        list-style: none;
        cursor: pointer;

        &:hover {
          background-color: #cccccc;
        }
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