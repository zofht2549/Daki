<template>
  <div id="canvas-container" ref="canvas">
    <canvas-item v-for="(item, idx) in items" :key="item.content ? item.content + idx : item.imgUrl + idx" :item="item" :idx="idx" :selected="selected == idx ? true:false"
     @select="idx => select(idx)" @remove-item="idx => removeItem(idx)"
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
      selected: null
    }
  },
  props: {
    type: String,
    isActive: Boolean
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
    }
  },
  created: function(){
    this.items = items
  },
  mounted: function(){
    this.$refs.canvas.addEventListener('click', this.createNewOne)
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