<template>
  <div id="stt-container">

  </div>
</template>

<script>
const recorder = require('node-record-lpcm16')
const speech = require('@google-cloud/speech').v1p1beta1

export default {
  methods: {
    speechToText: function(){
      const client = new speech.SpeechClient()

      const request = {
        config: {
          encoding: 'LINEAR16',
          sampleRageHertz: 16000,
          languageCode: 'ko-KR'
        },
        interimResults: false
      }

      const recognizeStream = client
      .streamingRecognize(request)
      .on('error', console.error)
      .on('data', data =>
        process.stdout.write(data.results[0] && data.results[0].alternatives[0]
         ? `Transcription: ${data.results[0].alternatives[0].transcript}\n`
         : '\n\nReached transcription time limit, press Ctrl+C\n'))

      recorder.record({
        sampleRageHertz: 16000,
        threshold: 0,
        recordProgram: 'sox',
        silence: '10.0'
      })
      .stream()
      .on('error', console.error)
      .pipe(recognizeStream)
    }
  },
  mounted: function(){
    this.speechToText()
  }
}
</script>

<style lang="scss">

</style>