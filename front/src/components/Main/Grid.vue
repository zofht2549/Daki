<template>
  <div id="grid-container">
    <grid-row v-for="(t, num) in nums" :key="'row-' + num" :num="num" :items="slicedItems(num)" :point="point" />
  </div>
</template>

<script>
import Dummy from './DummyGrid.js'
import GridRow from './GridRow.vue'

export default {
  data: function(){
    return {
      Dummy: Dummy,
      page: 0
    }
  },
  components: {
    GridRow
  },
  computed: {
    items: function(){
      return [...this.Dummy].reverse()
    },
    nums: function(){
      return Math.ceil(this.items.length / 4)
    },
    offset: function(){
      return  document.querySelector('#grid-container').offsetTop
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
  methods: {
    slicedItems: function(n){
      if (n == 0){
        return this.items.slice(0, 3)
      }
      return this.items.slice(n * 4 - 1, n * 4 + 3)
    }
  }
  // methods: {
  //   markerAnimation: function(){
  //     const scroll = window.scrollY + this.offset
  //     const markers = document.querySelectorAll('.marker')

  //     markers.forEach(ele => {
  //       if (scroll - ele.style.top < 100){
  //         ele.classList.add('smooth-show')
  //       }
  //     })
  //   },
  //   addScrollEvent: function(){
  //     window.addEventListener('scroll', this.markerAnimation)
  //   },
  //   removeScrollEvent: function(){
  //     window.removeEventListener('scroll', this.markerAnimation)
  //   }
  // },
  // mounted: function(){
  //   this.createMarker()
  //   this.addScrollEvent()
  // },
  // destroyed: function(){
  //   this.removeScrollEvent()
  // }
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

    .create-btn {
      width: 30%;
      min-width: 50px;
      min-height: 50px;
      aspect-ratio: 1/1;
      justify-self: center;
      align-self: center;
      border-radius: 50%;
      border: none;
      color: white;
      font-size: 3rem;
      font-weight: bold;
      background-color: #93D9CE;
      padding: 0;
      box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);

      &:hover {
        background-color: #48857b;
      }
    }

    & * {
      cursor: pointer;
    }
  }
</style>