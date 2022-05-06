<template>
  <div :class="['canvas-item', {'selected': selected, 'edit': editable}]" :id="`item-${idx}`"
   :style="{top: `${top}%`, left: `${left}%`, width: `${width}%`, height: `${height}%`, zIndex: index}"
   @click="clickHandler" @dblclick="dbclickHandler" @mousedown="dragMove" @mouseup="dropMove"
   ref="item">
    <textarea v-if="type == 'text'" :class="[`canvas-${item.type}`]"
     v-model="content" :disabled="!editable" autofocus @change="changeHandler"
     :style="{
      fontSize: `${fontStyle.size}px`, fontFamily: fontStyle.family, color: fontStyle.color,
      fontWeight: fontStyle.weight, textDecoration: fontStyle.underline, textAlign: fontStyle.align}">
    </textarea>

    <img v-else :class="[`canvas-${item.type}`]"
     :src="item.imgUrl" draggable="false">

    <span v-show="selected" class="size-controller" @mousedown="dragResize" />

    <button v-show="selected" class="remover" @click="remove">
      지우기
    </button>
  </div>
</template>

<script>
export default {
  data: function(){
    return {
      ...this.item,
      index: this.idx,
      editable: false,
      resizable: false,
      draggable: false
    }
  },
  props: {
    item: Object,
    idx: Number,
    selected: Boolean,
    canvasSize: Object
  },
  methods: {
    remove: function(){
      this.$emit('remove-item')
    },
    clickHandler: function(){
      if (!this.selected){
        this.$emit('select', this.idx)
        this.$refs.item.focus()
      }
    },
    dbclickHandler: function(){
      if (this.selected){
        this.editable = true
      }
    },
    changeHandler: function(){
      this.$emit('value-change', {content: this.content})
    },
    /// 리사이즈 ///
    dragResize: function(){
      console.log('why?')
      this.resizable = true
      const canvas = document.querySelector('#canvas-container')
      canvas.addEventListener('mousemove', this.resize)
      canvas.addEventListener('mouseup', this.dropResize)
    },
    resize: function(e){
      if (this.resizable){
        this.width += (e.movementX / this.canvasSize.w) * 100
        this.height += (e.movementY / this.canvasSize.h) * 100
      }
    },
    dropResize: function(){
      if (this.resizable){
        this.resizable = false
        const canvas = document.querySelector('#canvas-container')
        canvas.removeEventListener('mousemove', this.resize)
        canvas.removeEventListener('mouseup', this.dropResize)
        this.$emit('value-change', {width: this.width, height: this.height})
      }
    },
    /// 드래그 & 드랍 ///
    dragMove: function(e){
      if (this.selected && !this.editable && e.target.tagName !== 'SPAN'){
        this.draggable = true
        const canvas = document.querySelector('#canvas-container')
        canvas.addEventListener('mousemove', this.move)
      }
    },
    move: function(e){
      if (this.draggable){
        this.top += (e.movementY / this.canvasSize.h) * 100
        this.left += (e.movementX / this.canvasSize.w) * 100
      }
    },
    dropMove: function(){
      if (this.draggable){
        this.draggable = false
        const canvas = document.querySelector('#canvas-container')
        canvas.removeEventListener('mousemove', this.move)
        this.$emit('value-change', {top: this.top, left: this.left})
      }
    }
  },
  watch: {
    selected: function(){
      if (!this.selected && (!this.content && this.type == 'text')){
        this.$emit('remove-item', this.idx)
      }
      if (!this.selected){
        this.editable = false
        this.resizable = false
      }
    }
  }
}
</script>

<style lang="scss">
  .canvas-item {
    position: absolute;
    margin: 0;
    display: flex;

    & * {
      width: 100%;
      height: 100%;
      background-color: transparent;
      overflow: hidden;
      -webkit-touch-callout: none;
      -webkit-user-select: none;
      -khtml-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
      cursor: move;
    }

    .canvas-text {
      border: none;
      outline: none;
      resize: none;
      padding: 0;
      caret-color: #555555;
    }


    &.selected {
      outline: 1px #aaaaaa dashed;
      z-index: 999 !important;
    }

    &.edit {
      .canvas-text {
        cursor: text;
      }
    }

    .size-controller {
      cursor: nw-resize;
      position: absolute;
      bottom: 0;
      right: 0;
      width: 10px;
      height: 10px;
      border-top: 20px transparent solid;
      border-left: 0;
      border-bottom: 0;
      border-right: 20px rgba(170, 170, 170, 0.5) solid;
    }

    .remover {
      position: absolute;
      top: -25px;
      right: 0;
      width: 40px;
      height: 20px;
      color: white;
      background-color: rgb(255, 100, 100);
      border-radius: 5px;
      font-size: 0.5rem;
      border: none;
      padding: 0;
      cursor: pointer;

      &:hover {
        background-color: rgb(255, 85, 85);
      }
    }
  }
</style>