<template>
  <div class="UserList">
    <div class="box">
      <div class="box1">
        <div class="str">{{str}}</div>
      </div>
      <div class="box2">
        <div class="province">{{province}}</div>
        <div class="likes">{{likes}}</div>
        <img
          :src="imagePath"
          @click="toggleImage"
          class="img-btn"
        />
      </div>
    </div>
  </div>
</template>

<!-- 添加“scoped作用域”属性以将 CSS 限制为此组件 -->
<style scoped lang="scss">
.UserList {
  display: flex;
  justify-content: center;
  align-items: center;
}

.box {
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 2px 2px 4px #ddd;
  padding: 10px;
  display: flex;
  flex-direction: column; //将 flex 子元素沿竖直方向排列
  align-items: center; //将 flex 子元素在纵轴上居中对齐
  margin: 5px;
  width: 500px;
  transition: all 0.3s ease; //面板放大
}

.box:hover {
  transform: scale(1.04); //面板放大
}

.box1 {
  width: 100%;
  float: left;
  margin: 5px;
}

.str {
  flex: 1;
  text-align: center;
  font-size: 20px;
  //font-family:wpyt;
  width: 100%;
  height: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.2);
}

.box2 {
  width: 100%;
  display: flex;
}

.province {
  text-align: center;
  font-size: 14px;
  width: 120px;
  height: 20px;
  display: flex;
}

.likes {
  text-align: center;
  font-size: 14px;
  width: 100px;
  height: 20px;
  margin: auto 5px auto auto;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.img-btn {
  width: 16px;
  height: 16px;
  transition: all 0.3s ease; // 设置按钮元素的过渡效果
}
</style>


<script>
import axios from 'axios'

export default {
  name: 'UserList',
  props: {
    Obj: Object
  },
  data() {
    return {
      imagePath: require("@/assets/点赞-no.png"),
      str: this.Obj.str,
      province: this.Obj.province,
      likes: this.Obj.likes
    };
  },
  created() {
    if (localStorage.getItem(`isLiked${this.Obj.id}`)) {
      this.imagePath = require("@/assets/点赞-yes.png");
    }
  },
  methods: {
    toggleImage() {
      // 判断是否点赞过
      if (this.imagePath === require("@/assets/点赞-no.png")) {

        // 获取按钮元素
        const imgBtn = this.$el.querySelector('.img-btn');
        // 给按钮元素应用缩放和旋转的样式
        imgBtn.style.transform = 'scale(1.2) rotate(-10deg)';
        // 设置计时器，在 300 毫秒之后给按钮元素应用新的样式
        setTimeout(() => {
          imgBtn.style.transform = 'scale(1) rotate(10deg)';
          setTimeout(() => {
            imgBtn.style.transform = 'scale(1) rotate(0deg)';
          }, 300);
        }, 300);

        this.likes++;

        // 将图片替换为已点赞的图片
        this.imagePath = require("@/assets/点赞-yes.png");
        // 将已点赞的状态存入 localStorage
        localStorage.setItem(`isLiked${this.Obj.id}`, true);

        //向服务器发送发送put请求
        axios.put(`http://localhost:8087/increaseLikesById/${this.Obj.id}`)
          .then(res => {

            console.log(res.data);

          })
          .catch(error => {
            console.log("点赞出错！")
            console.error(error);
          });

      } else {

        this.likes--;

        // 否则将图片替换为未点赞的图片
        this.imagePath = require("@/assets/点赞-no.png");
        // 并且删除已点赞的状态
        localStorage.removeItem(`isLiked${this.Obj.id}`);

        // 向服务器发送发送put请求
        axios.put(`http://localhost:8087/decreaseLikesById/${this.Obj.id}`)
          .then(res => {
            console.log(res.data);
          })
          .catch(error => {
            console.log("取消点赞出错！")
            console.error(error);
          });
      }
    }
  }

}
</script>
