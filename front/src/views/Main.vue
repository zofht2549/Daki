<template>
  <section id="main-container">
    <navigation />
    <article class="tab-box">
      <span @click="() => tab = 0" :class="{'tab':true, 'active': tab == 0}">달력으로 보기</span>
      <span @click="() => tab = 1" :class="{'tab':true, 'active': tab == 1}">그리드로 보기</span>
    </article>
    <article class="main-body">
      <calendar />
    </article>
  </section>
</template>

<script>
import Navigation from '../components/Navigation.vue'
import Calendar from '../components/Main/Calendar.vue'

export default {
  data: () => {
    return {
      tab: 0,
    }
  },
  components: {
    Navigation,
    Calendar
  },
}
</script>

<style lang="scss">
  #main-container {
    border-left: 1px #cccccc solid;
    border-right: 1px #cccccc solid;
    width: 70%;
    min-height: 100vh;
    padding-top: 100px;

    .tab-box {
      width: 100%;
      height: 100px;
      display: flex;
      justify-content: center;
      align-items: flex-end;
      border-bottom: 1px #cccccc solid;
      overflow: hidden;

      .tab {
        width: 25%;
        height: 60px;
        background-color: #cccccc;
        color: #777777;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 1.5rem;
        font-weight: bold;
        margin: 0 3rem;
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        cursor: pointer;
        box-shadow: 2px 0 4px rgba(0, 0, 0, 0.5);
      }

      @keyframes scale-up {
        from {
          height: 60px;
        }
        to {
          height: 75px;
        }
      }

      .active {
        animation: scale-up 0.1s ease-in forwards;
        background-color: #93D9CE;
        color: white;
      }
    }

    .main-body {
      display: flex;
      justify-content: center;
    }
  }
</style>