package main.domain.model;

import java.util.HashMap;
import java.util.Map;

public class ClotheFactory {

    // hasmap no ordena, treemap ordena
    // hasmap es mas veloz el get cuando hay gran catidad de datos por la complejidad algoritmica de O(1), hashmap es O(log n), pero en pocos datos es imperseptible
    // hasmap permite 1 key nula, treemap no permite ninguna key nula(para poder ordenar), ambos permiten tener muchos valores nulos
    private static final Map<String, Clothe> CLOTHES = new HashMap<>();

    private ClotheFactory() {}

    public static Clothe getClothe(String name, String typeEye, String typeHair, String typeShirt, String typePant, String typeShoes) {
        if (!CLOTHES.containsKey(name)) {
        	CLOTHES.put(name, new Clothe(typeEye, typeHair, typeShirt, typePant, typeShoes));
        }
        return CLOTHES.get(name);
    }
	
}
