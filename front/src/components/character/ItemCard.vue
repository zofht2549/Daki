<template>
    <div id="item-box-container">
        <img :src="`${item.image}`" alt="">
        {{ item.title }}
    </div>
</template>
<script>

export default {
    props:{
        item : Object,
        // tab : String,
    },
}
</script>
<style lang="scss">
#item-box-container{
    box-shadow: inset 1px 2px 4px rgba(0, 0, 0, 0.25);
    background: #fff;
    text-align: center;
    padding: 5px 20px;
    margin: 5px;
    border-radius: 10px;
    position: relative;
    width: 30%;

    & > img{
        width: 100%;
    }

}
</style>