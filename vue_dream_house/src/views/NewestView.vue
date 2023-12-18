<template>
    <div class="Newest">
      <UserList
        v-for="(item, index) in Obj"
        :key="index"
        :Obj="item"
      />
    </div>
  </template>
  
  <script>
  // @ 是 /src 的别名
  import UserList from '@/components/UserList.vue'
  import axios from 'axios'
  
  export default {
    name: 'NewestView',
    components: {
      UserList
    },
  
    data() {
      return {
        Obj: []
      }
    },
  
    // computed会缓存结果，methods每次都会重新计算
    methods: {
      getList() {
        let list = [];
        let newObjects = {};
  
        axios.get('http://localhost:8087/findByTime')
          .then(res => {
            list = res.data;
  
            for (let i = 0; i < list.length; i++) {
              newObjects[i] = list[i];
            }
            console.log(newObjects);
            this.Obj = newObjects;
          })
          .catch(error => {
            this.$message({
              message: '获取页面内容失败！',
              type: 'error',
              duration: 2000
            });
            console.log("获取排行出错！")
            console.error(error);
          });
  
      }
  
    },
  
    created() {
      this.getList();
    }
  
  }
  </script>
  