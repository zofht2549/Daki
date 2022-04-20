<template>
  <div id="landingpage-container">
    <header class="main">
      <div class="image">
        <img src="@/assets/landing/diary.png"/>
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
      <div class="clear"></div>
    </section>
    <section class="content-padding">
      <div>bgimg</div>
      <article class="text-left">
        캐릭터를 육성하며 매일매일 일기 습관 생성
      </article>
      <article class="text-right">
        타이핑조차 귀찮을땐 음성인식을 통한 일기작성
      </article>
      <article class="text-left">
        포인트를 모으고 캐릭터를 꾸며보아요
      </article>
    </section>
    <section class="bgcolor content-padding">
      <div class="text">다키와 함께 일기써요</div>
      <div><img src="@/assets/landing/logo.png" alt=""></div>
      <div class="clear"></div>
    </section>
    <section>
      <div>
        이미지
      </div>
      <div>
        <button>시작하기</button>
      </div>
    </section>

    <footer>
        <span>contect us</span>
        <br>
        <span>부산 강서구 녹산산업중로 333</span>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'LandingPage',  
}
</script>

<style lang="scss">
#landingpage-container{
  .main{
    height:100vh;
    background-image: url("@/assets/landing/paper_bg.png");

    .image{
      float: left;
      padding-left: 15%;
    }
    .text{
      float: right;
      padding-right: 15%;
    }
  }
  .content-padding{
    padding-left: 15%;
    padding-right: 15%;

    .text-left{
      text-align: left;
    }
    .text-right{
      text-align: right;
    }
  }
  .bgcolor{
    background-color:#93D9CE;
    .text{
      font-size: 48px;
      float: left;
      font-weight: 400;
      color: #fff;
      text-shadow: 1px 2px 4px rgba(0, 0, 0, 0.25);
    }
    .image{
      float: right;
    }
    .clear{
      clear:both;
    }
  }
  button{
    color: #93D9CE;
    background-color: white;
    border: 1px solid #93D9CE;
    box-sizing: border-box;
    border-radius: 10px;
    box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.25);
    // padding-top: 15px;
    // padding-bottom: 15px;
    padding: 15px 30px 15px 30px;
    font-weight: 700;
  }
  footer{
    background-color: #d8d8d8;
  }
}
</style>