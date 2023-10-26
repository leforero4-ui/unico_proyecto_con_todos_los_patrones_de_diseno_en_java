driver que su implementación la conducimos nosotros

driven que su implementación es conducida por otro

las clases que representan objetos(sustantivos) al menos inicialmente extienden de clases abstractas y no implementan interfaces, por lo que si se necesita un comportamiento es mejor la composición que la herencia

las clases que representan comportamientos(advervios) al menos inicialmente implementan interfaces y no clases abstractas

patrones de diseño:

creacional

* builder: creación de player, ayuda cuando es inmutable osea que tiene sus propiedades finales, y muchos de sus parametros pueden ser null porque tendría varios constructores para evitar el parámetro null

* factory method: lógica de crear villanos por niveles

* singleton: para crear el villano supremo que aparecera durante unos segundos en todos los niveles

* prototype: para clonar los soldados cuando se está creando los villanos por niveles

* abstract factory: para crear el juego por armada: terrestre, naval, o aérea


estructural

* composite: crear infanteria con sus escuadrones y soldados

* flyweight: compartir la ropa de los soldados (estado intrinseco/extrinseco)

* facade: interface expuesta a las aplicaciones, en este caso la clase Controller.java