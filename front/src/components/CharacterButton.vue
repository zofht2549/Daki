<template>
  <div id="character-container">
    <div @click="openPopup()">
      <img src="@/assets/character/head.png" alt="">
			<div class="parts head">
				<img :src="require(`@/assets/character/${this.itemList.ItemImageHair}.png`)" alt="머리">
			</div>
			<div class="parts cloth">
				<img :src="require(`@/assets/character/${this.itemList.ItemImageCloth}.png`)" alt="옷">
			</div>
			<div class="parts deco">
				<img :src="require(`@/assets/character/${this.itemList.ItemImageDeco}.png`)" alt="장식">
			</div>
    </div>

    <div class="character-popup"
			:class="{ active : popupView }"
			>
      <character-pop-up
        @close-pop-up="openPopup()"
				@change-item="getCharItem()"></character-pop-up>
    </div>
  </div>
</template>
<script>
import CharacterPopUp from '@/components/CharacterPopUp.vue'

export default {
	name: 'CharacterButton',
	data: () =>{
		return {
			popupView : false,
			itemList:{
				// ItemImageBackground : 'background1',
				ItemImageCloth : null,
				ItemImageHair : null,
				ItemImageDeco : null
			},
		}
	},
	components:{
		CharacterPopUp,
	},
	methods:{
		openPopup(){
			this.popupView = (this.popupView) ? false : true;
		},
		getCharItem(){
			this.itemList.ItemImageCloth = this.$store.state.charItemList.ItemImageCloth
			this.itemList.ItemImageHair = this.$store.state.charItemList.ItemImageHair
			this.itemList.ItemImageDeco = this.$store.state.charItemList.ItemImageDeco

			this.$emit('change-item')
		}
	},

	created: function(){
		this.itemList.ItemImageCloth = this.$store.state.charItemList.ItemImageCloth
		this.itemList.ItemImageHair = this.$store.state.charItemList.ItemImageHair
		this.itemList.ItemImageDeco = this.$store.state.charItemList.ItemImageDeco
	}
}
</script>
<style lang="scss">
    #character-container{
      position: fixed;
      bottom:10%;
      right: 10%;

			& > div{
				& > .parts{
					position: absolute;
					width: 100%; height: 100%;
					left: 0px;
					top: 0px;
					z-index: 2;

					& > img {
						& {
							width: 100%; height: 100%;
						}
					}
				}
			}

      & > .character-popup{
        & {
          opacity: 0;
          display: none;
          visibility: hidden;
        }

        &.active {
          opacity: 1;
          display: block;
          visibility: visible;
        }
      }
    }
</style>
