目录结构：
core-database/
├── src/main/kotlin/
│   ├── dao/              # 基础 DAO 接口
│   ├── entity/           # 通用数据库实体
│   └── di/               # 数据库依赖注入
代码示例：
kotlin
// 数据库实例配置
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
@Provides
@Singleton
fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
return Room.databaseBuilder(
context,
AppDatabase::class.java, "app-db"
).addMigrations(MIGRATION_1_2) // 数据库迁移
.build()
}
private val MIGRATION_1_2 = object : Migration(1, 2) {
override fun migrate(database: SupportSQLiteDatabase) {
database.execSQL("ALTER TABLE users ADD COLUMN last_login INTEGER")
}
}
}
// 基础 DAO 接口
@Dao
interface BaseDao {     @Insert(onConflict = OnConflictStrategy.REPLACE)     suspend fun insert(item: T)
@Delete
suspend fun delete(item: T)
}