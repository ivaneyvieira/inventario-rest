package br.com.astrosoft.framework.viewmodel

import br.com.astrosoft.framework.model.Transaction

abstract class ViewModel<out V : VO>(private val view: IView) {
  private var inExcursion = false

  @Throws(EViewModel::class)
  fun exec(block: () -> Unit) {
      if(inExcursion) block()
      else {
        try {
          inExcursion = true
          transaction(block)
        }finally {
          inExcursion = false
        }
      }
  }

  private fun <T> transaction(block: () -> T) {
    return try {
      block()
      Transaction.commit()
    } catch(e: Throwable) {
      Transaction.rollback()
      throw e
    }
  }

  abstract fun toVO(): V
}

open class VO

class EViewModel(msg: String): Exception(msg)

interface IView
