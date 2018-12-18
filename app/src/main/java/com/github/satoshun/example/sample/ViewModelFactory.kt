package com.github.satoshun.example.sample

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory<VM : ViewModel> @Inject constructor(
  private val viewModel: Provider<VM>
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    @Suppress("UNCHECKED_CAST")
    return viewModel.get() as T
  }
}

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
  factory: ViewModelFactory<VM>
): Lazy<VM> {
  return viewModels(factoryProducer = { factory })
}
