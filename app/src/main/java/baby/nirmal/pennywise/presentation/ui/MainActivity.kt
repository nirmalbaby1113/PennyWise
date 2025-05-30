package baby.nirmal.pennywise.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import baby.nirmal.pennywise.BaseActivity
import baby.nirmal.pennywise.R
import baby.nirmal.pennywise.presentation.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Load data from API and store in Room
        viewModel.loadAccountsFromApi()
        viewModel.loadTransactionFromApi()
        viewModel.loadBudgetFromApi()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.localAccounts.collect { accounts ->
                    accounts.forEach{account ->
                        Log.d("PennyWise", "Account: $account")
                    }

                viewModel.localTransactions.collect{ transactions ->
                    transactions.forEach{ transaction ->
                        Log.d("PennyWise", "Transactions: $transaction")
                    }

                viewModel.localBudgets.collect{budgets ->
                    budgets.forEach{budget ->
                        Log.d("PennyWise", "Budgets: $budget")
                    }
                }

                }

                }
            }
        }


    }
}