<template>
    <el-row justify="space-between" type="flex">
        <el-col :span="10">
            <div style="text-align: center">
                浏览足迹
                <el-divider></el-divider>
                <el-card shadow="hover">
                    <div style="padding-left: 9rem">
                        <div id="footprint"></div>
                    </div>
                </el-card>
            </div>
        </el-col>
        <el-col :span="10">
            购物分类
            <el-divider></el-divider>
            <el-card shadow="hover">
                <div style="padding-left: 7rem">
                    <div id="category"></div>
                </div>
            </el-card>
        </el-col>
    </el-row>
</template>

<script>
import * as echarts from 'echarts'
import Utils from "../../../../../js/utils/Utils";

export default {
    name: "OverviewTabPane",
    data() {
        return {}
    },
    mounted() {
        let category = echarts.init(Utils.getCharts('category',500,400))
        category.setOption(
            {
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '75%',
                        roseType: 'true',
                        data: [
                            {value: 235, name: '视频广告'},
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
        let footprint = echarts.init(Utils.getCharts('footprint',500,400))
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
    },
    methods: {}
}
</script>

<style scoped>

</style>