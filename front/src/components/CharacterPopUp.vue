<template>
  <div id="popup-container">
    <div @click="closePopUp()">

    </div>
    <div class="popup-box">
      <img src="@/assets/close.png" class="close"
      @click="closePopUp()">
      <section class="deco-box">
          <article class="char-box">
            <div class="char">
              <img src="@/assets/character/character.png" alt="">
            </div>
            <div class="nickname">닉네임</div>
            <div class="tooltip">
              LV 20
              <progress class="progress-tag" value="100" max="200"></progress>
              <div class="tooltip-text">
                <img src="@/assets/heart.png" alt="">
                100/200
              </div>
            </div>
            <div>
              <img src="@/assets/coin.png" alt=""><span>5000P</span><button>포인트 충전</button>
            </div>
          </article>
          <article>
            <h3>인벤토리</h3>
            <div class="inventory">
              <div class="tab-box">
                <span @click="() => tab = 0" :class="{'tab':true, 'active': tab == 0}">치장</span>
                <span @click="() => tab = 1" :class="{'tab':true, 'active': tab == 1}">장식</span>
                <span @click="() => tab = 2" :class="{'tab':true, 'active': tab == 2}">배경</span>
                <span @click="() => tab = 3" :class="{'tab':true, 'active': tab == 3}">프레임</span>
              </div>
              <inventory-box />
            </div>
          </article>
      </section>
    </div>
  </div>
</template>

<script>
import InventoryBox from '@/components/character/InventoryBox.vue'

export default {
	data: () => {
	return {
		tab: 0,
		target: null
	}
},
  props:{
    popupVal: String,
  },
	components:{
		InventoryBox,
	},
  methods: {
    closePopUp: function(){
        this.$emit('close-pop-up');
    }
  }
}
</script>

<style lang="scss">
  #popup-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    //background-color: rgba(0, 0, 0, 0.35);
    z-index: 3;

    & > div:nth-child(1){
      position:absolute;
      width: 100%; height: 100%;
      z-index: 0;
      left: 0; top: 0;
      cursor: pointer;
      background-color: rgba(0,0,0,0.35);
    }

    .popup-box {
      position: absolute;
      left: 50%; top: 50%;
      background-color: white;
      width: 50%;
      height: auto;
      align-items: center;
      padding: 3rem;
      border-radius: 10px;
      -webkit-transform: translate(-50%, -50%);
      -moz-transform: translate(-50%, -50%);
      -ms-transform: translate(-50%, -50%);
      -o-transform: translate(-50%, -50%);
      transform: translate(-50%, -50%);
      z-index: 1;

      .close {
        width: 20px;
        aspect-ratio: 1/1;
        position: absolute;
        top: 10px;
        right: 10px;
        cursor: pointer;
      }

      & .deco-box{
          display: flex;
          justify-content: space-around;
          height: 100%;


					& > .char-box{

						& > .tooltip{
              position: relative;
              display: block;

              & > .tooltip-text{
                visibility: hidden;
                background-color: #eee;
                padding: 10px 0px;
                text-align: center;
                border-radius: 10px;
                width: 120px;
                border-radius: 10px;
                position: absolute;
                z-index: 1;
                font-size: 16px;
              }
            }

            & > .tooltip:hover{
              & > .tooltip-text{
                visibility: visible;
              }
            }
            & > div{

              font-size: 1.5rem;
              padding: 5px 0px;
              & > .progerr-tag{
                color: yellow;
                background-color: yellow;
              }
              ::-webkit-progress-value{
                background-color: yellow;
              }
              & > progress{
                color: yellow;

                & ::-webkit-progress-value{
                  background-color: yellow;
                }
              }
              & > button{
                font-size: 1rem;
                font-weight: 100;
                margin: 10px;
                padding: 2px 5px;

                display: table-cell;
                vertical-align: middle;
              }
            }

            & > .nickname{
              font-size: 1.5rem;
              font-weight: bold;
            }
						& > .char{
							width: 300px;
							height: 300px;
							border: 10px solid black;
							border-radius: 10px;
							text-align: center;
							display: table-cell;
							vertical-align: middle;
            
							& > img{
								width: 80%;
								height: 80%;
								object-fit: contain;
							}
						}
					}
          & > article{
            margin: 10px;
            & > .inventory{
              box-shadow: inset 1px 2px 4px rgba(0, 0, 0, 0.25);
              border-radius: 10px;
              height: 90%;

              & > .tab-box{
                display: flex;
                justify-content: space-around;

                & > .tab{
                  box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.25);
                  width: 100%;
                  text-align: center;
                  padding: 10px;
                  cursor: pointer;

                }
                & > .active {
                  animation: scale-up 0.1s ease-in forwards;
                  background-color: #93D9CE;
                  color: white;
                  box-shadow: inset 1px 2px 4px rgba(0, 0, 0, 0.25);
                  border-radius: 10px 10px 0px 0px;
                }
              }
            }
          }

      }
    }
  }
</style>