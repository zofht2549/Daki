<template>
  <div id="calendar-container">
    <calendar is-expanded trim-weeks :max-date="new Date()"
      :attributes="calendarAttrs" />
  </div>
</template>

<script>
import Calendar from 'v-calendar/lib/components/calendar.umd'

export default {
  data: () => {
    return {
      calendarAttrs: [{
        key: 'today',
        highlight: true,
        dates: new Date(),
      }]
    }
  },
  components: {
    Calendar,
  },
  methods: {
    makeDisabled: function(){
      const disalbled = document.querySelectorAll('.is-disabled')

      disalbled.forEach(ele => {
        let tar = ele.parentElement
        tar.classList.add('day-disabled')
      })
    },
    createButton: function(){
      const date = new Date()
      const y = date.getFullYear().toString();
      let m = (date.getMonth() + 1).toString();
      let d = date.getDate().toString();
      if (m.length == 1){
        m = '0' + m
      }
      if (d.length == 1){
        d = '0' + d
      }
      const today = document.querySelectorAll(`.id-${y}-${m}-${d}`)[0]
      const button = document.createElement('button')

      today.appendChild(button)
    }
  },
  mounted: function(){
    this.makeDisabled()
    this.createButton()
  }
}
</script>

<style lang="scss">
  #calendar-container {
    padding: 5rem;

    .vc-day {
      aspect-ratio: 1/1;
      padding: 0.5rem;
      flex-direction: column;
      justify-content: flex-start;
      align-items: flex-start;
      background-color: white;
      cursor: pointer;

      @keyframes scale {
        from {
          transform: scale(1);
          box-shadow: none;
        }
        to {
          transform: scale(1.05);
          box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
        }
      }

      &:hover {
        animation: scale 0.2s ease-in forwards;
        z-index: 2;
      }


      .vc-day-layer {
        width: 100%;
        height: 100%;
        justify-content: flex-start;
        align-items: flex-start;
        padding: 0.5rem;
      }

      button {
        font-size: 1.25rem;
        font-weight: bold;
        border-radius: 10px;
        border: 1px #93D9CE solid;
        color: #93D9CE;
        background-color: white;
        box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
        margin: auto auto 0;
        padding: 0.125rem;
        width: 100%;
        cursor: pointer;

        &::after {
          content: '+';
        }

        &:hover {
          background-color: #93D9CE;
          color: white;
        }
      }
    }
    
    .day-disabled {
      cursor: initial;

      &:hover {
        animation: none;
      }
    }
  }
</style>