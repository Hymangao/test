# 快速开始指南

## 环境准备

### 1. 安装必要工具

#### Android Studio
- 下载最新版Android Studio (Hedgehog 2023.1.1+)
- 安装JDK 17（Android Studio自带）

#### Gradle
- 项目使用Gradle 8.2
- 第一次打开项目时会自动下载

### 2. 打开项目
```bash
# 方式1: 使用命令行
cd "D:\Engineering with AI\Opencode Dev"
gradlew.bat build  # Windows
# 或
./gradlew build  # Linux/Mac

# 方式2: 使用Android Studio
# File -> Open -> 选择 "D:\Engineering with AI\Opencode Dev"
```

### 3. 等待依赖下载
- 第一次打开项目时，Gradle会下载所有依赖
- 可能需要10-30分钟，取决于网络速度
- 确保网络连接正常

## 配置API

### 修改API地址

打开文件:
```
app/src/main/java/com/example/sublandlord/data/remote/RetrofitClient.kt
```

修改第11行:
```kotlin
// 将这行
private const val BASE_URL = "https://api.sublandlord.com/v1/"

// 改为你的实际API地址，例如
private const val BASE_URL = "http://192.168.1.100:8000/api/"
```

### 如果没有后端API
应用会自动使用离线模式，所有数据存储在本地Room数据库中。

## 运行应用

### 方式1: Android Studio
1. 点击工具栏的运行按钮（绿色三角形）
2. 或按快捷键 `Shift + F10` (Windows/Linux) 或 `Control + R` (Mac)

### 方式2: 命令行
```bash
# Windows
gradlew.bat installDebug

# Linux/Mac
./gradlew installDebug

# 安装后，在设备上找到"二房东助手"应用图标并打开
```

### 连接设备
1. **真机**:
   - 在手机上启用"开发者选项"
   - 启用"USB调试"
   - 用USB线连接电脑
   - 接受调试授权

2. **模拟器**:
   - 在Android Studio中点击 Device Manager
   - 创建新模拟器（推荐使用Pixel 5, API 33）
   - 启动模拟器

## 项目结构速览

```
app/src/main/java/com/example/sublandlord/
│
├── data/                    # 数据层
│   ├── local/              # 本地数据（Room）
│   │   ├── dao/           # 数据访问对象
│   │   ├── entity/        # 数据库实体
│   │   └── converter/     # 类型转换
│   ├── remote/            # 远程数据（API）
│   │   ├── api/           # API接口
│   │   └── dto/           # 数据传输对象
│   └── repository/        # 数据仓库
│
├── di/                     # 依赖注入（Hilt）
│   ├── DatabaseModule.kt   # 数据库模块
│   └── NetworkModule.kt   # 网络模块
│
├── presentation/           # 展示层
│   ├── base/              # 基类
│   ├── properties/        # 房屋模块
│   ├── contracts/         # 合同模块
│   ├── dashboard/         # Dashboard
│   └── profile/           # 个人中心
│
├── ui/
│   ├── navigation/        # 导航
│   └── theme/             # 主题
│
└── MainActivity.kt        # 主Activity
```

## 当前可用功能

### ✅ 房屋管理（基础版）
- 查看房屋列表
- 空状态提示
- 加载状态显示
- 错误处理
- 底部导航栏

### 🚧 其他模块（待开发）
- 房屋添加/编辑
- 房间管理
- 合同管理
- Dashboard
- 提醒系统

## 常见问题

### Q: 编译时出现"Unsupported Kotlin runtime"错误
A: 确保JDK版本是17，在Android Studio中设置：
```
File -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle
Gradle JDK: 选择 JDK 17
```

### Q: 运行时崩溃 "Hilt not found"
A: 清理并重新构建：
```bash
gradlew.bat clean
gradlew.bat build
```

### Q: 应用显示空白页面
A: 检查AndroidManifest.xml中的Activity是否正确配置，查看Logcat是否有错误信息

### Q: 数据库报错 "no such table"
A: 卸载应用重新安装，会重新创建数据库

## 开发工具推荐

### Android Studio插件
1. **Kotlin Formatter** - 代码格式化
2. **Material Design Theme Editor** - UI主题编辑
3. **JSON to Kotlin Class** - JSON转Kotlin类
4. **Room Persistence Inspector** - 数据库查看

### 调试工具
1. **Layout Inspector** - UI布局检查
2. **Database Inspector** - 数据库查看
3. **Profiler** - 性能分析

## 下一步

### 如果你想继续开发：
1. 阅读 `README.md` 了解完整功能规划
2. 查看 `PROGRESS.md` 了解当前进度
3. 从"房屋管理完善"开始第二阶段开发

### 如果你想测试：
1. 运行应用
2. 尝试添加一些测试数据
3. 测试列表滚动和导航

### 如果你想学习：
1. 研究 `PropertiesViewModel` 了解状态管理
2. 研究 `PropertyRepository` 了解数据访问模式
3. 研究 `MainNavigation` 了解导航配置

## 有用的命令

```bash
# 清理构建
gradlew.bat clean

# 编译项目
gradlew.bat build

# 运行测试
gradlew.bat test

# 生成APK
gradlew.bat assembleDebug

# 安装到设备
gradlew.bat installDebug

# 查看依赖树
gradlew.bat app:dependencies
```

## 资源链接

- [Jetpack Compose文档](https://developer.android.com/jetpack/compose)
- [Hilt文档](https://developer.android.com/training/dependency-injection/hilt-android)
- [Room数据库](https://developer.android.com/training/data-storage/room)
- [Jetpack Navigation](https://developer.android.com/guide/navigation/navigation-compose)
- [Material Design 3](https://m3.material.io/)

## 支持

遇到问题？
1. 查看Logcat日志
2. 检查Gradle同步状态
3. 确保所有依赖已下载完成

祝开发愉快！🚀
