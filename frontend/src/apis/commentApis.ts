import {myAxios} from "../utils/MyAnxios";
import axios from "axios";

const COMMENT_PREFIX = "/api/pComment"
const ARTICLE_UPLOAD_PREFIX = "/file/upload"



export const uploadImageInComment = async (img: any, onProgress: any) => {
    return await axios.post(ARTICLE_UPLOAD_PREFIX, {
        'uploadFile': img
    }, {
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        baseURL: 'http://127.0.0.1:8088',
        // @ts-ignore
    }, onProgress)
}

export const publishCommentRequest = async (comment:any) => {
    return await myAxios.post(COMMENT_PREFIX + "/postComment", comment);
}




