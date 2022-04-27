<template>
  <div id="grid-container">
    <grid-row v-for="(t, num) in nums" :key="'row-' + num" :num="num"
     :items="slicedItems(num)" :point="point" />
  </div>
</template>

<script>
import store from '../../store/index.js'
import Dummy from './DummyGrid.js'
import GridRow from './GridRow.vue'

export default {
  data: function(){
    return {
      Dummy: Dummy,
      page: 1,
      flag: false,
    }
  },
  components: {
    GridRow
  },
  computed: {
    isEnd: function(){
      return store.state.isEnd
    },
    items: function(){
      return [...this.Dummy].reverse().slice(0, this.page * 8 - 1)
    },
    nums: function(){
      return Math.ceil(this.items.length / 4)
    },
    point: function(){
      const point = [[0, this.items[0].created_at.slice(0, 7)]]

      this.items.forEach((ele, idx) => {
        const tar = point[point.length-1][1]
        if (tar != ele.created_at.slice(0, 7)){
          point.push([idx, ele.created_at.slice(0, 7)])
        }
      })

      return point
    }
  },
  watch: {
    isEnd: function(){
      const _this = this
      if (!this.flag && this.isEnd){
        _this.flag = true
        setTimeout(() => {
          _this.page += 1
          _this.flag = false
        }, 1000)
      }
    }
  },
  methods: {
    slicedItems: function(n){
      if (n == 0){
        return this.items.slice(0, 3)
      }
      return this.items.slice(n * 4 - 1, n * 4 + 3)
    },
  }
}
</script>

<style lang="scss">
  #grid-container {
    width: 100%;
    padding: 5rem;
    display: grid;
    grid-template-columns: 1fr;
    gap: 2rem;
    position: relative;

    & * {
      cursor: pointer;
    }
  }
</style>