<template>
  <div class="container">
    <div class="left animate__animated animate__fadeInLeft" >
      <div class="logo"/>
      <div class="nav-logo-text" @click="showLogin">
        像素社区
      </div>
    </div>
    <div class="middle animate__animated animate__fadeInDown" >
      <a-input-search
          v-model:value="value"
          placeholder="搜一搜"
          style="width: 600px"
          size="large"
          @search="onSearch"
      />
    </div>

    <div class="right animate__animated animate__fadeInRight" @click="gotoUserInfo">
      <el-icon style="margin-right: 40px">
        <bell-filled style="font-size: 26px;color: #1DA57A"/>
      </el-icon>
      <a-avatar :size="64" style="color: #1DA57A" :src="imgUrl" @click=""/>
      <span style="color:#3db389;">{{nickName}}</span>
    </div>
    <user-info ref="userInfo"/>
  </div>
</template>


<script setup lang="ts">
import {BellFilled} from '@ant-design/icons-vue';
import {onMounted, ref} from "vue";
import 'animate.css'
import {useUserStore} from "../../../store/userStore";
import {useRouter} from "vue-router";
import UserInfo from "./userInfo.vue";

const userStore = useUserStore();
const imgUrl = ref(userStore.getAvatar())
const nickName = ref(userStore.getNickname())
const router = useRouter()

const userInfo = ref<any>(null)

const value = ref('')
onMounted(()=>{

})
const showLogin = ()=>{
  userStore.setIsShowModal(true)
}

const gotoUserInfo = () =>{
  userInfo.value.showDrawer();
}

const onSearch = () => {

}

</script>

<style scoped>
.container {
  height: 80px;
  display: flex;
  min-width: 100vw;
  flex-direction: row;
  align-items: center;
  justify-content: space-between
}

.left {
  display: flex;
  align-items: center;
  margin-left: 5%
}

.middle {
  align-items: center;
  text-align: center
}

.right {
  align-items: flex-end;
  margin-right: 5%;
}

.logo {
  width: 70px;
  height: 70px;
  background: linear-gradient(93.51deg, #1DA57A 2.84%, #40a185 99.18%);
}

.nav-logo-text {
  background: linear-gradient(93.51deg, #1DA57A 2.84%, #40a185 99.18%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  font-family: 'Poppins', serif;
  font-style: normal;
  font-weight: 700;
  font-size: 32px;
  margin-right: 30px;
  margin-left: 20px;
}
</style>