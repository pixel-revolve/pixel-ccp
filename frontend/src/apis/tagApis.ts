

import {myAxios} from "../utils/MyAnxios";

const ARTICLE_PREFIX = "/api/pTag"
export const listTags = async () => {
    return await myAxios.get(ARTICLE_PREFIX + "/selectAll")
}