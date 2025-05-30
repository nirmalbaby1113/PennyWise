package baby.nirmal.pennywise.data.repository

import baby.nirmal.pennywise.data.local.dao.TransactionDao
import baby.nirmal.pennywise.data.local.entities.TransactionEntity
import baby.nirmal.pennywise.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val apiService: ApiService,
    private val transactionDao: TransactionDao
) {

    fun getLocalTransactions(): Flow<List<TransactionEntity>> =
        transactionDao.getAllTransactions()

    suspend fun fetchAndSaveTransactions() {
        val transactions = apiService.getTransactions()
        transactions.forEach { transactionDao.insertTransaction(it) }
    }
}