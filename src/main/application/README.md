application:

driver- interfaces o puertos de guardar(lógica de como guardar no es dao) repository (exponen entidades de dominio)

driver- interfaces o puertos(arquitectura hexagonal) de servicios también llamados casos de uso (exponen dtos)

driver- implementaciones o adaptadores(arquitectura hexagonal) de servicios también llamados casos de uso

driven- interfaces o puertos de adaptadores(patrón de diseño) proveedores de librerias externas

dto los que se muestra al exterior

mapper de dto a entidades de dominio y viceversa

excepciones de esta capa