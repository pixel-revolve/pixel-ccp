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
        <a-card size="small" title="全站榜单" class="card2">
          <template #extra><a href="#">更多</a></template>
          <p>
            <a-avatar :src="imgUrl1" style="margin-right: 15px"/>
            帮闺蜜找靠谱男票，hc多多
          </p>
          <p>
            <a-avatar :src="imgUrl2" style="margin-right: 15px"/>
            讯飞太让人失望了
          </p>
          <p>
            <a-avatar :src="imgUrl3" style="margin-right: 15px"/>
            邮储银行总行面试
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
  MailOutlined
} from '@ant-design/icons-vue';
import 'animate.css'
import relativeTime from 'dayjs/plugin/relativeTime';
import {onMounted, ref, reactive} from "vue";
import {useRouter} from "vue-router";
import {getImgUrl} from '../../../utils/imgUtil.ts'
dayjs.extend(relativeTime);
const imgUrl1 = ref(getImgUrl())
const imgUrl2 = ref(getImgUrl())
const imgUrl3 = ref(getImgUrl())

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