<template>
  <div style="font-size: 27px;font-weight: 600" class="animate__animated animate__fadeInDown">
    像素广场
  </div>
  <div style="margin-top: 20px" class="animate__animated animate__fadeIn">
    <publish/>
  </div>
  <div class="middle-content">
    <summary-article v-for="record in records" :record="record"
                     class="animate__animated delay-1s animate__fadeInUp"/>
  </div>
  <div class="bottom-pagination">
    <a-pagination v-model:current="current"/>
  </div>
</template>

<script setup>
import Publish from './components/publish.vue'
import {onMounted, ref} from "vue";
import {listPageArticleVoRequest} from "../../apis/articleApi.ts";
import SummaryArticle from './components/summaryArticle.vue'
import {message} from "ant-design-vue";
import {getImgUrl} from "../../utils/imgUtil.ts";

const records = ref([])
const current = ref(10)
onMounted(async () => {
  try{
    const result = await listPageArticleVoRequest(1, 10);
    records.value = result.data.data.data.records
    console.log(JSON.stringify(records.value))
    records.value.forEach(record =>{
      record.imgUrl = getImgUrl()
    })
  }catch (e){
    message.error("获取文章失败")
  }

})
</script>

<style scoped lang="scss">
.middle-content {
  padding: 20px;
  width: 50vw;

  .like {
    padding-left: 8px;
    cursor: auto
  }

  .dislike {
    padding-left: 8px;
    cursor: auto
  }
}
.bottom-pagination{
  margin-top: 20px;
}
</style>