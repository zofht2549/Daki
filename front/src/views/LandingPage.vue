<template>
  <div id="landingpage-container">
    <header class="main">
      <div class="image">
        <img class="diary" src="@/assets/landing/diary.png"/>
      </div>
      <div class="text">
        <div>
          <span>다키에서</span>
          <br>
          <span>일기쓰기</span>
        </div>
        <button>로그인</button>
      </div>
    </header>
    <section class="bgcolor content-padding">
      <div class="text">
        <span>매일 기록하는 하루일과</span>
        <br>
        <span>이제 더 쉽고 예쁘게</span>
      </div>
      <div class="image">
        <img src="@/assets/landing/editor.png" alt="">
      </div>
      <img class="character" src="@/assets/landing/character.png" alt="">
    </section>
    <section class="content-padding edit-bg">
      <article class="text-left">
				<img src="@/assets/landing/pencil.png" alt="">
        <span>캐릭터를 육성하며</span>
        <br>
        <span>매일매일 일기 습관 생성</span>
      </article>
      <article class="text-right">
				<img src="@/assets/landing/voice.png" alt="">
        <span>타이핑조차 귀찮을땐</span>
        <br>
        <span>음성인식을 통한 일기작성</span>
      </article>
      <article class="text-left">
				<img src="@/assets/landing/coin.png" alt="">
        <span>포인트를 모으고</span>
        <br>
        <span>캐릭터를 꾸며보아요</span>
      </article>
    </section>
    <section class="bgcolor content-padding">
      <div class="text"><span>다키와 함께 일기써요</span></div>
      <div><img class="logoimg" src="@/assets/landing/logo.png" alt=""></div>
      <div class="door_upper">
        <img src="@/assets/landing/door_upper.png" alt="">
      </div>
    </section>
    <section class="content-padding bottom-section">
      <div class="door_botton">
        <img src="@/assets/landing/door_botton.png" alt="">
      </div>
      <div>
        <button class="start">시작하기</button>
      </div>
    </section>

    <footer>
        <span>contect us</span>
        <br>
        <span>부산 강서구 녹산산업중로 333</span>
        <span>Copyright ⓒ팀이름</span>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'LandingPage',  
}
</script>

<style lang="scss">
@font-face{
  font-family : 'kyobo';
  src : url("@/assets/landing/Kyobo Handwriting 2019.ttf")
}
#landingpage-container{    
  width:100%;
  .main{
		& {
			height:100vh;
			background-image: url("@/assets/landing/paper_bg.png");
			padding-left:15%;
			padding-right:15%;
			padding-top: 5%;
			display: flex;
			justify-content: space-between;
		}
    & .image{
      // float: left;

      .diary{
        width: 80%;
      }
    }
    .text{
      // float: right;
      font-family: kyobo;
      font-weight: 400;
      font-size: 64px;
    }
  }
  .content-padding{
    padding-left: 15%;
    padding-right: 15%;
    position: relative;
		text-align: center;

    .text-left{
      text-align: left;
      font-family: kyobo;
      font-weight: 400;
      font-size: 2.5rem;
      margin: 10% 0% 10% 0%;

			& > span{
				z-index: 2;

			}
			& > img{
				z-index: 1;
				position: absolute;
				width: 20%;
			}
    }
    .text-right{
      text-align: right;
      font-family: kyobo;
      font-weight: 400;
      font-size: 2.5rem;
      margin: 10% 0% 10% 0%;
			& > span{
				z-index: 2;

			}
			& > img{
				z-index: 1;
				position: absolute;
				width: 20%;
			}
    }
  }

  .bgcolor{
    background-color:#93D9CE;
    // display: table-cell;
    // vertical-align: middle;
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: relative;
    font-size:0;
		z-index: 2;
    .text{
      font-size: 48px;
      font-weight: 400;
      color: #fff;
      text-shadow: 1px 2px 4px rgba(0, 0, 0, 0.25);
      font-family: kyobo;

      // display: flex;
      // align-items: center;

      // margin-top: auto;
      // margin-bottom: auto;

      // display: inline-block;
    }
    .image{
      margin: 5%;
    }
    .character{
      width: 15%;
      position: absolute;
      transform: translate(800px,150px);
    }
  }
  .edit-bg{
  }
  .start{
    // display: flex;
    // align-items: center;
    // text-align: center;
    // width: 100%;
    // margin: 0;
    display: inline-block;
    margin-right: auto;
    margin-left: auto;
    margin-top: 5%;
    margin-bottom: 5%;
		margin: 0;
  }
	.bottom-section{
		padding-top: 300px;
		padding-bottom: 50px;
	}
  .logoimg{
    padding: 20%;
  }
  .door_upper{
    position: absolute;
    bottom: 0;
		& > img {
			width: 100px;
		}
  }
  .door_botton{
    position: absolute;
    top: 0%;
		& > img{
			width: 22px;
		}
  }
  button{
    font-size: 1.25rem;
    font-weight: bold;
    border-radius: 10px;
    border: 1px #93D9CE solid;
    color: #93D9CE;
    background-color: white;
    box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
    // margin: 3rem 0 0;
		margin: 0;
    padding: 1rem 5rem;
    cursor: pointer;

    &:hover {
      background-color: #93D9CE;
      color: white;
    }
  }
  footer{
    background-color: #d8d8d8;
  }
}
</style>