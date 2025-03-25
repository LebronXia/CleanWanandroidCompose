以下是 core 层 的完整设计方案，作为整个项目的 基础设施核心，提供可复用的通用能力，确保各模块高效协作且代码高度复用：
core 层整体结构
core/
├── core-network/           # 网络通信（Retrofit + OkHttp）
├── core-database/          # 数据库（Room + 迁移管理）
├── core-preferences/       # 本地存储（DataStore/SharedPreferences）
├── core-ui/                # UI 基础组件（Compose/View 组件库）
├── core-utils/             # 通用工具类（扩展函数、日志、日期处理）
├── core-navigation/        # 导航框架（统一路由管理）
└── core-analytics/         # 监控与统计（Firebase/Crashlytics）