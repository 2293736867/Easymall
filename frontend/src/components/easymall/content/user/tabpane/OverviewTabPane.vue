<template>
    <el-row justify="space-between" type="flex">
        <el-col :span="10">
            <div style="text-align: center">
                浏览足迹
                <el-divider></el-divider>
                <el-card v-loading="footprintLoading" shadow="hover">
                    <div style="padding-left: 9rem">
                        <div id="footprint"></div>
                    </div>
                </el-card>
            </div>
        </el-col>
        <el-col :span="10">
            购物分类
            <el-divider></el-divider>
            <el-card v-loading="categoryLoading" shadow="hover">
                <div style="padding-left: 7rem">
                    <div id="category"></div>
                </div>
            </el-card>
        </el-col>
    </el-row>

    <el-row v-loading="mapLoading" style="margin-top: 3rem">
        <el-col>
            <div id="container" style="height: 600px"></div>
        </el-col>
    </el-row>
    <el-row justify="end" style="margin-top: 3rem" type="flex">
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
import {defineComponent, onMounted,ref} from 'vue'
import { ElMessage } from 'element-plus'
import * as echarts from 'echarts'
import Utils from "../../../../../js/utils/Utils";
import AMapLoader from '@amap/amap-jsapi-loader';
import Config from "../../../../../js/constant/Config";

export default defineComponent({
    name: "OverviewTabPane",
    setup(){
        let map = ''

        const lat = ref(23.146736)
        const lng = ref(113.028458)
        const mapLoading = ref(true)
        const footprintLoading = ref(true)
        const categoryLoading = ref(true)

        onMounted(_ => {
            loadAMap()
            loadEcharts()
        })

        function loadAMap(){
            AMapLoader.load({
                "key": Config.AMapKey,
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
                map = new AMap.Map('container', {
                    zoom: 17,
                    center: [lng.value, lat.value],
                    viewMode: '3D'
                })
                map.on('complete',_=>{
                    mapLoading.value = false
                    ElMessage.success('地图加载完成')
                })
            }).catch(e => {
                ElMessage.error(e.message)
            })
        }

        function loadEcharts(){
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
                                {value: 4, name: '口红'},
                                {value: 10, name: '女裤'},
                                {value: 12, name: '男上装'},
                                {value: 6, name: '男鞋'},
                                {value: 9, name: '手机数码'}
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
            category.on('finished', _ => {
                if (categoryLoading.value === true)
                    categoryLoading.value = false
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
            footprint.on('finished', _ => {
                if (footprintLoading.value === true)
                    footprintLoading.value = false
            })
        }

        function jump(){
            map.setCenter([lng.value, lat.value])
        }

        function resetScale(){
            map.setZoom(10)
        }

        return {
            lat, lng, mapLoading, footprintLoading, categoryLoading,

            jump,resetScale
        }
    },
})
</script>

<style scoped>

</style>