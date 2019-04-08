package br.com.pintos.inventario.rest


import br.com.pintos.inventario.rest.ColetorApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServletInitializer : SpringBootServletInitializer() {

	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(ColetorApplication::class.java)
	}

}
