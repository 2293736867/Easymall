# 项目临时不可用，使用请切换到`v4.x`分支

# 5.0 版本准备中

- 项目架构升级中
- 预计采用`gRPC`/`dubbo`进行重新设计
- 数据库底层更改中

# 1 Easymall

一个前后端分离的商城项目，还处于开发阶段。

前端库：

- `ElementPlus`
- `Vue3`
- `Vuex`
- `axios`
- `Vue-router`
- `Vite`
- `jsSHA`
- `Echarts`
- `mitt`
- `pnpm`

后端库：

- `Spring Boot WebFlux`
- `Reactive Mongodb`
- `Docker`
- `JWT`
- `Guava`
- `hutool`
- `Bouncycastle`
- `snakeyaml`

# 2 项目目录说明

- `backend`：后端目录
- `frontend`：前端目录

# 3 目前已完成

- 前端UI首页
- 用户登录/注册
- 商品展示
- 商品详情
- 购物车

# 4 Todo

- 前端各种异步处理
- 购物车完善
- 订单业务逻辑
- 物流信息地图
- 订单报表

# 5 更新日志
# 2/19/2021

- 5.0版本准备中

# 2/16/2021
- ~~V5.0.0.alpha~~
- 全面使用`pnpm`而不是`npm`+`cnpm`混合
- 项目命名更改为`EasymallWeb`+`EasymallServer`
- 后端接口添加版本，大部分接口重做

# 1/13/2021
- V4.0.1
- `Vue3.0`进一步适配
- 引入`mitt`事件处理库
- 修复评分标准上限问题
- 抽离表单组件

# 1/10/2021
- 4.0版本

# 1/7/2021

- 4.0预览版，重大更新
- 全面重构API
- 引入谷歌地图
- 优化网络传输
- 添加订单以及购物车逻辑

# 1/5/2021
- 引入图表库`Echarts`
- 完善个人中心
- 添加订单分类
- 初步引入后台管理模块

# 1/4/2021
- V3.2.beta

# 1/2/2021
- V3.2.alpha

# 1/1/2021

- V3.1.2
- `Token`重大更新，采用更快更强大的`ECDSA`算法，添加过期时间以及`issuer`
- 添加`Yaml`文件读取库
- 添加个人中心页面

# 12/30/2020

- 3.1版本
- 添加更多商品图片
- 重建商品数据库
- 优化API
- 用户Token
- 修复若干Bugs

# 12/27/2020
- 3.0正式版
- `BootstrapVue`迁移到`ElementPlus`完成
- `Vue2`迁移到`Vue3`完成
- `UI`重大改进
- 更加友好的表单校验
- 引入`SHA3-512`加密
- 若干，忘记了

# 12/26/2020
3.0 alpha版。

# 12/25/2020 
3.0预览版，采用Vue3+ElementPlus重构前端。

# 12/14/2020
2.0版本发布，添加了商品展示功能。

# 12/09/2020

2.0预览版，拆分多模块项目，优化项目结构。
 
# 11/18/2020

1.0版本正式启动。
