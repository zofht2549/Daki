import Vue from 'vue'
import Vuex from 'vuex'
import jwtDecode from 'jwt-decode'
import axios from 'axios'

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
    },
    userItemList:[],
    wearItem:{
      itemBackground : null,
      itemCloth : null,
      itemHair : null,
      itemDeco : null
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
    },
    USER_ITEM_LIST(state, data){
      state.userItemList = data
    },
    CHANGE_ITEM(state,item){
      if((item.itemCategories == 0)){
        state.wearItem.itemCloth = item
      }else if((item.itemCategories == 1)){
        state.wearItem.itemHair = item
      }else if((item.itemCategories == 2)){
        state.wearItem.itemBackground = item
      }else{
        state.wearItem.itemDeco = item
      }
      console.log('지금 입은거',state.wearItem)
      // for(var a = 0 ; a < 14; a++){
      //   if(state.userItemList[a].itemNo == item){
      //     console.log('아이템 이름',state.userItemList[a].itemName)
      //     state.userItemList.wearFlag = 1
      //   }
      // }
    },
    USER_WEAR_ITEM(state,data){
      console.log('state',data)
      state.wearItem.itemBackground = data[0]
      state.wearItem.itemCloth = data[1]
      state.wearItem.itemDeco = data[2]
      state.wearItem.itemHair = data[3]
      console.log('데이터 입력 후',state.wearItem)
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
    },

    userItemList({commit},dollNo){
      const bearer_token = sessionStorage.getItem('accessToken')
      const session_token = sessionStorage.getItem('refreshToken')
      axios({
        methods: 'GET',
        url:`https://k6e105.p.ssafy.io:8080/api/item/info`,
        headers:{
          Authorization:`Bearer ${bearer_token}`,
          Refresh_Authorization:`${session_token}`
        },
        params:{dollNo: dollNo}
      })
      .then(res =>{
        console.log('확인')
        commit('USER_ITEM_LIST',res.data)
      })
    },
    changeItem({commit},item){
      commit('CHANGE_ITEM',item)
    },
    // userItemWear({commit},data){
    //   const bearer_token = sessionStorage.getItem('accessToken')
    //   const session_token = sessionStorage.getItem('refreshToken')
    //   axios({
    //     methods:'PUT',
    //     url:`https://k6e105.p.ssafy.io:8080/api/useritem/wear`,
    //     headers:{
    //       Authorization:`Bearer ${bearer_token}`,
    //       Refresh_Authorization:`${session_token}`
    //     }
    //   })
    //   .then(res =>{
    //     console.log('의상 체인지',data)
    //     commit('USER_ITEM_WEAR',res.data)
    //   })
    // }
    userItemWear({commit},data){
      commit('USER_WEAR_ITEM',data)
    }
  }
})
