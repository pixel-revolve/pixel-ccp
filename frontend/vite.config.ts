import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  css:{
    preprocessorOptions:{
      less:{
        modifyVars: {
          'primary-color': '#1DA57A',
          'link-color': '#1DA57A',
          'border-radius-base': '6px',
        },
        javascriptEnabled:true
      }
    }
  }
})
