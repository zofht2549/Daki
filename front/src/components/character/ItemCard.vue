<template>
    <div id="item-box-container"
        @click="dressUp()">
        <img :src="`${item.itemImage}`" alt="">
        {{ item.itemName }}
    </div>
</template>
<script>

export default {
    props:{
        item : Object,
        // tab : String,
    },
    methods:{
        dressUp(){
            // console.log(this.item.image)
            this.$emit('itemImage', this.item.itemImage)
            // this.$emit('categoryNum', this.item.categories)
            // console.log('출력',this.item)
            this.$store.dispatch('changeItem',this.item)
        }
    }
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
    cursor: pointer;

    & > img{
        width: 100%;
    }

}
</style>