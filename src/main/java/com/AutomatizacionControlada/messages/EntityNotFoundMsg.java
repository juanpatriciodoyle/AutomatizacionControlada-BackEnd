package com.AutomatizacionControlada.messages;

public class EntityNotFoundMsg extends RuntimeException{

    public EntityNotFoundMsg(){
        super("Entity not found");
    }
}
