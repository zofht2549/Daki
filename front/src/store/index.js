import Vue from 'vue'
import Vuex from 'vuex'
import jwtDecode from 'jwt-decode'
// import axios from 'axios'

Vue.use(Vuex)

const session = window.sessionStorage

export default new Vuex.Store({
  state: {
    scroll: 0,
    isEnd: false,
    user: null,
    nickName : null,
    charItemList : {
      ItemImageBackground : 'background1',
      ItemImageCloth : 'cloth1',
      ItemImageHair : 'hair1',
      ItemImageDeco : 'deco1'
    }
  },
  mutations: {
    SCROLLED(state, payload){
      state.scroll = payload
    },
    ARRIVED(state, payload){
      state.isEnd = payload
    },
    SETUSER(state, payload){
      session.setItem('user', payload)
      state.user = JSON.parse(payload)
    },
    GETUSER(state, user){
      state.user = user
    },
    CLEARUSER(state){
      state.user = null
    },
    USER_CHAR_DATA(state, itemList){
      state.charItemList.ItemImageBackground = itemList.ItemImageBackground
      state.charItemList.ItemImageCloth = itemList.ItemImageCloth
      state.charItemList.ItemImageDeco = itemList.ItemImageDeco
      state.charItemList.ItemImageHair = itemList.ItemImageHair
    }
  },
  actions: {
    scrollHandler({ commit }, payload){
      commit('SCROLLED', payload)
    },
    arrivedEnd({ commit }, payload){
      commit('ARRIVED', payload)
    },
    setUser({commit}, credentials){
      const { authorization } = credentials
      commit('SETUSER', JSON.stringify(jwtDecode(authorization)))
    },
    getUser({commit}){
      const session = window.sessionStorage
      try {
        const user = JSON.parse(session.getItem('user'))
        commit('GETUSER', user)
      }
      catch {
        return
      }
    },
    clearUser({ commit }){
      commit('CLEARUSER')
    },
    userCharData({commit},itemList){
      commit('USER_CHAR_DATA',itemList)
    }
  }
})
