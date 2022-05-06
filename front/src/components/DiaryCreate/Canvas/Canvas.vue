<template>
  <div id="canvas-container" ref="canvas" @click="createNewOne">
    <canvas-item v-for="(item, idx) in items" :key="item.content ? item.content + idx : item.imgUrl + idx" :item="item" :idx="idx" 
    :selected="selected == idx ? true:false" :canvasSize="{w: canvasWidth, h: canvasHeight}"
     @select="idx => select(idx)" @remove-item="idx => removeItem(idx)"
     @value-change="payload => valueChange(payload)"
     ref="canvas-item"/>

    <img v-if="file && isActive" :src="file" class="image-preview" ref="preview"
     :style="{top: `${mouseY}%`, left: `${mouseX}%`}" >
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
      canvasHeight: null,
      mouseX: null,
      mouseY: null,
      history: Array(1000),
      historyChange: false,
      pointer: -1,
      ctrl: false,
      shift: false
    }
  },
  props: {
    type: String,
    isActive: Boolean,
    changes: Object,
    file: String,
    historyChangeFromMenu: String
  },
  components: {
    CanvasItem
  },
  methods: {
    getCanvasSize: function(){
      this.canvasWidth = this.$refs.canvas.clientWidth
      this.canvasHeight = this.$refs.canvas.clientHeight
    },
    initItems: function(){
      this.items = items.map(item => {
        item.width = Number(item.width.replace('%', ''))
        item.height = Number(item.height.replace('%', ''))
        item.top = Number(item.top.replace('%', ''))
        item.left = Number(item.left.replace('%', ''))

        return item
      })
    },
    createNewOne: function(e){
      if (e.target.id == 'canvas-container' && !this.isActive){
        this.selected = null
        this.$emit('select', null)
      }
      if (this.isActive){
        const canvas = this.$refs.canvas
        const preview = this.$refs.preview

        let temp = {
          type: this.cursorType,
          top: e.offsetY / canvas.clientHeight * 100,
          left: e.offsetX / canvas.clientWidth * 100,
          width: 20,
          height: 10,
          imgUrl: null,
          content: null,
          fontStyle: {
            size: 16,
            family: 'HallymMjo-Regular',
            weight: 'normal',
            align: 'left'
          }
        }

        if (this.cursorType == 'image' || this.cursorType == 'sticker'){
          temp.width = (preview.width / this.canvasWidth) * 100
          temp.height =( preview.height / this.canvasHeight) * 100
          temp.top = this.mouseY
          temp.left = this.mouseX
          temp.imgUrl = this.file
        }

        this.items.push(temp)
        this.selected = this.$refs['canvas-item'].length
        this.$emit('select', temp)
      }
      this.$emit('deactivate')
      this.$refs.canvas.style.cursor = 'auto'
      this.mouseX = null; this.mouseY = null
    },
    keyAction: function(e){
      if (e.key == 'Control'){
        this.ctrl = true
      }
      if (e.key == 'Shift'){
        this.shift = true
      }
      if (this.selected){
        const item = this.items[this.selected]
        /// remove
        if (e.key == 'Backspace' || e.key == 'Delete'){
          this.removeItem(this.selected)
        }
        /// item move
        if (e.key == 'ArrowUp'){
          this.valueChange({top: item.top - 1})
        }
        else if (e.key == 'ArrowDown'){
          this.valueChange({top: item.top + 1})
        }
        else if (e.key == 'ArrowLeft'){
          this.valueChange({left: item.left - 1})
        }
        else if (e.key == 'ArrowRight'){
          this.valueChange({left: item.left + 1})
        }
        /// send to back
        if (e.key == '['){
          if (this.selected == 0){
            return
          }
          const temp = [...this.items]
          const idx = this.selected
          
          if (this.ctrl){
            const tar = temp.splice(idx, 1)
            temp.shift(tar)
            this.selected = 0
          }
          else {
            [temp[idx], temp[idx-1]] = [temp[idx-1], temp[idx]]
            this.selected = idx - 1
          }
          this.items = temp
        }
        /// bring to front
        else if (e.key == ']'){  
          if (this.selected == this.items.length - 1){
            return
          }
          const temp = [...this.items]
          const idx = this.selected
          
          if (this.ctrl){
            const tar = temp.splice(idx, 1)
            temp.append(tar)
            this.selected = this.items.length - 1
          }
          else {
            [temp[idx], temp[idx+1]] = [temp[idx+1], temp[idx]]
            this.selected = idx + 1
          }
          this.items = temp
        }
      }
      /// undo & redo
      if (e.key == 'z'){
        let tar
        if (this.ctrl){
          if (this.shift){
            tar = this.pointer + 1
          }
          else {
            tar = this.pointer - 1
          }

          if (this.history[tar]){
            this.pointer = tar
            this.historyChange = true /// history trigger
          }
        }
      }
    },
    removeCtrl: function(e){
      console.log(e)
      this.ctrl = false
    },
    removeShift: function(){
      this.shift = false
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
      this.$emit('select', null)
    },
    valueChange: function(payload){
      console.log('hy', payload)
      const tar = {...this.items[this.selected]}
      for (const key of Object.keys(payload)){
        tar[key] = payload[key]
      }
      this.$set(this.items, this.selected, tar)
    },
    /// new image, sticker assistant ///
    mouseMove: function(e){
      if (this.mouseX == null){
        this.mouseX = ((e.offsetX - this.$refs.preview.width / 2) / this.canvasWidth) * 100
      }
      if (this.mouseY == null){
        this.mouseY = ((e.offsetY - this.$refs.preview.height / 2) / this.canvasHeight) * 100
      }
      this.mouseX += (e.movementX / this.canvasWidth) * 100
      this.mouseY += (e.movementY / this.canvasHeight) * 100
    }
  },
  computed: {
    topOfHistory: function(){
      const temp = this.history.filter(ele => ele)
      return temp.length - 1
    }
  },
  watch: {
    isActive: function(){
      if (this.isActive){
        this.selected = null
      }
    },
    type: function(){
      if (!this.type){
        this.selected = null
      }
      this.cursorType = this.type
    },
    cursorType: function(){
      if (this.cursorType == 'text'){
        this.$refs.canvas.style.cursor = 'text'
      }
      else if (this.cursorType == 'image' || this.cursorType == 'sticker'){
        this.$refs.canvas.style.cursor = 'none'
      }
      else {
        this.$refs.canvas.style.cursor = 'auto'
      }
    },
    changes: function(){
      if (this.changes){
        const key = Object.keys(this.changes)[0]
        this.$set(this.items[this.selected].fontStyle, key, this.changes[key])
      }
    },
    file: function(){
      const canvas = this.$refs.canvas
      if (this.file){
        canvas.addEventListener('mousemove', this.mouseMove)
      }
      else {
        canvas.removeEventListener('mousemove', this.mouseMove)
      }
    },
    items: {deep: true, handler(){
      if (!this.historyChange){
        this.pointer += 1
        this.$nextTick(() => {
          this.$set(this.history, this.pointer, [...this.item])
        })
      }
    }},
    historyChange: function(){
      if (this.historyChange){
        console.log(this.pointer)
        this.items = this.history[this.pointer]
        this.$nextTick(() => {
          this.historyChange = false
        })
      }
    },
    pointer: function(){
      this.$emit('get-history-info', [this.pointer, this.topOfHistory])
    },
    historyChangeFromMenu: function(){
      if (this.historyChangeFromMenu == 'undo'){
        this.pointer -= 1
      }
      else if (this.historyChangeFromMenu == 'redo'){
        this.pointer += 1
      }
      this.historyChange = true
    }
  },
  created: function(){
    this.initItems()
  },
  mounted: function(){
    this.getCanvasSize()
    window.addEventListener('resize', this.getCanvasSize)
    document.addEventListener('keydown', this.keyAction)
    document.addEventListener('keyup', this.removeCtrl)
  },
  destroyed: function(){
    window.removeEventListener('resize', this.getCanvasSize)
    document.removeEventListener('keydown', this.keyAction)
    document.removeEventListener('keyup', this.removeCtrl)
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

    .image-preview {
      max-width: 500px;
      max-height: 500px;
      position: absolute;
      z-index: 987654321;
    }
  }
</style>