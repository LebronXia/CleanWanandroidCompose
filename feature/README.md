● 业务场景专属：每个 feature 对应一个独立功能模块（如 feature-home, feature-profile）
● 表现层实现：包含 UI（Compose）、ViewModel、导航等 Android 相关代码
● 依赖关系：依赖 domain 层获取业务逻辑，依赖 data 层获取具体实现
目录结构
features/
├── :feature-auth          # 登录/注册模块
│   ├── di/                # 模块专属的 Hilt 模块
│   ├── navigation/        # 导航定义（如 AuthNavGraph）
│   ├── ui/                # Compose 界面
│   │   ├── login/         # 登录界面组件
│   │   └── register/      # 注册界面组件
│   └── viewmodel/         # ViewModel 实现
├── :feature-home          # 首页模块
│   ├── ui/
│   │   ├── components/    # 首页独有 UI 组件
│   │   └── HomeScreen.kt
│   └── viewmodel/
│       └── HomeViewModel.kt
└── ...                    # 其他功能模块
