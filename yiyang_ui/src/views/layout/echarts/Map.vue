<template>
  <div class="china_data">
    <div class="data-time lcd" ref="dataTime"></div>
    <div ref="myChart" id="myChart"></div>
  </div>
</template>

<script>
import china from "@/assets/china.json";
import * as echarts from "echarts";
import dayjs from "dayjs";
export default {
  data() {
    return {
      mapData: {},
    };
  },
  mounted() {
    this.drawChart();
    setInterval(() => {
      this.getTime();
    }, 1000);
  },
  methods: {
    // 系统时间
    getTime() {
      this.$refs.dataTime.innerHTML = dayjs().format('YYYY年MM月DD日 HH:mm:ss')
    },
    drawChart() {
      let myChart = echarts.init(this.$refs.myChart);
      // 飞机图形
      var planePath =
        "path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z";
      // 散点图数据
      var data = [
        { name: "北京", value: [116.24, 39.55, 100] },
        { name: "深圳", value: [114.271522, 22.753644] },
        { name: "重庆", value: [106.54, 29.59] },
        { name: "浙江", value: [120.19, 30.26] },
        { name: "上海", value: [121.4648, 31.2891] },
        { name: "佛山", value: [112.877162, 23.160295] },
        { name: "西安", value: [108.948024, 34.263161] },
        { name: "大同", value: [112.549248, 37.857014] },
        { name: "石家庄", value: [114.502461, 38.045474] },
        { name: "哈尔滨", value: [126.642464, 45.756967] },
        { name: "乌鲁木齐", value: [87.617733, 43.792818] },
        { name: "青海", value: [96.043533, 35.726403] },
        { name: "兰州", value: [103.823557, 36.058039] },
        { name: "海口", value: [109.754859, 19.189767] },
        { name: "云南", value: [101.485106, 25.008643] },
        { name: "呼和浩特", value: [111.670801, 40.818311] },
        { name: "成都", value: [102.693453, 30.674545] },
        { name: "拉萨", value: [88.388277, 31.56375] },
      ];
      //飞线数据
      let LineData = [
        {
          fromName: "北京",
          toName: "深圳",
          coords: [
            [116.24, 39.55],
            [114.271522, 22.753644],
          ], //主要是这个数据
          value: 100,
        },
        {
          fromName: "北京",
          toName: "西安",
          coords: [
            [116.24, 39.55],
            [108.948024, 34.263161],
          ],
          value: 100,
        },
        {
          fromName: "哈尔滨",
          toName: "西安",
          coords: [
            [126.642464, 45.756967],
            [108.948024, 34.263161],
          ],
          value: 100,
        },
        {
          fromName: "北京",
          toName: "重庆",
          coords: [
            [116.24, 39.55],
            [106.54, 29.59],
          ],
          value: 100,
        },
        {
          fromName: "上海",
          toName: "深圳",
          coords: [
            [121.4648, 31.2891],
            [114.271522, 22.753644],
          ],
          value: 100,
        },
        {
          fromName: "北京",
          toName: "佛山",
          coords: [
            [116.24, 39.55],
            [112.877162, 23.160295],
          ],
          value: 100,
        },
      ];

      echarts.registerMap("china", china);
      let option = {
        //地图
        geo: {
          type: "map",
          map: "china",
          zoom: 1.0,
          top: "18%",
          roam: "scale",
          label: {
            normal: {
              show: true, //省份名展示
              fontSize: "10", //省份字体大小
              color: "white", //省份字体颜色
            },
            emphasis: {
              show: true,
              textStyle: {
                color: "white", //鼠标移入省份字体颜色变化
                fontSize: "14px", //鼠标移入省份字体大小变化
              },
            },
          },
          itemStyle: {
            normal: {
              borderColor: "#31FFFF", //地图边框颜色
              areaColor: "#122c69", //地图背景颜色
              borderWidth: 1, //设置外层边框
              shadowColor: "#276fce",
              shadowOffsetX: 0,
              shadowOffsetY: 1,
              opacity: 1,
            },
            emphasis: {
              show: true,
              areaColor: "#007acc", //鼠标放到地图上显示的颜色
              shadowOffsetX: 0,
              shadowOffsetY: 3,
              shadowBlur: 0,
              borderWidth: 0,
              opacity: 1,
              shadowColor: "#fff",
            },
          },
        },
        series: [
          // 区域散点图
          {
            type: "effectScatter",
            coordinateSystem: "geo",
            zlevel: 2,
            symbolSize: 10,
            rippleEffect: {
              //坐标点动画
              period: 3,
              scale: 2,
              brushType: "fill",
            },
            data: data,
            itemStyle: {
              //坐标点颜色
              normal: {
                show: true,
                color: "#31FFFF",
                shadowBlur: 20,
                shadowColor: "#fff",
              },
              emphasis: {
                areaColor: "#f00",
              },
            },
          },
          //飞线图
          {
            type: "lines",
            zlevel: 2,
            effect: {
              show: true,
              //飞机的速度  这里是s单位
              period: 5,
              trailLength: 0,
              symbol: planePath, //这是圆点替换了飞机
              // 飞机大小
              symbolSize: 16,
              color: "#9be4fd",
            },
            lineStyle: {
              normal: {
                color: "#fff",
                type: "dotted", // 虚线
                // 线条宽度
                width: 2,
                opacity: 1,
                curveness: 0.2, // 弯曲度
              },
              emphasis: {
                color: "#FF0000", //飞线悬浮颜色
              },
            },
            label: {
              normal: {
                show: false,
                position: "middle",
                formatter: "{b}",
              },
            },
            data: LineData,
          },
        ],
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
  },
};
</script>

<style scoped>
.china_data {
  width: 100%;
  height: 100%;
  position: relative;
  .data-time {
    position: absolute;
    top: 10px;
    width: 100%;
    
    text-align: center;
    background-image: linear-gradient(to bottom, #ebf0f3, #90c4e8);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    font-size: 40px;
  }
}

.china_data #myChart {
  width: 100%;
  height: 100%;
}
</style>
