<template>
  <div class="InputText">
    <input
      type="text"
      maxlength="15"
      v-model="inputValue"
      placeholder="发送你的梦想！"
    />
    <img
      src="../assets/send.png"
      class="submit-btn"
    />
  </div>
</template>

<style scoped lang="scss">
.InputText {
  position: fixed;
  bottom: 10px;
  left: 0;
  right: 0;
  display: flex;
  justify-content: space-between;
  padding: 10px;
  background-color: transparent;
  width: 300px;
  margin: 0 auto;
  //设置 z-index 值为 999,显示为最顶部
  z-index: 999;
}

input[type="text"] {
  flex: 1;
  height: 40px;
  padding: 0 10px;
  background-color: rgba(255, 255, 255, 0.7);
  border: 1px solid transparent;
  border-radius: 20px;
  box-shadow: 0 0 10px #42b983;
  transition: all 0.3s ease-in-out;
}

input[type="text"]:hover {
  transform: scale(1.05);
  box-shadow: 0 0 15px rgba(24, 144, 255, 0.8);
}

.submit-btn {
  height: 40px;
  padding: 2px 0px 0px 10px;
  background-color: transparent;
  border: none;
  cursor: pointer;
  transform: scale(1);
  transition: all 0.3s ease-in-out;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.submit-btn:hover {
  transform: scale(1.2);
}
</style>

<script>
import axios from 'axios'

export default {
  name: 'InputText',

  // 定义数据
  data() {
    return {
      isAnimating: false, // 用于标识动画是否正在播放
      inputValue: ''
    };
  },
  methods: {
    // 定义提交方法
    submit() {

      // 如果动画正在播放，直接返回
      if (this.isAnimating)
        return;

      //向服务器发送发送post请求
      axios.post(`http://localhost:8087/Add/${this.inputValue}`)
        .then(res => {

          console.log(res.data);
          this.inputValue = '';

          this.$message({
            message: '发送成功！',
            type: 'success',
            duration: 2000 // 自动关闭延迟时间，单位毫秒
          });
        })
        .catch(error => {
          this.$message({
            message: '发送失败！',
            type: 'error',
            duration: 2000 // 自动关闭延迟时间，单位毫秒
          });

          console.log("发送信息出错！")
          console.error(error);
        });

      this.isAnimating = true; // 标记动画正在播放
      // 获取提交按钮元素
      const btn = this.$el.querySelector(".submit-btn");
      // 记录提交按钮的原始 transform 值
      const originalTransform = btn.style.transform;
      // 设置提交按钮的 transform 值，使其向右上方移动
      btn.style.transform = "translate(100%, -100%)";

      // 设置动画播放完成后的回调函数
      setTimeout(() => {
        // 将提交按钮的 transform 值设置为原始值
        btn.style.transform = originalTransform;
        // 标记动画已经播放完毕
        this.isAnimating = false;
      }, 500);

    }
  },
  mounted() {
    // 在组件挂载完成后，给提交按钮元素绑定 click 事件
    this.$el.querySelector(".submit-btn").addEventListener("click", this.submit);
  }
};
</script>
