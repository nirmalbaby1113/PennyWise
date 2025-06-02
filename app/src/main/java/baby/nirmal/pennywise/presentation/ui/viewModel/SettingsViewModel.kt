package baby.nirmal.pennywise.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import baby.nirmal.pennywise.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val darkModeEnabled = userPreferencesRepository.darkModeEnabled
    val biometricEnabled = userPreferencesRepository.biometricEnabled
    val fallbackPin = userPreferencesRepository.fallbackPin

    fun setDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setDarkMode(enabled)
        }
    }

    fun setBiometric(enabled: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setBiometric(enabled)
        }
    }

    fun setFallbackPin(pin: String) {
        viewModelScope.launch {
            userPreferencesRepository.setFallbackPin(pin)
        }
    }
}