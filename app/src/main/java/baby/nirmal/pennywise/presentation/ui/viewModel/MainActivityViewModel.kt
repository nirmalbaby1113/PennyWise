package baby.nirmal.pennywise.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import baby.nirmal.pennywise.data.local.entities.AccountEntity
import baby.nirmal.pennywise.data.local.entities.BudgetEntity
import baby.nirmal.pennywise.data.local.entities.TransactionEntity
import baby.nirmal.pennywise.data.repository.AccountRepository
import baby.nirmal.pennywise.data.repository.BudgetRepository
import baby.nirmal.pennywise.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
    private val budgetRepository: BudgetRepository
) : ViewModel() {

    val localAccounts: Flow<List<AccountEntity>> = accountRepository.getLocalAccounts()
    val localTransactions: Flow<List<TransactionEntity>> = transactionRepository.getLocalTransactions()
    val localBudgets: Flow<List<BudgetEntity>> = budgetRepository.getLocalBudget()

    fun loadAccountsFromApi() {
        viewModelScope.launch {
            accountRepository.fetchAndSaveAccounts()
        }
    }

    fun loadTransactionFromApi(){
        viewModelScope.launch {
            transactionRepository.fetchAndSaveTransactions()
        }
    }

    fun loadBudgetFromApi(){
        viewModelScope.launch{
            budgetRepository.fetchAndSaveBudget()
        }
    }


}