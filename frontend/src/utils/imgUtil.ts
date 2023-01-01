const urls = ["https://nika-picbed.oss-cn-hangzhou.aliyuncs.com/pics-pixel/1.png",
    "https://nika-picbed.oss-cn-hangzhou.aliyuncs.com/pics-pixel/Snipaste_2022-12-30_23-42-31.png",
    "https://nika-picbed.oss-cn-hangzhou.aliyuncs.com/pics-pixel/Snipaste_2022-12-30_23-42-39.png",
    "https://nika-picbed.oss-cn-hangzhou.aliyuncs.com/pics-pixel/Snipaste_2022-12-30_23-42-48.png",
    "https://nika-picbed.oss-cn-hangzhou.aliyuncs.com/pics-pixel/Snipaste_2022-12-30_23-42-54.png",
    "https://nika-picbed.oss-cn-hangzhou.aliyuncs.com/pics-pixel/Snipaste_2022-12-30_23-42-59.png",
    "https://nika-picbed.oss-cn-hangzhou.aliyuncs.com/pics-pixel/Snipaste_2022-12-30_23-43-04.png",
    "https://nika-picbed.oss-cn-hangzhou.aliyuncs.com/pics-pixel/Snipaste_2022-12-30_23-43-09.png",
    "https://nika-picbed.oss-cn-hangzhou.aliyuncs.com/pics-pixel/Snipaste_2022-12-30_23-43-15.png]"];

export const getImgUrl = () =>{
    return urls[Math.floor(Math.random()*urls.length)-1];
}






