import {myAxios} from "../utils/MyAnxios";
import {TeamMemberUpdateRequest} from "../models/requests/TeamMemberRequests";

const AUTH_PREFIX = "/api/auth"


export const getCodeUsingPhone = async (phone: string) => {
    return await myAxios.post(AUTH_PREFIX + "/sendCode", null, {
        params: {
            phone: phone
        }
    });
}
export const loginByPhone = async (phone: string, code: string) => {
    return await myAxios.post(AUTH_PREFIX + "/login", {
        phone: phone,
        code: code
    });
}

