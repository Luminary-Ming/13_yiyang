<template>
    <div ref="target" class="data"></div>
</template>
<script>
import * as echarts from "echarts";
import "echarts-wordcloud";
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
                { name: "哈尔滨", value: Math.round(Math.random() * 100) },
                { name: "沈阳", value: Math.round(Math.random() * 100) },
                { name: "长春", value: Math.round(Math.random() * 100) },
                { name: "北京", value: Math.round(Math.random() * 100) },
                { name: "天津", value: Math.round(Math.random() * 100) },
                { name: "石家庄", value: Math.round(Math.random() * 100) },
                { name: "太原", value: Math.round(Math.random() * 100) },
                { name: "郑州", value: Math.round(Math.random() * 100) },
                { name: "济南", value: Math.round(Math.random() * 100) },
                { name: "合肥", value: Math.round(Math.random() * 100) },
                { name: "上海", value: Math.round(Math.random() * 100) },
                { name: "杭州", value: Math.round(Math.random() * 100) },
                { name: "南京", value: Math.round(Math.random() * 100) },
                { name: "南昌", value: Math.round(Math.random() * 100) },
                { name: "福州", value: Math.round(Math.random() * 100) },
                { name: "广州", value: Math.round(Math.random() * 100) },
                { name: "南宁", value: Math.round(Math.random() * 100) },
                { name: "拉萨", value: Math.round(Math.random() * 100) },
                { name: "昆明", value: Math.round(Math.random() * 100) },
                { name: "乌鲁木齐", value: Math.round(Math.random() * 100) },
                { name: "呼和浩特", value: Math.round(Math.random() * 100) },
                { name: "兰州", value: Math.round(Math.random() * 100) },
                { name: "银川", value: Math.round(Math.random() * 100) },
                { name: "西宁", value: Math.round(Math.random() * 100) },
                { name: "成都", value: Math.round(Math.random() * 100) },
                { name: "贵阳", value: Math.round(Math.random() * 100) },
                { name: "重庆", value: Math.round(Math.random() * 100) },
                { name: "武汉", value: Math.round(Math.random() * 100) },
                { name: "长沙", value: Math.round(Math.random() * 100) },
                { name: "西安", value: Math.round(Math.random() * 100) },
                { name: "海口", value: Math.round(Math.random() * 100) },
                { name: "台北", value: Math.round(Math.random() * 100) },
                { name: "钓鱼岛", value: Math.round(Math.random() * 100) },
                { name: "香港", value: Math.round(Math.random() * 100) },
                { name: "澳门", value: Math.round(Math.random() * 100) },
            ];
            // 渲染 echarts
            this.renderChart();
        },
        renderChart() {
            const options = {
                // X 轴不需要展示
                xAxis: {
                    show: false,
                    type: "value",
                },
                // X 轴不需要展示
                yAxis: {
                    show: false,
                    type: "value",
                },
                // 核心数据配置
                series: [
                    {
                        type: "wordCloud",
                        shape: "circle",
                        keepAspect: false,
                        left: "center",
                        top: "center",
                        width: "70%",
                        height: "80%",
                        right: null,
                        bottom: null,
                        sizeRange: [12, 60],
                        rotationRange: [-90, 90],
                        rotationStep: 45,
                        gridSize: 8,
                        drawOutOfBound: false,
                        shrinkToFit: false,
                        layoutAnimation: true,
                        textStyle: {
                            fontFamily: "sans-serif",
                            fontWeight: "bold",
                            color: function () {
                                return "rgb(" + [Math.round(Math.random() * 160), Math.round(Math.random() * 160), Math.round(Math.random() * 160)].join(",") + ")";
                            },
                        },
                        emphasis: {
                            focus: "self",
                            textStyle: {
                                textShadowBlur: 10,
                                textShadowColor: "#333",
                            },
                        },
                        data: this.datas,
                    },
                ],
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
    height: 100%;
}
</style>
