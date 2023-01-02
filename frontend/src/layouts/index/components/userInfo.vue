<template>
  <a-drawer
      title="查看个人信息"
      :width="720"
      :visible="visible"
      :body-style="{ paddingBottom: '80px' }"
      :footer-style="{ textAlign: 'right' }"
      @close="onClose"
  >
    <a-form :model="form" :rules="rules" layout="vertical">
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="id" name="id">
            <a-input v-model:value="form.id" disabled/>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="用户名" name="username">
            <a-input v-model:value="form.username" placeholder="Please enter username" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="昵称" name="nickname">
            <a-input v-model:value="form.nickname" placeholder="Please enter nickname" />
          </a-form-item>
        </a-col>

      </a-row>
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="电话" name="phone">
            <a-input v-model:value="form.phone" placeholder="Please enter phone" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="邮箱" name="email">
            <a-input
                v-model:value="form.email"
                style="width: 100%"
                addon-after="@qq.com"
                placeholder="please enter email address"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="头像地址" name="avatarUrl">
            <a-input v-model:value="form.avatar" placeholder="Please enter avatarUrl" />
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
    <template #extra>
      <a-space>
        <a-button @click="onClose">Cancel</a-button>
        <a-button type="primary" @click="upgradeUserInfo">Submit</a-button>
      </a-space>
    </template>
  </a-drawer>
</template>
<script lang="ts">
import { PlusOutlined } from '@ant-design/icons-vue';
import { defineComponent, reactive, ref } from 'vue';
import type { Rule } from 'ant-design-vue/es/form';
import {useUserStore} from "../../../store/userStore";
import {updateUserInfoRequest} from "../../../apis/userApis";
import {message} from "ant-design-vue";
export default defineComponent({
  components: {
    PlusOutlined,
  },
  setup() {

    const userStore = useUserStore();

    const form = ref({
      id:userStore.getUserId(),
      username: userStore.getUsername(),
      nickname:userStore.getNickname(),
      phone:userStore.getPhone(),
      email:userStore.getEmail(),
      avatar: userStore.getAvatar(),
    });

    const rules: Record<string, Rule[]> = {
      username: [{ required: true, message: 'Please enter username' }],
      nickname: [{ required: true, message: 'please enter nickname' }],
      phone: [{ required: true, message: 'Please enter phone' }],
      email: [{ required: true, message: 'Please enter phone' }],
      avatarUrl: [{ required: true, message: 'Please enter avatar' }],
    };

    const visible = ref<boolean>(false);

    const showDrawer = () => {
      visible.value = true;
    };

    const onClose = () => {
      visible.value = false;
    };

    const upgradeUserInfo = async () =>{
      try {
        const res = await updateUserInfoRequest(form.value);
        if(res.data.code === 0){
          message.info("更新成功")
        }
      }catch (e){
        message.error("更新用户信息失败")
      }
    }
    return {
      form,
      rules,
      visible,
      showDrawer,
      onClose,
      upgradeUserInfo
    };
  },
});
</script>