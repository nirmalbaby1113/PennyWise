package baby.nirmal.pennywise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import baby.nirmal.pennywise.data.local.dao.AccountDao
import baby.nirmal.pennywise.data.local.dao.BudgetDao
import baby.nirmal.pennywise.data.local.dao.TransactionDao
import baby.nirmal.pennywise.data.local.entities.AccountEntity
import baby.nirmal.pennywise.data.local.entities.BudgetEntity
import baby.nirmal.pennywise.data.local.entities.TransactionEntity

@Database(
    entities = [
        AccountEntity::class,
        TransactionEntity::class,
        BudgetEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao
    abstract fun budgetDao(): BudgetDao

    // Optionally add type converters here in the future

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Example: remove a table
                db.execSQL("DROP TABLE IF EXISTS user_preferences")
            }
        }
    }
}