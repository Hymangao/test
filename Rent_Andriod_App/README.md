# 二房东助手 (SubLandlord Assistant)

一个为二房东设计的Android应用，用于管理房屋、合同、财务和提醒。

## 项目概述

### 核心功能
1. **房屋管理** - 管理所持房屋信息，支持整租和分租模式
2. **合同管理** - 管理与房东和租户的合同
3. **财务Dashboard** - 收入/支出报表，利润分析
4. **提醒系统** - 自动化的收租、付租、合同到期提醒

### 技术栈
- **语言**: Kotlin 1.9+
- **UI框架**: Jetpack Compose
- **架构模式**: MVVM + Clean Architecture
- **依赖注入**: Hilt
- **数据库**: Room (本地SQLite)
- **网络层**: Retrofit + OkHttp
- **导航**: Jetpack Navigation Compose
- **状态管理**: StateFlow + Compose State
- **图片加载**: Coil

## 项目结构

```
app/
├── src/main/java/com/example/sublandlord/
│   ├── data/
│   │   ├── local/
│   │   │   ├── dao/              # Room DAO接口
│   │   │   ├── entity/           # 数据库实体
│   │   │   ├── converter/        # 类型转换器
│   │   │   └── SubLandlordDatabase.kt
│   │   ├── remote/
│   │   │   ├── api/               # API接口
│   │   │   ├── dto/               # 数据传输对象
│   │   │   └── RetrofitClient.kt
│   │   └── repository/            # 数据仓库
│   ├── di/
│   │   ├── DatabaseModule.kt      # 数据库依赖注入
│   │   └── NetworkModule.kt      # 网络依赖注入
│   ├── domain/
│   │   ├── model/                 # 领域模型
│   │   └── usecase/               # 用例
│   ├── presentation/
│   │   ├── base/
│   │   │   └── BaseViewModel.kt   # ViewModel基类
│   │   ├── properties/            # 房屋模块
│   │   ├── contracts/             # 合同模块
│   │   ├── dashboard/             # Dashboard模块
│   │   └── profile/               # 个人中心
│   ├── ui/
│   │   ├── navigation/            # 导航配置
│   │   └── theme/                 # UI主题
│   ├── MainActivity.kt
│   └── SubLandlordApplication.kt
```

## 数据模型

### 核心实体
1. **Property** - 房屋信息
   - 基本信息：名称、地址、城市、区域
   - 位置信息：经纬度
   - 房屋信息：面积、楼层、房间数
   - 状态：空置/出租中/装修中/维修中
   - 图片和设施列表

2. **Room** - 房间信息（分租模式）
   - 关联房屋ID
   - 房间号、面积、租金
   - 状态、最大居住人数

3. **Landlord** - 房东信息
   - 基本信息：姓名、电话、微信号
   - 银行信息（加密）
   - 身份证号（加密）

4. **Tenant** - 租户信息
   - 基本信息：姓名、电话、微信号
   - 身份证件
   - 紧急联系人
   - 信用评分

5. **LandlordContract** - 与房东的合同
   - 关联房屋和房东
   - 合同期限
   - 租金、押金
   - 付款周期和方式
   - 逾期费用策略

6. **Lease** - 与租户的租约
   - 关联房间和租户
   - 租期、租金
   - 水电费政策
   - 付款信息

7. **Transaction** - 财务记录
   - 收入/支出
   - 分类：租金、押金、维修等
   - 关联房屋、房间、合同
   - 凭证图片

8. **Reminder** - 提醒
   - 类型：收租、付租、合同到期
   - 目标日期、提前天数
   - 优先级、状态

## 已完成功能

### 第一阶段：基础架构 ✅
- [x] Android项目初始化
- [x] Gradle配置
- [x] Room数据库设计和实现
- [x] 网络层封装
- [x] Hilt依赖注入配置
- [x] MVVM架构基础类
- [x] Jetpack Navigation导航
- [x] 房屋管理模块（基础版）
  - 房屋列表UI
  - PropertyRepository
  - PropertiesViewModel
  - 基础数据模型

## 待开发功能

### 第二阶段：房屋管理完善
- [ ] 房屋添加/编辑表单
- [ ] 图片上传
- [ ] 地图选点
- [ ] 房间管理
- [ ] 房屋详情页
- [ ] 筛选和搜索

### 第三阶段：合同管理
- [ ] 房东管理（CRUD）
- [ ] 租户管理（CRUD）
- [ ] 房东合同创建和管理
- [ ] 租约创建和管理
- [ ] 合同模板生成
- [ ] 电子签集成

### 第四阶段：财务Dashboard
- [ ] 概览仪表盘
- [ ] 财务报表（收入/支出/利润）
- [ ] 交易流水列表
- [ ] 图表集成
- [ ] 数据导出

### 第五阶段：提醒系统
- [ ] 提醒数据模型
- [ ] 后台定时任务
- [ ] 推送通知集成
- [ ] 提醒中心页面
- [ ] 提醒设置页面

### 第六阶段：设置和优化
- [ ] 个人中心
- [ ] 设置页面
- [ ] 数据备份/恢复
- [ ] 帮助和反馈

### 第七阶段：测试和上线
- [ ] 单元测试
- [ ] 集成测试
- [ ] UI测试
- [ ] 性能优化
- [ ] 打包上线

## 快速开始

### 环境要求
- Android Studio Hedgehog (2023.1.1) 或更高版本
- JDK 17
- Android SDK 34
- Gradle 8.2

### 运行项目
1. 克隆项目到本地
2. 使用Android Studio打开项目
3. 等待Gradle同步完成
4. 点击运行按钮或使用命令：
   ```bash
   ./gradlew installDebug
   ```

### API配置
修改 `RetrofitClient.kt` 中的 `BASE_URL` 为实际API地址：
```kotlin
private const val BASE_URL = "https://api.sublandlord.com/v1/"
```

## 配色方案
- 主色调: #1890FF (蓝色 - 专业、信任)
- 辅助色: #52C41A (绿色 - 收入、完成)
- 警告色: #FAAD14 (橙色 - 待处理)
- 危险色: #FF4D4F (红色 - 支出、逾期)
- 背景色: #F5F7FA (浅灰)
- 文字色: #262626 (主文字), #595959 (次要文字)

## 导航结构
底部导航栏（4个标签）：
- 首页 (Home) - Dashboard概览
- 房屋 (Properties) - 房屋管理
- 合同 (Contracts) - 合同管理
- 我的 (Profile) - 个人中心

## 开发规范

### 代码风格
- 遵循Kotlin官方代码风格指南
- 使用Compose Material 3设计规范
- Repository模式分离数据层
- ViewModel管理UI状态
- Flow/StateFlow处理异步数据

### 提交规范
```
feat: 新功能
fix: 修复bug
docs: 文档更新
style: 代码格式调整
refactor: 重构
test: 测试相关
chore: 构建/工具链相关
```

## 注意事项

1. **安全性**
   - 敏感信息（身份证、银行卡号）使用加密存储
   - 使用HTTPS通信
   - Token认证

2. **离线支持**
   - 使用Room缓存数据
   - 网络请求队列

3. **性能优化**
   - 图片压缩和缓存
   - 列表分页加载
   - 数据库索引优化

## 贡献指南

1. Fork本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'feat: Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交Pull Request

## 许可证

本项目仅供学习参考使用。

## 联系方式

如有问题或建议，欢迎提Issue。
