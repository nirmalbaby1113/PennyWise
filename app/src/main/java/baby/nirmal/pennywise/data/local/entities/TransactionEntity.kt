package baby.nirmal.pennywise.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val accountId: Int,
    val amount: Double,
    val date: Long,
    val type: String, // e.g., "income" or "expense"
    val category: String,
    val note: String?
)
