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
                userId:-1,
                nickName:'',
                avatar:'',
                phone:'',
                email:''
            }
        ),
    actions:{
        setUserId(userId:number){
            this.userId = userId
        },
        getUserId(){
            return this.userId;
        },
        setPhone(phone:string){
            this.phone = phone
        },
        getPhone(){
            return this.phone;
        },
        setEmail(email:string){
            this.email = email
        },
        getEmail(){
            return this.email;
        },
        setAvatar(avatar:string){
            this.avatar = avatar
        },
        getAvatar(){
            return this.avatar;
        },
        getUsername(){
            return this.username;
        },
        setUsername(username:string){
            this.username = username
        },
        getNickname(){
          return this.nickName;
        },
        setNickname(nickname:string){
          this.nickName = nickname
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










