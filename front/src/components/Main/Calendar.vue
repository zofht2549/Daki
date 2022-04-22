<template>
  <div id="calendar-container">
    <calendar is-expanded trim-weeks :max-date="new Date()"
      :attributes="calendarAttrs" ref="calendar"
      />
      <!-- :fromPage="(page)=>{console.log(page)}"
      :toPage="(page)=>{console.log(page)}"
      :dayclick="(day)=>{console.log(day)}" -->
  </div>
</template>

<script>
import Calendar from 'v-calendar/lib/components/calendar.umd'
import Dummy from './Dummy.js'

const VCalendar =  {
  data: () => {
    return {
      calendarAttrs: [{
        key: 'today',
        highlight: true,
        dates: new Date(),
      }],
      flag: false,
      flag2: false
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
      const todayCol = document.querySelectorAll(`.id-${this.page[0]}-${this.page[1]}-${this.page[2]}`)[0]
      const button = document.createElement('button')

      todayCol.appendChild(button)
    },
    randomColor: function(){
      return '#' + Math.floor(Math.random() * 0xffffff).toString(16)
    },
    loadDairies: function(init=true){
      const parent = document.querySelectorAll('.vc-day')[0]
      const w = parent.clientWidth * 0.5

      for (const key in Dummy){
        if (key.slice(0, 7) == `${this.page[0]}-${this.page[1]}`){
          const tar = document.querySelectorAll(`.id-${key}`)[0]
          let wrapper

          if (init){
            wrapper = document.createElement('div')
            wrapper.setAttribute('class', 'diary-wrapper')
          }
          else {
            for (const child of tar.childNodes.entries()){
              if (child[1].classList[0] == 'diary-wrapper'){
                wrapper = child[1]
                while (wrapper.firstChild){
                  wrapper.removeChild(wrapper.firstChild)
                }
                break
              }
            }
          }
          
          for (const item of Dummy[key]){
            const idx = Dummy[key].indexOf(item)
            if ((idx+1) * 40 > w){
              break
            }
            const p = document.createElement('p')
            p.setAttribute('class', 'diary-title')
            // 랜덤 색상 부여
            // p.setAttribute('style', `background-color: ${this.randomColor()}`)
            p.innerText = item.title
            wrapper.appendChild(p)
          }
          tar.appendChild(wrapper)
        }
      }
    },
  },
  computed: {
    page: function(){
      const time = this.$refs.calendar._data
      let y = String(time.pages[0].year); let m = String(time.pages[0].month); let d = String(time.focusableDay);

      if (m.length == 1){
        m = '0' + m
      }
      if (d.length == 1){
        d = '0' + d
      }
      this.flag2 = !this.flag2

      return [y, m, d]
    },
  },
  watch: {
    flag2: function(){
      console.log('called!!!')
    }
  },
  created: function(){
    this.$nextTick(()=>console.log('nextTick!!'))
  },
  mounted: function(){
    this.makeDisabled()
    this.loadDairies()
    this.createButton()

    // // console.log(this.$refs.calendar._data.pages[0])

    const _this = this

    window.addEventListener('resize', function(){
      if (_this.flag){
        clearTimeout()
        return
      }
      else {
        _this.flag = true
        setTimeout(function(){
          _this.loadDairies(false)
          _this.flag = false
        }, 1000)
      }
    })
  },
}

export default VCalendar
</script>

<style lang="scss">
  #calendar-container {
    width: 100%;
    padding: 5rem;

    .vc-container {
      width: 100%;

      .vc-pane-container, .vc-pane-layout, .vc-pane, .vc-headr, .vc-weeks {
        width: 100%;
      }

      .vc-day {
        width: 100%;
        min-width: 32px;
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
          width: 80%;
          position: absolute;
          bottom: 5%;
          left: 10%;
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

      .diary-wrapper {
        width: 100%;
        height: 50%;
        overflow: hidden;

        .diary-title {
          width: 100%;
          max-width: 150px;
          text-overflow: ellipsis;
          white-space: nowrap;
          overflow: hidden;
          margin: 0.25rem 0;
          padding: 0.375rem;
          color: white;
          font-weight: bold;
          box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
          background-color: #93D9CE;
        }
      }
    }
  }
</style>