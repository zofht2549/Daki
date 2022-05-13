<template>
  <article id="editor" ref="editor">
    <menu-bar :selected="selected" :isCreated="isCreated" :historyInfo="historyInfo"
     @active-menu="params => activate(params)" @image-upload="file => fileSetter(file)" @history-change="payload => historyChange(payload)"
     @submit="submit('manual')" />
    
    <Canvas :type="type" :isActive="isActive" :changes="changes" :file="file" :historyChangeFromMenu="historyChangeFromMenu"
     @deactivate="deactivate" @select="tar => select(tar)" @get-history-info="info => getHistoryInfo(info)" />
  </article>
</template>

<script>
import MenuBar from './Menus/MenuBar.vue'
import Canvas from './Canvas/Canvas.vue'
import AWS from 'aws-sdk'

export default {
  data: function(){
    return {
      type: null,
      isActive: false,
      selected: null,
      changes: null,
      file: null,
      historyInfo: [0, 0],
      historyChangeFromMenu: null
    }
  },
  props: {
    title: String
  },
  components: {
    MenuBar,
    Canvas
  },
  computed: {
    isCreated: function(){
      return !this.isActive
    },
    items: function(){
      return this.$children[1].items
    }
  },
  methods: {
    activate: function(params){
      this.type = params.menu
      this.isActive = params.isActive
    },
    deactivate: function(){
      this.isActive = false
      this.changes = null
      this.file = null
    },
    select: function(tar){
      this.selected = tar
    },
    changeSetter: function(payload){
      if (this.selected){
        this.changes = payload
      }
    },
    fileSetter: function(file){
      this.file = file
    },
    getHistoryInfo: function(info){
      this.historyInfo = info
      this.historyChangeFromMenu = null
    },
    historyChange: function(payload){
      this.historyChangeFromMenu = payload
    },
    submit: function(type){
      // const data = {
      //   title: this.title,
      //   content: JSON.stringify(this.items)
      // }
      // console.log(data)
      this.fileUploadToS3()
      this.$emit('is-save', type)
      setTimeout(() => {
        this.$emit('is-save')
      }, 1000)
    },
    autoSave: function(){
      this.submit('auto')
    },
    fileUploadToS3: function(){
      this.items.forEach(ele => {
        if (ele.imgUrl && ele.imgUrl.slice(8, 15) != 'diarypj'){
          const credential = {
            accessKeyId: 'AKIAZYOLGFVFTHMZXGGK',
            secretAccessKey: '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',
            region: 'ap-northeast-2'
          }
          const s3 = new AWS.S3(credential)
          console.log(s3)
          const params = {
            Bucket: 'diarypj',
            Key: ele.imgUrl,
            Body: ele.imgUrl,
            ContentEncoding: 'base64',
            ContentType: 'image/*',
          }

          s3.upload(params, (err, data) => {
            if (err){
              console.dir(err)
            }
            console.dir(data)
          })
        }
      })
    }
  },
  mounted: function(){
    window.setInterval(this.autoSave, 150000)
    this.$on('value-change', payload => this.changeSetter(payload))
  },
  destroyed: function(){
    console.log('heuy')
    window.clearInterval(this.autoSave)
  }
}
</script>

<style lang="scss">
  #editor {
    width: 100%;
    min-height: 125vh;
    margin: 3rem;
    border: 1px #cccccc solid;
    background-color: #cccccc;
    display: flex;
    flex-direction: column;
  }
</style>