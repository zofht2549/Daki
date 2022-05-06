<template>
    <div id="mypage-container">
      <navigation />
      <section class="info">
				<article class="char-box">
					<div class="char">
						<img src="@/assets/landing/character.png" alt="">
					</div>
					<div class="char-info">
						<div class="nickname">닉네임</div>
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
							<span class="content">닉네임</span>
							<button
								class="nickname_button" @click="changeNickname()">
								닉네임 변경
							</button>
						</div>
						<div for="birth">
							<span class="title">생년월일</span>
							<span class="content">1997-10-07</span>
						</div>
						<div for="gender">
							<span class="title">성별</span>
							<span class="content">여</span>
						</div>
					</form>
					<article class="botton-area">
						<button @click="changePassword()">비밀번호 수정</button>
						<br>
						<a href="#">회원탈퇴</a>
					</article>
				</article>
      </section>
			<div>
				<character-button></character-button>
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

export default {
  name: 'MyPage',
	data: () => {
		return {
			
			popupVal : false,
			target : false,

			passwordView : false,
			nicknameView : false,
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
    showDateDiary: function(t){
      this.target = t
    },
		changePassword(){
			this.passwordView = (this.passwordView) ? false : true
		},
		changeNickname(){
			this.nicknameView = (this.nicknameView) ? false : true
		}

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

					& > img{
						width: 80%;
						height: 80%;
						object-fit:contain;
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
				.botton-area{
					text-align: center;
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
		& > div{
			position: fixed;
			bottom: 10%;
			right: 10%;
		}
  }
</style>
