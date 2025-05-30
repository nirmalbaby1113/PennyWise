package baby.nirmal.pennywise.data.remote

import baby.nirmal.pennywise.data.local.entities.AccountEntity
import baby.nirmal.pennywise.data.local.entities.BudgetEntity
import baby.nirmal.pennywise.data.local.entities.TransactionEntity
import retrofit2.http.GET

interface ApiService {

    @GET("v3/1889f295-14e7-4792-a478-09b32ada3d9f")
    suspend fun getAccounts(): List<AccountEntity>

    @GET("v3/3734bf34-8a8a-4378-9a57-bc07e5f25fb3")
    suspend fun getTransactions(): List<TransactionEntity>

    @GET("v3/c080ecc1-721f-4921-b3c5-33b798ce3556")
    suspend fun getBudgets(): List<BudgetEntity>

}