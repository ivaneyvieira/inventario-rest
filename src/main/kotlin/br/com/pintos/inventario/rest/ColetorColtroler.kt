package br.com.pintos.inventario.rest

import br.com.astrosoft.framework.rest.ControlerModel
import br.com.pintos.inventario.viewmodel.ColetorVO
import br.com.pintos.inventario.viewmodel.ViewModelColetor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
@RequestMapping("coletor")
class ColetorColtroler: ControlerModel<ColetorVO, ViewModelColetor>() {
  override fun createViewModel() = ViewModelColetor(this)

  @GetMapping("/leitura/{value}/{id}")
  @ResponseBody
  fun processaLeitura(@PathVariable value: String, @PathVariable id: String?, session: HttpSession) =
    actionControler(id, session) {
      updateModel()
      processaLeitura(value)
    }

  @GetMapping("/viewmodel/{id}")
  @ResponseBody
  fun viewModel(@PathVariable id: String?, session: HttpSession) = actionControler(id, session) {
    updateModel()
  }

  @GetMapping("/fecha_lote/{id}")
  @ResponseBody
  fun fecharLote(@PathVariable id: String?, session: HttpSession) = actionControler(id, session) {
    fecharLote()
  }

  @GetMapping("/fecha_usuario/{id}")
  @ResponseBody
  fun fecharUsuario(@PathVariable id: String?, session: HttpSession) = actionControler(id, session) {
    fecharUsuario()
  }
}







