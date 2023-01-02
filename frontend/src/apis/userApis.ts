import {myAxios} from "../utils/MyAnxios";

const USER_PREFIX = "/api/pUser"



export const getCurrentUserRequest = async () => {
    return await myAxios.get(USER_PREFIX + "/me")
}

export const updateUserInfoRequest = async (userInfo:any) => {
    return await myAxios.put(USER_PREFIX + "/update",userInfo)
}


