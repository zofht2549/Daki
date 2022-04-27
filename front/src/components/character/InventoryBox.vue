<template>
    <div id="inventory-container">
        <div class="item">
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
        </div>
    </div>
</template>
<script>

</script>
<style lang="scss">
#inventory-container{
    display: flex;

    & > .item{
        box-shadow: inset 1px 2px 4px rgba(0, 0, 0, 0.25);
        background: #fff;
        text-align: center;
        padding: 5px 30px;
        margin: 10px;
        border-radius: 10px;
        position: relative;


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