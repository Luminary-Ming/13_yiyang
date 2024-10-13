<template>
    <div class="data" ref="target"></div>
</template>

<script>
import * as echarts from "echarts";
export default {
    data() {
        return {
            mChart: null,
            mapData: 70,
        };
    },
    methods: {
        randomData() {
            this.mapData = (Math.random() * 100).toFixed(2);
            // 渲染 echarts
            this.renderChart();
        },
        renderChart() {
            const options = {
                series: [
                    {
                        type: "gauge",
                        axisLine: {
                            lineStyle: {
                                width: 30,
                                color: [
                                    [0.3, "#67e0e3"],
                                    [0.7, "#37a2da"],
                                    [1, "#fd666d"],
                                ],
                            },
                        },
                        pointer: {
                            itemStyle: {
                                color: "auto",
                            },
                        },
                        axisTick: {
                            distance: -20,
                            length: 8,
                            lineStyle: {
                                color: "#fff",
                                width: 2,
                            },
                        },
                        splitLine: {
                            distance: -30,
                            length: 30,
                            lineStyle: {
                                color: "#fff",
                                width: 2,
                            },
                        },
                        axisLabel: {
                            color: "inherit",
                            distance: 40,
                            fontSize: 14,
                        },
                        detail: {
                            valueAnimation: true,
                            formatter: "{value} km/h",
                            color: "inherit",
                            fontSize: 14,
                        },
                        data: [
                            {
                                value: this.mapData,
                            },
                        ],
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

<style lang="less" scoped>
.data {
    width: 100%;
    height: 100%;
}
</style>
