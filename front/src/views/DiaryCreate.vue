<template>
  <section id="diary-create-container">
    <navigation />

    <editor ref="editor" />

    <button class="submit-btn" @click="submitClick">
      작성 완료
    </button>

    <div v-if="isSave" class="save-box">
      <div class="auto-save">
        <img class="auto-img" src="../assets/loading.gif">
        <p class="auto-msg">저장중입니다</p>
      </div>
    </div>
  </section>
</template>

<script>
import Navigation from '../components/Navigation.vue'
import Editor from '../components/DiaryCreate/Editor.vue'

export default {
  data: function(){
    return {
      isSave: false
    }
  },
  components: {
    Navigation,
    Editor
  },
  methods: {
    submitClick: function(){
      this.isSave = true
      this.submitItems()
    },
    submitItems: function(){
      const editor = this.$refs.editor
      const canvas = editor.$children[1]
      const items = canvas.items
      console.log(items)
      setTimeout(() => {
        this.isSave = false
      }, 1000);
    },
    autoSave: function(){
      this.isSave = true
      this.submitItems()
    }
  },
  created: function(){
    window.setInterval(this.autoSave, 150000)
  },
  destroyed: function(){
    window.clearInterval(this.autoSave)
  }
}
</script>

<style lang="scss">
  #diary-create-container {
    border-left: 1px #cccccc solid;
    border-right: 1px #cccccc solid;
    width: 70%;
    min-height: 100vh;
    padding-top: 100px;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;

    .submit-btn {
      font-size: 1.25rem;
      font-weight: bold;
      border-radius: 10px;
      border: 1px #93D9CE solid;
      color: #93D9CE;
      background-color: white;
      box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
      margin-bottom: 5rem;
      padding: 1rem;
      width: 20%;
      cursor: pointer;

      &:hover {
        background-color: #93D9CE;
        color: white;
      }
    }

    .save-box {
      position: fixed;
      top: 0;
      left: 0;
      width: 100vw;
      height: 100vh;
      background-color: rgba(0, 0, 0, 0.35);
      color: white;
      z-index: 987654321;
      display: flex;
      justify-content: center;
      align-items: center;

      .auto-save {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;

        .auto-img {
          width: 100px;
          aspect-ratio: 1/1;
        }

        .auto-msg {
          font-size: 1.25rem;
          color: white;
          margin: 1rem;
        }
      }
    }
  }
</style>