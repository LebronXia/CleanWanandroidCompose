domain/
├── :domain-base           # 基础领域模型（所有模块共享）
├── :domain-auth           # 认证相关业务逻辑
│   ├── model/             # 领域模型（如 AuthToken, UserSession）
│   ├── repository/        # 接口定义（如 AuthRepository）
│   └── usecase/           # 认证相关用例（如 LoginUseCase）
├── :domain-payment        # 支付业务逻辑
│   ├── model/             # 领域模型（如 PaymentOrder）
│   ├── repository/        # 接口定义（如 PaymentRepository）
│   └── usecase/           # 支付用例（如 CreateOrderUseCase）
└── ...                    # 其他业务领域