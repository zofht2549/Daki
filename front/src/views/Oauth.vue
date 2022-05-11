<template>
  <div></div>
</template>

<script>
import axios from 'axios'

export default {
  methods: {
    getGoogleUser: function(token){
      console.log(token)
      axios({
        method: 'get',
        url: 'https://www.googleapis.com/oauth2/v3/userinfo',
        params: {
          'access_token': token
        },
        headers: { 
          authorization: `Bearer ${token}`, 
          accept: 'application/json' 
        }
      })
      .then(res => console.log(res))
      .catch(err => console.log(err))
    }
  },
  created: function(){
    if (this.$route.hash){
      const hash = this.$route.hash.replace('#', '').split('&')
      const temp = {}
      for (const ele of hash){
        const t = ele.split('=')
        temp[t[0]] = t[1]
      }
      if (temp['access_token']){
        this.getGoogleUser(temp['access_token'])
      }
    }
  }
}
</script>

<style>

</style>