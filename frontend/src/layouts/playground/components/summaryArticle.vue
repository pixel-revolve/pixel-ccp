<template>
  <a-comment @click="goToDetail" style="border: 1px solid #deede7;border-radius: 6px;margin-top: 15px;padding: 5px">
    <template #actions>
      <span key="comment-basic-like">
        <a-tooltip title="Like">
          <template v-if="action === 'liked'">
            <LikeFilled @click="like"/>
          </template>
          <template v-else>
            <LikeOutlined @click="like"/>
          </template>
        </a-tooltip>
        <span class="like">
          {{  record.upvoteCount }}
        </span>
      </span>
      <span key="comment-basic-reply-to"><comment-outlined/> {{ record.commentCount }}</span>
      <span key="comment-basic-view" style="display: inline-block;align-items: center">
          <folder-view-outlined/>
          {{ record.watchCount }}
        </span>
    </template>
    <template #author><a style="font-size: 15px;margin-right: 20px;">{{ record.nickname }}</a></template>
    <template #avatar>
      <a-avatar :src="record.avatar" alt="Han Solo"/>
    </template>
    <template #content>
      <p style="font-size: 18px;">
        {{record.summary}}
      </p>
    </template>
    <template #datetime>
      <a-tooltip :title="dayjs().format('yyyy-MM-DDTHH:mm:ss.000+00:00')" >
        <span>{{ dayjs(record.createdOn).format('YYYY-MM-DD HH:mm:ss') }}</span>
      </a-tooltip>
      <span style="margin-left: 30px">
        <a-tag color="green">{{ record.tags }}</a-tag>
      </span>
    </template>
  </a-comment>
</template>

<script setup lang="ts">
import dayjs from 'dayjs';
import {
  LikeFilled,
  LikeOutlined,
  FolderViewOutlined,
  CommentOutlined
} from '@ant-design/icons-vue';
import { ref,defineProps} from 'vue'
import relativeTime from 'dayjs/plugin/relativeTime';
import {useRouter} from "vue-router";
import {getImgUrl} from "../../../utils/imgUtil.ts";
dayjs.extend(relativeTime);
const router = useRouter();
const data = defineProps(['record']);
const record = ref(data.record)

const likes = ref<number>(0);
const dislikes = ref<number>(0);
const action = ref<string>();


const goToDetail = () =>{
  console.log("id是："+record.value.id)
  router.push({
    path:"/index/article/detail",
    query:{
      'id':record.value.id,
      'nickname':record.value.nickname
    }
  })
}

const like = () => {
  likes.value = 1;
  dislikes.value = 0;
  action.value = 'liked';
  console.log(JSON.stringify(record.value))
};

const dislike = () => {
  likes.value = 0;
  dislikes.value = 1;
  action.value = 'disliked';
};
</script>

<style scoped lang="scss">




</style>