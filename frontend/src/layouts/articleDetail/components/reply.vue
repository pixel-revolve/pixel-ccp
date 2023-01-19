<template>
  <a-comment>
    <template #actions>
      <span key="comment-nested-reply-to"><a-button type="dashed" size="small" @click="reply">回复</a-button></span>
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
</template>

<script setup lang="ts">
import {LikeFilled, LikeOutlined,CommentOutlined,MoneyCollectOutlined} from '@ant-design/icons-vue';

</script>

<style scoped>

</style>