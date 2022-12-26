import { defineStore } from 'pinia'
import {myAxios} from "../utils/MyAnxios";

const useUserStore = defineStore('userStore', {
    persist:true,
    state: () =>
        (
            {
                username: '',
                authorization: '',
                isShowModal:false,
                userId:-1
            }
        ),
    actions:{
        setUserId(userId:number){
            this.userId = userId
        },
        getUserId(){
            return this.userId;
        },
        setIsShowModal(isShowModal:boolean){
            this.isShowModal = isShowModal
        },
        getIsShowModal(){
            return this.isShowModal;
        },
        getAuthorization(){
          return this.authorization;
        },
        setAuthorization(authorization:string){
            this.authorization=authorization
        },
        clearToken(){
            myAxios.defaults.headers.token = ''
            this.authorization = ''
        }
    }
})

export {
    useUserStore
}










