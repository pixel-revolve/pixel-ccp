public type articleVos = {
    //主题ID
    id: number
    //用户ID
    userId: number
    //用户昵称
    nickname: string
    //评论数
    commentCount: number
    //收藏数
    collectionCount: number
    //点赞数
    upvoteCount: number
    //查看数
    watchCount: number
    //摘要
    summary: string
    //最新回复时间
    latestRepliedOn: number
    //标签
    tags: string
    //附件价格(分)
    attachmentPrice: number
    //IP地址
    ip: string
    //IP城市地址
    ipLoc: string
    //创建时间
    createdOn: string
}


public type PPostCreateVo = {
    //用户ID
    userId:number
    //用户昵称
    nickname:string
    //摘要
    summary:string
    // 文章内容
    contents:PPostContentDTO[]
    //标签
    tags:string[]
    //附件价格(分)
    // attachmentPrice:number
}



public type PPostContentDTO = {
    // 文章内容
    content:string
    // 内容类型 类型，1标题，2文字段落，3图片地址，4视频地址，5语音地址，6链接地址，7附件资源，8收费资源
    type:number
    // 内容排序
    sort:number
}

