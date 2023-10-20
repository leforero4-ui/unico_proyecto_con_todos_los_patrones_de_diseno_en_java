infrastructure:

driven- implementaciones o adaptadores(arquitectura hexagonal) de adaptadores(patrón de diseño) proveedores de librerias externas

driver- implementaciones o adaptadores de guardar(lógica de como guardar no es dao)- repository

driven- interfaces o puertos de daos(representan la base de datos tienen el crud básico)(exponen entidades de los daos)

driven- implementaciones o adaptadores de daos(representan la base de datos tienen el crud básico)

entidades de los daos

controller por donde entra la petición dependiendo de la técnología

mapper de entidades de los daos a entidades de dominio y viceversa

excepciones de esta capa