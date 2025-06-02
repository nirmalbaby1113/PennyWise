package baby.nirmal.pennywise.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import baby.nirmal.pennywise.data.local.datastore.UserPreferencesKeys
import baby.nirmal.pennywise.data.local.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val context: Context
) {
    val darkModeEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[UserPreferencesKeys.DARK_MODE_ENABLED] ?: false
        }

    val biometricEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[UserPreferencesKeys.BIOMETRIC_ENABLED] ?: false
        }

    val fallbackPin: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[UserPreferencesKeys.FALLBACK_PIN]
        }

    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[UserPreferencesKeys.DARK_MODE_ENABLED] = enabled
        }
    }

    suspend fun setBiometric(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[UserPreferencesKeys.BIOMETRIC_ENABLED] = enabled
        }
    }

    suspend fun setFallbackPin(pin: String) {
        context.dataStore.edit { preferences ->
            preferences[UserPreferencesKeys.FALLBACK_PIN] = pin
        }
    }
}