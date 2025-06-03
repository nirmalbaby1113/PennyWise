package baby.nirmal.pennywise.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import baby.nirmal.pennywise.BaseActivity
import baby.nirmal.pennywise.R
import baby.nirmal.pennywise.databinding.ActivityMainBinding
import baby.nirmal.pennywise.presentation.ui.fragments.BudgetFragment
import baby.nirmal.pennywise.presentation.ui.fragments.HomeFragment
import baby.nirmal.pennywise.presentation.ui.fragments.SettingsFragment
import baby.nirmal.pennywise.presentation.ui.fragments.TransactionFragment
import baby.nirmal.pennywise.presentation.ui.viewModel.MainViewModel
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setting up the toolbar
        setupToolbarInsets()
        //Setting up the bottom nav
        setupBottomNav()
        // Load default fragment
        loadFragment(HomeFragment())

    }

    private fun setupBottomNav(){
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_transactions -> {
                    loadFragment(TransactionFragment())
                    true
                }
                R.id.nav_budgets -> {
                    loadFragment(BudgetFragment())
                    true
                }
                R.id.nav_settings -> {
                    loadFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun setupToolbarInsets(){
        val toolbar = binding.toolbar

        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { view, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            val layoutParams = view.layoutParams
            layoutParams.height = resources.getDimensionPixelSize(R.dimen.action_bar_size) + topInset
            view.layoutParams = layoutParams
            insets
        }
    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun setToolbarTitle(title: String) {
        binding.appTitle.text = title
    }


}




/*
* // Load data from API and store in Room
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


*
* */