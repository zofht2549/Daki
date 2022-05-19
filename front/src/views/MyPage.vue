<template>
    <div id="mypage-container">
      <navigation />
      <section class="info">
				<article class="char-box">
					<div class="char">
						<!-- <img src="@/assets/landing/character.png" alt=""> -->
						<img src="@/assets/character/head.png" alt="">
						<div class="parts background">
							<!-- <img :src="require(`@/assets/character/${this.itemList.ItemImageBackground}.png`)" alt="배경"> -->
							<img :src="`${this.itemList.ItemImageBackground}`" alt="">
						</div>
						<div class="parts head">
							<!-- <img :src="require(`@/assets/character/${this.itemList.ItemImageHair}.png`)" alt="머리"> -->
							<img :src="`${this.itemList.ItemImageHair}`" alt="">
						</div>
						<div class="parts cloth">
							<!-- <img :src="require(`@/assets/character/${this.itemList.ItemImageCloth}.png`)" alt="옷"> -->
							<img :src="`${this.itemList.ItemImageCloth}`" alt="">
						</div>
						<div class="parts deco">
							<!-- <img :src="require(`@/assets/character/${this.itemList.ItemImageDeco}.png`)" alt="장식"> -->
							<img :src="`${this.itemList.ItemImageDeco}`" alt="">
						</div>
					</div>
					<div class="char-info">
						<div class="nickname">{{ user.nickName}}</div>
						<div><img src="@/assets/coin.png" alt=""><span>5000P</span><button>포인트 충전</button></div>
						<a>캐릭터 변경</a>
					</div>
				</article>
				<article>
					<form action="#">
						<textarea name="introduce" id="" cols="30" rows="10"></textarea>
					</form>
				</article>
				<hr>
				<article class="infomation-box">
					<form action="#">
						<p>회원정보</p>
						<div for="id">
							<span class="title">아이디</span>
							<span class="content">tndk4997</span>
						</div>
						<div for="nickname">
							<span class="title">닉네임</span>
							<span class="content">{{ user.nickName }}</span>
							<button
								class="nickname_button" @click="changeNickname()">
								닉네임 변경
							</button>
						</div>
						<div for="birth">
							<span class="title">생년월일</span>
							<span class="content">{{ user.birth }}</span>
						</div>
						<div for="gender">
							<span class="title">성별</span>
							<span class="content">{{ user.userGender }}</span>
						</div>
					</form>
					
				</article>
				<article class="botton-area">
					<button @click="changePassword()">비밀번호 수정</button>
					<br>
					<a href="#">회원탈퇴</a>
				</article>
      </section>
			<div>
				<character-button
					@change="getCharItem()">
				</character-button>
			</div>
			<div class="change-password" :class="{ active : passwordView }">
				<change-password @close-pop-up="changePassword()" />
			</div>
			<div class="change-nickname" :class="{ active : nicknameView}">
				<change-nickname @close-pop-up="changeNickname()" />
			</div>

    </div>
    
</template>
<script>
import Navigation from '../components/Navigation.vue'
import ChangePassword from '@/components/Mypage/ChangePassword.vue'
import ChangeNickname from '@/components/Mypage/ChangeNickname.vue'
import CharacterButton from '@/components/CharacterButton.vue'
import { mapState } from 'vuex'

export default {
  name: 'MyPage',
	data: () => {
		return {
			
			popupVal : false,
			// target : false,

			passwordView : false,
			nicknameView : false,

			itemList:{
				ItemImageBackground : null,
				ItemImageCloth : null,
				ItemImageHair : null,
				ItemImageDeco : null
			},
		}
	},
  components:{
    Navigation,
		ChangePassword,
		ChangeNickname,
		CharacterButton,
  },
  methods:{
		openPop(){
			this.view = (this.view) ? false : true; 
		},
		changePassword(){
			this.passwordView = (this.passwordView) ? false : true
		},
		changeNickname(){
			this.nicknameView = (this.nicknameView) ? false : true
		},
		getCharItem(){
		this.itemList.ItemImageBackground = this.$store.state.wearItem.itemBackground.itemImage
		this.itemList.ItemImageCloth = this.$store.state.wearItem.itemCloth.itemImage
		this.itemList.ItemImageHair = this.$store.state.wearItem.itemHair.itemImage
		this.itemList.ItemImageDeco = this.$store.state.wearItem.itemDeco.itemImage
		console.log('getchar확인',this.itemList.ItemImageBackground)
		console.log('getchar이미지 확인',this.itemList.ItemImageBackground.itemImage)
		}
  },
	created: function(){
		// this.itemList.ItemImageBackground = this.$store.state.charItemList.ItemImageBackground
		// this.itemList.ItemImageBackground = this.$store.state.wearItem.itemBackground
		// this.itemList.ItemImageCloth = this.$store.state.wearItem.itemCloth
		// this.itemList.ItemImageHair = this.$store.state.wearItem.itemHair
		// this.itemList.ItemImageDeco = this.$store.state.wearItem.itemDeco
		// console.log('확인',this.itemList.ItemImageBackground)



    this.dollNo = this.$store.state.user.doll_no
    console.log(this.dollNo)
    this.$store.dispatch('userItemList',this.dollNo)
		for(var i = 1 ; i < this.$store.state.userItemList.length; i++){
			if(this.$store.state.userItemList[i].itemCategory == 2 && this.$store.state.userItemList[i].wearFlag == 1){
				this.itemList.ItemImageBackground = this.$store.state.userItemList[i].itemImage
			}
			else if(this.$store.state.userItemList[i].itemCategory == 0 && this.$store.state.userItem[i].wearFlag == 1){
				this.itemList.ItemImageCloth = this.$store.state.userItemList[i].itemImage
			}
			else if(this.$store.state.userItemList[i].itemCategory == 1 && this.$store.userItem[i].wearFlag == 1){
				this.itemList.ItemImageHair = this.$store.state.userItemList[i].itemImage
			}
			else if(this.$store.state.userItemList[i].itemCategory == 3 && this.$store.state.userItem[i].wearFlag == 1){
				this.itemList.ItemImageDeco = this.$store.state.userItemList[i].itemImage
			}
		}

	},
	computed:{
		...mapState([
      'user',
			'charItemList',
			'wearItem'
    ])
	}
}
</script>
<style lang="scss">
	.pop {
		& {
			opacity: 0;
			display: none;
			visibility: hidden;
		}

		&.active {
			& {
				opacity: 1;
				display: block;
				visibility: visible;
			}
		}
	}
	.change-password{
		& {
			opacity: 0;
			display: none;
			visibility: hidden;
		}
		&.active {
			&{
				opacity: 1;
				display: block;
				visibility: visible;
			}
		}
	}
	.change-nickname{
		& {
			opacity: 0;
			display: none;
			visibility: hidden;
		}
		&.active{
			&{
				opacity: 1;
				display: block;
				visibility: visible;
			}
		}
	}
@media only screen and (min-width:800px){

  #mypage-container{
    border-left: 1px #cccccc solid;
    border-right: 1px #cccccc solid;
    width: 70%;
    min-height: 100vh;
		padding-top: 10%;

		& .info{
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			width: 70%;
			margin: 0 auto;

			& > a {
        margin: 2rem;
        font-size: 1.25rem;
        font-weight: bold;
        text-decoration: none;
        color: #555555;
      }

			& .char-box{
				display: table;

				& .char{
					width: 150px;
					height: 150px;
					border: 5px solid black;
					border-radius: 10px;
					text-align: center;
					vertical-align: top;
					display: table-cell;
					position: relative;

					& > img{
						width: 80%;
						height: 80%;
						object-fit:contain;
					}
					& > img{
						width: 100%;
						height: 100%;
						left: 0; top: 0;
						object-fit: contain;
						position: absolute;
						z-index: -2;
					}
					& > .parts{
						position: absolute;
						width: 100%; height: 100%;
						left: 0px;
						top: 0px;
						z-index: -1;

						& > img {
							& {
								width: 100%; height: 100%;
							}
						}
					}

					& > .background{
						//position: absolute;
						//width: 100%; height: 100%;
						//left: 0; top: 0;
						position: absolute;
						z-index: -3;
					}
				}
				& .char-info{
					display: table-cell;
					vertical-align: bottom;
					padding: 10px;

					& > .nickname{
						font-size: 1.5rem;
					}

					& > div > button{
						font-size: 1rem;
						font-weight: 100;
						margin: 10px;
						padding: 2px 5px;
					}

					& > a{
						font-size: 0.9rem;
						padding: 0px;
						margin: 0px;
					}
				}
			}
			& > article{
				width: 100%;
				& > form{
					width: 100%;

					& > p{
						font-size: 1.5rem;
						font-weight: bold;
					}
					& > textarea{
						display: block;
						width:100% !important;
						border-radius: 10px;
						resize: none;
						padding: 15px;
						margin: 10px 0px;
					}		
					
					& > div{
						display: flex;
						justify-content: space-between;
						padding: 10px 0px;

						& > .title{
							width: 30%;
							font-size: 1.5rem;
							font-weight: bold;
						}

						& > .content{
							width: 70%;
							border: none;
							border-bottom: 1px #cccccc solid;
							font-size: 1.5rem;
							margin: 0 1rem;
							padding: 0.25rem 0.5rem;
						}

						& > .nickname_button{
							font-size: 12px;
							position: absolute;
							left: 65%;
							bottom: 25%;
						}
					}
				}
			}
			& > .botton-area{
					text-align: center;
			}
		}

		button {
			font-size: 1.25rem;
			font-weight: bold;
			border-radius: 10px;
			border: 1px #93D9CE solid;
			color: #93D9CE;
			background-color: white;
			box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
			margin: 2rem 0;
			padding: 0.5rem 1rem;
			cursor: pointer;

			&:hover, &:disabled {
				background-color: #93D9CE;
				color: white;
			}
		}
		& > div{
			position: fixed;
			bottom: 10%;
			right: 10%;
		}
  }
}
@media only screen and (max-width:799px){
	#mypage-container{
		width: 100%;
		padding-top: 20%;
		padding-left: 16px;
		padding-right: 16px;

		& > .info{


			& > .char-box{
				display: flex;
				justify-content: space-around;
				
				& > .char{
					text-align: center;
					border: 5px solid black;
					border-radius: 10px;
					width: 150px;
					height: 150px;
					// margin: auto;
					position: relative;

					& > img{
						width: 80%;
					}
					& > img{
						width: 100%;
						height: 100%;
						left: 0; top: 0;
						object-fit: contain;
						position: absolute;
						z-index: -2;
					}
					& > .parts{
						position: absolute;
						width: 100%; height: 100%;
						left: 0px;
						top: 0px;
						z-index: -1;

						& > img {
							& {
								width: 100%; height: 100%;
							}
						}
					}

					& > .background{
						//position: absolute;
						//width: 100%; height: 100%;
						//left: 0; top: 0;
						position: absolute;
						z-index: -3;
					}
				}
			}
			& > article{
				width: 100%;
				& > form{
					width: 100%;

					& > p{
						font-size: 1.5rem;
						font-weight: bold;
					}
					& > textarea{
						display: block;
						width:100% !important;
						border-radius: 10px;
						resize: none;
						padding: 15px;
						margin: 10px 0px;
					}		
					
					& > div{
						display: flex;
						justify-content: space-between;
						padding: 10px 0px;

						& > .title{
							width: 30%;
							font-size: 1.5rem;
							font-weight: bold;
						}

						& > .content{
							width: 70%;
							border: none;
							border-bottom: 1px #cccccc solid;
							font-size: 1.5rem;
							margin: 0 1rem;
							padding: 0.25rem 0.5rem;
						}

						& > .nickname_button{
							font-size: 12px;
							position: absolute;
							left: 65%;
							bottom: 25%;
						}
					}
				}
			}

			& > .botton-area{
				text-align: center;
			}

			& > .infomation-box{
				display: flex;
				justify-content: space-between;

				& > form{
					& > p{
						font-size: 1.25rem;
						font-weight: bold;
					}
				}
			}
		}
		button {
			font-size: 1.25rem;
			font-weight: bold;
			border-radius: 10px;
			border: 1px #93D9CE solid;
			color: #93D9CE;
			background-color: white;
			box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
			margin: 2rem 0;
			padding: 0.5rem 1rem;
			cursor: pointer;

			&:hover, &:disabled {
				background-color: #93D9CE;
				color: white;
			}
		}
	}
}

</style>
