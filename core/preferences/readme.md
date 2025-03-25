core-preferences/
├── src/main/kotlin/
│   ├── di/                # 依赖注入配置
│   ├── manager/           # 偏好设置管理接口与实现
│   ├── model/             # 复杂数据结构的序列化模型
│   └── extension/         # 扩展函数（简化 API 调用）
├── src/main/proto/        # （可选）Proto DataStore 的协议定义
└── build.gradle.kts       # 模块依赖配置