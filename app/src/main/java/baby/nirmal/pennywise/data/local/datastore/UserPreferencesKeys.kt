package baby.nirmal.pennywise.data.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object UserPreferencesKeys {
    val DARK_MODE_ENABLED = booleanPreferencesKey("dark_mode_enabled")
    val BIOMETRIC_ENABLED = booleanPreferencesKey("biometric_enabled")
    val FALLBACK_PIN = stringPreferencesKey("fallback_pin")
}