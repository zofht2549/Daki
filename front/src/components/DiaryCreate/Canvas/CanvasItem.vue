<template>
  <div :class="['canvas-item', {'selected': selected, 'edit': editable}]" :id="`item-${idx}`"
   :style="{top: `${top}px`, left: `${left}px`, width: `${width}px`, height: `${height}px`, zIndex: index}"
   @click="clickHandler" @dblclick="dbclickHandler" @mousedown="dragMove" @mouseup="dropMove"
   ref="item">
    <textarea v-if="type == 'text'" :class="[`canvas-${item.type}`]"
     v-model="content" :disabled="!editable"
     :style="{
      fontSize: `${fontStyle.size}px`, fontFamily: fontStyle.family, color: fontStyle.color,
      fontWeight: fontStyle.weight, textAlign: fontStyle.align}">
    </textarea>

    <img v-else :class="[`canvas-${item.type}`]"
     :src="item.imgUrl" draggable="false">

    <span v-show="selected" class="size-controller" 
    @mousedown="dragResize" />
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
    selected: Boolean
  },
  methods: {
    clickHandler: function(){
      if (!this.selected){
        this.$emit('select', this.idx)
      }
    },
    dbclickHandler: function(){
      if (this.selected){
        this.editable = true
      }
    },
    /// 리사이즈 ///
    dragResize: function(){
      this.resizable = true
      const canvas = document.querySelector('#canvas-container')
      canvas.addEventListener('mousemove', this.resize)
      canvas.addEventListener('mouseup', this.dropResize)
    },
    resize: function(e){
      if (this.resizable){
        this.width += e.movementX
        this.height += e.movementY
      }
    },
    dropResize: function(){
      this.resizable = false
      const canvas = document.querySelector('#canvas-container')
      canvas.removeEventListener('mousemove', this.resize)
      canvas.removeEventListener('mouseup', this.dropResize)
    },
    /// 드래그 & 드랍 ///
    dragMove: function(e){
      console.log(e)
      if (e.target.tagName !== 'SPAN'){
        this.draggable = true
        const canvas = document.querySelector('#canvas-container')
        canvas.addEventListener('mousemove', this.move)
      }
    },
    move: function(e){
      if (this.draggable){
        this.top += e.movementY
        this.left += e.movementX
      }
    },
    dropMove: function(){
      console.log('drop!!!')
      this.draggable = false
      const canvas = document.querySelector('#canvas-container')
      canvas.removeEventListener('mousemove', this.move)
    },
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
  },
  mounted: function(){
    const canvas = document.querySelector('#canvas-container')
    this.width = canvas.clientWidth * Number(this.width.replace('%', '')) / 100
    this.height = canvas.clientHeight * Number(this.height.replace('%', '')) / 100
    this.top = canvas.clientHeight * Number(this.top.replace('%', '')) / 100
    this.left = canvas.clientWidth * Number(this.left.replace('%', '')) / 100
  }
}
</script>

<style lang="scss">
  .canvas-item {
    position: absolute;
    margin: 0;
    overflow: hidden;
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
  }

</style>