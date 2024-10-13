<template>
    <div class="box">
        <!-- 左边 
    <div class="left">
      <div class="top">
        <HorizontalBar></HorizontalBar>
      </div>
      <div class="middle">
        <RadarBar></RadarBar>
      </div>
      <div class="bottom">
        <VerticalBar></VerticalBar>
      </div>
    </div>
    -->
        <!-- 中间 -->
        <div class="center">
            <div class="center-top">
                <TotalData></TotalData>
            </div>
            <div class="center-map">
                <ChinaMap></ChinaMap>
            </div>
        </div>
        <!-- 右边 
    <div class="right">
      <div class="top">
        <LineBar></LineBar>
      </div>
      <div class="middle">
        <GaugeBar></GaugeBar>
      </div>
      <div class="bottom">
        <WordCloud></WordCloud>
      </div>
    </div>
    --></div>
</template>

<script>
import HorizontalBar from "@/views/layout/echarts/HorizontalBar.vue";
import RadarBar from "@/views/layout/echarts/RadarBar.vue";
import VerticalBar from "@/views/layout/echarts/VerticalBar.vue";
import ChinaMap from "@/views/layout/echarts/Map.vue";
import WordCloud from "@/views/layout/echarts/WordCloud.vue";
import LineBar from "@/views/layout/echarts/LineBar.vue";
import GaugeBar from "@/views/layout/echarts/GaugeBar.vue";
import TotalData from "@/views/layout/echarts/TotalData.vue";
export default {
    components: {
        HorizontalBar,
        RadarBar,
        VerticalBar,
        ChinaMap,
        WordCloud,
        LineBar,
        GaugeBar,
        TotalData,
    },
};
</script>

<style lang="less" scoped>
.box {
    background-color: #151939;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: space-around;
    align-items: center;
    .left {
        flex: 2;
        height: 100%;
        background-color: rgba(228, 225, 225, 0.1);
        display: flex;
        flex-direction: column;
    }
    .center {
        flex: 4;
        height: 100%;
        background-color: rgba(161, 153, 153, 0.1);
        display: flex;
        flex-direction: column;
        .center-top {
            height: 300px;
            border-bottom: 1px dashed #55566b;
        }
        .center-map {
            flex: 1;
        }
    }
    .right {
        flex: 2;
        height: 100%;
        background-color: rgba(228, 225, 225, 0.1);
        display: flex;
        flex-direction: column;
    }
    .top {
        width: 100%;
        flex: 1;
        padding: 5px;
        background-color: rgba(228, 225, 225, 0.1);
    }
    .middle {
        width: 100%;
        flex: 1;
        padding: 5px;
    }
    .bottom {
        width: 100%;
        flex: 1;
        padding: 5px;
        background-color: rgba(228, 225, 225, 0.1);
    }
}
</style>
