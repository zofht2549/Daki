<template>
  <div :class="['grid-row', `row-${num}`]">
    <button v-if="num == 0" class="create-btn">+</button>
    <card v-for="item of items" :key="item.title" :item="item"/>
  </div>
</template>

<script>
import Card from './GridCard.vue'

export default {
  data: function(){
    return {
      markers: []
    }
  },
  props: {
    num: Number,
    items: Array,
    point: Array
  },
  components: {
    Card
  },
  methods: {
    createMarker: function(){
      this.point.forEach(ele => {
        if (this.num == 0 && ele[0] < 3){
          this.markers.push(ele[1])
        }
        else if ((ele[0] >= this.num * 4 - 1) && (ele[0] < this.num * 4 + 3)){
          this.markers.push(ele[1])
        }
      })
    }
  },
  mounted: function(){
    this.createMarker()
  }
}
</script>

<style lang="scss">
  .grid-row {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 2rem;
  }
</style>