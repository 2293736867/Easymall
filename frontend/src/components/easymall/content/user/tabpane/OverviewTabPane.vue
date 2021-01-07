<template>
    <el-row justify="space-between" type="flex">
        <el-col :span="10">
            <div style="text-align: center">
                浏览足迹
                <el-divider></el-divider>
                <el-card shadow="hover" v-loading="footprintLoading">
                    <div style="padding-left: 9rem">
                        <div id="footprint"></div>
                    </div>
                </el-card>
            </div>
        </el-col>
        <el-col :span="10">
            购物分类
            <el-divider></el-divider>
            <el-card shadow="hover" v-loading="categoryLoading">
                <div style="padding-left: 7rem">
                    <div id="category"></div>
                </div>
            </el-card>
        </el-col>
    </el-row>

    <el-row style="margin-top: 3rem" v-loading="mapLoading">
        <el-col>
            <div id="container" style="height: 600px"></div>
        </el-col>
    </el-row>
    <el-row justify="end" type="flex" style="margin-top: 3rem">
        <el-col :span="10">
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item label="经度">
                    <el-input v-model="lng"></el-input>
                </el-form-item>
                <el-form-item label="纬度">
                    <el-input v-model="lat"></el-input>
                </el-form-item>
                <el-button type="primary" @click="jump">
                    跳转
                </el-button>
                <el-button type="primary" @click="resetScale">缩小</el-button>
            </el-form>
        </el-col>
    </el-row>
</template>

<script>
import * as echarts from 'echarts'
import Utils from "../../../../../js/utils/Utils";
import AMapLoader from '@amap/amap-jsapi-loader';

export default {
    name: "OverviewTabPane",
    data() {
        return {
            lat: 23.146736,
            lng: 113.028458,
            map:'',
            mapLoading:true,
            footprintLoading:true,
            categoryLoading:true,
        }
    },
    mounted() {
        //                116.5/40
        AMapLoader.load({
            "key": "4f3d7b3911cdd2f0b56115362056f248",              // 申请好的Web端开发者Key，首次调用 load 时必填
            "version": "1.4.15",   // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
            "plugins": [],           // 需要使用的的插件列表，如比例尺'AMap.Scale'等
            "AMapUI": {             // 是否加载 AMapUI，缺省不加载
                "version": '1.1',   // AMapUI 缺省 1.1
                "plugins": [],       // 需要加载的 AMapUI ui插件
            },
            "Loca": {                // 是否加载 Loca， 缺省不加载
                "version": '1.3.2'  // Loca 版本，缺省 1.3.2
            },
        }).then((AMap) => {
            this.map = new AMap.Map('container', {
                zoom: 17,//级别
                center: [this.lng, this.lat],//中心点坐标
                viewMode: '3D'//使用3D视图
            })
            this.map.on('complete',_ =>{
                this.mapLoading = false
                this.$message.success('地图加载完成')
            })
        }).catch(e => {
            this.$message.error(e)
        })

        let category = echarts.init(Utils.getCharts('category', 500, 400))
        category.setOption(
            {
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '75%',
                        roseType: 'true',
                        data: [
                            {value: 274, name: '联盟广告'},
                            {value: 310, name: '邮件营销'},
                            {value: 335, name: '直接访问'},
                            {value: 400, name: '搜索引擎'}
                        ],
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 30,
                                shadowColor: 'rgba(0, 0, 0, 0.2)'
                            }
                        }
                    }
                ]
            }
        )
        category.on('finished',_ =>{
            if(this.categoryLoading === true)
                this.categoryLoading = false
        })
        let footprint = echarts.init(Utils.getCharts('footprint', 500, 400))
        footprint.setOption({
            xAxis: {
                data: ['购物车数量', '收藏夹数量', '订单数量']
            },
            yAxis: {},
            series: [{
                type: 'bar',
                data: [5, 10, 20],
                itemStyle: {
                    color: function (params) {
                        let colorList = ['#F5C11D', '#2AC91D', '#CA3822']
                        return colorList[params.dataIndex % colorList.length]
                    }
                }
            }],
        })
        footprint.on('finished',_ =>{
            if(this.footprintLoading === true)
                this.footprintLoading = false
        })
    },
    methods: {
        jump(){
            this.map.setCenter([this.lng,this.lat])
        },
        resetScale(){
            this.map.setZoom(10)
        }
    },
}
</script>

<style scoped>

</style>