<template>
    <div id="inventory-container">
        <item-card
            v-for="item in Hello" :key="item.id"
            :item="item"
            :tab="tab"
            @itemImage="itemImage"
            @categoryNum="categoryNum"></item-card>
            <!-- {{ item.title }} -->

        <!-- <div class="item">
            <img src="@/assets/character/item.png" alt="">
            <p>item name</p>
        </div>
        <div class="item">
            <img src="@/assets/character/item.png" alt="">
            <p>item name</p>
            <div class="unused">
                <div>
                    <div><img src="@/assets/coin.png" alt=""> 1000P</div>
                    <div><img src="@/assets/heart.png" alt=""> +5</div>
                </div>
            </div>
        </div>
        <div class="item">
            <img src="@/assets/character/item.png" alt="">
            <p>item name</p>
        </div> -->
    </div>
</template>
<script>
import Dummmy from './Dummmy.js'
import ItemCard from '@/components/character/ItemCard.vue'

export default{
    components:{
        ItemCard,
    },
    data: () => {
        return{
            Dummmy:Dummmy,
            Hello : null,
            ItemImage : null,
            CategoriesNum : null,
        }
    },
    props:{
        tab: Number,
    },
    methods:{
        itemImage(data){
            this.ItemImage = data
            this.$emit('itemImage', data);
        },
        categoryNum(data){
            this.CategoriesNum = data
            this.$emit('categoryNum', data)
            console.log('카테고리 넘버 (중간)',data)
        }
    },
    created(){
        this.Hello = this.Dummmy.filter((item) => {
            return (item.categories == this.tab);
        });

        console.log(this.Dummmy);
    },
    watch:{
        matchingTab: function(){
            this.Hello = this.Dummmy.filter((item) =>{
                return (item.categories == this.tab)
            })
        },
        tab:function(){
            this.Hello = this.Dummmy.filter((item) =>{
                return (item.categories == this.tab)
            })
            console.log(this.tab)
        }
    }
}
</script>
<style lang="scss">
#inventory-container{
    display: flex;
    flex-wrap: wrap;

    & > .item{
        // box-shadow: inset 1px 2px 4px rgba(0, 0, 0, 0.25);
        // background: #fff;
        // text-align: center;
        // padding: 5px 30px;
        // margin: 10px;
        // border-radius: 10px;
        // position: relative;


        & > img{
            width: 50%;
        }
        & > .unused{
            position: absolute;
            background: rgba(0, 0, 0, 0.25);
            width: 100%;
            height: 100%;
            border-radius: 10px;
            left: 0px;
            top: 0px;
            color: #fff;
            font-weight: bold;
            display: table;

            & > div{
                display: table-cell;
                vertical-align: middle;
            }

        }      
    }

}
</style>