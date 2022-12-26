# Vue 3 + TypeScript + Vite

This template should help get you started developing with Vue 3 and TypeScript in Vite. The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

## Recommended IDE Setup

- [VS Code](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar)

## Type Support For `.vue` Imports in TS

Since TypeScript cannot handle type information for `.vue` imports, they are shimmed to be a generic Vue component type by default. In most cases this is fine if you don't really care about component prop types outside of templates. However, if you wish to get actual prop types in `.vue` imports (for example to get props validation when using manual `h(...)` calls), you can enable Volar's Take Over mode by following these steps:

1. Run `Extensions: Show Built-in Extensions` from VS Code's command palette, look for `TypeScript and JavaScript Language Features`, then right click and select `Disable (Workspace)`. By default, Take Over mode will enable itself if the default TypeScript extension is disabled.
2. Reload the VS Code window by running `Developer: Reload Window` from the command palette.

You can learn more about Take Over mode [here](https://github.com/johnsoncodehk/volar/discussions/471).


## 项目结构说明

assets文件夹用来放置图标文件

components用来放置自定义组件和包装组件。

layouts文件用来放整个页面文件。

models用来定义你的模型。比如接收的模型，发送的模型，这是ts文件，用来规范代码。目前只定义了一个user类用来示范（类比Java）

router用定义路由，主要是掌管layouts里的整个路由。（写了示例）

store文件夹来定义全局变量。比如用户登陆时候用的token asdas

utils里放工具类。目前放置了anxios的请求工具类，以及请求拦截的定义。

## package.json的说明

![image-20221029221941409](C:\Users\ht\AppData\Roaming\Typora\typora-user-images\image-20221029221941409.png)

第一个用来预览，第二个用来打包。