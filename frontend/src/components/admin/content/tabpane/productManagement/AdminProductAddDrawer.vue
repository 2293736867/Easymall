<template>
    <el-drawer ref="drawer" direction="rtl" size="25%" title="商品添加">
        <el-form ref="form" :model="form" :rules="rules" status-icon style="margin-left: 2.5rem;margin-right: 2.5rem;">
            <el-form-item label="名称" prop="name">
                <el-input v-model="form.name" clearable prefix-icon="el-icon-connection"></el-input>
            </el-form-item>
            <el-form-item label="价格" prop="price">
                <el-input v-model="form.price" clearable prefix-icon="el-icon-price-tag"></el-input>
            </el-form-item>
            <el-form-item label="评分" prop="rating">
                <el-input v-model="form.rating" clearable prefix-icon="el-icon-medal"></el-input>
            </el-form-item>
            <el-form-item label="运费" prop="freight">
                <el-input v-model="form.freight" clearable prefix-icon="el-icon-mobile"></el-input>
            </el-form-item>
            <el-form-item label="分类" prop="category">
                <el-input v-model="form.category" clearable prefix-icon="el-icon-reading"></el-input>
            </el-form-item>
            <el-form-item label="库存" prop="num">
                <el-input v-model="form.num" clearable prefix-icon="el-icon-reading"></el-input>
            </el-form-item>
            <el-form-item label="描述" prop="description">
                <el-input v-model="form.description" clearable prefix-icon="el-icon-collection-tag"></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="commit" style="margin-top: 2rem">
            <i class="el-icon-arrow-right"></i>
            添加
            <i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>
</template>

<script>
import REG from "../../../../../js/constant/REG";
import axios from "axios"
import URL from "../../../../../js/constant/URL";

export default {
    name: "AdminProductAddDrawer",
    data(){
        let nameCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入名称'))
            callback()
        }

        let priceCheck = (rule, value, callback) => {
            if (!value) {
                callback(new Error('请输入价格'))
            }
            callback()
        }

        let ratingCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入评分'))
            callback()
        }

        let freightCheck = (rule,value,callback) => {
            if(!value)
                callback(new Error('请输入运费'))
            callback()
        }

        let categoryCheck = (rule,value,callback) => {
            if(!value)
                callback(new Error('请输入分类'))
            callback()
        }

        let numCheck = (rule,value,callback) => {
            if(!value)
                callback(new Error('请输入库存'))
            callback()
        }

        return{
            form:{
                id:'',
                name:'',
                price:'',
                rating:'',
                freight:'',
                category:'',
                num:'',
                description:''
            },
            user:'',
            rules: {
                name: [{validator: nameCheck, trigger: 'blur'}],
                price: [{validator: priceCheck, trigger: 'blur'}],
                rating:[{validator:ratingCheck,trigger:'blur'}],
                freight: [{validator: freightCheck, trigger: 'blur'}],
                num:[{validator:numCheck,trigger:'blur'}],
                category: [{validator: categoryCheck, trigger: 'blur'}],
            },
        }
    },
    methods:{
        commit(){
            this.$refs['form'].validate((valid) => {
                if(valid){
                    axios.post(URL.productAdd,{
                        name:this.form.name,
                        price:this.form.price,
                        rating:this.form.rating,
                        freight:this.form.freight,
                        category:this.form.category,
                        num:this.form.num,
                        description:this.form.description
                    }).then(res=>{
                        if(parseInt(res.data.code) === 110000){
                            this.$message.success('添加成功')
                            this.form.id = res.data.data
                            this.$emit('success',this.form)
                            this.$refs.drawer.handleClose()
                        }else{
                            this.$message.error('未知原因添加失败')
                        }
                    })
                }else{
                    this.$message.warning('请输入合法信息')
                }
            })
        },
    },
    emits:['success']
}
</script>

<style scoped>

</style>