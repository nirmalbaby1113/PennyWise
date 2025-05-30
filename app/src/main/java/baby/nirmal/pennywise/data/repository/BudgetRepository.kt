package baby.nirmal.pennywise.data.repository

import baby.nirmal.pennywise.data.local.dao.BudgetDao
import baby.nirmal.pennywise.data.local.dao.TransactionDao
import baby.nirmal.pennywise.data.local.entities.BudgetEntity
import baby.nirmal.pennywise.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BudgetRepository @Inject constructor(
    private val apiService: ApiService,
    private val budgetDao: BudgetDao
){
    fun getLocalBudget() : Flow<List<BudgetEntity>> = budgetDao.getAllBudgets()

    suspend fun fetchAndSaveBudget() {
        val budgets = apiService.getBudgets()
        budgets.forEach { budgetDao.insertBudget(it) }
    }

}