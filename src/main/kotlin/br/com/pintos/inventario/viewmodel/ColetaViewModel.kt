package br.com.pintos.inventario.viewmodel

import br.com.astrosoft.framework.viewmodel.EViewModel
import br.com.astrosoft.framework.viewmodel.IView
import br.com.astrosoft.framework.viewmodel.ViewModel
import br.com.pintos.inventario.model.EStatusLeitura.ERRO
import br.com.pintos.inventario.model.EStatusLeitura.SUCESSO
import br.com.pintos.inventario.model.Leitura
import br.com.pintos.inventario.model.Lote
import br.com.pintos.inventario.model.Produto

class ColetaViewModel(view: IView): ViewModel(view) {
  fun doLeitura() = exec {
    leitura?.let {leitura ->
      val loja = userInformation.inventario?.loja
      val lote = Lote.findNumLote(leitura, loja)
      if(lote != null) doMudarLote(lote)
      else {
        doLeitura(leitura)
        quantidade = userInformation.quantidade
      }
    }
    leitura = ""
  }

  private fun doLeitura(leitura: String) {
    val produto = Produto.findLeitura(leitura)
    codigo = produto?.codigo
    grade = produto?.grade
    descricao = produto?.descricao
    val coleta = userInformation.coleta ?: throw EViewModel("Coleta não encontrada")
    Leitura(leitura = leitura, observacao = "", quant = if(produto == null) 0 else 1,
            status = if(produto == null) ERRO else SUCESSO, coleta = coleta, produto = produto, saldo = null).insert()
  }

  private fun doMudarLote(lote: Lote) {
    userInformation.lote = lote
    userInformation.coleta = null
    TODO()
  }

  var inventarioLoja = userInformation.inventarioLoja
  var usuarioApelido = userInformation.usuarioApelido
  var loteColeta = userInformation.loteColeta
  var leitura: String? = null
  var codigo: String? = null
  var grade: String? = null
  var descricao: String? = null
  var quantidade: Int? = null
}