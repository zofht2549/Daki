import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    scroll: 0,
    isEnd: false,
    accessToken: null,
    credentials:{
      email:null,
      password:null,
      fcmToken:null,
    }
  },
  mutations: {
    SCROLLED(state, payload){
      state.scroll = payload
    },
    ARRIVED(state, payload){
      state.isEnd = payload
    },

    // acounts
    LOGIN:function(state, data){
      state.credentials.email = data.email
    }
  },
  actions: {
    scrollHandler({ commit }, payload){
      commit('SCROLLED', payload)
    },
    arrivedEnd({ commit }, payload){
      commit('ARRIVED', payload)
    },
    // accounts
    login: function({commit}, credentials){
      console.log('확인',credentials)
      axios({
        method: 'POST',
        url: 'http://k6e105.p.ssafy.io:8080/api/auth/login',
        // url : `${process.env.VUE_APP_API_URL}/api/auth/login`
        data:credentials,
        headers: {
          "Access-Control-Allow-Origin" : "*",
          "Access-Control-Allow-Methods" : "GET,POST,PUT,DELETE,OPTIONS",
          "Access-Control-Allow-Headers": "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With"
      }

      })
        .then(res => {
          console.log('또 확인')
          // console.log(res)
          console.log(this.credentials)
          commit('LOGIN',res.data)
        })
        .catch(err =>{
          console.log('확인확인')
          console.log(err)
        })
    }
  },
})
