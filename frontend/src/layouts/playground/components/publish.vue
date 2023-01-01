<template>
  <a-card style="display: flex;width: 50vw;flex-direction: column;">
    <div style="display: flex;width: 500px;align-items: center">
      <a-avatar :src="imgUrl"/>
    </div>
    <div style="margin-top: 20px;display: flex;width: 500px;align-items: center">
      <div style="font-size: 15px">
        添加标签
      </div>
      <div>
        <a-auto-complete
            v-model:value="tagValue"
            :options="options"
            style="width: 200px;margin-left: 20px"
            placeholder="标签"
            :filter-option="filterOption"
        />
        <a-button style="margin-left: 10px" @click="addTag">添加</a-button>
      </div>

    </div>
    <div style="margin-top: 20px;display: flex">
      <div style="margin-right: 10px" v-if="tagList.length !== 0" class="animate__animated animate__fadeInRight">标签：
      </div>
      <a-tag v-for="(tag,index) in tagList" :key="index" color="green" class="animate__animated animate__fadeInRight">
        {{ tag }}
      </a-tag>
    </div>

    <div style="margin-top: 20px;display: flex;width: 500px;align-items: center">
      <div style="font-size: 15px">
        添加标题
      </div>
      <div>
        <a-input v-model:value="summary" placeholder="可爱的标题" style="margin-left: 20px;width: 100%"/>
      </div>
    </div>

    <div style="margin-top: 20px;display: flex;width: 500px;align-items: center">
      <div style="font-size: 15px;min-width: 60px">
        文章内容
      </div>
      <div style="width: 100%">
        <a-textarea v-model:value="content" placeholder="说说你的新鲜事儿吧" allow-clear
                    style="margin-left: 20px;width:100%"/>
      </div>
    </div>
    <div style="margin-top: 20px" v-show="isUploadLink" class="animate__animated animate__fadeInLeft">
      <a-input v-model:value="httpBody">
        <template #addonBefore>
          <a-select v-model:value="httpChoose" style="width: 90px">
            <a-select-option value="http://">http://</a-select-option>
            <a-select-option value="https://">https://</a-select-option>
          </a-select>
        </template>
        <template #addonAfter>
          <a-select v-model:value="httpBackFix" style="width: 80px">
            <a-select-option value=".com">.com</a-select-option>
            <a-select-option value=".jp">.jp</a-select-option>
            <a-select-option value=".cn">.cn</a-select-option>
            <a-select-option value=".org">.org</a-select-option>
          </a-select>
        </template>
      </a-input>
    </div>
    <div style="margin-top: 20px;display: flex;">
      <a-upload
          :custom-request="uploadImg"
          v-model:file-list="fileList"
          name="file"
      >
        <a-button type="dashed">
          <upload-outlined></upload-outlined>
          上传图片
        </a-button>
      </a-upload>
      <a-button type="dashed" style="margin-left: 30px" @click="isUploadLink = true" v-show="isUploadLink === false">上传链接</a-button>
      <a-button type="primary" style="margin-left: 30px" @click="createArticle">发布</a-button>
    </div>
  </a-card>
</template>

<script setup lang="ts">
import '../../../../node_modules/animate.css/animate.css'
import {UploadOutlined} from '@ant-design/icons-vue';
import {onMounted, ref} from "vue";
import {listTags} from "../../../apis/tagApis";
import {message} from "ant-design-vue";
import {createArticleRequest, uploadImageInArticle} from "../../../apis/articleApi";
import {getImgUrl} from "../../../utils/imgUtil.ts";

interface Option {
  value: string;
}
const imgUrl = ref(getImgUrl())
const isUploadLink = ref(false)
const httpBody = ref('')
const httpChoose = ref('https://')
const httpBackFix = ref('.com')
const imgLink = ref('')
const tagList = ref([])
const options = ref([]);
const tagValue = ref('')
const fileList = ref([]);
const summary = ref('')
const content = ref('')


const concatHttp = () => {
  return httpChoose.value + httpBody.value + httpBackFix.value;
}



const textContent: PPostContentDTO = {
  content: '',
  type: 2,
  sort: 1
}

const imgContent: PPostContentDTO = {
  content: '',
  type: 3,
  sort: 1
}

const httpContent: PPostContentDTO = {
  content: '',
  type: 6,
  sort: 1
}


const userId = ref(1594320204085702657);

onMounted(async () => {
  try{
    const result = await listTags();
    let optionList: any = result.data.data.records;
    options.value = optionList.map(dealWithMapToArray)
    console.log(JSON.stringify(options.value))
  }catch (e){
    message.error("获取标签失败")
  }
})

const addTag = () => {
  let isSame = true;
  tagList.value.forEach(value => {
    if (value == tagValue.value) {
      isSame = isSame && false
    }
  })
  if (isSame) {
    tagList.value.push(tagValue.value)
  }
  console.log(tagList.value)
}


const uploadImg = async (file: any) => {
  const result = await uploadImageInArticle(file.file, (ev) => {
    // ev - axios 上传进度实例，上传过程触发多次
    // ev.loaded 当前已上传内容的大小，ev.total - 本次上传请求内容总大小
    console.log(ev);
    const percent = (ev.loaded / ev.total) * 100;
    // 计算出上传进度，调用组件进度条方法
    file.onProgress({percent});
  })
  console.log(result)
  if (result.data.code === 0) {
    message.info('上传成功')
    imgLink.value = result.data.data
    file.onSuccess(result.data, file);
  } else {
    file.onError('上传失败')
  }
}

const createArticle = async () => {
  const post = fillPostData();
  const result = await createArticleRequest(post);
  if(result.data.code === 0){
    message.info("发布成功")
  }else {
    message.error("发布失败")
  }
}


const fillPostData = () =>{
  let postContents : PPostContentDTO[] = [];
  if(content.value !== ''){
    textContent.content = content.value
    postContents.push(textContent)
  }
  if(imgLink.value!==''){
    imgContent.content = imgLink.value
    postContents.push(imgContent)
  }
  if(httpBody.value !== ''){
    httpContent.content = concatHttp()
    postContents.push(httpContent)
  }
  let post: PPostCreateVo = {
    userId: userId.value,
    nickname: '之后改',
    summary: summary.value,
    contents: postContents,
    tags: tagList.value
  };
  return post
}

const filterOption = (input: string, option: Option) => {
  return option.value;
};
const dealWithMapToArray = (oldvalue: any) => {
  return {value: oldvalue.tag}
}

</script>

<style scoped>

</style>