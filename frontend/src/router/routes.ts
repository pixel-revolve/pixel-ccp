const routes = [
    {
        path: "/",
        redirect: '/index/playground'
    },
    {
        path: "/index",
        component: import("../layouts/index/IndexPage.vue"),
        children: [
            {
                path: 'playground',
                component: import("../layouts/playground/PlaygroundPage.vue"),
            },
            {
                path: 'article/detail',
                component: import("../layouts/articleDetail/ArticleDetailPage.vue"),
            },
            {
                path: 'topic',
                component: import("../layouts/topic/TopicPage.vue")
            },
            {
                path: 'dynamic',
                component: import("../layouts/dynamic/DynamicPage.vue")
            }
        ]
    },
    {
        path: "/test",
        component: import("../layouts/TestPage.vue")
    }
]

export default routes;