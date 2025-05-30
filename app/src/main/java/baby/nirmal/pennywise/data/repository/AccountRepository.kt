package baby.nirmal.pennywise.data.repository

import baby.nirmal.pennywise.data.local.dao.AccountDao
import baby.nirmal.pennywise.data.local.dao.TransactionDao
import baby.nirmal.pennywise.data.local.entities.AccountEntity
import baby.nirmal.pennywise.data.local.entities.TransactionEntity
import baby.nirmal.pennywise.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val apiService: ApiService,
    private val accountDao: AccountDao,
    private val transactionDao: TransactionDao
) {

    fun getLocalAccounts(): Flow<List<AccountEntity>> = accountDao.getAllAccounts()

    suspend fun fetchAndSaveAccounts() {
        val accounts = apiService.getAccounts()
        accounts.forEach { accountDao.insertAccount(it) }
    }


}