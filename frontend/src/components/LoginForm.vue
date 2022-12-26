<template>
  <a-modal v-model:visible="isLoginVisible" width="310px" style="padding-bottom: -10px">
    <template #footer>
    </template>
    <p style="font-weight: 600;font-size: 22px">
      手机登录
    </p>
    <p>
      <a-input v-model:value="phoneNumber" style="width: 100%" placeholder="请输入手机号码"/>
    </p>
    <p>
      <a-input-search
          v-model:value="code"
          placeholder="请输入验证码"
          size="middle"
      >
        <template #enterButton>
          <a-button style="color: #105b43" type="dashed" @click="getCode">获取验证码</a-button>
        </template>
      </a-input-search>
    </p>
    <p>
      <a-button type="primary" style="width: 100%" @click="login">登录</a-button>
    </p>
    <p style="color: #105b43">其他登陆方式</p>
    <p>注册登录即表示同意<span style="color: #105b43">用户协议，隐私协议</span></p>
  </a-modal>
</template>

<script setup>
import {ref, watch, defineProps} from "vue";
import {getCodeUsingPhone, loginByPhone} from "../apis/authApis.ts";
import {message, notification} from "ant-design-vue";
import {useUserStore} from "../store/userStore.ts";
const isLoginVisiblePass = defineProps(['isLoginVisible']);
const isLoginVisible = ref(true)
//开关自己
watch(() => isLoginVisiblePass.isLoginVisible, (value) => {
  console.log('登录组件watch:' + value)
  isLoginVisible.value = value
}, {immediate: true})
const phoneNumber = ref('')
const code = ref('')
const userStore = useUserStore();
const getCode = async () => {
  try {
    const result = await getCodeUsingPhone(phoneNumber.value);
    if (result.data.data) {
      code.value = result.data.data;
      notification.open({
        message: '登录',
        description: '验证码是' + result.data.data,
      });
    }
  } catch (e) {
    message.error("发送验证码失败")
  }
}
const login = async () =>{
  try {
    const result = await loginByPhone(phoneNumber.value,code.value);
    if(result.data.code === 0){
      userStore.setIsShowModal(false);
      userStore.setAuthorization(result.data.data)
      message.info("登录成功" + "token:" + userStore.getAuthorization())
    }
  }catch (e){
    message.error("登录出错")
  }

}

</script>

<style scoped>

</style>