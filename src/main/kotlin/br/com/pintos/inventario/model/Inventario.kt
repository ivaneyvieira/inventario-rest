package br.com.pintos.inventario.model

import br.com.astrosoft.framework.model.SimpleBaseModel
import br.com.pintos.inventario.model.EStatusInventario.ABERTO
import br.com.pintos.inventario.model.finder.InventarioFinder
import io.ebean.annotation.Cache
import io.ebean.annotation.DbEnumValue
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Cache(enableQueryCache=false)
@Table(name = "inventario")
class Inventario(
        var numero: Int,
        var data: LocalDate,
        var observacao: String,
        @Enumerated(EnumType.STRING)
        @Column(name = "tipoInventario")
        var tipoInventario: ETipoInventario,
        @Enumerated(EnumType.STRING)
        @Column(name = "statusInventario")
        var statusInventario: EStatusInventario,
        @ManyToOne
        var loja: Loja,
        @ManyToOne
        var fornecedor: Fornecedor?,
        @ManyToOne
        var cl: CL?
) : SimpleBaseModel() {
    companion object Find : InventarioFinder() {
      fun inventariosAberto(): List<Inventario> {
        return where()
                .statusInventario.eq(ABERTO)
                .orderBy().numero.asc()
                .findList()
      }
    }
}

enum class ETipoInventario(
        @get:DbEnumValue
        val valor: String
) {
    DIVERGENCIA("Com divergencias"), SIMPLES("Simples")
}

enum class EStatusInventario(
        @get:DbEnumValue
        val valor: String
) {
    ABERTO("Aberto"), FECHADO("Fechado")
}
