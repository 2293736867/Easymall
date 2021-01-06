<template>
    <el-drawer direction="rtl" size="50%" title="订单">
        <div id="ordersDetail"></div>
        <el-button type="primary">已订单</el-button>
    </el-drawer>
</template>

<script>
import * as echarts from 'echarts'
import Utils from "../../../../../../../js/utils/Utils";
export default {
    name: "OrdersIndexDrawer",
    data(){
        return{

        }
    },
    methods:{
        init(){
            let ordersDetailCharts = echarts.init(Utils.getCharts('ordersDetail',600,600))
            ordersDetailCharts.setOption({
                name: '订单分类',
                type: 'pie',
                radius: '65%',
                roseType: 'true',
                data: [
                    {value: 1, name: '未付款'},
                    {value: 3, name: '已付款'},
                    {value: 4, name: '已发货'},
                    {value: 3, name: '已完成'},
                    {value: 2, name: '未评价'}
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 30,
                        shadowColor: 'rgba(0, 0, 0, 0.2)'
                    }
                }
            })
        }
    }
}
</script>

<style scoped>

</style>