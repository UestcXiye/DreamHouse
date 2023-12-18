<template>
  <div id="app">
    <h1 class="MyName">Dream House</h1>
    <nav>
      <router-link to="/">排行</router-link> |
      <router-link to="/Newest">最新</router-link> |
      <router-link to="/Random">随机</router-link>
    </nav>
    <router-view />
    <InputText />
    <div class="newText">该列表仅显示50条内容！</div>
  </div>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 20px;
  font-size: 30px;
  font-family: pyt;
  text-shadow: 2px 2px 2px rgba(0, 0, 0, 0.2);

  a {
    text-decoration: none;
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      -webkit-tap-highlight-color: transparent; //清除蓝框
      text-decoration: none;
      color: #42b983;
    }
  }
}

.MyName {
  font-family: pyt;
  font-size: 60px;
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-image: linear-gradient(to right, #41ffa9, rgba(79, 168, 252, 0.6));
  margin: 20px 10px 0px 10px;
  text-shadow: 3px 3px 3px rgba(0, 0, 0, 0.2); //文本阴影
}
body {
  background: rgb(244, 189, 255);
  background: linear-gradient(
    90deg,
    rgb(183, 180, 255) 0%,
    rgb(255, 255, 255) 50%,
    rgb(165, 240, 255) 100%
  );
}

// 设置全局滚动条
body::-webkit-scrollbar {
  width: 5px;
  background-color: #f5f5f5;
}

body::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #c4ffe4;
}

.newText {
  height: 80px;
  margin: 10px auto;
  font-size: 13px;
}
</style>

<script>
import InputText from '@/components/InputText.vue'

export default {
  name: 'App',
  components: {
    InputText
  },
}
</script>