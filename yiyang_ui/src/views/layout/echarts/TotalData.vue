<template>
    <div class="box">
        <div class="total-data">康养中心：<span ref="totalCountTarget" class="lcd"></span> 人</div>
        <div class="data-list">
            <div class="data-item">
                华北：
                <span ref="city1" class="data-item-num lcd"></span>
            </div>
            <div class="data-item">
                东北：
                <span ref="city2" class="data-item-num lcd"></span>
            </div>
            <div class="data-item">华东：<span ref="city3" class="data-item-num lcd"></span></div>
            <div class="data-item">中南：<span ref="city4" class="data-item-num lcd"></span></div>
            <div class="data-item">西南：<span ref="city5" class="data-item-num lcd"></span></div>
            <div class="data-item">西北：<span ref="city6" class="data-item-num lcd"></span></div>
        </div>
    </div>
</template>
<script>
export default {
    data() {},
    methods: {
        loadData() {
            this.$refs.totalCountTarget.innerHTML = Math.floor(Math.round(Math.random() * 999999) + 999999)
                .toString()
                .replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            this.$refs.city6.innerHTML = Math.floor(Math.round(Math.random() * 5555) + 5555)
                .toString()
                .replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            this.$refs.city5.innerHTML = Math.floor(Math.round(Math.random() * 5555) + 5555)
                .toString()
                .replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            this.$refs.city4.innerHTML = Math.floor(Math.round(Math.random() * 5555) + 5555)
                .toString()
                .replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            this.$refs.city3.innerHTML = Math.floor(Math.round(Math.random() * 5555) + 5555)
                .toString()
                .replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            this.$refs.city2.innerHTML = Math.floor(Math.round(Math.random() * 5555) + 5555)
                .toString()
                .replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            this.$refs.city1.innerHTML = Math.floor(Math.round(Math.random() * 5555) + 5555)
                .toString()
                .replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        },
    },
    mounted() {
        setInterval(() => {
            this.loadData();
        }, 1500);
    },
};
</script>

<style lang="less" scoped>
.box {
    width: 100%;
    height: 100%;
    font-style: 12px;
    display: flex;
    flex-direction: column;
    .total-data {
        color: #cbd5e3;
        span {
            margin: 0 20px;
            font-size: 66px;
            font-weight: bold;
            background-image: linear-gradient(to bottom, #ebf0f3, #90c4e8);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }
    }
    .data-list {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-evenly;
        .data-item {
            width: 30%;
            color: #7e89a2;
        }
        .data-item-num {
            font-size: 40px;
            color: #90c4e8;
            font-weight: bold;
            background-image: linear-gradient(to bottom, #ebf0f3, #90c4e8);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }
    }
}
</style>
