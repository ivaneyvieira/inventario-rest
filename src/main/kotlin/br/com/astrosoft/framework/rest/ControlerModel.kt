package br.com.astrosoft.framework.rest

import br.com.astrosoft.framework.viewmodel.EViewModel
import br.com.astrosoft.framework.viewmodel.IView
import br.com.astrosoft.framework.viewmodel.VO
import br.com.astrosoft.framework.viewmodel.ViewModel
import javax.servlet.http.HttpSession

abstract class ControlerModel<V: VO, VIEWMODEL: ViewModel<V>>: IView {
  private val sessionRepository = HashMap<String, VIEWMODEL>()
  abstract fun createViewModel(): VIEWMODEL

  fun getViewModel(idSession: String): VIEWMODEL {
    println("Sessão id: $idSession")
    val value = sessionRepository[idSession]
    return if(value == null) {
      val model = createViewModel()
      sessionRepository[idSession] = model
      model
    } else value
  }

  fun closeViewModel(idSession: String) {
    sessionRepository.remove(idSession)
  }

  fun actionControler(id: String?, session: HttpSession, execViewModel: VIEWMODEL.() -> Unit): ResultUI<V> {
    val idSession = if(id.isNullOrBlank() || id == "NULO") session.id else id
    val viewModel = getViewModel(idSession)
    return try {
      viewModel.execViewModel()
      ResultUI(idSession, viewModel.toVO(), Messages())
    } catch(e: EViewModel) {
      ResultUI(idSession, viewModel.toVO(), Messages(msgError = e.message ?: ""))
    }
  }
}

data class ResultUI<V: VO>(val id: String?, val vo: V, val messages: Messages)

data class Messages(var msgWarning: String = "", var msgError: String = "", var msgInfo: String = "")

inline fun <reified T> HttpSession.get(name: String): T? = getAttribute(name)  as? T
inline fun <reified T> HttpSession.set(name: String, value: T) = setAttribute(name, value)

