package com.github.satoshun.example.sample

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.satoshun.example.sample.databinding.MainActBinding
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = DataBindingUtil.setContentView<MainActBinding>(this, R.layout.main_act)
  }
}

class MainFragment : Fragment() {
  @Inject lateinit var factory: Provider<MainViewModel>

  private val viewModel: MainViewModel by viewModels2(factory)
}

@MainThread
inline fun <reified VM : ViewModel> Fragment.viewModels2(
  provider: Provider<VM>
) = viewModels<VM>(factoryProducer = { aaa(provider) })

fun <T : ViewModel> aaa(p: Provider<T>): ViewModelProvider.Factory {
  return object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return p.get() as T
    }
  }
}

class MainViewModel @Inject constructor() : ViewModel()
