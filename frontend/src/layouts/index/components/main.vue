<template>
  <div class="container">
    <div class="left animate__animated animate__fadeInLeft">
      <a-menu
          v-model:selectedKeys="state.selectedKeys"
          class="left-menu"
          mode="vertical"
          :open-keys="state.openKeys"
          @click="handleClick"
      >
        <a-menu-item key="playground" >
          <template #icon>
            <MailOutlined/>
          </template>
          广场
        </a-menu-item>
        <a-menu-item key="topic">
          <template #icon>
            <MailOutlined/>
          </template>
          话题
        </a-menu-item>
        <a-menu-item key="dynamic">
          <template #icon>
            <MailOutlined/>
          </template>
          动态
        </a-menu-item>
      </a-menu>
    </div>

    <div class="middle">
      <router-view/>
    </div>
    <div class="right animate__animated animate__fadeInRight">
      <div>
        <a-card :bordered="true" class="card1">
          <div class="create-center-title">创作中心</div>
          <div class="create-center-content">
            <div class="create-center-content-left">
              <div>发动态</div>
              <file-text-outlined style="font-size: 40px"/>
            </div>
            <div class="create-center-content-right">
              <div>写文章</div>
              <file-text-two-tone style="font-size: 40px;" two-tone-color="#1DA57A"/>
            </div>
          </div>
          <div class="create-center-bottom">
            <a-button type="primary" style="margin-top: 30px;width: 70%">
              进入创作者中心
            </a-button>
          </div>
        </a-card>
        <a-card size="small" title="热门话题" class="card2">
          <template #extra><a href="#">更多</a></template>
          <p v-for="(data,index) in postLikeList" :key="index" style="display: flex;justify-content: space-between">
            <div style="color: #1DA57A"> # {{data.title}} </div>
            <div><like-outlined style="margin-right: 5px"/>{{data.upvoteCount}}</div>
          </p>
        </a-card>
        <div class="card3">
          <el-carousel :interval="5000" arrow="always">
            <el-carousel-item v-for="item in 3" :key="item">
              <h3 text="2xl" justify="center">广告 {{ item }}</h3>
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import dayjs from 'dayjs';
import {
  FileTextOutlined,
  FileTextTwoTone,
  MailOutlined,
    LikeOutlined
} from '@ant-design/icons-vue';
import 'animate.css'
import relativeTime from 'dayjs/plugin/relativeTime';
import {onMounted, ref, reactive} from "vue";
import {useRouter} from "vue-router";
import {getImgUrl} from '../../../utils/imgUtil.ts'
import {postLikeRank} from "../../../apis/articleApi.ts";
import {message} from "ant-design-vue";
dayjs.extend(relativeTime);

const postLikeList = ref([]);

onMounted(async ()=>{
  try {
    const res = await postLikeRank();
    if(res.data.code === 0){
      postLikeList.value = res.data.data
    }
  }catch (e){
    message.error("获取热点榜单失败");
  }

})

const router = useRouter();
const state = reactive({
  rootSubmenuKeys: ['playground', 'topic', 'dynamic'],
  openKeys: ['playground'],
  selectedKeys: [],
});

const handleClick = (e:any)=> {
  console.log('click', e.key);
  router.push('/index/'+e.key)
};

</script>

<style scoped lang="scss">
@font-face {
  font-family: 'iconfont';  /* Project id 3767109 */
  src: url('//at.alicdn.com/t/c/font_3767109_ndri6hj9vwr.woff2?t=1668259522266') format('woff2'),
  url('//at.alicdn.com/t/c/font_3767109_ndri6hj9vwr.woff?t=1668259522266') format('woff'),
  url('//at.alicdn.com/t/c/font_3767109_ndri6hj9vwr.ttf?t=1668259522266') format('truetype');
}

.container {
  display: flex;
  min-width: 100vw;
  justify-content: space-around;

  .left {
    .left-menu {
      width: 256px;
      min-height: 100vh
    }
  }

  .right {
    display: flex;
    flex-direction: column;

    .card1 {
      width: 300px;

      .create-center-title {
        font-size: 20px;
        font-weight: 600;
      }

      .create-center-content {
        display: flex;
        width: 100%;
        justify-content: space-around;
        margin-top: 20px;

        .create-center-content-left {
          display: flex;
          flex-direction: column;
        }

        .create-center-content-right {
          display: flex;
          flex-direction: column;
        }
      }

      .create-center-bottom {
        display: flex;
        justify-content: center;
      }
    }

    .card2 {
      width: 300px;
      margin-top: 20px
    }

    .card3 {
      margin-top: 20px;
      height: 300px;
    }

  }
}


.el-menu-demo {
  border-bottom: none;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}


/**
  这是轮播图组件
 */
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

</style>