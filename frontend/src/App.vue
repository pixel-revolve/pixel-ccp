<script setup lang="ts">
import {onMounted, ref, watch} from "vue";
import LoginForm from '../src/components/LoginForm.vue'
import {useUserStore} from "./store/userStore";
const userStore = useUserStore();
const isShow = ref(false);
onMounted(()=>{
  console.log(userStore.getIsShowModal())
  isShow.value = userStore.getIsShowModal();
})
watch(
    () => userStore.getIsShowModal(),
    (value) => {
      console.log("监听到的store中的：" +value)
      isShow.value = value;
    },{
      immediate:true
    })
</script>

<template>
  <login-form :isLoginVisible="isShow"/>
  <router-view/>
</template>

<style scoped>
</style>
