<template>
    <el-row justify="center" type="flex">
        <el-col :span="12">
            <div style="padding-left: 8rem">
                <div id="ordersEcharts"></div>
            </div>
        </el-col>
    </el-row>
</template>

<script>
import * as echarts from 'echarts'
import Utils from "../../../../../js/utils/Utils";

export default {
    name: "OrdersIndexTabPane",
    mounted() {
        let ordersEcharts = echarts.init(Utils.getCharts('ordersEcharts', 600, 600))
        ordersEcharts.setOption({
            series: [{
                name: '订单详情',
                type: 'pie',
                radius: '55%',
                roseType: 'true',
                data: [
                    {value: 1, name: '未付款'},
                    {value: 3, name: '已付款'},
                    {value: 4, name: '已发货'},
                    {value: 3, name: '已完成'},
                    {value: 2, name: '未评价'}
                ]
            }],
            emphasis: {
                itemStyle: {
                    shadowBlur: 30,
                    shadowColor: 'rgba(0, 0, 0, 0.2)'
                }
            }
        })
        ordersEcharts.on('click', event => {
            this.$emit('toOrders',event.dataIndex)
        })
    },
    emits: ['toOrders'],
}
</script>

<style scoped>

</style>