<template>
  <div id="canvas-container" ref="canvas" @click="createNewOne">
    <canvas-item v-for="(item, idx) in items" :key="item.content ? item.content + idx : item.imgUrl + idx" :item="item" :idx="idx" 
    :selected="selected == idx ? true:false" :canvasSize="{w: canvasWidth, h: canvasHeight}"
     @select="idx => select(idx)" @remove-item="idx => removeItem(idx)"
     @value-change="payload => valueChange(payload)"
     ref="canvas-item"/>
  </div>
</template>

<script>
import {items} from './Dummy.js'
import CanvasItem from './CanvasItem.vue'

export default {
  data: function(){
    return {
      cursorType: null,
      items: null,
      selected: null,
      canvasWidth: null,
      canvasHeight: null
    }
  },
  props: {
    type: String,
    isActive: Boolean,
    changes: Object
  },
  components: {
    CanvasItem
  },
  methods: {
    createNewOne: function(e){
      if (e.target.id == 'canvas-container' && !this.isActive){
        this.selected = null
        this.$emit('select', null)
      }
      if (this.isActive){
        const canvas = this.$refs.canvas
        const temp = {
          type: this.cursorType,
          top: `${e.offsetY / canvas.clientHeight * 100}%`,
          left: `${e.offsetX / canvas.clientWidth * 100}%`,
          width: '100px',
          height: '100px',
          imgUrl: null,
          content: null,
          fontStyle: {
            size: null,
            family: null,
            weight: null,
            align: null
          }
        }
        this.items.push(temp)
        this.selected = this.$refs['canvas-item'].length
        this.$emit('select', temp)
      }
      this.$emit('deactivate')
      this.$refs.canvas.style.cursor = 'auto'
    },
    select: function(idx){
      if (this.selected == idx){
        return
      }
      const tar = this.items[idx]
      this.selected = idx
      this.$emit('select', tar)
    },
    removeItem: function(idx){
      this.items.splice(idx, 1)
    },
    getCanvasSize: function(){
      this.canvasWidth = this.$refs.canvas.clientWidth
      this.canvasHeight = this.$refs.canvas.clientHeight
    },
    valueChange: function(payload){
      const tar = {...this.items[this.selected]}
      for (const key of Object.keys(payload)){
        tar[key] = payload[key]
      }
      this.$set(this.items, this.selected, tar)
    }
  },
  watch: {
    type: function(){
      this.cursorType = this.type
    },
    cursorType: function(){
      if (this.cursorType == 'text'){
        this.$refs.canvas.style.cursor = 'text'
      }
      else {
        this.$refs.canvas.style.cursor = 'auto'
      }
    },
    changes: function(){
      const key = Object.keys(this.changes)[0]
      this.$set(this.items[this.selected].fontStyle, key, this.changes[key])
    }
  },
  created: function(){
    this.items = items
  },
  mounted: function(){
    this.getCanvasSize()
    window.addEventListener('resize', this.getCanvasSize)
  },
  destroyed: function(){
    window.removeEventListener('resize', this.getCanvasSize)
  }
}
</script>

<style lang="scss">
  #canvas-container {
    // display: flex;
    position: relative;
    height: 100%;
    margin: 3rem;
    background-color: white;
    box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
    overflow: hidden;
  }
</style>