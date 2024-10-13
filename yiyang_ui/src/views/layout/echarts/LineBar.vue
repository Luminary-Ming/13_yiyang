<template>
  <div ref="target" class="data"></div>
</template>
<script>
import * as echarts from "echarts";
export default {
  data() {
    return {
      mChart: null,
      datas: [],
    };
  },
  methods: {
    randomData() {
      this.datas = [
        { name: "北京", value: Math.round(Math.random() * 100) },
        { name: "上海", value: Math.round(Math.random() * 100) },
        { name: "天津", value: Math.round(Math.random() * 100) },
        { name: "深圳", value: Math.round(Math.random() * 100) },
        { name: "广州", value: Math.round(Math.random() * 100) },
        { name: "石家庄", value: Math.round(Math.random() * 100) },
        { name: "福州", value: Math.round(Math.random() * 100) },
      ];
      // 渲染 echarts
      this.renderChart();
    },
    renderChart() {
      const options = {
        // X 轴展示选项
        xAxis: {
          type: "category",
          // 根据数据筛选
          data: this.datas.map((item) => item.name),
          // 文字色值
          axisLabel: {
            color: "#9EB1C8",
          },
        },
        // Y 轴展示数据
        yAxis: {
          // 数据展示
          type: "value",
          // 不显示轴
          show: false,
          // 最大值（防止触顶）
          max: function (value) {
            // 取整
            return parseInt(value.max * 1.2);
          },
        },
        // echarts 网格绘制的位置，对应 上、右、下、左
        grid: {
          top: 16,
          right: 0,
          bottom: 26,
          left: -26,
          // 计算边距时，包含标签
          containLabel: true,
        },
        series: {
          type: "line",
          // 数据筛选
          data: this.datas.map((item) => ({
            name: item.name,
            value: item.value,
          })),
          // 每个轴的样式
          itemStyle: {
            color: "#479AD3", // 设置柱子的颜色
            barBorderRadius: 5, // 设置柱子的圆角
            shadowColor: "rgba(0, 0, 0, 0.3)", // 设置柱子的阴影颜色
            shadowBlur: 5, // 设置柱子的阴影模糊大小
          },
          // 文本
          label: {
            show: true,
            // 设置标签位置为顶部
            position: "top",
            textStyle: {
              // 设置标签文本颜色
              color: "#fff",
            },
            // 设置数字为百分比
            formatter: "{c}%",
          },
        },
      };
      this.mChart.setOption(options);
    },
  },
  mounted() {
    this.mChart = echarts.init(this.$refs.target);
    this.randomData();
    setInterval(() => {
      this.randomData();
    }, 1500);
  },
};
</script>

<style scoped>
.data {
  width: 100%;
  height: 100%;;
}
</style>
