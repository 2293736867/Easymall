<template>
    <el-row style="width: 80px;position: relative;left: 0;border-right: 2px solid #409EFF">
        <el-col>
            <el-image src="/default/SideBarUser.png"></el-image>
        </el-col>
        <el-col style="border-bottom: 1px solid black;margin-top: 0.5rem">
            cc
        </el-col>
    </el-row>
<!--    <el-menu :collapse="isCollapse" class="el-menu-vertical-demo side-bar" default-active="1-1-1">-->
<!--        <el-menu-item index="0">-->
<!--            <el-tooltip content="用户头像" effect="light" placement="right">-->
<!--                <div @click="showAvatarDrawer">-->
<!--                    <i class="el-icon-user"></i>-->
<!--                    ccc-->
<!--                </div>-->
<!--            </el-tooltip>-->
<!--        </el-menu-item>-->
<!--        <el-menu-item index="1">-->
<!--            <el-tooltip content="购物车" effect="light" placement="right">-->
<!--                <el-badge :value="cardNum">-->
<!--                    <i class="el-icon-shopping-cart-1"></i>-->
<!--                </el-badge>-->
<!--            </el-tooltip>-->
<!--        </el-menu-item>-->
<!--        <el-menu-item index="2">-->
<!--            <el-tooltip content="收藏" effect="light" placement="right">-->
<!--                <el-badge :value="favoriteNum">-->
<!--                    <i class="el-icon-star-off"></i>-->
<!--                </el-badge>-->
<!--            </el-tooltip>-->
<!--        </el-menu-item>-->
<!--        <el-submenu index="3">-->
<!--            <template #title>-->
<!--                <i class="el-icon-location"></i>-->
<!--                <span>cc</span>-->
<!--            </template>-->
<!--            <el-menu-item-group>-->
<!--                <template #title>分组</template>-->
<!--                <el-menu-item index="1-1">选项1-1</el-menu-item>-->
<!--                <el-menu-item index="1-2">选项1-2</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--        </el-submenu>-->
<!--    </el-menu>-->

    <AvatarDrawer v-model="avatarDrawer"></AvatarDrawer>
</template>

<script>
import AvatarDrawer from "./AvatarDrawer.vue";
export default {
    name: "SideBar",
    components: {AvatarDrawer},
    data() {
        return {
            isCollapse: true,
            cardNum: '12',
            favoriteNum: '12',
            avatarDrawer:false
        }
    },
    methods: {
        showAvatarDrawer(){
            this.avatarDrawer = true
        }
    }
}
</script>

<style scoped>

</style>