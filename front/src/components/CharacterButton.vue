<template>
    <div id="character-container">
        <div @click="openPopup()">
            <img src="@/assets/character/character.png" alt="">
        </div>

        <div class="character-popup" :class="{ active : popupView }">
            <character-pop-up
                @close-pop-up="openPopup()"></character-pop-up>
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
        }
    },
    components:{
        CharacterPopUp,
    },
    methods:{
        openPopup(){
            this.popupView = (this.popupView) ? false : true;
        }
    }
}
</script>
<style lang="scss">
    #character-container{
        position: fixed;
        bottom:10%;
        right: 10%;

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
