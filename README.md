# Único proyecto con todos los patrones de diseño en java 17

patrones de diseño:

creacionales

* **Builder**: creación de player, ayuda cuando es inmutable osea que tiene sus propiedades finales, y muchos de sus parámetros pueden ser null porque tendría varios constructores para evitar el parámetro null en el constructor
* **Factory method**: lógica de crear villanos por niveles
* **Singleton**: para crear el villano supremo, debe ser único
* **Prototype**: para clonar los soldados cuando se están creando los villanos por niveles
* **Abstract factory**: para crear el juego por armada: naval, o aérea


estructurales

* **Composite**: crear estructura de infanteria con sus escuadrones y soldados que ataquen al mismo tiempo
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
* **Visitor**: debido a que los enemigos y el jugador no deberían poder recuperarse se saca la funcionalidad aparte para que no esté en las clases Soldier, Squadron, Supreme y Player, ya que no debería estar allí y además se deja abierto a extender otras funcionalidades que no deban estar en esas clases como por ejemplo el exportar a pdf, xml, o csv,  
en este caso se puso a la clase visitante y las clases visitadas en el mismo paquete para que pudiera tener acceso a los campos protegidos lo que es una desventaja del patrón
* **Interpreter**: para buscar enemigos por tipos en el tablero  
expresión ::= enemigo | conjución | alternativa | '('expresión')'  
enemigo ::= 'soldado' | 'escuadron' | 'supremo' | 'fortaleza'  
conjución ::= enemigo '&' enemigo  
alternativa ::= enemigo '|' enemigo
* **State**: estado del enemigo para calcular el contraataque: dormido, confundido, o enfurecido
* **Chain of responsability**: para determinar la precisión al asertar un disparo al enemigo, esto depende de los ambientes que se carguen al juego, pueden ser combinados como frio y calor con lluvia
* **Command**: los invocadores pueden ser un combo o un ataque/sanación directo, en la función execute se llama al receiver,  
los comandos pueden ser invocados desde diferentes partes: en un combo y directamente al ejecutar el ataque o la sanación, también si caes en una mina de hielo tus ataques y sanaciones seran congelados 5 turnos y estaran estos comandos en una pila esperando a ser ejecutados después de los 5 turnos
* **Memento**: hacer puntos de restauración
* **Template method**: difinir las misiones a completar en los niveles


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

