import Vue from 'vue'
import Vuex from 'vuex'
import jwtDecode from 'jwt-decode'

Vue.use(Vuex)

const session = window.sessionStorage

export default new Vuex.Store({
  state: {
    scroll: 0,
    isEnd: false,
    user: null
  },
  mutations: {
    SCROLLED(state, payload){
      state.scroll = payload
    },
    ARRIVED(state, payload){
      state.isEnd = payload
    },
    // acounts
    SETUSER(state, payload){
      session.setItem('user', payload)
      state.user = JSON.parse(payload)
    },
    CLEARUSER(state){
      state.user = null
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
    setUser({commit}, credentials){
      const { accessToken } = credentials
      commit('SETUSER', JSON.stringify(jwtDecode(accessToken)))
    },
    clearUser({ commit }){
      commit('CLEARUSER')
    }
  },
})
