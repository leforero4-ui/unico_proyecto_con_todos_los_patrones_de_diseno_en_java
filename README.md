# Único proyecto con todos los patrones de diseño en java 17

patrones de diseño:

creacionales

* **Builder**: creación de player, ayuda cuando es inmutable osea que tiene sus propiedades finales, y muchos de sus parametros pueden ser null porque tendría varios constructores para evitar el parámetro null
* **Factory method**: lógica de crear villanos por niveles
* **Singleton**: para crear el villano supremo, debe ser único
* **Prototype**: para clonar los soldados cuando se están creando los villanos por niveles
* **Abstract factory**: para crear el juego por armada: naval, o aérea


estructurales

* **Composite**: crear infanteria con sus escuadrones y soldados que ataquen al mismo tiempo
* **Flyweight**: compartir la ropa de los soldados (estado intrinseco(flyweight) = ropa / estado extrinseco = vida)
* **Facade**: interface expuesta a las aplicaciones ocultando las complejidades del juego, logros, y estadisticas, en este caso la clase Controller.java
* **Adapter**: usar librerias externas como Lanterna para dibujar en consola
* **Bridge**: habilidad de cada enemigo que usa para atacar
* **Decorator**: para meter algunos enemigos en un fuerte brindandole una protección extra
* **Proxy**: ya que controller es un recurso pesado se le puede aplicar un proxy virtual para que se inicialice solo si se usa,  
y un proxy de caché para obtener las estadisticas por nivel ya que puede ser pesado su calculo,  
y un proxy de protección para no ver los logros si se ha iniciado en beta

comportamientos

* **Iterator**: recorrer el tablero del juego para dibujar los enemigos
* **Strategy**: estrategía(habilidad) para calcular el nivel de ataque de cada enemigo
* **Mediator**: interface que controla los ataques y los contraataques entre el jugador y los enemigos, en este caso GameableUseCase.java
* **Observer**: para que los protectores(Subscribers) del enemigo supremo(Publisher) reciban un porcentaje del ataque cuando es atacado(subscribe), esto es programación reactiva
* **Visitor**: debido a que los enemigos y el jugador no deberían poder recuperarse se saca la funcionalidad aparte para que no afecte las clases Soldier, Squadron, Supreme y Player
* **Interpreter**: para buscar enemigos por tipos en el tablero  
expresión ::= enemigo | conjución | alternativa | '('expresión')'  
enemigo ::= 'soldado' | 'escuadron' | 'supremo' | 'fortaleza'  
conjución ::= enemigo '&' enemigo  
alternativa ::= enemigo '|' enemigo
* **State**: estado del enemigo para calcular el contraataque: dormido, confundido, o enfurecido
* **Chain of responsability**: para determinar la precisión al asertar un disparo al enemigo, esto depende de los ambientes que se carguen al juego, pueden ser combinados como frio y calor con lluvia
* **Command**: cada uno de los elementos en los combos predefinidos es un comando, en la función execute se llama al receiver, y es invocado en el combo o por separado, también si caes en una mina de hielo y tus ataques son congelados 5 turnos


notas:

driver que su implementación la conducimos nosotros

driven que su implementación es conducida por otro

las clases que representan objetos(sustantivos) al menos inicialmente extienden de clases abstractas y no implementan interfaces, por lo que si se necesita un comportamiento es mejor la composición que la herencia

las clases que representan comportamientos(advervios) al menos inicialmente implementan interfaces y no clases abstractas

tipos de programación:

* **programación imperativa**: se centra mas en el como se hace(definición de variables(let, const, var), decisiones(if, else, else if, switch), loops(for, while, do while, foreach), errores(try catch), async(async await))

	* _programación orientada a objetos_ (de programación imperativa), pilares abstración, encapsulamiento, polimorfismo, y herencia
	
		* _programación orientada a aspectos_ (de programación imperativa) conceptos que se entrecruzan


* **programación declarativa**: se centra en el que se quiere lograr mas que en el como(map, filter, reduce)

	* _programación reactiva_ (de programación declarativa) es inspirada en el patrón de diseño Observer por los publisher-subscripcion, pero son flujos de datos a los que se subscriben, estos flujos de datos pueden ser otros flujos u otros eventos, también pueden comandos como los del patrón de diseño command, también se pueden escribir estos flujos como programación funcional
	
		* _programación funcional_ (de programación declarativa) es cuando se usa funciones como variables, las cuáles incluso se pueden pasar por parámetros como por ejemplo en los Stream que usa java.util.List, se pueden usar interfaces funcionales que se pueden implementar en variables(incluso se pueden pasar por parámetros) que se llamarían lambdas

