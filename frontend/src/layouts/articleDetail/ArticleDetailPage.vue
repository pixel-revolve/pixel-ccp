<template>
  <div style="width: 50vw;border: 1px solid #deede7;border-radius: 6px;padding: 25px">
    <a-comment>
      <template #actions>
        <span key="comment-nested-reply-to"><a-button type="dashed" size="small">回复</a-button></span>
      </template>
      <template #author>
        <a>{{ articleDetail.nickname }}</a>
        <span style="margin-left: 20px">
        <a-tag color="green">帅气</a-tag>
      </span>
      </template>
      <template #avatar>
        <a-avatar :src="articleDetail.avatar" alt="Han Solo"/>
      </template>
      <template #content>
        <p style="font-size: 18px;margin-top: 15px">
        <div v-for="(item,index) in articleDetail.contents " :key="index" :style="computeStyle(item.type)">
          {{ item.content }}
        </div>
        </p>
        <div style="display: flex;flex-direction: column;margin-top: 15px">
          <div style="display: flex;">
            <div style="color: #838384">
              发布于 {{ dayjs(articleDetail.createdOn).format('YYYY-MM-DD HH:mm:ss') }}
            </div>
            <div style="margin-left: 20px;color: #838384">
              ip属地:{{ articleDetail.ip }}
            </div>
            <div style="margin-left: 20px;color: #838384">
              最后回复事件:{{ articleDetail.latestRepliedOn }}
            </div>
            <div style="margin-left: 20px;color: #838384">
              观看次数:{{ articleDetail.watchCount }}
            </div>
          </div>
          <div style="display: flex;margin-top: 15px">
            <span key="comment-basic-like">
        <a-tooltip title="Like">
          <template v-if="action === 'liked'">
            <LikeFilled @click="like"/>
          </template>
          <template v-else>
            <LikeOutlined @click="like"/>
          </template>
        </a-tooltip>
        <span style="padding-left: 8px; cursor: auto">
          {{ articleDetail.upvoteCount }}
        </span>
      </span>
            <span style="margin-left: 20px">
              <comment-outlined />
              {{articleDetail.commentCount}}
            </span>
            <span style="margin-left: 20px" @click="insertCollection">
              <money-collect-outlined />
              {{articleDetail.collectionCount}}
            </span>
          </div>
        </div>
      </template>
    </a-comment>
  </div>

</template>

<script setup lang="ts">
import {computed, onMounted, reactive, ref} from "vue";
import {useRoute} from "vue-router";
import {
  changeIsLikeByIdRequest,
  checkIsLikedByIdRequest,
  getArticleDetailByIdRequest,
  insertCollectionRequest
} from "../../apis/articleApi.ts";
import {message} from "ant-design-vue";
import {getImgUrl} from "../../utils/imgUtil.ts";
import {LikeFilled, LikeOutlined,CommentOutlined,MoneyCollectOutlined} from '@ant-design/icons-vue';
import relativeTime from 'dayjs/plugin/relativeTime';
import dayjs from "dayjs";
import {useUserStore} from "../../store/userStore.ts";
dayjs.extend(relativeTime);

const userStore = useUserStore();
const articleId = ref();
const route = useRoute();

const styles = ref({
  title: {
    fontSize: '22px'
  },
  paragraph: {
    fontSize: '18px',
    color: '#2b2b2b'
  },
  picUrl: {
    color: '#74797e',
  },
  videoUrl: {
    color: '#105b43'
  },
  voiceUrl: {
    color: '#3ed0b2'
  },
  linkUrl: {
    color: '#2bc3d0',
    fontSize: '16px'
  },
  attachUrl: {
    color: '#0e539a'
  },
  moneyUrl: {
    color: '#ffd14e'
  }
})
const computeStyle = (status: any) => {
  console.log("status:" + status)
  switch (status) {
    case 1:
      return styles.value.title;
    case 2:
      return styles.value.paragraph;
    case 3:
      return styles.value.picUrl;
    case 4:
      return styles.value.videoUrl;
    case 5:
      return styles.value.voiceUrl;
    case 6:
      return styles.value.linkUrl;
    case 7:
      return styles.value.attachUrl;
    case 8:
      return styles.value.moneyUrl;
  }
}


const imgUrl = ref(getImgUrl())
let articleDetail: any = ref({});

const likes = ref<number>(0);
const dislikes = ref<number>(0);
const action = ref<string>();

onMounted(async () => {
  articleId.value = route.query.id;
  console.log("文章id:" + articleId.value)
  try {
    const res = await getArticleDetailByIdRequest(articleId.value);
    await changeIsLikeByIdRequest(articleId.value)
    const res2 = await changeIsLikeByIdRequest(articleId.value);
    if(res2.data.code === 0){
      if(res2.data.msg === '取消点赞'){
        likes.value = 0
        dislikes.value = 1
        action.value = 'disliked'
      }else if(res2.data.msg === '成功点赞'){
        likes.value = 1
        dislikes.value = 0
        action.value = 'liked'
      }
    }
    if (res.data.code === 0) {
      articleDetail.value = res.data.data
      articleDetail.value.nickname = route.query.nickname
      console.log("文章详情：" + JSON.stringify(articleDetail))
    } else {
      message.error(res.data.msg)
    }
  } catch (e) {
    message.error("获取不到该文章")
  }
})


const like = async () => {
  try {
    const res = await changeIsLikeByIdRequest(articleId.value);
    if (res.data.code === 0) {
      console.log("点赞数:" + res.data.data)
      articleDetail.value.upvoteCount = res.data.data
      console.log(articleDetail.value.upvoteCount)
    }
  } catch (e) {
    message.error("点赞失败")
  }
  if (dislikes.value === 1 && likes.value === 0) {
    likes.value = 1;
    dislikes.value = 0;
    action.value = 'liked';
  } else {
    likes.value = 0;
    dislikes.value = 1;
    action.value = 'disliked';
  }
};

const insertCollection = async () =>{
  const userId = userStore.getUserId();
  try {
    const res = await insertCollectionRequest(articleId.value);
    if(res.data.code === 0){
      console.log(res.data.data)
      articleDetail.value.collectionCount = res.data.data
    }
  }catch (e){
    message.error("收藏失败")
  }
}


</script>

<style scoped>

</style>